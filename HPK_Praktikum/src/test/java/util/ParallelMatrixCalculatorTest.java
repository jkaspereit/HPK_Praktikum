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

import calculator.AbstractMatrixCalculator;
import calculator.ParallelMatrixCalculator;

public class ParallelMatrixCalculatorTest extends AbstractMatrixCalculatorTest{

	@Override
	protected AbstractMatrixCalculator getCalculator() {
		return new ParallelMatrixCalculator();
	}

}
