package error;

import org.antlr.v4.runtime.DefaultErrorStrategy;
import org.antlr.v4.runtime.FailedPredicateException;
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
	
	@Override
	public void reportError(Parser recognizer, RecognitionException e) {
		recognizer.notifyErrorListeners(createMsg(e.getOffendingToken()));
	}
	
	@Override
	protected void reportFailedPredicate(Parser recognizer, FailedPredicateException e) {
		recognizer.notifyErrorListeners(createMsg(e.getOffendingToken()));
	}
	
	@Override
	protected void reportMissingToken(Parser recognizer) {
		recognizer.notifyErrorListeners("missing token");
	}
	
	
	private String createMsg(Token token) {
		return " " + token.getText();
	}
	
}
