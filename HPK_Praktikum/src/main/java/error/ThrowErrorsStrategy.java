package error;

import org.antlr.v4.runtime.DefaultErrorStrategy;
import org.antlr.v4.runtime.InputMismatchException;
import org.antlr.v4.runtime.NoViableAltException;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Token;

public class ThrowErrorsStrategy extends DefaultErrorStrategy {

	@Override
	public void recover(Parser recognizer, RecognitionException e) {
		throw new IllegalArgumentException(e);
	}
	
	@Override
	public Token recoverInline(Parser recognizer) throws RecognitionException {
		throw new IllegalArgumentException(new InputMismatchException(recognizer));
	}
	
	@Override
	public void sync(Parser recognizer) throws RecognitionException {
	}
	
	@Override
	protected void reportNoViableAlternative(Parser recognizer, NoViableAltException e) {
		recognizer.notifyErrorListeners(createMsg(e.getOffendingToken()));
	}
	
	@Override
	protected void reportInputMismatch(Parser recognizer, InputMismatchException e) {
		recognizer.notifyErrorListeners(createMsg(e.getOffendingToken()));
	}
	
	private String createMsg(Token token) {
		return " " + token.getText();
	}
	
}
