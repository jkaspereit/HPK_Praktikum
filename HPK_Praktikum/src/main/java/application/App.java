package application;


import de.lab4inf.wrb.Differentiator;
import de.lab4inf.wrb.Function;
import util.Functions;
import de.lab4inf.wrb.Integrator;

public class App {


    public static void main(String[] args) throws Exception {

        String path = System.getProperty("java.library.path");
        System.out.println("PATH=" + path);
        System.setProperty("java.library.path", System.getProperty("java.library.path") + ":" + System.getProperty("user.dir") + "/lib/");
        System.out.println("path=" + System.getProperty("java.library.path"));


        Integrator in = new Integrator();
        Differentiator dif = new Differentiator();
        Function fct = Functions.SIN.getFunction();
        double resDiff = dif.differentiate(fct, 1.0);
        System.out.println("done diff");
        double result = in.integrate(fct, 0.0, 0.5);
        System.out.println("res=" + result);

//		Script script = new WRBScript();
//
//		double y, x1 = 5, x2 = -5;
//
//		String task =  "2.0e-5 - 3.0e1";
//		String task =  "x=2;f(x)=2x";
//
//        System.out.println(script.parse(task));


    }
}
