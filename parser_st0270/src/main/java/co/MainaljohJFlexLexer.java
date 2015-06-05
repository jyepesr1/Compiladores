package co;

import java.io.*;
import co.edu.eafit.dis.st0270.p20151.aljoh.pl0.lexer.aljohJFlexLexer;
import co.edu.eafit.dis.st0270.p20151.aljoh.pl0.tokens.EnumToken;

public class MainaljohJFlexLexer {

    private static File archivo;
    private static FileReader reader;
    private static boolean stdinput;
    private static boolean repetido;
    private static String nombre;
    private static aljohJFlexLexer lexer;

    private static void usage(int errorcode){
        System.err.println("java co.MainaljohJflexLexer"+ 
                           "[fichero]... [-] [fichero]...");
        System.exit(errorcode);
    }

    private static void error(){ 
        System.out.println("error: \"" + lexer.yytext() +
                           "\" fila: " + lexer.getLine() +
                           " col: " + lexer.getColumn());
    }

    private static String nonPrinting(String id){
        for(int i=0;i<id.length();i++){
            int character = (int) id.charAt(i);
            if(character<32 || character>126){
                if(i+1 < id.length())
                    id = id.substring(0,i)+character+id.substring(i+1);
                else 
                    id = id.substring(0,i)+character;
            }
        }
        return id;
    }

    public static int lexer(String name){
        try{
            String id = "";
            boolean nonPrinting = false;

            lexer = new aljohJFlexLexer(reader);

            String value = new String();

            if(!name.equals(nombre)) System.out.println("Fichero: "+name);
            
            int t = lexer.getNextToken();

            while(t != lexer.YYEOF){
                switch(t){
                    case 0:
                        value = "identifier";
                        if(lexer.yytext().length() > 32) throw new Error();
                        if(lexer.yytext().matches("[0-9](.*)")) 
                            throw new Error();
                        id = nonPrinting(lexer.yytext());
                        nonPrinting = true;
                        break;
                    case 1:
                        value = "keyword";
                        break;
                    case 2:
                        value = "Integer";
                        int num = Integer.parseInt(lexer.yytext());  
                        if((num > 0) && (lexer.yytext().charAt(0) == '0')) 
                            throw new Error();
                        break;
                    case 3:
                        value = "operator";
                        break;
                    case 4:
                        value = "separator";
                        break;
                }

                if(!nonPrinting)
                    System.out.println("Tipo: " + value +
                                   " valor: \"" + lexer.yytext() +
                                   "\" fila: " + lexer.getLine() +
                                   " col: " + lexer.getColumn());
                else{ 
                    System.out.println("Tipo: " + value +
                                   " valor: \"" + id +
                                   "\" fila: " + lexer.getLine() +
                                   " col: " + lexer.getColumn());
                    nonPrinting = false;
                }

                t = lexer.getNextToken();
            }
        }catch(Error a){
            error();
            return 1;
        }
        catch(RuntimeException a){
           error();
           return 1; 
        }
        catch(Exception a){
            System.out.println(a);
            System.exit(3); 
        }   
        return 0;
    }

    public static void Std(String name){
        try{
            archivo = new File(name);
            BufferedReader lector = new BufferedReader(
                                              new InputStreamReader(System.in));
            BufferedWriter escritor = null;
            String s;
            int ret;
            if(!repetido) System.out.println("Fichero: entrada estandar");
            while ((s = lector.readLine()) != null){
                escritor = new BufferedWriter(new FileWriter(archivo));
                escritor.write(s);
                reader = new FileReader(name);
                escritor.close();
                ret = lexer(nombre);
                if(ret == 1) break;
            }
            lector.close();
            archivo.delete();
            repetido = stdinput = true;
        }
        catch(FileNotFoundException error){
            System.err.println(error);
            usage(2); 
        }   
        catch(IOException ex){
            System.out.println("Error, la entrada estandar "+
                               "solo se debe usar una vez");
            System.exit(4);
        }
    }

    public static void notStd(String name){
        try{
            reader = new FileReader(name);
            stdinput = false;
        }catch(FileNotFoundException error){
            System.err.println(error);
            usage(2); 
        }  
    }

    public static void main(String[] args){

        nombre = "a.pl0";
        repetido = false;

        if(args.length > 0) stdinput = false;
        else Std(nombre);

        for(int i=0; i<args.length; i++){       
         
            if(args[i].equals("-")) Std(nombre); 
            else notStd(args[i]);

            if(!stdinput) lexer(args[i]); 
        }
    }
}
