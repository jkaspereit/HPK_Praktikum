package util;

import java.util.HashMap;
import java.util.List;

import de.lab4inf.wrb.Function;
import org.antlr.v4.runtime.tree.ParseTree;

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
		HashMap<String, Double> originalMemory = script.getMemoryVariables();
		script.setMemoryVariables((HashMap<String, Double>) originalMemory.clone());
		for(int i = 0; i < args.length; i++) {
			script.setVariable(formalParameters.get(i), args[i]);
		}
		double result = script.visit(tree);
		script.setMemoryVariables(originalMemory);
		return result;
	}

}
