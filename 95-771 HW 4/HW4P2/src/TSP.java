import java.util.LinkedList;
import java.util.Scanner;


public class TSP {

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

			System.out.println("Crime Records Processed:");
			System.out.println();
			Builder builder = new Builder(start, end);
			builder.build();
			System.out.println();

			BruteForce bf = new BruteForce(builder.getLen(),
					builder.getMatrix());
			LinkedList<LinkedList<Integer>> result = bf.permute();
			LinkedList<Integer> optimal = bf.findOptimal(result);
			double length = bf.computeLength(optimal);

			System.out.print("Hamiltonan Cycle (minimal): ");
			bf.printPath(optimal);

			System.out.print("Length of Cycle: ");
			double mile = length / 5280;
			System.out.println(mile + " miles");
		} catch (NumberFormatException e) {
			System.out.println("Start or End must be integers");
			return;
		}

	}
}
