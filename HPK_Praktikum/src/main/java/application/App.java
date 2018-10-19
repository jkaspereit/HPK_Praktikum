package application;

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


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {    		
    		
    	class testLexer extends LibExprLexer{

			public testLexer(CharStream input) {
				super(input);
				// TODO Auto-generated constructor stub
			}
			
			@Override
			public void recover(LexerNoViableAltException e) {
				throw new IllegalArgumentException(e);
			}
    		
    	}
    	
    	ANTLRInputStream input = new ANTLRInputStream("5+4*2");
    	LibExprLexer lexer = new testLexer(input);
    	CommonTokenStream commonTokenStream = new CommonTokenStream(lexer);
    	LibExprParser parser = new LibExprParser(commonTokenStream);
    	parser.removeErrorListeners();
    	parser.addErrorListener(new IllegalArgumentExceptionListener());
    	parser.setErrorHandler(new ThrowErrorsStrategy());
    	ParseTree tree = parser.prog();
    	
    	BaseVisitor bv = new BaseVisitor();
    	bv.visit(tree);
    	System.out.println(bv.getResult());
    	
    	System.out.println(tree.toStringTree(parser));
    	
//	Listener
//    	parser.addParseListener(new LibExprBaseListener());
//    	ParseTree tree = parser.prog();
//    	System.out.println(tree.toStringTree(parser));
    }
}
