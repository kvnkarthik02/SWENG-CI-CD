import java.util.ArrayList;

public class Interpriter {   

    public Interpriter() {
    }

    /**
     * Calculates a given equation.
     * 
     * @param equation String representing an equation.
     * @return result calculated from the equation.
     */
    public static Double calculate(String equation) {
    	
        double answer = 0;
        String[] splitEquation = splitEquation(equation);
        Stack<Double> nums = new Stack<Double>();
        Stack<String> ops = new Stack<String>(); 
        
        // main calculation loop
        for (int i = 0; i < splitEquation.length; i++) {
            String currentString = splitEquation[i];
            if (isOperator(currentString)) { // is an operator so add to stack or do a calculation if possible
                if (ops.isEmpty() || getOperatorPowerLevel(currentString) > getOperatorPowerLevel(ops.safePop())) { // adds current operator to stack if its Precedence is higher
                    ops.push(currentString);
                } else {
                    while (!ops.isEmpty() && getOperatorPowerLevel(currentString) <= getOperatorPowerLevel(ops.safePop())) {
                        String calculate = ops.pop();
                        doACalculation(calculate, ops, nums);
                    }
                    ops.push(currentString);
                }
            } else if (!currentString.equals("(") && !currentString.equals(")")) { // is a value so add to stack
                double value = Double.parseDouble(currentString);
                nums.push(value);
            } else if (currentString.equals("(")) { // if its a bracket then add to stack so it can be calculate below
                ops.push(currentString);
            } else if (currentString.equals(")")) { // performs a calculation on all parts of the values in brackets
                while (!ops.isEmpty() && isOperator(ops.safePop())) {
                    String calculate = ops.pop();
                    doACalculation(calculate, ops, nums);
                }
                if (!ops.isEmpty() && ops.safePop().equals("(")) {
                    ops.pop();
                } else {
                    System.out.println("Error: missing bracket in equation");
                }
            }
        }

        // clean Stacks before getting answer
        while (!ops.isEmpty() && isOperator(ops.safePop())) {
            String calculate = ops.pop();
            doACalculation(calculate, ops, nums);
            System.out.println(calculate);
        }

        // returning result left in value stack
        answer = nums.pop();

        // check for Errors in stacks
        if (!ops.isEmpty() || !nums.isEmpty()) {
            System.out.println("Error: invalid input format (values where left in stacks with no use)");
        }

        return answer;
    }

    /**
     * used for converting the input equation into its values and operators in separate parts for calculation
     * 
     * @param eq String representing an equation.
     * @return equation split into separate parts represented as an array.
     */
    private static String[] splitEquation(String eq) {
        ArrayList<String> splitEq = new ArrayList<String>();

        //remove white spaces from equation
        eq = eq.replaceAll("\\s+","");

		eq = eq.replace("+-","-");
		eq = eq.replace("-+", "-");
		eq = eq.replace("--", "+");
		//Replaces instances of numbers beside brackets with the two number separated with a multiplication operator(*) such as:
		// 5(5) -> 5*(5)
		// (5)5 -> (5) * 5
		for (int number = 0; number <= 9; number++) {
		    userInput = userInput.replace(number + "(", number + "*(");
		    userInput = userInput.replace(")" + number, ")*" + number);
		}		
		//userInput = userInput.replace("-(", "-1*(");

        System.out.println("interpreted as:" + eq);

        //finds where chars in the string change from operators to values and adds them to spliEq once they are found
        int trackerPoint = 0;
        boolean isNegative = false;
        
        for (int i = 0; i < eq.length(); i++) {
        	 if (eq.charAt(i) == '-' 
        			 && ((i != 0 && !Character.isDigit(eq.charAt(i - 1))) || i == 0)
        			 && ((i != 0 && eq.charAt(i - 1) != ')') || i == 0))
        	 {
        		 i++;
        		 isNegative = true;
        	 }
        	 // Adds '(' onto the list.
        	 else if (eq.charAt(i) == '(')
             {
             	trackerPoint++;	// Skip to next char.
             	splitEq.add(Character.toString(eq.charAt(i)));
             }
        	 // If an operator, adds the number previous and adds it to the list.
        	 //  Then adds the operator to the list.
        	 //	If 
        	 else if (isOperator(eq.charAt(i)) || (!isNegative && !isOperator(eq.charAt(i)) && isOperator(eq.charAt(trackerPoint)))
        			 || (eq.charAt(i) == ')' && i != eq.length() - 1)) {
                String val = eq.substring(trackerPoint, i);
                trackerPoint = i;
                if (!val.isEmpty())
                {
                	splitEq.add(val);
                }
                splitEq.add(Character.toString(eq.charAt(i)));
                trackerPoint++;
                isNegative = false;
            }
        }
        
        if (eq.charAt(eq.length() - 1) == ')')
        {
        	String val = eq.substring(trackerPoint, eq.length() - 1);
        	if (!val.isEmpty())
        	{
        		splitEq.add(val);
        	}
            splitEq.add(Character.toString(eq.charAt(eq.length() - 1)));
        }
        
        else if (isOperator(splitEq.get(splitEq.size() - 1))) {
            String val = eq.substring(trackerPoint, eq.length());
            splitEq.add(val);
        }
        

        //convert from arraylist to string array
        String[] splitString = new String[splitEq.size()];
        splitEq.toArray(splitString);

        //System.out.println(splitString[0] + " " + splitString[1] + " " + splitString[2]);

        return splitString;

    }

