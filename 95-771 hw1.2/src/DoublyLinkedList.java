import java.math.BigInteger;


public class DoublyLinkedList {
    private DoubleNode first,last;
    
    public DoublyLinkedList() {
        this.first = null;
        this.last = null;
    }

    public void deleteChar(BigInteger c) {
        DoubleNode temp;
        for(temp = first; temp != null ; temp= temp.getNext()) {
            if(temp.getC() == c) {
                if (temp == first) {
                    first = temp.getNext();
                } else if(temp == last){
                    temp.getPrev().setNext(null);
                } else {
                    temp.getPrev().setNext(temp.getNext());   
                }
                return;
            }
        }
        System.out.println("Not Found");
        return;
    }

    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return first==null;
    }

    public BigInteger removeCharAtEnd() {
        DoubleNode T = first;
        if(T==null) {
            throw new NullPointerException("List Empty");
        } else {
            while(T.getNext()!=null) {
            T=T.getNext(); 
        }
            if(T.getPrev()==null) {
            first = null;
            return T.getC();
        } else {
            T.getPrev().setNext(null);
            return T.getC();
        }
        }
        
        
    }

    public int countNodes() {
        int count = 0;
        DoubleNode point = first;
        while (point!= null) {
            point = point.getNext();
            count++;
        }
        return count; // tail is not null
    }

    public void reverse() {
        DoubleNode temp = first;
        for(last = first; last.getNext() != null;) {
            last = last.getNext();
        }
        first = last;
        last = temp;
        DoubleNode p = first;
        while (p!= null) {
            temp = p.getNext();
            p.setNext(p.getPrev());
            p.setPrev(temp);
            p = p.getNext();
        }
    }

    public void addCharAtEnd(BigInteger c) {
        DoubleNode temp = first;
        DoubleNode m = new DoubleNode();
        if(first == null){
            m.setC(c);
            first = m;
            m.setNext(null);
        } else{
            while(temp.getNext() != null){
                temp = temp.getNext();            
            }
            temp.setNext(m);
            m.setC(c);
            m.setPrev(temp);
            m.setNext(null);
            }
        }
        
    public String toString(){
        String ret = "";
        DoubleNode cur = first;
        while(cur != null){
            ret += cur.item;
            cur = cur.next;
        }
        return ret;
    }

    public BigInteger getNth(int n) {
        if (isEmpty())
            return null;
        if (n > countNodes())
            return null;
        DoubleNode point = first;
        for (int i = 0; i < n; i++) {
            point = point.getNext();
        }
        return point.getC();
        
    }

}
