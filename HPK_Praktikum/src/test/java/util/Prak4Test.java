package util;

import de.lab4inf.wrb.Script;
import application.WRBScript;
import de.lab4inf.wrb.Prak4Tester;

public class Prak4Test extends Prak4Tester {

    @Override
    protected Script getScript() {
        return new WRBScript();
    }
}
