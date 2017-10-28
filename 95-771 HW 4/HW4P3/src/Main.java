import java.util.LinkedList;
import java.util.Scanner;


public class Main {

	/**
	 * main method
	 * 
	 * @param args
	 *            arguments
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter start index:");
		String startString = scanner.nextLine();
		System.out.println("Enter end index:");
		String endString = scanner.nextLine();

		scanner.close();

		try {
			int start = Integer.parseInt(startString);
			int end = Integer.parseInt(endString);

			Builder builder = new Builder(start, end);
			builder.build();
			Location[] location = builder.getLocation();

			Prim prim = new Prim(builder.getLen(), builder.getMatrix());
			prim.run();
			prim.generateTree();
			prim.printPreOrderWalk();
			int[] nonOptimalTour = prim.getPrimPath();
			
			BruteForce bf = new BruteForce(builder.getLen(),
					builder.getMatrix());
			LinkedList<LinkedList<Integer>> result = bf.permute();
			LinkedList<Integer> optimal = bf.findOptimal(result);
			int[] optimalTour = bf.getArrayOptimal(optimal);
			
			KMLWriter kmlWriter = new KMLWriter(nonOptimalTour, optimalTour, location);
			kmlWriter.write();
		} catch (NumberFormatException e) {
			System.out.println("Start or End must be integers");
			return;
		}

	}
}
