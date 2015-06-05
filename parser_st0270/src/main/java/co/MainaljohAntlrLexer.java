package co;

import co.edu.eafit.dis.st0270.p20151.aljoh.pl0.lexer.aljohAntlrLexer;
import java.io.*;
import org.antlr.v4.runtime.*;
import java.math.BigInteger;

public class MainaljohAntlrLexer {

   private static CharStream afs;
   private static aljohAntlrLexer lexer;
   private static Token t, eof;

   /**
    * This method ends the program if there is a problem in the Standard
    * input or reading a file
    */
   private static void usage(int errcode) {
      System.err.println("Usage: java MainAljohAntlrLexer <file>");
      System.exit(errcode);
   }

   /**
    * This methos prints each token
    */
   private static void printToken(Token t, String type) {
      System.out.println(" Tipo: " + type +
                         " valor: " + '"'+t.getText()+ '"' +
                         " fila: " + t.getLine() +
                         " columna: " +
                         t.getCharPositionInLine());
   }

   /**
    * This method changes the nonprintable characters to ints
    */
   private static String nonPrintableChars(String id){
      for(int i=0 ; i<id.length() ; i++){
         int character = (int)id.charAt(i);
         if(character<32 || character>126){
            if(i+1 < id.length())
               id = id.substring(0,i)+character+id.substring(i+1);
            else 
               id = id.substring(0,i)+character;
         }
      }
      return id;
   }

   /**
    * This method prints the message for the Error
    */
   private static void printError(Token t){
      System.err.println(" Error: " + t.getText() + " fila: "
            + t.getLine() + " Columna: " + t.getCharPositionInLine());
   }

   /**
    * This method splits the input string in tokens
    */
   private static void scanner(Token t, Token eof, aljohAntlrLexer lexer)
      throws Exception{

         while (t.getType() != eof.getType()) {

            switch(t.getType()) {

               case  aljohAntlrLexer.WS:

                  // Ignore files
                  break;

               case aljohAntlrLexer.SEPARATORS:

                  printToken(t, "separator");
                  break;

               case aljohAntlrLexer.OPERATORS:

                  printToken(t, "operator");
                  break;

               case aljohAntlrLexer.KEYWORDS:

                  printToken(t, "keyword");
                  break;

               case aljohAntlrLexer.INT:

                  BigInteger numero = new BigInteger(t.getText());
                  BigInteger maxInt = BigInteger.valueOf(Integer.MAX_VALUE);
                  BigInteger cero = BigInteger.valueOf(0L);

                  if(numero.compareTo(maxInt) > 0){ 
                     printError(t);
                     throw new Exception();
                  }

                  printToken(t, "integer");
                  break;

               case aljohAntlrLexer.ID:

                  boolean encontre = t.getText().matches("[0-9](.*)");

                  if(t.getText().length() > 32 || encontre){
                     printError(t);
                     throw new Exception();
                  }

                  String id = nonPrintableChars(t.getText());

                  System.out.println(" Tipo: " + "identifier" +
                        " valor: " + '"'+ id + '"' +
                        " fila: " + t.getLine() +
                        " columna: " + t.getCharPositionInLine());
                 // printToken(t, "identifier");
                  
                  break;
            }
            try{
               t = lexer.nextToken();
            }catch(Exception e){
               break;
            }
         }
      }

   /**
    * This method reads the Standard Input
    */
   private static void stdIn() throws Exception{
      System.out.println("Fichero: Entrada Estándar");
      afs = new UnbufferedCharStream(System.in);
      lexer = new aljohAntlrLexer(afs);
      lexer.setTokenFactory(new CommonTokenFactory(true));
      lexer.removeErrorListeners();
      lexer.addErrorListener(new VerboseListener());
      t = lexer.nextToken();
      eof = lexer.emitEOF();
      scanner(t, eof,lexer);

   }

   /**
    * This method reads the file
    * @param nombre It's the name of file
    */
   public static void fileStream(String nombre) throws Exception{
      try {
         afs = new ANTLRFileStream(nombre);
      }
      catch (IOException ioe) {
         System.err.println("Error: " + ioe);
         usage(2);
      }

      lexer = new aljohAntlrLexer(afs);
      lexer.removeErrorListeners();
      lexer.addErrorListener(new VerboseListener());

      System.out.println("Fichero: "+ nombre);

      t = lexer.nextToken();
      eof = lexer.emitEOF();
      scanner(t, eof, lexer);
   }

   /**
    * This method starts the scanner.
    * <p>
    * @param args It accepts multiply files name.
    *
    */
   public static void main(String args[]) {
      boolean stdin = true;

      if(args.length == 0){
         try {
            stdIn();  
         } catch(Exception e) {
         }
      }else{
         for(int i=0; i< args.length; i++){
            if(stdin && args[i].equals("-")){
               stdin = false;
               try{
                  stdIn();
               }catch(Exception e){
                  continue;
               }
            }else if(!args[i].equals("-")){
               try{
                  fileStream(args[i]);
               }catch(Exception e){
                  continue;
               }
            }else{
               System.err.println("Error, Sólo se puede usar una "+
                     "vez la entrada estándar");

               continue;
            }
         }
      }
   }

   /**
    * This inner class sets the message for Standard Error
    */
   private static class VerboseListener extends BaseErrorListener {
      @Override
      public void syntaxError(Recognizer<?, ?> recognizer,
               Object offendingSymbol,
               int line, int charPositionInLine,
               String msg,
               RecognitionException e)
      {
         char token = msg.charAt(msg.length()-2);

         System.err.println(" Error: " + '"' +token+ '"'+" fila: "
                  + line + " Columna: " + charPositionInLine);
         throw e;
      }
   }
}
