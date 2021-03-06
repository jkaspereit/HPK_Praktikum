// Generated from /home/sisi/IdeaProjects/HPK_Praktikum/HPK_Praktikum/src/main/java/antlr/LibExpr.g4 by ANTLR 4.7
package antlr;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class LibExprLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, E=6, WS=7, ID=8, INT=9, DOUBLE=10, 
		NEWLINE=11, END_EXPR=12, MUL=13, DIV=14, ADD=15, SUB=16, POW=17;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "LETTER", "DIGIT", "E", "WS", 
		"ID", "INT", "DOUBLE", "NEWLINE", "END_EXPR", "MUL", "DIV", "ADD", "SUB", 
		"POW"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'='", "'('", "','", "')'", "'e'", null, null, null, null, null, 
		null, "';'", "'*'", "'/'", "'+'", "'-'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, "E", "WS", "ID", "INT", "DOUBLE", 
		"NEWLINE", "END_EXPR", "MUL", "DIV", "ADD", "SUB", "POW"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public LibExprLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "LibExpr.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\23p\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3"+
		"\b\3\b\3\t\3\t\3\t\3\n\6\n<\n\n\r\n\16\n=\3\n\3\n\3\13\3\13\3\13\7\13"+
		"E\n\13\f\13\16\13H\13\13\3\f\6\fK\n\f\r\f\16\fL\3\r\7\rP\n\r\f\r\16\r"+
		"S\13\r\3\r\3\r\6\rW\n\r\r\r\16\rX\3\16\5\16\\\n\16\3\16\3\16\3\16\3\16"+
		"\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\24\5\24"+
		"o\n\24\2\2\25\3\3\5\4\7\5\t\6\13\7\r\2\17\2\21\b\23\t\25\n\27\13\31\f"+
		"\33\r\35\16\37\17!\20#\21%\22\'\23\3\2\5\4\2C\\c|\3\2\62;\4\2\13\13\""+
		"\"\2u\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\21"+
		"\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2"+
		"\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3"+
		"\2\2\2\3)\3\2\2\2\5+\3\2\2\2\7-\3\2\2\2\t/\3\2\2\2\13\61\3\2\2\2\r\63"+
		"\3\2\2\2\17\65\3\2\2\2\21\67\3\2\2\2\23;\3\2\2\2\25A\3\2\2\2\27J\3\2\2"+
		"\2\31Q\3\2\2\2\33[\3\2\2\2\35a\3\2\2\2\37c\3\2\2\2!e\3\2\2\2#g\3\2\2\2"+
		"%i\3\2\2\2\'n\3\2\2\2)*\7?\2\2*\4\3\2\2\2+,\7*\2\2,\6\3\2\2\2-.\7.\2\2"+
		".\b\3\2\2\2/\60\7+\2\2\60\n\3\2\2\2\61\62\7g\2\2\62\f\3\2\2\2\63\64\t"+
		"\2\2\2\64\16\3\2\2\2\65\66\t\3\2\2\66\20\3\2\2\2\678\7g\2\289\5\27\f\2"+
		"9\22\3\2\2\2:<\t\4\2\2;:\3\2\2\2<=\3\2\2\2=;\3\2\2\2=>\3\2\2\2>?\3\2\2"+
		"\2?@\b\n\2\2@\24\3\2\2\2AF\5\r\7\2BE\5\r\7\2CE\5\27\f\2DB\3\2\2\2DC\3"+
		"\2\2\2EH\3\2\2\2FD\3\2\2\2FG\3\2\2\2G\26\3\2\2\2HF\3\2\2\2IK\5\17\b\2"+
		"JI\3\2\2\2KL\3\2\2\2LJ\3\2\2\2LM\3\2\2\2M\30\3\2\2\2NP\5\17\b\2ON\3\2"+
		"\2\2PS\3\2\2\2QO\3\2\2\2QR\3\2\2\2RT\3\2\2\2SQ\3\2\2\2TV\7\60\2\2UW\5"+
		"\17\b\2VU\3\2\2\2WX\3\2\2\2XV\3\2\2\2XY\3\2\2\2Y\32\3\2\2\2Z\\\7\17\2"+
		"\2[Z\3\2\2\2[\\\3\2\2\2\\]\3\2\2\2]^\7\f\2\2^_\3\2\2\2_`\b\16\2\2`\34"+
		"\3\2\2\2ab\7=\2\2b\36\3\2\2\2cd\7,\2\2d \3\2\2\2ef\7\61\2\2f\"\3\2\2\2"+
		"gh\7-\2\2h$\3\2\2\2ij\7/\2\2j&\3\2\2\2ko\7`\2\2lm\7,\2\2mo\7,\2\2nk\3"+
		"\2\2\2nl\3\2\2\2o(\3\2\2\2\13\2=DFLQX[n\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}