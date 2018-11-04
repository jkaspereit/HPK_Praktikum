package application;

import static java.lang.Math.cos;
import static java.lang.Math.pow;
import static java.lang.Math.random;
import static java.lang.Math.sin;
import static java.lang.Math.tan;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Random;

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
		
		Script script = new WRBScript();
 
		String task = "h(x,y)=x**y;";
		
		InputStream stream = new ByteArrayInputStream(task.getBytes());
		
		System.out.println(stream);
		
		System.out.println("First Test: " +script.parse(task));
		
//        for (int j = 0; j<10; j++ ) {
//            double x = 4;
//            double y = 2;
//            double z = -j;
//            double expected = pow(x, pow(y, z));
//            script.setVariable("x", x);
//            script.setVariable("y", y);
//            script.setVariable("z", z);
//
//            System.out.println("EXPECTED: " + expected);
//            
//            double result = script.parse("x**y** z");
//            System.out.println(result);
//            result = script.parse("x^ y ^ z");
//            System.out.println(result);
//            result = script.parse("x**y ^ z");
//            System.out.println(result);
//            result = script.parse("x ^y **z");
//            System.out.println(result);
//        }
//		
        

	}
}
