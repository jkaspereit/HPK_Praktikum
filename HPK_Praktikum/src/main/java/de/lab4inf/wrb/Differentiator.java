package de.lab4inf.wrb;

//import java.util.logging.Logger;

import de.lab4inf.wrb.Function;

public class Differentiator {

//    static final Logger logger = java.util.logging.Logger.getLogger("de.lab4inf");
    private double eps = 1.E-8;
    static {
        try {
            String currentDir = System.getProperty("user.dir");
            System.load(System.getProperty("user.dir") + "/lib/libDifferentiator.so");
            System.out.println("lib loaded");
        } catch (Throwable error) {
            System.out.println("LibPath: " + System.getProperty("java.library.path"));
            System.out.println("native lib loading failed " + error);
//            logger.severe("LibPath: " + System.getProperty("java.library.path"));
//            logger.severe("native lib loading failed " + error);
            System.exit(-1);
        }
    }


    /**
     * Call the native Call
     * @param f the function to differntiate
     * @param x function value
     * @return the result
     */
    public double differentiate(Function f, double x) {
        return differentiate(f, x, eps);
    }

    /**
     * Native Call
     * @param f the function to differntiate
     * @param x function value
     * @param eps eps
     * @return the result
     */
    public native double differentiate(Function f, double x, double eps);

    /**
     * Get the value of eps.
     * @return the eps
     */
    public double getError() {
        return eps;
    }

    /**
     * Set the eps attribute.
     * @param eps the eps to set
     */
    public void setError(double eps) {
        this.eps = eps;
    }
}
