import java.util.LinkedList;


public class BruteForce {
	private int len;
	private double[][] matrix;

	/**
	 * Constructor
	 * 
	 * @param len
	 *            length of the data
	 * @param matrix
	 *            the adjacent matrix
	 */
	public BruteForce(int len, double[][] matrix) {
		this.len = len;
		this.matrix = matrix;
	}

	/**
	 * using iteration to get all permutations of the paths, which is V!, using
	 * linked list
	 * 
	 * @return all permutations of the paths
	 */
	public LinkedList<LinkedList<Integer>> permute() {
		int[] num = new int[len];
		for (int i = 0; i < len; i++) {
			num[i] = i;
		}

		LinkedList<LinkedList<Integer>> result = new LinkedList<LinkedList<Integer>>();

		// start from an empty list
		result.add(new LinkedList<Integer>());

		for (int i = 0; i < num.length; i++) {
			// list of list in current iteration of the array num
			LinkedList<LinkedList<Integer>> current = new LinkedList<LinkedList<Integer>>();

			for (LinkedList<Integer> l : result) {
				// # of locations to insert is largest index + 1
				for (int j = 0; j < l.size() + 1; j++) {
					// + add num[i] to different locations
					l.add(j, num[i]);

					LinkedList<Integer> temp = new LinkedList<Integer>(l);
					current.add(temp);
					l.remove(j);
				}
			}

			result = new LinkedList<LinkedList<Integer>>(current);
		}

		for (LinkedList<Integer> ll : result) {
			if (ll.size() > 0)
				ll.add(ll.get(0));
		}

		return result;
	}

	/**
	 * find the optimal path from all paths, which has the minimum length
	 * 
	 * @param result
	 *            all permutations of the paths
	 * @return the optimal path of linked list
	 */
	public LinkedList<Integer> findOptimal(
			LinkedList<LinkedList<Integer>> result) {
		LinkedList<Integer> optimal = null;
		double min = Double.MAX_VALUE;
		for (LinkedList<Integer> ll : result) {
			if (computeLength(ll) < min) {
				optimal = ll;
				min = computeLength(ll);
			}
		}
		return optimal;
	}

	/**
	 * compute the length of optimal path
	 * 
	 * @param list
	 *            the linked list of the optimal path
	 * @return the double value of the length
	 */
	public double computeLength(LinkedList<Integer> list) {
		double length = 0;
		int from, to;
		for (int i = 0; i < len; i++) {
			from = list.get(i);
			to = list.get(i + 1);
			length += matrix[from][to];
		}
		return length;
	}

	/**
	 * print out the path of a list, which should be the optimal path
	 * 
	 * @param list
	 *            the linked list to print
	 */
	public void printPath(LinkedList<Integer> list) {
		for (int i = 0; i < list.size() - 1; i++) {
			System.out.print(list.get(i) + ", ");
		}
		System.out.println(list.get(list.size() - 1));
	}

	/**
	 * get the array version of the optimal path
	 * 
	 * @param optimal
	 *            linked list of the optimal path
	 * @return the array version of the optimal path
	 */
	public int[] getArrayOptimal(LinkedList<Integer> optimal) {
		int[] optimlTour = new int[len+1];
		for (int i = 0; i < optimal.size(); i++) {
			optimlTour[i] = optimal.get(i);
		}
		return optimlTour;
	}
}
