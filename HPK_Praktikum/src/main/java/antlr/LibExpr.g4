grammar LibExpr;

ID : [a-zA-Z]+;
INT: '-'? [0-9]+;
DOUBLE: '-'? [0-9]+ '.' [0-9]+;
NEWLINE: '\r'? '\n';
WS: [ \t]+ -> skip;

prog: stat+;

stat: expr NEWLINE 				#printExpr
	|ID '=' expr NEWLINE		#assign
	|NEWLINE					#blank					
	;	

expr: expr op=('*'|'/') expr	#MulDiv
	| expr op=('+'|'-') expr	#AddSub
	| INT						#int
	| DOUBLE					#double
	| ID						#id
	| '(' expr ')'				#parens
	;
	
MUL: '*';
DIV: '/';
ADD: '+';
SUB: '-';