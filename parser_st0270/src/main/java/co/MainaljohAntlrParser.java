package co;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.ANTLRFileStream;

import co.edu.eafit.dis.st0270.p20151.aljoh.pl0.lexer.aljohAntlrLexer;
import co.edu.eafit.dis.st0270.p20151.aljoh.pl0.parser.aljohAntlrParserIDeclVarsVisitor;
import co.edu.eafit.dis.st0270.p20151.aljoh.pl0.parser.aljohAntlrParserLexer;
import co.edu.eafit.dis.st0270.p20151.aljoh.pl0.parser.aljohAntlrParserParser;

import java.io.IOException;

public class MainaljohAntlrParser {

    public static void main(String args[]) {

        //MainaljohAntlrLexer mainlexer = new MainaljohAntlrLexer();
        ANTLRFileStream afs;

        try {
           // mainlexer.fileStream(args[0]);
            afs = new  ANTLRFileStream(args[0]);
            aljohAntlrParserLexer lexer = new aljohAntlrParserLexer(afs);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            aljohAntlrParserParser parser = new aljohAntlrParserParser(tokens);
            ParseTree tree = parser.program();
            aljohAntlrParserIDeclVarsVisitor  eval = new aljohAntlrParserIDeclVarsVisitor();
            eval.visit(tree);

        }catch (Exception ioe) {
            ioe.printStackTrace();
        }
    }
}
