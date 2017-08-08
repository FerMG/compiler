class MyParser extends Parser;
{
   private Programa     programa;
   private Comando      cmd;
   private int          writeType;
   private String       element;
   private StackCommand stack;

   
   public void init(){
       programa = new Programa();
       stack    = new StackCommand();
  
   }
}

prog    :   "programa" declara corpo "fimprog"
        {
            //System.out.println(programa.writeCode());
            System.out.println(programa.writeJava());
            //System.out.println("Agora vai rodar...");
            //programa.run();
        }
        ;
          
declara :   "declare" ID 
             {
                programa.addVariable(LT(0).getText());
             }
             (VIRG ID
             {
                programa.addVariable(LT(0).getText());
             }
             )* PF
        ;
    
corpo   :   "inicio" (comando)+ "fim"
        ;
        
comando :   (cmdIf | cmdPrnt | cmdRead)
        ;

cmdIf   :   "se" AP 
                   (ID | NUM)  {element = LT(0).getText();  }  
                    OPREL      {element += LT(0).getText(); }
                   (ID | NUM)  {element += LT(0).getText(); }
                { 
                   cmd = new ComandoIf();
                   ((ComandoIf)cmd).setLogicalExpr(element);
                   stack.push(cmd);
                }   
                FP "entao" corpo 
                ("senao"
                {
                  ComandoIf tmp = (ComandoIf)stack.getTopo();
                  tmp.changeMode(ComandoIf.ELSE_MODE);
                }
                  corpo 
                ) ?
                
                {
                    Comando cmd = stack.pop();
                    if (stack.isEmpty()){
                        programa.addCommand(cmd);
                    }
                    else{
                        ComandoIf tmp = (ComandoIf)stack.getTopo();
                        tmp.addCommand(cmd);
                    }
                }
                
                
        ;
        
cmdPrnt :   "mostre" {cmd = new ComandoWrite();}
             ( ID {writeType = ComandoWrite.TYPE_ID;} 
               | 
               TEXTO {writeType = ComandoWrite.TYPE_TEXT;}
             ) {element = LT(0).getText();} 
               PF 
             {
            
                ((ComandoWrite)cmd).setType(writeType);
                ((ComandoWrite)cmd).setContent(element);
                if (stack.isEmpty()){
                   programa.addCommand(cmd);
                }
                else{
                   Comando tmp = stack.getTopo();
                   ((ComandoIf)tmp).addCommand(cmd);
                }
             }
        ;
        
cmdRead :   "leia" { cmd = new ComandoRead(); }
             (ID) 
             {
               element = LT(0).getText();
             }
             PF
             {
                ((ComandoRead)cmd).setId(element);
                if (stack.isEmpty()){
                   programa.addCommand(cmd);
                }
                else{
                   Comando tmp = stack.getTopo();
                   ((ComandoIf)tmp).addCommand(cmd);
                }
             }
        ;
        
        

class MyLexer extends Lexer;
options{
   caseSensitive = true;
}

WS      : (' ' | '\n' | '\r' | '\t') {_ttype=Token.SKIP;}
        ;
        
ID      : ('a'..'z' | 'A'..'Z') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')*
        ;
        
NUM     : ('0'..'9')+ ('.' ('0'..'9')+)?
        ;
        
AP      : '('
        ;
        
FP      : ')'
        ;
        
PF      : '.'
        ;

VIRG    : ','
        ;
        
OPREL   : '>' | '<' | '='
        ;
        
TEXTO   : '"' ('a'..'z' | 'A'..'Z' | ' ' | '0'..'9')* '"'
        ;