#!/bin/bash
# Create *.so file 

echo "starting..."
gcc -I/usr/lib/jvm/default-java/include -I/usr/lib/jvm/default-java/include/linux -o libWRB.so -shared ./src/jni/util_Differentiator.cpp -fPIC
echo "... done"




