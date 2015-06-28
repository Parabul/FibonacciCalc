package kz.anarbek.tasks.fibonacci;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.log4j.Logger;

public class FibonacciNumberCalc {

	final static Logger logger = Logger.getLogger(FibonacciNumberCalc.class);
	
	private static final String DEFAULT = "Default";
	private static int precision;
	private static int n;
	private static String type;

	private MathContext mc;
	private BigDecimal two;
	private BigDecimal phi;
	private BigDecimal binetDenominator;

	public FibonacciNumberCalc(int precision) {
		this.mc = new MathContext(precision);
		this.two = BigDecimal.valueOf(2);
		this.phi = (BigDecimal.ONE.add(sqrtHeron(5), mc)).divide(two, mc);
		this.binetDenominator = phi.multiply(two, mc).subtract(BigDecimal.ONE,
				mc);
	}

	public BigDecimal sqrtHeron(double d) {
		BigDecimal bd = BigDecimal.valueOf(d);
		BigDecimal xn = BigDecimal.valueOf(Math.sqrt(d));

		BigDecimal epsilon = BigDecimal.TEN.pow(2 - mc.getPrecision(), mc);

		BigDecimal xn1 = null;
		do {
			xn1 = (BigDecimal.ONE.divide(two, mc)).multiply(
					xn.add(bd.divide(xn, mc), mc), mc);
			xn = xn1;

		} while (epsilon.compareTo(((bd.divide(xn1, mc)).subtract(xn1, mc))
				.abs(mc)) <= 0);

		return xn1;
	}

	public BigInteger culculateByBinet(int n) {
		BigDecimal tmp = ((phi.pow(n, mc)).subtract(phi.negate(mc).pow(-n, mc),
				mc)).divide(binetDenominator, mc);

		return tmp.setScale(0, BigDecimal.ROUND_HALF_UP).toBigIntegerExact();
	}

	public BigInteger culculateInline(int n) {
		if (n < 0)
			throw new IllegalArgumentException("Negative values not allowed");
		if (n == 0)
			return BigInteger.ZERO;
		if (n == 1)
			return BigInteger.ONE;
		if (n == 2)
			return BigInteger.ONE;
		BigInteger x1 = BigInteger.ONE;
		BigInteger x2 = BigInteger.ONE;
		for (int i = 3; i <= n; i++) {
			BigInteger x3 = x2.add(x1);
			x1 = x2;
			x2 = x3;
		}
		return x2;
	}

	public static String getResultInfo(BigInteger result) {
		StringBuffer sb = new StringBuffer();
		sb.append("\n# Fibonacci Number Calc\n\n");

		sb.append("Precision: ");
		sb.append(precision);
		sb.append("\n");

		sb.append("Type: ");
		sb.append(type);
		sb.append("\n");

		sb.append("Ref number: ");
		sb.append(n);
		sb.append("\n");

		sb.append("Result: ");
		sb.append(result);
		sb.append("\n");

		return sb.toString();
	}

	public static void main(String[] args) {

		precision = 100;
		n = 100;
		type = DEFAULT;

		Options options = new Options();
		
		options.addOption("t", true, "Default or Binet");
		options.addOption("p", true, "Precision");
		options.addOption("n", true, "Ref Number");
		
		
		CommandLineParser parser = new DefaultParser();
		try {
			CommandLine cmd = parser.parse(options, args);
			if (cmd.hasOption("h")) {
				precision = Integer.valueOf(cmd.getOptionValue("p"));
			}

			if (cmd.hasOption("p")) {
				precision = Integer.valueOf(cmd.getOptionValue("p"));
			}
			if (cmd.hasOption("n")) {
				n = Integer.valueOf(cmd.getOptionValue("n"));
			}
			if (cmd.hasOption("t")) {
				type = cmd.getOptionValue("t");
			}
			FibonacciNumberCalc calc = new FibonacciNumberCalc(precision);
			if (DEFAULT.equals(type)) {
				logger.info(getResultInfo(calc.culculateInline(n)));
			} else {
				logger.info(getResultInfo(calc.culculateByBinet(n)));
			}

		} catch (ParseException e) {
			logger.info(e.getMessage());
		}
	}

}
