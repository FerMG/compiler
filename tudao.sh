#!/bin/bash
echo "STARTING..."
java -cp antlr.jar antlr.Tool gram.g
echo "PARSER AND LEXER GENERATED"
if [ $? -eq 0 ]; then
   javac -d ./bin -classpath antlr.jar:. *.java
   echo "ALL CLASSES COMPILED"
   if [ $? -eq 0 ]; then
       java -cp ./bin:antlr.jar:. MainClass
   else 
       echo "GAVE BAD COMPILING"
   fi
else
   echo "GAVE BAD TO GENERATE PARSER"
fi