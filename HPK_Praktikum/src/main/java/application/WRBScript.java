package application;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Set;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import antlr.BaseVisitor;
import antlr.LibExprLexer;
import antlr.LibExprParser;
import error.IllegalArgumentExceptionListener;
import util.Function;

public class WRBScript implements Script {

	HashMap<String, Function> memoryFunctions;

	HashMap<String, Double> memoryVariables;

	BaseVisitor visitor;

	/**
	 * Default - Constructor
	 */
	public WRBScript() {
		memoryFunctions = new HashMap<>();
		memoryVariables = new HashMap<>();
		initMathFunctions();
		visitor = new BaseVisitor(this);
	}

	@Override
	public Double parse(String definition) {
		CharStream stream = CharStreams.fromString(definition);
		return prog(stream);
	}
	
	@Override
	public double parse(InputStream defStream) throws IOException {
		CharStream stream = CharStreams.fromStream(defStream);
		return prog(stream);
	}
	
	public Double visit(ParseTree tree) { 
		return visitor.visit(tree);
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
	
	public HashMap<String, Double> getMemoryVariables() {
		return memoryVariables;
	}
	
	public void setMemoryVariables(HashMap<String, Double> memoryVariables) {
		this.memoryVariables = memoryVariables;
	}
	
	public HashMap<String, Function> getMemoryFunctions() {
		return memoryFunctions;
	}
	
	public void setMemoryFunctions(HashMap<String, Function> memoryFunctions) {
		this.memoryFunctions = memoryFunctions;
	}

	/**
	 * Creates the Parse-Tree for the given input.
	 * 
	 * @param inputString input
	 * @return {@link ParseTree}
	 */
	private double prog(CharStream input) {
		LibExprLexer lexer = createLexer(input);
		LibExprParser parser = createParser(lexer);
		ParseTree tree = parser.prog();
		return visit(tree);
	}

	/**
	 * Creates a lexer for the given input.
	 * 
	 * @param inputString input
	 * @return {@link LibExprLexer}
	 */
	private LibExprLexer createLexer(CharStream input) {
		return new LibExprLexer(input);
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
//		parser.removeErrorListeners();
		parser.addErrorListener(new IllegalArgumentExceptionListener());
//		parser.setErrorHandler(new ThrowErrorsStrategy());
		return parser;
	}

	/**
	 * Initializes mathematical standard functions
	 */
	private void initMathFunctions() {
		// sin cos tan
		Function sin = (args) -> Math.sin(args[0]); 
		Function cos = (args) -> Math.cos(args[0]); 
		Function tan = (args) -> Math.tan(args[0]); 
		setFunction("sin", sin);
		setFunction("cos", cos);
		setFunction("tan", tan);
		// asin acos atan
		Function asin = (args) -> Math.asin(args[0]);
		Function acos = (args) -> Math.acos(args[0]); 
		Function atan = (args) -> Math.atan(args[0]); 
		setFunction("asin", asin);
		setFunction("acos", acos);
		setFunction("atan", atan);
		// max min
		Function max = (args) -> {
			double result = args[0];
			for(double arg: args) {
				if (result < arg) {
					result = arg;
				}
			}
			return result;
		};
		Function min = (args) -> {
			double result = args[0];
			for(double arg: args) {
				if (result > arg) {
					result = arg;
				}
			}
			return result;
		};
		setFunction("max", max);
		setFunction("min", min);
		
	}
	
	
	@Override
	public Script concat(Script that) {
		WRBScript concatScript = new WRBScript();
		HashMap<String, Double> mv = (HashMap<String, Double>) getMemoryVariables().clone(); 
		mv.putAll(((WRBScript) that).getMemoryVariables());
		concatScript.setMemoryVariables(mv);
		HashMap<String, Function> mf = (HashMap<String, Function>) getMemoryFunctions().clone();
		mf.putAll(((WRBScript) that).getMemoryFunctions());
		concatScript.setMemoryFunctions(mf);
		return concatScript;
	}

}
