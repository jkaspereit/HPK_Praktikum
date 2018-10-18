package antlr;

import java.util.HashMap;
import java.util.Map;

import org.antlr.v4.runtime.tree.ParseTree;

import antlr.LibExprBaseVisitor;
import antlr.LibExprParser;
import antlr.LibExprVisitor;
import antlr.LibExprParser.AddSubContext;
import antlr.LibExprParser.AssignContext;
import antlr.LibExprParser.DoubleContext;
import antlr.LibExprParser.IdContext;
import antlr.LibExprParser.IntContext;
import antlr.LibExprParser.MulDivContext;
import antlr.LibExprParser.ParensContext;
import antlr.LibExprParser.PrintExprContext;

/**
 * This class provides an empty implementation of {@link LibExprVisitor}, which
 * can be extended to create a visitor which only needs to handle a subset of
 * the available methods.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 *        operations with no return type.
 */

public class BaseVisitor extends LibExprBaseVisitor<Double> {

	// Memory
	private Map<String, Double> memory = new HashMap<String, Double>();
	// Result of an evaluation
	private Double result;
	// getter
	public Double getResult() {
		return result;
	}

	/**
	 * ID '=' expr NEWLINE
	 */
	@Override
	public Double visitAssign(AssignContext ctx) {
		String id = ctx.ID().getText(); // ID
		double value = visit(ctx.expr()); // Value
		memory.put(id, value); // save in memory
		return value;
	}

	/**
	 * expr NEWLINE
	 */
	@Override
	public Double visitPrintExpr(PrintExprContext ctx) {
		Double value = visit(ctx.expr()); // evaluate the expr child
		result = value;
		return 0.0; // dummy Value
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
		if (memory.containsKey(id))
			return memory.get(id);
		return 0.0; // dummy, id not found
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
		//	has to be division 
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
		//	has to be subtraction 
		return left - right;
	}

	/**
	 * '(' expr ')'
	 */
	@Override
	public Double visitParens(ParensContext ctx) {
		return visit(ctx.expr());
	}
}