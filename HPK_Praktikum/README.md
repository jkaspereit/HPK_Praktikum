<h1>Setup JNI</h1>

<h2>How to</h2>
1. create header files
2. write .c/.cpp files
3. create depending .so library
4. reference library in java 
5. call method


<h3></h3>
<h2>Scratches</h2>
<h3>LibPath</h3>
System.setProperty("java.library.path", "/home/sisi/CLionProjects/HPK/"); //WORKAROUND


<h3>Create header files</h3>
javac -h X Y    
X created file   
Y source files
javac -h . ./Differentiator.java

<h3>Create .so file</h3>
run /HPK/createSo.sh



