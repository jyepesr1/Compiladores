lexer grammar aljohAntlrLexer;

@lexer::header{
    package co.edu.eafit.dis.st0270.p20151.aljoh.pl0.lexer;
}

@lexer::rulecatch {
       catch (RecognitionException e) {
                 throw e;
       }
}

KEYWORDS
        : 'const'
        | 'var'
        | 'procedure'
        | 'call'
        | 'begin'
        | 'end'
        | 'if'
        | 'then'
        | 'while'
        | 'do'
        | 'odd'
        | 'CONST'
        | 'VAR'
        | 'PROCEDURE'
        | 'CALL'
        | 'BEGIN'
        | 'END'
        | 'IF'
        | 'THEN'
        | 'WHILE'
        | 'DO'
        | 'ODD'
        ;

INT 
        : ('1'..'9')('0'..'9')* | '0'
        ;


ID 
        : ('\u0030'..'\u0039'|'\u0041'..'\u005A'|'\u0061'..'\u007A'
          |'\u00A1'..'\u00FF')('\u0030'..'\u0039'|'\u0041'..'\u005A'
          |'\u0061'..'\u007A'|'\u005F'|'\u00A1'..'\u00FF')* 
        ;


OPERATORS
        : '='
        | ':='
        | '+'
        | '-'
        | '*'
        | '/'
        | '<'
        | '<='
        | '>'
        | '>='
        | '<>'
        ;


SEPARATORS
        : '('
        | ')'
        | ';'
        | ','
        | '.'
        ;


WS  
        : ( ' '
        | '\t'
        | '\r'
        | '\n'
        | '\f'
        ) -> skip
        ; 
