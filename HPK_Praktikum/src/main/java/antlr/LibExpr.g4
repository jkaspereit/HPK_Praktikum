grammar LibExpr;

ID : [a-zA-Z]+;
INT: '-'? [0-9]+;
DOUBLE: '-'? [0-9]+ '.' [0-9]+;
NEWLINE: '\r'? '\n' -> skip;
WS: [ \t]+ -> skip;
END_EXPR: ';';

prog: stat* END_EXPR*
	| stat END_EXPR? ')' {notifyErrorListeners("Too many parentheses");}	
	;

stat: expr 							#printExpr
	|ID '=' expr 					#assignDeclaration
	|ID '(' expr (',' expr)* ')'	#assignFunction				
	;	

expr: DOUBLE 'e' INT				#expo10	
	| expr op=('*'|'/') expr		#MulDiv
	| expr op=('+'|'-') expr		#AddSub
	| INT							#int
	| DOUBLE						#double
	| ID							#id
	| '(' expr ')'					#parens
	;
	
MUL: '*';
DIV: '/';
ADD: '+';
SUB: '-';