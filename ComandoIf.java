import java.util.ArrayList;
import java.util.Scanner;

public class ComandoIf extends Comando{
    public static final int IF_MODE   = 1;
    public static final int ELSE_MODE = 2;
    
    private ArrayList<Comando> commandList;
    private ArrayList<Comando> elseList;
    private String             logicalExpr;
    private int                mode;
    
    public ComandoIf(){
        this.commandList = new ArrayList<Comando>();
        this.elseList    = new ArrayList<Comando>();
        this.mode        = IF_MODE;
    }
    public void setLogicalExpr(String expr){
        this.logicalExpr = expr;
    }
    public void addCommand(Comando comando){
        if (mode == IF_MODE)
           this.commandList.add(comando);
        else
           this.elseList.add(comando);
    }
    
    public void changeMode(int mode){
        this.mode = mode;
    }

    public void run(){
        Scanner teclado = new Scanner(System.in);
        System.out.println("A expr é verdadeira? 1 / 0");
        int opcao = teclado.nextInt();
        if (opcao == 1){
            for (Comando c: commandList){
                c.run();
            }
        }
        
    }
    public String writeCode(){
        StringBuilder str = new StringBuilder();
        str.append("<if expr=\"").append(logicalExpr).append("\">\n");
        str.append("<commandList>\n");
        for(Comando c: commandList){
            str.append(c.writeCode());
        }
        str.append("</commandList>\n");
        if (!elseList.isEmpty()){
           str.append("<elseList>\n");
           for (Comando c: elseList){
               str.append(c.writeCode());
           }
           str.append("</elseList>\n");
        }
        str.append("</if>\n");
        return str.toString();
    }
    
    public String writeJava(){
        StringBuilder str = new StringBuilder();
        str.append("if (");
        str.append(logicalExpr);
        str.append("){\n");
        for (Comando c: commandList){
            str.append(c.writeJava());
        }
        str.append("}\n");
        if (!elseList.isEmpty()){
            str.append("else{\n");
            for (Comando c: elseList){
                str.append(c.writeJava());
            }
            str.append("}\n");
        }
        return str.toString();
    }
}