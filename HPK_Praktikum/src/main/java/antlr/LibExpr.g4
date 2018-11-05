grammar LibExpr;

fragment LETTER : [a-zA-Z];
fragment DIGIT : [0-9];

E: 'e' INT;
WS: [ \t]+ -> skip;
ID : LETTER (LETTER | INT)*;
INT: DIGIT+;
DOUBLE: DIGIT* '.' DIGIT+;
NEWLINE: '\r'? '\n' -> skip;
END_EXPR: ';';

prog: (stat END_EXPR)* stat? EOF		
	| stat END_EXPR? EOF 		 						
	;



stat: expr 									#printExpr
	|ID '=' expr 							#assignDeclaration
	|ID  formalParameters '=' expr			#assignFunction				
	;
	
formalParameters: '(' ID (',' ID)* ')'
	;

expr: DOUBLE 'e' op=('+'|'-') INT			#expo10	
	| DOUBLE E								#expo10short
	| '-' expr								#negExpr
	| <assoc=right>expr POW expr			#pow
	| expr op=('*'|'/') expr				#MulDiv
	| expr op=('+'|'-') expr				#AddSub
	| INT									#int
	| DOUBLE								#double
	| ID									#id
	| ID parameters							#function
	| '(' expr ')'							#parens
	; 

parameters:  '(' expr (',' expr)* ')';
	
MUL: '*';
DIV: '/';
ADD: '+';
SUB: '-';
POW: ('^'| '**');