import java.io.*;
public class MainClass{
    public static void main(String args[]){
        try{
            String arquivo = "input.prog";
            
            if (args.length != 0){
                arquivo = args[0];
            }
            
            MyLexer lexer   = new MyLexer(new FileInputStream(new File(arquivo)));
            MyParser parser = new MyParser(lexer);
            parser.init();
            parser.prog();
            
          
            
            
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
}