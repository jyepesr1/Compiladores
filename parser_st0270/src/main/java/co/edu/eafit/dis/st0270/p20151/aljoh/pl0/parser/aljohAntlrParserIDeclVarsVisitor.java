package co.edu.eafit.dis.st0270.p20151.aljoh.pl0.parser;

import java.util.ArrayList;
import java.util.List;

public class aljohAntlrParserIDeclVarsVisitor extends
                                aljohAntlrParserBaseVisitor<String>{ 
    
    List vars = new ArrayList<String>();

    public String visitEvalBlock(aljohAntlrParserParser.EvalBlockContext ctx){
        return "";    	
    }
    
    @Override
    public String visitVar(aljohAntlrParserParser.VarContext ctx){
        System.out.println("Entre a esta Huevada");
        for(int i=0; i<ctx.ID().size(); i++){
        	String id = ctx.ID(i).toString();
        	vars.add(id);
        }
        return vars.toString();
    }

    @Override
    public String visitAssign(aljohAntlrParserParser.AssignContext ctx){
        String id = ctx.ID().getText();
        System.out.println(id);
    	//int val = visit(ctx.expr());
    	return id;
    }

}

