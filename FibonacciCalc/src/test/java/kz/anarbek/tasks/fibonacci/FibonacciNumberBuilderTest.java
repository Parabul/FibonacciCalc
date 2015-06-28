package kz.anarbek.tasks.fibonacci;

import java.math.BigInteger;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FibonacciNumberBuilderTest {

	@Test
	public void positive() {
		FibonacciNumberCalc builder = new FibonacciNumberCalc(100);
		BigInteger culculatedByBinet = builder.culculateByBinet(100);
		BigInteger culculatedInline = builder.culculateInline(100);
		assertEquals(culculatedByBinet, culculatedInline);
	}

	@Test(expected = IllegalArgumentException.class)
	public void negative() {
		new FibonacciNumberCalc(-10);
	}

}
