package com.webCalc.springboot.demo;

import java.util.Scanner;

public class CalculatorInput
{
    // Set scanner to a universal variable so it can be used by all functions.
    private static Scanner input;


    /**
     * This program takes in an equation from the user and calculates the result.
     */
    public static void main(String[] args)
    {
        // Have dummy character array here so it enters the while loop.
        String equation = "";

        // As long as the user's input is not 'exit', then loop indefinitely.
        while (equation != null)
        {
            equation = askInput();

            if (equation != null)
            {
                if (Interpriter.isValidEquation(equation))
                {
                    //Call function here to resolve equation.
                    System.out.println("Answer: " + Interpriter.calculate(equation));
                }
                else System.out.println("Please input a valid equation.");
            }
        }

        // Closes scanner input.
        input.close();
    }

    /**
     * Asks equation input from user to be calculated.
     *
     * @return string to be used to resolve calculations.
     */
    public static String askInput()
    {
        boolean exit = false;
        String userInput = null;

        while(!exit)
        {
            // Creates scanner for user input.
            input = new Scanner(System.in);
            System.out.println("Please enter an equation for the calculator. You may use addition(+), subtraction(-), multiplication(*), division(/) and exponentials(^) or type 'exit' to leave.");
            if (input.hasNextLine())
            {
                userInput = input.nextLine();
                if (userInput.equalsIgnoreCase("exit"))
                {
                    exit = true;
                    System.out.println("You have exited the program");
                }
                else
                {
                    exit = true;
                    return userInput;
                    // userInput = answer.replaceAll("\\s+","");
                    // userInput = userInput.replace("+-","-");
                    // userInput = userInput.replace("-+", "-");
                    // userInput = userInput.replace("--", "+");
                    // //Replaces instances of numbers beside brackets with the two number separated with a multiplication operator(*) such as:
                    // // 5(5) -> 5*(5)
                    // // (5)5 -> (5) * 5
                    // for (int number = 0; number <= 9; number++)
                    // {
                    // 	userInput = userInput.replace(number + "(", number + "*(");
                    // 	userInput = userInput.replace(")" + number, ")*" + number);
                    // }

                    //userInput = userInput.replace("-(", "-1*(");
                }
            }
        }
        return userInput;
    }

}
