package application;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Set;

import org.antlr.runtime.tree.TreeWizard.Visitor;
import org.antlr.v4.parse.ANTLRParser.element_return;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.LexerNoViableAltException;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.tree.ParseTree;
import org.stringtemplate.v4.compiler.CodeGenerator.args_return;

import antlr.BaseVisitor;
import antlr.LibExprLexer;
import antlr.LibExprParser;
import error.IllegalArgumentExceptionListener;
import error.ThrowErrorsStrategy;
import util.Function;

public class WRBScript implements Script {

	HashMap<String, Function> memoryFunctions;

	HashMap<String, Double> memoryVariables;

	BaseVisitor visitor;

	boolean allowAutoCorrection;

	/**
	 * Default - Constructor
	 */
	public WRBScript() {
		memoryFunctions = new HashMap<>();
		initMathFunctions();
		memoryVariables = new HashMap<>();
		visitor = new BaseVisitor(this);
		allowAutoCorrection = true;
	}

	/**
	 * WRBScript Constructor
	 * 
	 * @param allowAutoCorrection Defines whether autoCorrection is allowed or not.
	 */
	public WRBScript(boolean allowAutoCorrection) {
		memoryFunctions = new HashMap<>();
		initMathFunctions();
		memoryVariables = new HashMap<>();
		visitor = new BaseVisitor(this);
		this.allowAutoCorrection = allowAutoCorrection;
	}

	@Override
	public Double parse(String definition) {
		ParseTree tree = setUp(definition);
		return visit(tree);
	}
	
	public Double visit(ParseTree tree) {
		return visitor.visit(tree);
	}

	@Override
	public double parse(InputStream defStream) throws IOException {
		return parse(defStream.toString());
	}

	@Override
	public Set<String> getFunctionNames() {
		return memoryFunctions.keySet();
	}

	@Override
	public Set<String> getVariableNames() {
		return memoryVariables.keySet();
	}

	@Override
	public void setFunction(String name, Function fct) {
		memoryFunctions.put(name, fct);
	}

	@Override
	public Function getFunction(String name) {
		Function returnValue = memoryFunctions.get(name);
		if (returnValue == null) {
			throw new IllegalArgumentException("Kein Eintrag für die Funktion: " + name + " gefunden.");
		}
		return returnValue;
	}

	@Override
	public double getVariable(String name) {
		Double returnValue = memoryVariables.get(name);
		if (returnValue == null) {
			throw new IllegalArgumentException("Kein Eintrag für die Variable: " + name + " gefunden.");
		}
		return returnValue;
	}

	@Override
	public void setVariable(String name, double value) {
		memoryVariables.put(name, value);
	}

	/**
	 * Creates the Parse-Tree for the given input.
	 * 
	 * @param inputString input
	 * @return {@link ParseTree}
	 */
	private ParseTree setUp(String inputString) {
		LibExprLexer lexer = createLexer(inputString);
		LibExprParser parser = createParser(lexer);

		return parser.prog();
	}

	/**
	 * Creates a lexer for the given input.
	 * 
	 * @param inputString input
	 * @return {@link LibExprLexer}
	 */
	private LibExprLexer createLexer(String inputString) {
		ANTLRInputStream input = new ANTLRInputStream(inputString);
		LibExprLexer lexer;
		if (allowAutoCorrection) {
			lexer = new LibExprLexer(input);
		} else { // no auto correction allowed.
			// overwrite recover method from LibExpLexer
			class Lexer extends LibExprLexer {

				public Lexer(CharStream input) {
					super(input);
					// TODO Auto-generated constructor stub
				}

				@Override
				public void recover(LexerNoViableAltException e) {
					throw new IllegalArgumentException(e);
				}

				@Override
				public void recover(RecognitionException re) {
					throw new IllegalArgumentException(re);
				}

			}
			// lexer without auto correction
			lexer = new Lexer(input);
			lexer.removeErrorListeners();
		}
		return lexer;
	}

	/**
	 * Creates a parser based on the given lexer.
	 * 
	 * @param lexer {@link LibExprLexer}
	 * @return {@link LibExprParser}
	 */
	private LibExprParser createParser(LibExprLexer lexer) {
		CommonTokenStream commonTokenStream = new CommonTokenStream(lexer);
		LibExprParser parser = new LibExprParser(commonTokenStream);
		if (!allowAutoCorrection) { // auto correction not allowed
			parser.removeErrorListeners(); // remove default listeners
			parser.addErrorListener(new IllegalArgumentExceptionListener()); // add listener for illegal arguments
			parser.setErrorHandler(new ThrowErrorsStrategy()); // error handling
		}
		return parser;
	}

	private void initMathFunctions() {
		Function sin = (args) -> Math.sin(args[0]); 
		Function cos = (args) -> Math.cos(args[0]); 
		Function tan = (args) -> Math.tan(args[0]); 
		
		setFunction("sin", sin);
		setFunction("cos", cos);
		setFunction("tan", tan);
	}

}
