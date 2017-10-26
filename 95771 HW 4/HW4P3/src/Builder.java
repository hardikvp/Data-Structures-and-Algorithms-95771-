import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;


public class Builder {
	private static final String FILE_NAME = "src/CrimeLatLonXY1990.csv.txt";
	private int start, end, len;
	private Coordinate[] graph;
	private Location[] location;
	private double[][] matrix;

	/**
	 * This nested class is to hold information of a coordinate
	 * 
	 * @author zhaoru
	 *
	 */
	private class Coordinate {
		private double x, y;

		/**
		 * Constructor
		 * 
		 * @param x
		 *            x coordinate
		 * @param y
		 *            y coordinate
		 */
		public Coordinate(double x, double y) {
			this.x = x;
			this.y = y;
		}

		/**
		 * get x coordinate
		 * 
		 * @return x
		 */
		public double getX() {
			return x;
		}

		/**
		 * get y coordinate
		 * 
		 * @return y
		 */
		public double getY() {
			return y;
		}
	}

	/**
	 * Constructor
	 * 
	 * @param newStart
	 *            start index
	 * @param newEnd
	 *            end index
	 */
	public Builder(int newStart, int newEnd) {
		this.start = newStart;
		this.end = newEnd;
		this.len = end - start + 1;
		this.graph = new Coordinate[len];
		this.location = new Location[len];
		this.matrix = new double[len][len];
	}

	/**
	 * build the graph using the information by the file
	 */
	public void build() {
		try {
			BufferedReader in = new BufferedReader(new FileReader(FILE_NAME));
			String line = in.readLine();
			for (int i = 0; i < start + 1; i++) {
				if (line != null) {
					line = in.readLine();
				}
			}
			for (int j = 0; j < len; j++) {
				if (line != null) {
					processLine(line, j);
					line = in.readLine();
				}
			}
			in.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
			return;
		} catch (IOException e) {
			System.out.println("IO error.");
			return;
		}
		buildMatrix();
	}

	/**
	 * helper function to read and process one line
	 * 
	 * @param line
	 *            one line
	 * @param index
	 *            index of the graph according to this line
	 */
	private void processLine(String line, int index) {
		StringTokenizer st;
		String[] element = new String[9];
		int numElms = 0;
		st = new StringTokenizer(line, ",");

		while (st.hasMoreTokens()) {
			element[numElms] = st.nextToken();
			numElms++;
		}
		graph[index] = new Coordinate(Double.parseDouble(element[0]),
				Double.parseDouble(element[1]));
		location[index] = new Location(Double.parseDouble(element[7]),
				Double.parseDouble(element[8]));
	}

	/**
	 * build the matrix using the graph
	 */
	private void buildMatrix() {
		double wid, hei;
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				wid = graph[j].getX() - graph[i].getX();
				hei = graph[j].getY() - graph[i].getY();
				matrix[i][j] = Math.sqrt(Math.pow(wid, 2) + Math.pow(hei, 2));
			}
		}
	}

	/**
	 * get length of the data
	 * 
	 * @return length of the data
	 */
	public int getLen() {
		return len;
	}

	/**
	 * get the adjacent matrix
	 * 
	 * @return adjacent matrix
	 */
	public double[][] getMatrix() {
		return matrix;
	}

	/**
	 * get the location array
	 * 
	 * @return the location array
	 */
	public Location[] getLocation() {
		return location;
	}
}
