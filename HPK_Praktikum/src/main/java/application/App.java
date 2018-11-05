package application;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Locale;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.LexerNoViableAltException;

import antlr.LibExprLexer;

/**
 * Hello world!
 *
 */
public class App {


	
	public static void main(String[] args) throws Exception {

		
		Script script = new WRBScript();
 
		double y, x1 = 5, x2 = -5;
		
		String task =  "2.0e-5 - 3.0e1";
		
		
        System.out.println(script.parse(task));
        

	}
}
