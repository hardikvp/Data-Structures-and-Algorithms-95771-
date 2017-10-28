import java.math.BigInteger;
import java.util.Random;

public class MerkelHerman {
    private DoublyLinkedList list = new DoublyLinkedList();
    private DoublyLinkedList l = new DoublyLinkedList();
    static BigInteger sum = new BigInteger("0");
    
    public MerkelHerman() {
        
    }
    public DoublyLinkedList getList() {
        return this.list;
    }

    public StringBuilder generateBinary(String s) {
        StringBuilder sb = new StringBuilder();
        byte[] bytes = s.getBytes();
        for (byte b : bytes) {
            String bs = Integer.toBinaryString(0x100 | b).substring(1);
            sb.append(bs);
        }  
        return sb;
    }
    public DoublyLinkedList generateSuperincreasingSequence(int n) {
        Random randomGenerator = new Random();
        for(int i = 0; i < n; i++) {
            BigInteger temp = new BigInteger(String.valueOf(randomGenerator.nextInt()));
            temp = temp.abs().add(sum);
            list.addCharAtEnd(temp); 
            sum = sum.add(temp);
        }
        
        return list;
    }
    public BigInteger generateQ() {
        Random randomGenerator = new Random();
        BigInteger temp = new BigInteger(String.valueOf(randomGenerator.nextInt()));
        BigInteger c = (temp).abs();
        BigInteger o = sum.add(c);
        return o;
    }
    public BigInteger generateR(BigInteger q) {
        Random random = new Random();
        BigInteger r = BigInteger.valueOf((int) (random.nextDouble() * (q.longValue() - 1)) + 1); // consider nextint() could yield// q.intValue() to negative
        if (r.compareTo(BigInteger.valueOf(0)) != 1)
            r = r.abs();
        while (!r.gcd(q).equals(BigInteger.valueOf(1))) {
            r = new BigInteger(String.valueOf((int) (random.nextDouble() * (q.intValue() - 1)) + 1));
            if (r.compareTo(BigInteger.valueOf(0)) != 1)
                r = r.abs();
        }
        return r;
    }
    public DoublyLinkedList generatePublicKey(BigInteger a, BigInteger o) {
        int num = list.countNodes();
        for (int i = 0; i < num; i++) { 
            l.addCharAtEnd(list.getNth(i).multiply(a).mod(o));
        }
        return l;
    }
    
}
