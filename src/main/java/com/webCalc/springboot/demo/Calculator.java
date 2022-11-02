package com.webCalc.springboot.demo;

public class Calculator extends Interpriter{
    private String input;
    private String answer;
    
    public String getInput(){
        return input;
    }

    public void setInput(String input){
        this.input = input;
    }

    public String getAnswer(){
        answer = calculate(input);
        return answer;
    }

}
