package util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.stream.DoubleStream;

import org.antlr.v4.codegen.model.dbg;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MultithreadingMatrixCalculatorTest extends AbstractMatrixCalculatorTest{

	
	@Override
	protected double[][] calculate(double[][] m1, double[][] m2) throws InterruptedException, ExecutionException {
		return matrixCalculator.mathParallel(m1, m2);
	}

}
