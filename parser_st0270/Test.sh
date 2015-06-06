#!/bin/bash

# Solamente es necesario cambiar el nombre del grupo, NADA MAS! 
# Para usar pruebas solamente es guardar los archivos en la carpeta de test

groupName=aljoh

path=src/'test'/java/co/edu/eafit/dis/st0270/p20151/$groupName/pl0

if [ $1 -eq 0 ]; then
    echo Prueba con Antlr 
    for i in $(ls $path); do 
        echo && cat $path/$i && echo 
        java -cp $CLASSPATH:./target/classes/ co.Main$groupName'AntlrLexer' $path/$i 
        echo 
    done
else 
   echo Prueba con JFlex
    for i in $(ls $path); do 
        echo && cat $path/$i && echo 
        java -cp $CLASSPATH:./target/classes/ co.Main$groupName'JFlexLexer' $path/$i
        echo 
    done
fi
