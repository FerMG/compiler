#!/bin/bash
find ./in -name "*.prog" -exec sh -c 'mkdir -p ./out/${0##*/} && ./exec.sh ${0} > ./out/${0##*/}/Teste.java' {} \;