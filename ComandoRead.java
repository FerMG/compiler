import java.util.Scanner;
public class ComandoRead extends Comando{
    private String id;
    
    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    
    public void run(){
        System.out.println("Rodando comando READ");
        Scanner teclado = new Scanner(System.in);
        int valor = teclado.nextInt();
        Programa.setVariableValue(id,valor);
        
    }
    public String writeCode(){
        return "<read>"+id+"</read>\n";
    }
    
    public String writeJava(){
        return id+"=teclado.nextInt();\n";
    }
}