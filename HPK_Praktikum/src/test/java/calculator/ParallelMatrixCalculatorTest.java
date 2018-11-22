package calculator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.stream.DoubleStream;

import org.antlr.v4.codegen.model.dbg;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import calculator.AbstractMatrixCalculator;
import calculator.ParallelMatrixCalculator;
import util.AbstractMatrixCalculatorTest;

public class ParallelMatrixCalculatorTest extends AbstractMatrixCalculatorTest{

	@Override
	protected AbstractMatrixCalculator getCalculator() {
		return new ParallelMatrixCalculator();
	}

}
