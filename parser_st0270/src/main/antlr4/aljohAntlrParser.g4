grammar aljohAntlrParser;

@parser::header{
    package co.edu.eafit.dis.st0270.p20151.aljoh.pl0.parser;
}

@lexer::header{
    package co.edu.eafit.dis.st0270.p20151.aljoh.pl0.parser;
}

@parser::rulecatch {
   catch (RecognitionException e) {
      throw e;
   }
}

program		: block '.'                                            #evalProgram
            ;

block       : defconst? defvar? defproc* instruction?              #evalBlock
	         ;

defconst 	: 'const' ID '=' INT (',' ID '=' INT)* ';'             #const
			   ;

defvar		: 'var' ID (',' ID)* ';'                               #var
			   ;

defproc		: 'procedure' ID ';' block ';'                         #proc
			   ;

instruction : ID ':=' expr                                         #assign
				| 'call' ID                          			          #call       
				| 'begin' instruction (';' instruction)* ';'?'end'     #begin
				| 'if' condition 'then' instruction                    #if
				| 'while' condition 'do' instruction                   #while
				;

condition	: 'odd' expr                                           #odd
			   | expr ('='|'<>'|'<'|'>'|'<='|'>=') expr               #evalCond 
			   ;

expr	: ('+'|'-')? term (('+'| '-') term)*                         #addSub
		;

term	: factor (('*'|'/') factor)*                                #timesDiv
		;

factor	: ID                                                     #id
		   | INT                                                    #int
		   | '(' expr ')'                                           #parens
		   ;

ID	: ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')* ;
INT : '1'..'9'('0'..'9')*|'0' ;
//NEWLINE  : '\r'? '\n';

WS : [ \t\r\n]+ -> skip ;