    /**
     * Checks if the given string is an operator.
     * 
     * @param x String to be checked if an operator.
     * @return True if x is an operator. False, otherwise.
     */
    private static boolean isOperator(String x) {
        if (x.equals("+") || x.equals("-") || x.equals("*") || x.equals("/") || x.equals("^") || x.equals("exp") || x.equals("e") || x.equals("log") || x.equals("l")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if the given character is an operator.
     * 
     * @param x character to be checked if an operator.
     * @return True if x is an operator. False, otherwise.
     */
    private static boolean isOperator(char x) {
        if (x == '+' || x == '-' || x == '*' || x == '/' || x == '^') {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Takes an equation and determines if it's a valid equation.
     * 
     * @param equation String representing the equation.
     * @return True if the equation is valid. False, otherwise.
     */
    public static boolean isValidEquation(String equation)
    {
    	int openBracketsCount = 0;
    	int closeBracketsCount = 0;
    	int operatorCount = 0;
    	
    	// Accounts for equations such as 
    	//	*1+2+3
    	//	1+2+3*
    	//	)1+2+3
    	//	1+2+3(
    	if ((equation.charAt(0) != '-' && isOperator(equation.charAt(0))) || isOperator(equation.charAt(equation.length() - 1))
    			|| equation.charAt(0) == ')' || equation.charAt(equation.length() - 1) == '(')
		{
			return false;
		}
    	
    	for (int currentChar = 0; currentChar < equation.length(); currentChar++)
    	{
    		if (equation.charAt(currentChar) == '(')
    		{
    			// Accounts for equations where an operator is after an open bracket such as:
    			//	1+(*2+3)
    			if (isOperator(equation.charAt(currentChar + 1)))
    			{
    				return false;
    			}
    			openBracketsCount++;
    		}
    		else if (equation.charAt(currentChar) == ')')
    		{   
    			// Accounts for equations where a close brackets is after an operator such as:
    			//	1+(2+3*)
    			if (isOperator(equation.charAt(currentChar - 1)))
    			{
    				return false;
    			}
    			closeBracketsCount++;
    		}
    		// Accounts for decimal points.
    		else if (equation.charAt(currentChar) == '.')
    		{
    			// Return true if there's a number after the decimal point such as:
    			//	.1
    			if (currentChar < equation.length() - 1 && Character.isDigit(equation.charAt(currentChar + 1)))
    			{
    				return true;
    			}
    			// Return true if there's a number before the decimal point such as:
    			//	2.
    			else if (Character.isDigit(equation.charAt(currentChar - 1)))
    			{
    				return true;
    			}
    			// Otherwie, return false.
    			return false;
    		}
    		// Accounts for equations that contain non-operator and non-digit characters.
    		else if (!isOperator(equation.charAt(currentChar)) && !Character.isDigit(equation.charAt(currentChar)))
    		{
    			return false;
    		}
    		// Account for equations with two operators in a row.
    		else if (currentChar < equation.length() - 1 && isOperator(equation.charAt(currentChar)) 
    				&& equation.charAt(currentChar) == equation.charAt(currentChar + 1))
    		{
    			System.out.println("There are duplicate operators that cannot be resolved.");
    			return false;
    		}
    		else if (isOperator(equation.charAt(currentChar)) && currentChar != 0)
    		{
    			operatorCount++;
    		}
    		
    	}
    	
    	// Accounts for unpaired brackets such as:
    	//	1+(2+3
    	//	1+2+3)
    	if (openBracketsCount != closeBracketsCount)
		{
			return false;
		}
    	
    	// Accounts for sole numbers such as:
    	//	1
    	if (operatorCount == 0)
    	{
    		System.out.println("Please enter an equation with operators.");
    		return false;
    	}
    	 
    	return true;
    }

    /**
     * Resolves precedence levels for operators, 
     * i.e. Resolution of operators are as follows: 
     * 	exponent, multiplication or division, then addition or subtraction.
     * 
     * @param x String representing an operator.
     * @return the precedence level of the given operator.
     */
    private static int getOperatorPowerLevel(String x) {
        if (x.equals("+") || x.equals("-")) {
            return 1;
        } else if (x.equals("*") || x.equals("/")) {
            return 2;
        } else if (x.equals("^") || x.equals("e") || x.equals("l")) {
            return 3;
        }
        return 0;
    }

    /**
     * Resolves a calculator given an operator and the numbers involved.
     * 
     * @param operator String current operator
     * @param ops Stack<String> of operators
     * @param nums Stack<Double> of numbers to be used in calculations.
     */
    private static void doACalculation(String operator, Stack<String> ops, Stack<Double> nums) {
        double val1, val2;

        if (nums.isEmpty()) {
            System.out.println("Error in input");
            return;
        } else {
            val2 = nums.pop();
        }

        if (nums.isEmpty() && !operator.equals("e") && !operator.equals("l")) {
            System.out.println("Error in input");
            return;
        } else if (!operator.equals("e") && !operator.equals("l")) {
            val1 = nums.pop();
        } else {
            System.out.println("Error in input");
            return;
        }

        double output = 0;

        if (operator.equals("+")) {
            output = val1 + val2;
        } else if (operator.equals("-")) {
            output = val1 - val2;
        } else if (operator.equals("*")) {  
            output = val1 * val2;
        } else if (operator.equals("/")) {
            output = val1 / val2;
        } else if (operator.equals("^")) {
            output = Math.pow(val1, val2);
        } else if (operator.equals("e")) {
            output = Math.exp(val2);
        } else if (operator.equals("l")) {
            output = Math.log(val2);
        }

        //System.out.println("Val1: " + val1 + ", Val2: " + val2 + ", Output: " + output);

        nums.push(output);
    }
}
