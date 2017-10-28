
public class ListOfCrimes {
    private Node head = null;
    
    private class Node {
        private Node next;
        private String [] item;
        
        public Node(String[] i) {
            this.item = i;
            this.next = null;
        }
    }
    public ListOfCrimes () {  
    }
    
    public void add(String[] s) {
        head = add(head, s);
    }
    private Node add(Node x, String[] s1) {
        if(x == null) {
            Node n = new Node (s1);
            return n;
        } else {
          while(x.next != null) {
              x = x.next;
          }
          x.next = new Node(s1);
        }
        return head;
    }
    
    public String[] get(int i) {
        Node t = head;
        int x = 0;
        while (x != i) {
            t = t.next;
        }
        return t.item;
    }
    
    public int size() {
        return size(head);
        }
    /**
     * Checks the size of the list.
     * @param x Node to be checked
     * @return the size of the list
     */
    private int size(Node x) {
        if (x == null) {
            return 0;
        }
        return 1 + size(x.next);
    }
    
    public void toString(ListOfCrimes l) { 
        for(int i = 0; i < l.size();i++) {
            String[] strings = l.get(i);
            for (int j = 0; j < strings.length; j++) {
                System.out.print(strings[j] + " ");
            }
            System.out.println();
        }
        
    }

}
