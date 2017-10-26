import java.io.*;


public class KMLWriter {
	private static final String FILE_NAME = "PGHCrimes.kml";
	private Location[] nonOptimalTourLocation, optimalTourLocation;

	/**
	 * Constructor
	 * 
	 * @param nonOptimalTour
	 *            nonOptimalTour
	 * @param optimalTour
	 *            optimalTour
	 * @param location
	 *            location array holding the location of the two paths
	 */
	public KMLWriter(int[] nonOptimalTour, int[] optimalTour,
			Location[] location) {
		nonOptimalTourLocation = new Location[nonOptimalTour.length];
		optimalTourLocation = new Location[optimalTour.length];

		for (int i = 0; i < nonOptimalTour.length; i++) {
			nonOptimalTourLocation[i] = location[nonOptimalTour[i]];
		}
		for (int j = 0; j < optimalTour.length; j++) {
			optimalTourLocation[j] = location[optimalTour[j]];
		}
	}

	/**
	 * method to write the kml file
	 */
	public void write() {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME));

			bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
			bw.write("<kml xmlns=\"http://earth.google.com/kml/2.2\">\n");
			bw.write("<Document>\n");
			bw.write("<name>Pittsburgh TSP</name><description>TSP on Crime</description><Style id=\"style6\">\n");
			bw.write("<LineStyle>\n");
			bw.write("<color>73FF0000</color>\n");
			bw.write("<width>5</width>\n");
			bw.write("</LineStyle>\n");
			bw.write("</Style>\n");
			bw.write("<Style id=\"style5\">\n");
			bw.write("<LineStyle>\n");
			bw.write("<color>507800F0</color>\n");
			bw.write("<width>5</width>\n");
			bw.write("</LineStyle>\n");
			bw.write("</Style>\n");
			bw.write("<Placemark>\n");
			bw.write("<name>TSP Path</name>\n");
			bw.write("<description>TSP Path</description>\n");
			bw.write("<styleUrl>#style6</styleUrl>\n");
			bw.write("<LineString>\n");
			bw.write("<tessellate>1</tessellate>\n");
			bw.write("<coordinates>\n");
			for (Location l1 : nonOptimalTourLocation) {
				bw.write(l1.getLon() + "," + l1.getLat() + "," + 0.000000
						+ "\n");
			}
			bw.write("</coordinates>\n");
			bw.write("</LineString>\n");
			bw.write("</Placemark>\n");
			bw.write("<Placemark>\n");
			bw.write("<name>Optimal Path</name>\n");
			bw.write("<description>Optimal Path</description>\n");
			bw.write("<styleUrl>#style5</styleUrl>\n");
			bw.write("<LineString>\n");
			bw.write("<tessellate>1</tessellate>\n");
			bw.write("<coordinates>\n");
			for (Location l2 : optimalTourLocation) {
				bw.write(l2.getLon() + 0.001 + "," + l2.getLat() + 0.001 + ","
						+ 0.000000 + "\n");
			}
			bw.write("</coordinates>\n");
			bw.write("</LineString>\n");
			bw.write("</Placemark>\n");
			bw.write("</Document>\n");
			bw.write("</kml>\n");

			bw.close();
		} catch (IOException e) {
			System.out.println("Writing file error.");
			return;
		}
	}
}
