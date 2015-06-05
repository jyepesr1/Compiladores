/* Analizador lexico para el lenguaje PL/0 */
package co.edu.eafit.dis.st0270.p20151.aljoh.pl0.lexer;

import co.edu.eafit.dis.st0270.p20151.aljoh.pl0.tokens.EnumToken;

%%
%class aljohJFlexLexer
%unicode
%line
%column
%function getNextToken
%int 
%public 

%{
public int getLine() {
    return yyline + 1;
}

public int getColumn() {
    return yycolumn + 1;
}
%}

LineTerminator = \r|\n|\r\n
Inputcharacter = [^\r\n]
WhiteSpace     = {LineTerminator} | [ \t\n\f]

Identifier        = ([:letter:]|[:digit:])([:jletterdigit:] | "_")*

DecIntegerLiteral = 0+ | 0*[1-9]([0-9])* 

Keyword           = "const" | "var" | "procedure" | "call" | "begin" | "end" 
                    | "if" | "then" | "while" | "do" | "odd"       

%%

<YYINITIAL> {
    {Keyword}                   { return EnumToken.Keyword.ordinal(); }
    {DecIntegerLiteral}         { return EnumToken.DecIntegerLiteral.ordinal();}
    {Identifier}                { return EnumToken.Identifier.ordinal(); }
    "." | "," | ";" | "(" | ")" { return EnumToken.Separator.ordinal(); }
    ":=" | "<>" | "=" | "<" | ">" | "<=" | ">=" | "+" | "-" | "*" | "/" 
                                { return EnumToken.Operator.ordinal(); }
    {WhiteSpace}                { /* Ignore */ }
} 

.|\n                        { throw new Error(); }
