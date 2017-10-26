/*import java.math.BigInteger;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        System.out.println("Enter the String to be Encrypted");
        Scanner scan = new Scanner(System.in); 
        String b = scan.nextLine();
        scan.close();
        if(b.length() > 80) {
            System.out.println("Length of String too long");
        }
        System.out.println("Clear text:");
        System.out.println(b);
        System.out.println("Number of clear text bytes = " + b.length());
        
        MerkelHerman mh = new MerkelHerman();
        DoublyLinkedList w = mh.generateSuperincreasingSequence(b.length() * 8);
        BigInteger q = mh.generateQ();
        BigInteger R = mh.generateR(q);
        DoublyLinkedList pk = mh.generatePublicKey(R,q);
        System.out.println(pk.countNodes());
        StringBuilder sb = mh.generateBinary(b);
        BigInteger encrypt = new BigInteger("0");
        for (int i = 0; i <sb.length(); i++) {
            BigInteger x = BigInteger.valueOf((sb.charAt(i)));
            BigInteger y = pk.getNth(i);
            encrypt = encrypt.add(x.multiply(y));
        }
        System.out.println(b + " is encrypted as " + encrypt);
        
        BigInteger decryptNum = encrypt.multiply(R.modInverse(q)).mod(q);
        StringBuilder decryptBin = new StringBuilder();
        for (int j = 0; j < b.length() * 8; j++)
            decryptBin.append(0);
        DoublyLinkedList r = mh.getList();
        int nodes = r.countNodes();
        while (decryptNum.compareTo(BigInteger.valueOf(0)) != 0) {
            BigInteger temp = w.getNth(nodes-1);
            if (decryptNum.compareTo(temp) != -1) {
                
                decryptNum = decryptNum.subtract(temp);
                decryptBin.setCharAt(nodes - 1, '1');
            }
            nodes--;
        }
        System.out.println(decryptBin);

    }

}
*/