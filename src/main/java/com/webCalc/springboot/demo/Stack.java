package com.webCalc.springboot.demo;


public class Stack<T> {
    public StackVar<T> topOfStack;

    public Stack() {
    }

    /**
     * Stores an item on top of the stack.
     * 
     * @param item to be pushed on top of the stack.
     */
    public void push(T item) {
        StackVar<T> newTop = new StackVar<T>(item);
        newTop.nextVar = topOfStack;
        topOfStack = newTop;
    }

    /**
     * Looks at the top of the stack without removing the item.
     * 
     * @return item at the top of the stack.
     */
    public T safePop() {
        return topOfStack.getVar();
    }

    /**
     * Returns and removes the item at the top of the stack.
     * 
     * @return the item at the top of the stack.
     */
    public T pop() {
        T val = topOfStack.getVar();
        topOfStack = topOfStack.nextVar;
        return val;
    }

    /**
     * Checks if the stack is empty.
     * 
     * @return True if stack is empty. False, otherwise.
     */
    public boolean isEmpty() {
        if (topOfStack == null) {
            return true;
        }
        return false;
    }
}
