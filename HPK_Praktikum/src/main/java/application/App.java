package application;

import static java.lang.Math.cos;
import static java.lang.Math.random;
import static java.lang.Math.sin;
import static java.lang.Math.tan;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.LexerNoViableAltException;
import org.antlr.v4.runtime.tree.ParseTree;

import antlr.BaseVisitor;
import antlr.LibExprLexer;
import antlr.LibExprParser;
import error.IllegalArgumentExceptionListener;
import error.ThrowErrorsStrategy;
import util.Function;

/**
 * Hello world!
 *
 */
public class App {

	public static void main(String[] args) throws Exception {

		class testLexer extends LibExprLexer {

			public testLexer(CharStream input) {
				super(input);
				// TODO Auto-generated constructor stub
			}

			@Override
			public void recover(LexerNoViableAltException e) {
				throw new IllegalArgumentException(e);
			}

		}

		String task1 = "a = 5; f(y)=y*y";
        String task2 = "a = 4; f(z)=2*z";
        Script script = new WRBScript(false), script2 = new WRBScript(false), script3;
        script.parse(task1);
        script2.parse(task2);
        System.out.println("Expected :  25.0 VALUE: " + script.getFunction("f").eval(script.getVariable("a")));
        System.out.println("Expected :  08.0 VALUE: " + script2.getFunction("f").eval(script2.getVariable("a")));
        System.out.println("Expected :  16.0 VALUE: " + script.getFunction("f").eval(script2.getVariable("a")));
        System.out.println("Expected :  10.0 VALUE: " + script2.getFunction("f").eval(script.getVariable("a")));
        script3 = script.concat(script2);

        System.out.println("Expected s2 a : " + (script2.getVariable("a") + " VALUE: " + script3.getVariable("a")));
        System.out.println("Expected : 08.0 " + script3.getFunction("f").eval(script3.getVariable("a")));

//	Listener
//    	parser.addParseListener(new LibExprBaseListener());
//    	ParseTree tree = parser.prog();
//    	System.out.println(tree.toStringTree(parser));
	}
}
