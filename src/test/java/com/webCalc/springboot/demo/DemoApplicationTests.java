package com.webCalc.springboot.demo;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests 
{

	@Test
	 // Test SpringBoot
	void contextLoads() 
	{
	}
	
	@Test
	// Tests interpreter constructor.
	public void interpreterConstructor()
	{
		new Interpriter();
	}
	
	@Test
	// Tests interpreter calculate() error catches.
	public void calculateErrorCatch()
	{
		String input = "+";
		String output = "Error: problem found in equation";
		assertEquals("Test with single operator.", output, Interpriter.calculate(input));
		
		input = "1";
		assertEquals("Test with single operator.", output, Interpriter.calculate(input));
		
		input = "1 ** 1";
		assertEquals("Test duplicate operator.", output, Interpriter.calculate(input));
		
		input = ("1 */ 1");
		assertEquals("Test for multiple operators in a row.", output, Interpriter.calculate(input));
		
		input = "test";
		assertEquals("Test for non-equations.", output, Interpriter.calculate(input));
		
		input = "(1 + 2";
		assertEquals("Test for unpaired brackets.", output, Interpriter.calculate(input));
		
		input = "1 + (*2)";
		assertEquals("Test for misplaced operators.", output, Interpriter.calculate(input));
		
		input = "1 + (2*)";
		assertEquals("Test for misplaced operators.", output, Interpriter.calculate(input));
		
		input = "1 +. (2)";
		assertEquals("Test for misplaced operators.", output, Interpriter.calculate(input));
		
		input = "";
		output = "Error: please enter an equation";
		assertEquals("Test with no input.", output, Interpriter.calculate(input));
		
		input = null;
		assertEquals("Test with no input.", output, Interpriter.calculate(input));
		
	}
	
	// Tests interpreter calculate() addition.
	@Test
	public void calculateAddition()
	{
        String input = "10+20";
        String output = "30.000";
        assertEquals("Test addition using whole numbers.", output, Interpriter.calculate(input));
        
        input = "    10     +      20";
        assertEquals("Test addition using whole numbers with unnecessary whitespaces.", output, Interpriter.calculate(input));  
        
        input = "10.01 + 20.2234";
        output = "30.233";
        assertEquals("Test addition using floating point numbers.", output, Interpriter.calculate(input));
        
        input = "10.1 + 10.1 + 5.6";
        output = "25.800";
        assertEquals("Test addition with more than two numbers.", output, Interpriter.calculate(input));
	}
	
	//Tests interpreter calculate() decimal point position such as:
	//	1. = 1.0
	//	.1	= 0.1
	@Test
	public void calculateDecimalPointPosition()
	{
		String input = "10. + 20.";
		String output = "30.000";
		assertEquals("Test decimal point after a whole number.", output, Interpriter.calculate(input));
		
		input = ".1 + .2";
		output = ".300";
		assertEquals("Test decimal point before a whole number.", output, Interpriter.calculate(input));
	}
	
	// Tests interpreter calculate() subtraction.
	@Test
	public void calculateSubtraction()
	{
		String input = "20-10";
		String output = "10.000";
		assertEquals("Test subtraction using whole numbers.", output, Interpriter.calculate(input));
	
		input = "10-20";
		output = "-10.000";
		assertEquals("Test subtraction with a negative result.", output, Interpriter.calculate(input));
		
		input = "10 - +20";
		assertEquals("Test subtraction with a '+' operator, eg. '10 - +20'.", output, Interpriter.calculate(input));
		
		input = "-10 - -20";
		output = "10.000";
		assertEquals("Test subtraction between two negative numbers.", output, Interpriter.calculate(input));
		
		input = "-24.5 - 234.5 - -1000";
		output = "741.000";
		assertEquals("Test subtraction with more than three negative numbers.", output, Interpriter.calculate(input));
		
	}
	
	// Tests interpreter calculate() multiplication.
	@Test
	public void calculateMultiplication()
	{
		// basic multiplication test
	    String input = "5*5";
	    String output = "25.000";
	    assertEquals("Test with basic multiplication '5*5' ", output, Interpriter.calculate(input));
	    
	    // complex multiplication test
	    input = "5*5 *    2.5556 *   0.7* .1101 *  44444444.4";
	    output = "218844546.448";
	    assertEquals("Test with complex multiplication '5*5 *    2.5556 *   0.7* .1101 *  44444444.4' ", output, Interpriter.calculate(input));

	    input = "5*5*-5*-5";
	    output = "625.000";
	    assertEquals("Test multiplication between negative and positive numbers.", output, Interpriter.calculate(input));
	      
	}
	
	// Tests interpreter calculate() division.
	@Test
	public void calculateDivision()
	{
		// basic division test
	    String input = "5/5";
	    String output = "1.000";
	    assertEquals("Test with basic division '5/5' ", output, Interpriter.calculate(input));

	    // complex division test
	    input = "44444444.4 /    2.5556 /   0.7/ .1101 /  5";
	    output = "45130405.808";
	    assertEquals("Test with complex division '44444444.4 /    2.5556 /   0.7/ .1101 /  5' ", output, Interpriter.calculate(input));
	    
	    input = "300 / 5 * 5 / 100";
	    output = "3.000";
	    assertEquals("Test division and multiplication in the same equation.", output, Interpriter.calculate(input));
	    
	    input = "-300 / -5 * 5 / 100";
	    assertEquals("Test division using negative numbers", output, Interpriter.calculate(input));
	}
	
	// Tests interpreter calculate() powers.
	@Test
	public void calculatePowers()
	{
		// basic power test
	    String input = "5^5";
	    String output = "3125.000";
	    assertEquals("Test with basic power '5^5' ", output, Interpriter.calculate(input));

	    // complex power test
	    input = "44 ^    2.5556 ^ 0.232";
	    output = "9.428";
	    assertEquals("Test with complex power '44 ^    2.5556 ^ 0.232' ", output, Interpriter.calculate(input));
	    
	    input = "44/2^2";
	    output = "11.000";
	    assertEquals("Test powers and division in the same equation.", output, Interpriter.calculate(input));
	    
	    input = "44*2^2";
	    output = "176.000";
	    assertEquals("Test powers and multiplication in the same equation.", output, Interpriter.calculate(input));
	    
	    input = "44*2^2/5^5";
	    output = ".056";
	    assertEquals("Test powers, division and multiplication in the same equation.", output, Interpriter.calculate(input));
	    
	    input = "44*2^-2/5^5";
	    output = ".004";
	    assertEquals("Test powers, division, multiplication and negatives in equation.", output, Interpriter.calculate(input));
	}
	
	// Test interpreter calculate brackets.
	@Test
	public void calculateBracket()
	{
		 // basic bracket test
	    String input = "(5+5) + (4+4)";
	    String output = "18.000";
	    assertEquals("Test with basic bracket '(5+5) + (4+4)' ", output, Interpriter.calculate(input));

	    // complex bracket test
	    input = "(44 ^    2.5556) ^ 0.232";
	    output = "9.428";
	    assertEquals("Test with complex bracket '(44 ^    2.5556) ^ 0.232' ", output, Interpriter.calculate(input));
	    
	    input = "5(-20)";
	    output = "-100.000";
	    assertEquals("Test brackets resolving to multiplication.", output, Interpriter.calculate(input));
	    
	    input = "(5^(2*3)/-25/5^2)+-50--(10)4";
	    output = "-35.000";
	    assertEquals("Test with complex brackets '(5^(2*3)/-25/5^2)+-50--(10)4'.", output, Interpriter.calculate(input));
	}
	
	// Test interpreter with natural log.
	@Test
	public void calculateNaturalLog()
	{
		String input = "log(10)";
		String output = "2.303";
		assertEquals("Test natural log with positive whole number.", output, Interpriter.calculate(input));
		
		input = "log(-10)";
		output = "NaN";
		assertEquals("Test natural log with negative whole number.", output, Interpriter.calculate(input));
		
		// (-((5^(2*3)/-25/5^2)+-50--(10)4)) = 35
		input = "log(-((5^(2*3)/-25/5^2)+-50--(10)4))";
	    output = "3.555";
	    assertEquals("Test with complex natural log 'log(-((5^(2*3)/-25/5^2)+-50--(10)4))'.", output, Interpriter.calculate(input));
	    
	    // The same as the above equation except it's being multiplied by 2 and then being halved, 
	    //  cancelling each other resulting in the same output as above.
	    input = "2log(-((5^(2*3)/-25/5^2)+-50--(10)4))/2";
	    assertEquals("Test with natural log being operated on.", output, Interpriter.calculate(input));
	}
	
	// Test interpreter with natural exponent.
	@Test
	public void calculateNaturalExponent()
	{
		String input = "exp(10)";
		String output = "22026.466";
		assertEquals("Test natural exponent with positive whole number.", output, Interpriter.calculate(input));

		input = "exp(-1)";
		output = ".368";
		assertEquals("Test natural exponent with negative whole number.", output, Interpriter.calculate(input));

		// (-((5^(2*3)/-25/5^2) + 15)) = 10
		input = "exp(-((5^(2*3)/-25/5^2) + 15))";
		output = "22026.466";
		assertEquals("Test with complex natural exponent 'exponent(-((5^(2*3)/-25/5^2)+-50--(10)4))'.", output, Interpriter.calculate(input));

		// The same as the above equation except it's being multiplied by 2 and then being halved, 
		//  cancelling each other resulting in the same output as above.
		input = "2exp(-((5^(2*3)/-25/5^2) + 15))/2";
		assertEquals("Test with natural exponent being operated on.", output, Interpriter.calculate(input));
		
		// Tests for exp and log cancelling each other such that their input is left behind such as:
		// exp(log(x)) = x
		input = "exp(log(10))";
		output = "10.000";
		assertEquals("Test natural log in natural exponent cancel each other.", output, Interpriter.calculate(input));
		
		// Tests for exp and log cancelling each other such that their input is left behind such as:
		// exp(log(x)) = x
		input = "log(exp(10))";
		assertEquals("Test natural exponent in natural log cancel each other.", output, Interpriter.calculate(input));
	
	}
	

}
