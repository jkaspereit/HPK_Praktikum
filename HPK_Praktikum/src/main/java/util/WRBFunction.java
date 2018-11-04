package util;

import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;

import antlr.BaseVisitor;
import application.WRBScript;

public class WRBFunction implements Function {

	private List<String> formalParameters;
	
	private ParseTree tree; 
	
	WRBScript script;;
	
	public WRBFunction(WRBScript script, List<String> formalParameters, ParseTree tree) {
		this.script = script;
		this.formalParameters = formalParameters;
		this.tree = tree;
	}

	@Override
	public double eval(double... args) {
		for(int i = 0; i < args.length; i++) {
			script.setVariable(formalParameters.get(i), args[i]);
		}
		return script.visit(tree);
	}

}
