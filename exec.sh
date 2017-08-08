#!/bin/bash
if [ -z "$1" ]; then
    #echo "Executando MainClass sem parâmetro!"
    java -cp ./bin:antlr.jar:. MainClass
else
    #echo "Executando MainClass com o parâmetro: $1"
    java -cp ./bin:antlr.jar:. MainClass $1
fi