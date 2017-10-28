
public class Prim {
	private int len;
	private double[][] matrix;
	private Node[] mst;
	private boolean[][] tree;
	private int[] preOrderWalk;
	private int walkCount;

	/**
	 * this nexted class is to hold data for one node of the mst, including
	 * parent index, weight of the node with its parent node, and if it is
	 * visited
	 * 
	 * @author zhaoru
	 *
	 */
	private class Node {
		private int pIndex;
		private double weight;
		private boolean visited;

		/**
		 * Constructor
		 */
		public Node() {
			weight = Double.MAX_VALUE;
			visited = false;
		}

		/**
		 * get parent node's index
		 * 
		 * @return parent node's index
		 */
		public int getpIndex() {
			return pIndex;
		}

		/**
		 * set parent node's index
		 * 
		 * @param pIndex
		 *            parent node's index
		 */
		public void setpIndex(int pIndex) {
			this.pIndex = pIndex;
		}

		/**
		 * get the weight of the node with its parent node
		 * 
		 * @return the weight of the node with its parent node
		 */
		public double getWeight() {
			return weight;
		}

		/**
		 * set the weight of the node with its parent node
		 * 
		 * @param weight
		 *            the weight of the node with its parent node
		 */
		public void setWeight(double weight) {
			this.weight = weight;
		}

		/**
		 * if the node is visited
		 * 
		 * @return true or false
		 */
		public boolean isVisited() {
			return visited;
		}

		/**
		 * set if the node is visited
		 * 
		 * @param visited
		 *            true or false
		 */
		public void setVisited(boolean visited) {
			this.visited = visited;
		}
	}

	/**
	 * Constructor
	 * 
	 * @param len
	 *            length of the data
	 * @param matrix
	 *            the adjacent matrix builder from the builder
	 */
	public Prim(int len, double[][] matrix) {
		this.len = len;
		this.matrix = matrix;
		tree = new boolean[len][len];
		preOrderWalk = new int[len + 1];
		walkCount = 0;

		mst = new Node[len];
		for (int i = 0; i < len; i++) {
			mst[i] = new Node();
		}

		mst[0].setWeight(0);
	}

	/**
	 * run the prim algorithm to build the mst
	 */
	public void run() {
		while (!isEmpty()) {
			// extract Min
			Node min = null;
			int minIndex = 0;
			for (int j = 0; j < len; j++) {
				if (!mst[j].isVisited()) {
					min = mst[j];
					minIndex = j;
					break;
				}
			}
			for (int i = 0; i < len; i++) {
				if (!mst[i].isVisited() && min.getWeight() > mst[i].getWeight()) {
					min = mst[i];
					minIndex = i;
				}
			}
			min.setVisited(true);

			// update
			for (int k = 0; k < len; k++) {
				if (!mst[k].isVisited()
						&& matrix[minIndex][k] < mst[k].getWeight()) {
					mst[k].setpIndex(minIndex);
					mst[k].setWeight(matrix[minIndex][k]);
				}
			}
		}
	}

	/**
	 * generate the pre order walking tree using the mst
	 */
	public void generateTree() {
		for (int a = 0; a < len; a++) {
			for (int b = 0; b < len; b++) {
				tree[a][b] = false;
			}
		}

		for (int i = 1; i < len; i++) {
			int j = mst[i].getpIndex();
			tree[j][i] = true;
		}
	}

	/**
	 * helper method to print out the walk path recursively
	 * 
	 * @param x
	 *            index of node of the path
	 */
	private void printWalk(int x) {
		walkCount++;
		preOrderWalk[walkCount] = x;
		for (int i = 0; i < len; i++) {
			if (tree[x][i] != false)
				printWalk(i);
		}

	}

	/**
	 * print out the walk path recursively
	 */
	public void printPreOrderWalk() {
		preOrderWalk[walkCount] = 0;
		preOrderWalk[len] = 0;
		for (int i = 0; i < len; i++) {
			if (tree[0][i] != false)
				printWalk(i);
		}
	}

	/**
	 * compute the length of the pre order walking path
	 * 
	 * @return the length of the cycle
	 */
	public double computeLength() {
		double length = 0;
		int from, to;
		for (int i = 0; i < len; i++) {
			from = preOrderWalk[i];
			to = preOrderWalk[i + 1];
			length += matrix[from][to];
		}
		return length;
	}

	/**
	 * get the pre order walk path by prim algorithm, which is not potimal
	 * 
	 * @return the pre order walk path
	 */
	public int[] getPrimPath() {
		return preOrderWalk;
	}

	/**
	 * check if all nodes of the mst are visited
	 * 
	 * @return true or false
	 */
	private boolean isEmpty() {
		for (Node n : mst) {
			if (!n.isVisited()) {
				return false;
			}
		}
		return true;
	}
}
