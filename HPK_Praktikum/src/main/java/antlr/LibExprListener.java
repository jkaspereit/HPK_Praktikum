// Generated from /home/sisi/IdeaProjects/HPK_Praktikum/HPK_Praktikum/src/main/java/antlr/LibExpr.g4 by ANTLR 4.7
package antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link LibExprParser}.
 */
public interface LibExprListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link LibExprParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(LibExprParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibExprParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(LibExprParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibExprParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStat(LibExprParser.StatContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibExprParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStat(LibExprParser.StatContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibExprParser#printExpr}.
	 * @param ctx the parse tree
	 */
	void enterPrintExpr(LibExprParser.PrintExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibExprParser#printExpr}.
	 * @param ctx the parse tree
	 */
	void exitPrintExpr(LibExprParser.PrintExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibExprParser#assignDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterAssignDeclaration(LibExprParser.AssignDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibExprParser#assignDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitAssignDeclaration(LibExprParser.AssignDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibExprParser#assignFunction}.
	 * @param ctx the parse tree
	 */
	void enterAssignFunction(LibExprParser.AssignFunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibExprParser#assignFunction}.
	 * @param ctx the parse tree
	 */
	void exitAssignFunction(LibExprParser.AssignFunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibExprParser#formalParameters}.
	 * @param ctx the parse tree
	 */
	void enterFormalParameters(LibExprParser.FormalParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibExprParser#formalParameters}.
	 * @param ctx the parse tree
	 */
	void exitFormalParameters(LibExprParser.FormalParametersContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expo10short}
	 * labeled alternative in {@link LibExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpo10short(LibExprParser.Expo10shortContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expo10short}
	 * labeled alternative in {@link LibExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpo10short(LibExprParser.Expo10shortContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parens}
	 * labeled alternative in {@link LibExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterParens(LibExprParser.ParensContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parens}
	 * labeled alternative in {@link LibExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitParens(LibExprParser.ParensContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MulDiv}
	 * labeled alternative in {@link LibExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMulDiv(LibExprParser.MulDivContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MulDiv}
	 * labeled alternative in {@link LibExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMulDiv(LibExprParser.MulDivContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AddSub}
	 * labeled alternative in {@link LibExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAddSub(LibExprParser.AddSubContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AddSub}
	 * labeled alternative in {@link LibExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAddSub(LibExprParser.AddSubContext ctx);
	/**
	 * Enter a parse tree produced by the {@code negExpr}
	 * labeled alternative in {@link LibExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNegExpr(LibExprParser.NegExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code negExpr}
	 * labeled alternative in {@link LibExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNegExpr(LibExprParser.NegExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code double}
	 * labeled alternative in {@link LibExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterDouble(LibExprParser.DoubleContext ctx);
	/**
	 * Exit a parse tree produced by the {@code double}
	 * labeled alternative in {@link LibExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitDouble(LibExprParser.DoubleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code function}
	 * labeled alternative in {@link LibExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterFunction(LibExprParser.FunctionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code function}
	 * labeled alternative in {@link LibExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitFunction(LibExprParser.FunctionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expo10}
	 * labeled alternative in {@link LibExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpo10(LibExprParser.Expo10Context ctx);
	/**
	 * Exit a parse tree produced by the {@code expo10}
	 * labeled alternative in {@link LibExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpo10(LibExprParser.Expo10Context ctx);
	/**
	 * Enter a parse tree produced by the {@code pow}
	 * labeled alternative in {@link LibExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterPow(LibExprParser.PowContext ctx);
	/**
	 * Exit a parse tree produced by the {@code pow}
	 * labeled alternative in {@link LibExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitPow(LibExprParser.PowContext ctx);
	/**
	 * Enter a parse tree produced by the {@code id}
	 * labeled alternative in {@link LibExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterId(LibExprParser.IdContext ctx);
	/**
	 * Exit a parse tree produced by the {@code id}
	 * labeled alternative in {@link LibExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitId(LibExprParser.IdContext ctx);
	/**
	 * Enter a parse tree produced by the {@code int}
	 * labeled alternative in {@link LibExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterInt(LibExprParser.IntContext ctx);
	/**
	 * Exit a parse tree produced by the {@code int}
	 * labeled alternative in {@link LibExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitInt(LibExprParser.IntContext ctx);
	/**
	 * Enter a parse tree produced by {@link LibExprParser#parameters}.
	 * @param ctx the parse tree
	 */
	void enterParameters(LibExprParser.ParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link LibExprParser#parameters}.
	 * @param ctx the parse tree
	 */
	void exitParameters(LibExprParser.ParametersContext ctx);
}