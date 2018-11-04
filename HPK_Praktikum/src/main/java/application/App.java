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
		String tast = "((((-2 + 5)+0)+0)*1)";
		
        Script script = new WRBScript();
        
        
        System.out.println(script.parse(tast));

	}
}
