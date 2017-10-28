import java.math.BigInteger;

public class DoubleNode {
    BigInteger item;
    DoubleNode next;
    DoubleNode previous;
    

    /**
     * Constructor with no arguments. Assign null values to previous, next and
     * the null character to c.
     */
    public DoubleNode() {
        next = null;
        previous = null;
        item = null;
    }

    

    public DoubleNode(DoubleNode next, BigInteger bi) {
        this.next = next;
        this.item = bi;
    }



    public BigInteger getC() {
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
   
    public void setC(BigInteger c) {
        this.item = c;
    }

    public void setNext(DoubleNode next) {
        this.next = next;
    }

    public void setPrev(DoubleNode prev) {
        this.previous = prev;
    }

}