package antlr;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import antlr.LibExprParser.AddSubContext;
import antlr.LibExprParser.AssignDeclarationContext;
import antlr.LibExprParser.AssignFunctionContext;
import antlr.LibExprParser.DoubleContext;
import antlr.LibExprParser.Expo10Context;
import antlr.LibExprParser.Expo10shortContext;
import antlr.LibExprParser.ExprContext;
import antlr.LibExprParser.FunctionContext;
import antlr.LibExprParser.IdContext;
import antlr.LibExprParser.IntContext;
import antlr.LibExprParser.MulDivContext;
import antlr.LibExprParser.NegExprContext;
import antlr.LibExprParser.ParensContext;
import antlr.LibExprParser.PowContext;
import antlr.LibExprParser.PrintExprContext;
import antlr.LibExprParser.ProgContext;
import antlr.LibExprParser.StatContext;
import application.WRBScript;
import util.WRBFunction;

/**
 * This class provides an implementation of {@link LibExprVisitor}, which is
 * extended to create a visitor which handles a subset of the available methods.
 *
 * @param <Double> The return type of the visit operation.
 * 
 */

public class BaseVisitor extends LibExprBaseVisitor<Double> {

	WRBScript script;

	// Result of an evaluation
	private Double result;

	// getter
	public Double getResult() {
		return result;
	}

	public BaseVisitor(WRBScript script) {
		this.script = script;
	}

	@Override
	public Double visitProg(ProgContext ctx) {
		// Remove EOF
		ctx.removeLastChild();
		if (ctx.getText().endsWith(";")) {
			// remove ';'
			ctx.removeLastChild(); // END_EXPR
		}
		return multithreading(ctx);
	}

	/**
	 * Multithreading: every single stat will be visit in a new thread.
	 * 
	 * @param ctx ParseTree.stat
	 * @return value of the last stat.printexpr
	 */
	private Double multithreading(ProgContext ctx) {
		double value = 0.0; // dummy value
		ExecutorService threadPool = Executors.newCachedThreadPool(); 
		// start a new thread for every stat
		for (StatContext stat: ctx.stat()) {
			// thread run implementation
			Callable<Double> statThread = new Callable<Double>() {

				@Override
				public Double call() throws Exception {
					return visit(stat);
				}
			};
			// future is waiting for needed results
			Future<Double> result = threadPool.submit(statThread);
			try {
				value = result.get();
			} catch (InterruptedException e) {
				// Deadlock interrupted
				e.printStackTrace();
			} catch (ExecutionException e) {
				// solo reason for an execution bug is an illegal argument!
				throw new IllegalArgumentException(e);
			}

		}
		return value;
	}

	/**
	 * ID '=' expr NEWLINE
	 */
	@Override
	public Double visitAssignDeclaration(AssignDeclarationContext ctx) {
		String id = ctx.ID().getText(); // ID
		double value = visit(ctx.expr()); // Value
		script.setVariable(id, value);// save in memory
		return value;
	}

	/**
	 * expr NEWLINE
	 */
	@Override
	public Double visitPrintExpr(PrintExprContext ctx) {
		Double result = visit(ctx.expr()); // evaluate the expr child
		// result = value;
		return result; // dummy Value
	}

	/**
	 * INT
	 */
	@Override
	public Double visitInt(IntContext ctx) {
		return Double.valueOf(ctx.INT().getText());
	}

	/**
	 * DOUBLE
	 */
	@Override
	public Double visitDouble(DoubleContext ctx) {
		return Double.valueOf(ctx.DOUBLE().getText());
	}

	/**
	 * ID
	 */
	@Override
	public Double visitId(IdContext ctx) {
		String id = ctx.ID().getText();
		return script.getVariable(id);
	}

	@Override
	public Double visitAssignFunction(AssignFunctionContext ctx) {
		String id = ctx.ID().getText();
		List<String> formalParameters = new ArrayList<String>();
		for (TerminalNode node : ctx.formalParameters().ID()) {
			formalParameters.add(node.getText());
		}
		ParseTree parseTree = ctx.expr();
		script.setFunction(id, new WRBFunction(script, formalParameters, parseTree));
		return 0.0;
	}

	/**
	 * FUNCTION
	 */
	@Override
	public Double visitFunction(FunctionContext ctx) {
		String id = ctx.ID().getText();
		double[] args = new double[ctx.parameters().expr().size()];
		int i = 0;
		for (ExprContext expr : ctx.parameters().expr()) {
			args[i] = visit(expr);
			i++;
		}
		double result = script.getFunction(id).eval(args);
		return result;
	}

	/**
	 * EXPO10
	 */
	@Override
	public Double visitExpo10(Expo10Context ctx) {
		Double value = Double.valueOf(ctx.DOUBLE().getText());
		Double exponent = Double.valueOf(ctx.INT().getText());
		if (ctx.op == null || ctx.op.getType() == LibExprParser.ADD) {
			return value * (Math.pow(10, exponent));
		}
		// op has to be SUB
		return value / (Math.pow(10, exponent));
	}

	@Override
	public Double visitExpo10short(Expo10shortContext ctx) {
		Double value = Double.valueOf(ctx.DOUBLE().getText());
		Double exponent = Double.valueOf(ctx.E().getText().replace("e", ""));
		return value * (Math.pow(10, exponent));
	}

	/**
	 * expr op=('*'|'/') expr
	 */
	@Override
	public Double visitMulDiv(MulDivContext ctx) {
		double left = visit(ctx.expr(0)); // get value of left subexpression
		double right = visit(ctx.expr(1)); // get value of right subexpression
		// checks on multiplication
		if (ctx.op.getType() == LibExprParser.MUL)
			return left * right;
		// has to be division
		return left / right;
	}

	/**
	 * expr op=('+'|'-') expr
	 */
	@Override
	public Double visitAddSub(AddSubContext ctx) {
		double left = visit(ctx.expr(0)); // get value of left subexpression
		double right = visit(ctx.expr(1)); // get value of right subexpression
		// checks on addition
		if (ctx.op.getType() == LibExprParser.ADD)
			return left + right;
		// has to be subtraction
		return left - right;
	}

	/**
	 * -expr
	 */
	@Override
	public Double visitNegExpr(NegExprContext ctx) {
		Double resultExpr = visit(ctx.expr());
		return resultExpr * -1;
	}

	@Override
	public Double visitPow(PowContext ctx) {
		double left = visit(ctx.expr(0)); // get value of left subexpression
		double right = visit(ctx.expr(1)); // get value of right subexpression
		return Math.pow(left, right);
	}

	/**
	 * '(' expr ')'
	 */
	@Override
	public Double visitParens(ParensContext ctx) {
		return visit(ctx.expr());
	}

}