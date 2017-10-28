
public class Location {
	private double lat, lon;

	/**
	 * Constructor
	 * 
	 * @param lat
	 *            latitude of a node
	 * @param lon
	 *            longitude of a node
	 */
	public Location(double lat, double lon) {
		this.lat = lat;
		this.lon = lon;
	}

	/**
	 * get the latitude
	 * 
	 * @return latitude
	 */
	public double getLat() {
		return lat;
	}

	/**
	 * get the longitude
	 * 
	 * @return longitude
	 */
	public double getLon() {
		return lon;
	}
}
