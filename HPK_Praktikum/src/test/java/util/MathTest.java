package util;

import de.lab4inf.wrb.Differentiator;
import de.lab4inf.wrb.Function;
import de.lab4inf.wrb.Integrator;
import de.lab4inf.wrb.Script;
import application.WRBScript;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MathTest {

    private Script script;
    private static Integrator inte;
    private static Differentiator diff;
    private double EPS = 1.E-8;

    /**
     *
     */
    @Before
    public final void scriptTestSetUp() {
        script = new WRBScript();
        inte = new Integrator();
        diff = new Differentiator();
        assertNotNull("no script implementation", script);
    }


    @Test
    public void testFunctions() {
        double a = 60;
        //f(x)
        double z = 120;
        String task = "f(x) = x*x;";
        script.parse(task);
        Function f = script.getFunction("f");
        assertEquals(z, diff.differentiate(f, a, EPS), EPS);
    }

    @Test
    public void testSquare() {
        double b = 150, a = 5.5;
        double z = Math.log(Math.abs(b)) - Math.log(Math.abs(a));
        String task = "f(x) = 1/x;";
        script.parse(task);
        Function f = script.getFunction("f");
        assertEquals(z, inte.integrate(f, a, b), EPS);
    }

}


