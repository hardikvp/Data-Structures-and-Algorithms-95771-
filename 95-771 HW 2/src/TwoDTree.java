import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class TwoDTree {
    private Node root;
    private ArrayList<Point2D> a = new ArrayList<Point2D>();
    private ArrayList<String[]> b = new ArrayList<String[]>();
    private String str;
    
    private class Node {
        private Node left, right;
        private Point2D key;
        private String [] val;
        private int o;
        private int count = 0;
        
        public Node(Point2D p, String[] str, int orientation) {
            this.key = p;
            this.val = str;
            this.o = orientation;
            this.left = null;
            this.right = null;
        }
    }
   
    public TwoDTree(String crimeDataLocation) throws IOException {
        this.str = crimeDataLocation;
        if (str !=null) {
            FileReader fr = new FileReader(str);
            CSVReader c = new CSVReader(fr);
            boolean eof = false;
            int count = 0;
            while (!eof) {
                if (count == 0) {
                    count += 1;
                } else {
                    String[] values = c.readCSVLine();
                    if (values == null) {
                        eof = true;
                    } else {
                        Point2D p = new Point2D(Double.parseDouble(values[0]),Double.parseDouble(values[1]));
                        this.insert(p, values);
                        }
                    }
                }
            c.close();
            }
            
        }
    
    public void insert(Point2D p, String[] values) {
        root = insert (this.root,p, values,0);
    }
    private Node insert(Node x,Point2D p, String[] values, int orientation) {

        if(x == null) {
            a.add(p);
            b.add(values);
            return new Node (p,values,orientation);
        } else {
            if(orientation == 0) {
                if(p.x() < x.key.x()) {
                    orientation = orientation - 1;
                   x.left = insert(x.left,p,values,orientation);
                } else {
                    orientation = orientation - 1;
                    x.right = insert(x.right,p,values,orientation);
                }
            } else {
                if(p.y() < x.key.y()) {
                    orientation = orientation - 1;
                    x.left = insert(x.left,p, values, orientation);
                } else {
                    orientation = orientation - 1;
                    x.right = insert(x.right,p,values, orientation);
            }    
        }
            
     }
        x.count = 1 + size(x.left) + size(x.right);
        return x;
        
    }

    public boolean isEmpty() {
        return root == null;// is the set empty? 
    }
    public int size() { 
        return size(root); 
        }
    private int size(Node x) {
        if (x == null) 
            return 0;
        return x.count;
    }
    
    public boolean contains(Point2D p) {
        Node x = root;
        while (x != null) {
            if(x.o == 0) {
                if(p.x() < x.key.x()) {
                    x.o = x.o - 1;  
                    x = x.left;
                    } else if (p.x() > x.key.x()) {
                    x.o = x.o - 1;
                    x = x.right;
                    } else if (p.x() == x.key.x()) {
                    return true;
                    } else {
                        if(p.y() < x.key.y()) {
                            x.o = x.o - 1;  
                            x = x.left;
                            } else if (p.x() > x.key.x()) {
                            x.o = x.o - 1;
                            x = x.right;
                            } else if (p.x() == x.key.x()) {
                            return true;
                            }
                        }
                }
            }
     return false;
    }
  
    public void preOrderPrint() {
        printPreorder(root);
    }
    
    private void printPreorder(Node x) {
        if (x == null)
            return;
        /* first print data of node */
        for (int i = 0; i < x.val.length; i ++) {
        System.out.println(x.val[i] + " ");
        }
        /* then recur on left sutree */
        printPreorder(x.left);
 
        /* now recur on right subtree */
        printPreorder(x.right);
    }

    public void inOrderPrint() {
        Queue<String[]> q = new LinkedList<String[]>();
        q = inorder(root, q);
    }
    private Queue<String[]> inorder(Node x, Queue<String[]> q) {
        if (x == null) return null;
        inorder(x.left, q);
        q.add(x.val);
        inorder(x.right, q);
        return q;
    }
    public void postOrderPrint() {
        printPostorder(root); 
    }
    
    private void printPostorder(Node node) {
        if (node == null)
            return;
        // first recur on left subtree
        printPostorder(node.left);
        // then recur on right subtree
        printPostorder(node.right);
        // now deal with the node
        for (int i = 0; i < node.val.length; i ++) {
            System.out.println(node.val[i] + " ");
            }
    }

    public ListOfCrimes findPointsInRange(RectHV rect) {
        ListOfCrimes c = new ListOfCrimes();
        for (Point2D item : a) {
            if (rect.contains(item)) {
                int z = a.indexOf(item);
                c.add(b.get(z));  // all points that are inside the rectangle 
                }
        }
        c.toString(c);
        return c;
    }
    
    public Point2D nearestNeighbor(Point2D p) {
        ArrayList<Double> l = new ArrayList<Double>();
        ArrayList<Point2D> m = new ArrayList<Point2D>();
        ArrayList<String[]> y = new ArrayList<String[]>();
        if(a == null) {
            return null;// a nearest neighbor in the set to point p; null if the set is empty 
        } else {
            for (Point2D item : a) {
                l.add(p.distanceTo(item));
                m.add(item);
                y.add(b.get(a.indexOf(item)));
            }
         double min = l.get(0);
         Point2D min1 = m.get(0);
         for(int i = 1 ;i < l.size(); i++) {
             if(min > l.get(i)) {
                 min = l.get(i);
                 min1 = m.get(i);
             }
         }
         return min1;
         }  
    }
}