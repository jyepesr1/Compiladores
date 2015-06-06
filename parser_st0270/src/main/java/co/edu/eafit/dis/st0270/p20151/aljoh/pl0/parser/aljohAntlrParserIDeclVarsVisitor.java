package co.edu.eafit.dis.st0270.p20151.aljoh.pl0.parser;

import java.util.ArrayList;
import java.util.List;

public class aljohAntlrParserIDeclVarsVisitor extends
                                aljohAntlrParserBaseVisitor<Set>{ 
    
    Set vars = new HashSet();
    
    @Override 
    public Set visitEvalProgram(aljohAntlrParserParser.PrintContext ctx) { 
        return visitChildren(ctx);
    }
   
    @Override
    public Set visitEvalBlock(aljohAntlrParserParser.EvalBlockContext ctx){
        return visitChildren(ctx);
    }
    @Override
    public Set visitConst(aljohAntlrParserParser.ConstContext ctx){
        return visitChildren(ctx); 
    }

    @Override
    public Set visitVar(aljohAntlrParserParser.VarContext ctx){ 
        return visitChildren(ctx);
    }
    @Override
    public Set visitProc(aljohAntlrParserParser.ProcContext ctx){
        return visitChildren(ctx);
    }
    @Override
    public Set visitAssign(aljohAntlrParserParser.AssignContext ctx){
        return visitChildren(ctx); 
    }
    @Override
    public Set visitCall(aljohAntlrParserParser.CallContext ctx){
        return visitChildren(ctx); 
    }

    @Override
    public Set visitBegin(aljohAntlrParserParser.BeginContext ctx){
        return visitChildren(ctx); 
    }

    @Override
    public Set visitIf(aljohAntlrParserParser.IfContext ctx){
        return visitChildren(ctx); 
    }

    @Override
    public Set visitWhile(aljohAntlrParserParser.WhileContext ctx){
        return visitChildren(ctx); 
    }

    @Override
    public Set visitOdd(aljohAntlrParserParser.OddContext ctx){
        return visitChildren(ctx); 
    }

    @Override
    public Set visitEvalCond(aljohAntlrParserParser.EvalCondContext ctx){
        return visitChildren(ctx); 
    }

    @Override
    public Set visitAddSub(aljohAntlrParserParser.AddSubContext ctx){
        return visitChildren(ctx); 
    }

    @Override
    public Set visitSetimesDiv(aljohAntlrParserParser.SetimesDivContext ctx){
        return visitChildren(ctx); 
    }

    @Override
    public Set visitId(aljohAntlrParserParser.IdContext ctx){
        return visitChildren(ctx); 
    }

    @Override
    public Set visitInt(aljohAntlrParserParser.IntContext ctx){
        return visitChildren(ctx); 
    }

    @Override
    public Set visitParens(aljohAntlrParserParser.ParensContext ctx){
        return visitChildren(ctx); }
   }

}

