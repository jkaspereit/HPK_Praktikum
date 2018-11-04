package application;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class WRBScriptTest extends AbstractScriptTest{
	final double eps = 1.E-8;
    Script script;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public final void setUp() throws Exception {
        script = getScript();
        assertNotNull("no script implementation", script);
    }
    
    /**
     * Get the actual implementation for the script test.
     * 
     * @return script implementation
     */
    protected Script getScript() {
        return new WRBScript(false);
    }

    /**
     * Test method for
     * {@link de.lab4inf.wrb.Script#getVariable(java.lang.String)}.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testGetUnknownVariable() throws Exception {
        String key = "dummy";
        script.getVariable(key);
    }

    @Test
    public void testSignedSecondArg() throws Exception {
        String task = "2 + -6";
        assertEquals(-4.0, script.parse(task), eps);
    }


    @Test
    public final void testLongAdd() throws Exception {
        String task = "2.0 + 3 + 4.0 + 5";
        assertEquals(14, script.parse(task), eps);
    }

    @Test
    public final void testLongMult() throws Exception {
        String task = "2 * 3.0 * 4 * 5.000";
        assertEquals(120, script.parse(task), eps);
    }

    @Test
    public final void testLongMixed() throws Exception {
        String task = "2.0 * 3 * 4.0 + 5 + 6.0 / 3";
        assertEquals(31, script.parse(task), eps);
    }

    @Test
    public void testParseBracket() throws Exception {
        String task = " 2*(4.0 + 3)";
        assertEquals(14, script.parse(task), eps);
    }                                      
    
    @Test
    public void testEmptyExpr() throws Exception {
        String task = ";;;";
        assertNull(script.parse(task));
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testEmptyInput() throws Exception {
        String task = "";
        assertEquals(14, script.parse(task), eps);
    }
    
//    @Test(expected = IllegalArgumentException.class)
//    public final void testIllegalArgument() throws Exception {
//    	String task = "5 + 4 .";
//        script.parse(task);
//    }
    

    
}
