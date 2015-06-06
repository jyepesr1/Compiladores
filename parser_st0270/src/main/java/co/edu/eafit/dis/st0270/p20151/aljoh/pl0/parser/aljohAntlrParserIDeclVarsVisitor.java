package co.edu.eafit.dis.st0270.p20151.aljoh.pl0.parser;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.antlr.v4.runtime.tree.TerminalNode;

import co.edu.eafit.dis.st0270.p20151.aljoh.pl0.parser.aljohAntlrParserParser.DefprocContext;

public class aljohAntlrParserIDeclVarsVisitor extends
                                aljohAntlrParserBaseVisitor<HashSet<String>>{ 
    
    HashSet<String> global = new HashSet<String>();
    aljohAntlrParserParser.EvalProgramContext program;
    String nombreProc = new String();
    List<HashSet<String>> lista = new ArrayList<HashSet<String>>();
    
     
    public HashSet<String> visitEvalProgram(aljohAntlrParserParser.EvalProgramContext ctx) { 
    	System.out.println("*** Decl Vars ***");
        program = ctx;
    	visit(ctx.block());
    	return new HashSet<String>();
    }
   
    
    public HashSet<String> visitEvalBlock(aljohAntlrParserParser.EvalBlockContext ctx){
        if(ctx.defvar() != null) //return new HashSet<String>();
        visit(ctx.defvar());
        for(DefprocContext p : ctx.defproc()){
        	visit(p);
        }
        if(ctx.instruction() != null) //return new HashSet<String>();
            visit(ctx.instruction());
 
    	return new HashSet<String>() ;
    }
    
    public HashSet<String> visitVar(aljohAntlrParserParser.VarContext ctx){ 
        HashSet<String> local = new HashSet<String>();
    	for(TerminalNode t : ctx.ID()){
        	local.add(t.getText());
    	}
    	
    	lista.add(local);
    	
    	if(ctx.getParent().getParent().equals(program)){
    		global = local;
    		System.out.println("*** global ***\n"
    							+ "vars: " + global.toString()+".");
    		System.out.println("hidings: [].");
    	}else{
    		System.out.println("*** "+ nombreProc + " ***");
    		System.out.println("vars: " + local.toString()+".");
    		HashSet<String> res = new HashSet<String>();
    		for(String s : lista.get(lista.size()-1)){
    			for(HashSet<String> hs : lista.subList(0, lista.size()-1)){
    				if(hs.contains(s)){
    					res.add(s);
    				}
    			}
    		}
    		System.out.println("hidings: " + res.toString() + ".");
    	}
    	
    	return local;  	
    }
    
    public HashSet<String> visitProc(aljohAntlrParserParser.ProcContext ctx){
        nombreProc=ctx.ID().getText();
        visit(ctx.block());
        lista.remove(lista.size()-1);
        return new HashSet<String>();
    }
    
    public HashSet<String> visitBegin(aljohAntlrParserParser.BeginContext ctx) {
      if(ctx.getParent().getParent() == program){
    		System.out.println("*** main ***");
    		System.out.println("vars: [].");
    		System.out.println("hidings: [].");
    	}
    	return new HashSet<String>(); 
    	
    }
    
}

