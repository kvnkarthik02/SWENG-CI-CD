public class StackVar<T> {
    private T item;
    public StackVar<T> nextVar;

    /**
     * Current item of stack.
     * 
     * @param var Item of stack.
     */
    public StackVar(T var) {
        item = var;
    }

    /**
     * Gets the current item of stack.
     * 
     * @return current item of stack.
     */
    T getVar() {
        return item;
    }

    /**
     * 
     * @return
     */
    StackVar<T> nextVar() {
        return nextVar;
    }
}
