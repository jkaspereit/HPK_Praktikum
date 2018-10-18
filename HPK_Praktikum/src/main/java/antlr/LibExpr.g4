grammar LibExpr;

ID : [a-zA-Z]+;
INT: '-'? [0-9]+;
DOUBLE: '-'? [0-9]+ '.' [0-9]+;
NEWLINE: '\r'? '\n' -> skip;
WS: [ \t]+ -> skip;
END_EXPR: ';';

prog: stat+;

stat: expr END_EXPR?			#printExpr
	|ID '=' expr END_EXPR?		#assign
	|END_EXPR					#blank					
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