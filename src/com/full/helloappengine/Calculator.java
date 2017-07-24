package com.full.helloappengine;

public class Calculator {

	/**
	 * Sums a and b and returns the result.
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public int add(int a, int b) {
		return a + b;
	}

	// test
	public static void main(String args[]) {
		Calculator calculator = new Calculator();
		int a = 5;
		int b = 4;
		int result = calculator.add(a, b);

		if (result == 9)
			System.out.println("Pass");
		else
			System.out.println("FAIL result was = " + result + " expected 9");

		a = 15;
		b = 9;
		result = calculator.add(a, b);

		if (result == 24)
			System.out.println("Pass");
		else
			System.out.println("FAIL result was = " + result + " expected 9");
	}

}
