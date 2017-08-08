public class Teste{
    public static void main(String args[]){
   java.util.Scanner teclado = new java.util.Scanner(System.in);
int d;
int b;
int c;
int a;
a=teclado.nextInt();
b=teclado.nextInt();
c=teclado.nextInt();
d=teclado.nextInt();
if (a>b){
System.out.println("primeiro if");
}
else{
if (c<d){
System.out.println("segundo if");
}
else{
System.out.println("segundo else");
}
System.out.println("primeiro else");
}
System.out.println("agora outra coisa");
System.out.println("vamos comecear um if sem else");
if (a<b){
System.out.println("if sem else");
}
}
}

