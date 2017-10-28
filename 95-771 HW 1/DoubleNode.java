public class DoubleNode {
    char item;
    DoubleNode next;
    DoubleNode previous;
    

    /**
     * Constructor with no arguments. Assign null values to previous, next and
     * the null character to c.
     */
    public DoubleNode() {
        next = null;
        previous = null;
    }

    

    public char getC() {
        return this.item;
    }

    public DoubleNode getNext() {
        return this.next;
    }

    public DoubleNode getPrev() {
        return this.previous;
    }

    /**
     * Test driver for DoubleNode
     */
   
    public void setC(char c) {
        this.item = c;
    }

    public void setNext(DoubleNode next) {
        this.next = next;
    }

    public void setPrev(DoubleNode prev) {
        this.previous = prev;
    }

}