package co.edu.eafit.dis.st0270.p20151.aljoh.pl0.parser;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

import org.antlr.v4.runtime.tree.TerminalNode;
import co.edu.eafit.dis.st0270.p20151.aljoh.pl0.parser.aljohAntlrParserParser.DefprocContext;

public class aljohAntlrParserIDeclVarsVisitor extends
                                aljohAntlrParserBaseVisitor<HashSet<String>>{ 
    
    HashSet<String> global = new HashSet<String>();
    aljohAntlrParserParser.EvalProgramContext program;
    Stack<String> nombreProc = new Stack<String>();
    Stack<HashSet<String>> anteriores = new Stack<HashSet<String>>();
    List<HashSet<String>> lista = new ArrayList<HashSet<String>>();
    
     
    public HashSet<String> visitEvalProgram(aljohAntlrParserParser.EvalProgramContext ctx) { 
        System.out.println("Program");
        program = ctx;
    	visit(ctx.block());
    	return new HashSet<String>();
    }
   
    
    public HashSet<String> visitEvalBlock(aljohAntlrParserParser.EvalBlockContext ctx){
        System.out.println("Block");
        if(ctx.defvar() == null) return new HashSet<String>();
        visit(ctx.defvar());
        for(DefprocContext p : ctx.defproc()){
        	visit(p);
        }
 
    	return new HashSet<String>() ;
    }
    
    
    public HashSet<String> visitConst(aljohAntlrParserParser.ConstContext ctx){
        System.out.println("Const");
        return new HashSet<String>(); 
    }

    
    public HashSet<String> visitVar(aljohAntlrParserParser.VarContext ctx){ 
    	   System.out.println("Var");
        HashSet<String> local = new HashSet<String>();
    	for(TerminalNode t : ctx.ID()){
        	local.add(t.getText());
        }
    	
    	lista.add(local);
    	
    	if(ctx.getParent().getParent().equals(program)){
    		global = local;
    		System.out.println("*** global ***\n"
    							+ "vars: " + global.toString());
    	}else{
    		System.out.println("*** "+ nombreProc.pop() + " ***");
    		System.out.println("vars: " + local.toString());
    		for(String s : global){
    			if(local.contains(s)){
    				local.remove(s);
    			}
    		}
    		System.out.println("hidings: " + local.toString());
    	}
    	
    	return local;  	
    }
    
    public HashSet<String> visitProc(aljohAntlrParserParser.ProcContext ctx){
        System.out.println("Proc");
        nombreProc.push(ctx.ID().getText());
        visit(ctx.block());
    	return null;
    }
    
    public HashSet<String> visitAssign(aljohAntlrParserParser.AssignContext ctx){
        return null; 
    }
    
    public HashSet<String> visitCall(aljohAntlrParserParser.CallContext ctx){
        return null; 
    }

    
    public HashSet<String> visitBegin(aljohAntlrParserParser.BeginContext ctx){
        return null; 
    }

    
    public HashSet<String> visitIf(aljohAntlrParserParser.IfContext ctx){
        return null; 
    }

    
    public HashSet<String> visitWhile(aljohAntlrParserParser.WhileContext ctx){
        return null; 
    }

    
    public HashSet<String> visitOdd(aljohAntlrParserParser.OddContext ctx){
        return null; 
    }

    
    public HashSet<String> visitEvalCond(aljohAntlrParserParser.EvalCondContext ctx){
        return null; 
    }

    
    public HashSet<String> visitAddSub(aljohAntlrParserParser.AddSubContext ctx){
    	return new HashSet<String>(); 
    }

    
    public HashSet<String> visitTimesDiv(aljohAntlrParserParser.TimesDivContext ctx){
        return new HashSet<String>(); 
    }

    
    public HashSet<String> visitId(aljohAntlrParserParser.IdContext ctx){
        return new HashSet<String>(); 
    }

    
    public HashSet<String> visitInt(aljohAntlrParserParser.IntContext ctx){
        return new HashSet<String>(); 
    }

    
    public HashSet<String> visitParens(aljohAntlrParserParser.ParensContext ctx){
        return null; 
    }
}

