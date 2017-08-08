import java.util.ArrayList;
import java.util.HashMap;

public class Programa{
    public static HashMap<String,Integer>  varList;
    public static ArrayList<Comando> comandos;
    
    public Programa(){
        varList = new HashMap<String,Integer>();
        comandos = new ArrayList<Comando>();
    }
    
    public static void addVariable(String var){
        varList.put(var,0);
    }
    public static void addCommand(Comando cmd){
        comandos.add(cmd);
    }
    public static void setVariableValue(String var, Integer value){
        if (varList.get(var) != null){
            varList.put(var, value);
        }
    }
    public static Integer getVarValue(String var){
        return varList.get(var);
    }
    
    
    public void run(){
        for (Comando c: comandos){
            c.run();
        }
        
    }
    public String writeCode(){
        StringBuilder str = new StringBuilder();
        str.append("<program>\n");
        str.append("<variableList>\n");
        for (Object var: varList.values().toArray()){
            str.append("<var id=").append(var).append("/>\n");
        }
        str.append("</variableList>\n");
        str.append("<commands>");
        for (Comando cmd: comandos){
            str.append(cmd.writeCode());
        }
        str.append("</commands>\n");
        str.append("</program>");
        return str.toString();
    }
    
    public String writeJava(){
        StringBuilder str = new StringBuilder();
        str.append("public class Teste{\n");
        str.append("    public static void main(String args[]){\n");
        str.append("   java.util.Scanner teclado = new java.util.Scanner(System.in);\n");
        for (String var: varList.keySet()){
            str.append("int "+var+";\n");
        }
        for (Comando c: comandos){
            str.append(c.writeJava());    
        }
        str.append("}\n");
        str.append("}\n");
        return str.toString();
        
    }
    
}