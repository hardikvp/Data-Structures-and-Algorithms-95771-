import java.util.Scanner;


public class TSP {

	/**
	 * main method
	 * 
	 * @param args
	 * arguments
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

			Prim prim = new Prim(builder.getLen(), builder.getMatrix());
			prim.run();
			prim.generateTree();
			System.out.print("Hamiltonan Cycle (not necessarily optimum): ");
			prim.printPreOrderWalk();

			System.out.print("Length of Cycle: ");
			double length = prim.computeLength();
			// foot to mile
			double mile = length / 5280;
			System.out.println(String.format("%.2f", mile) + " miles");
		} catch (NumberFormatException e) {
			System.out.println("Start or End must be integers");
			return;
		}

	}
}
