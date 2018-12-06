#!/bin/bash
# Create *.so file 

echo "creating .h files...."
javac -h ./src/main/c/jni/ ./src/main/java/de/lab4inf/wrb/Integrator.java ./src/main/java/de/lab4inf/wrb//Differentiator.java ./src/main/java/de/lab4inf/wrb/Function.java
#javac -h ./src/main/c/jni/ ./src/main/java/util/Differentiator.java ./src/main/java/util/Function.java
#javac -h ./src/main/jni/ ./src/main/java/util/Integrator.java ./src/main/java/util/Differentiator.java ./src/main/java/util/Function.java
echo "...done"

echo "creating .so lib...."
gcc -I/usr/lib/jvm/default-java/include -I/usr/lib/jvm/default-java/include/linux -shared -fPIC -o ./lib/libIntegrator.so ./src/main/c/jni/de_lab4inf_wrb_Integrator.cpp
gcc -I/usr/lib/jvm/default-java/include -I/usr/lib/jvm/default-java/include/linux -shared -fPIC -o ./lib/libDifferentiator.so ./src/main/c/jni/de_lab4inf_wrb_Differentiator.cpp
echo "... done"
