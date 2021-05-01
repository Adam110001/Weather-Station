package ac.uk.napier.set07110Object;

public class WeatherReading {
	private int year;
	private int month;
	private int day;
	private int hour;
	private double wind;
	private double temp;

	public WeatherReading (int newYear, int newMonth, int newDay, int newHour, double newWind, double newTemp) {
		this.year = newYear;
		this.month = newMonth;
		this.day = newDay;
		this.hour = newHour;
		this.wind = newWind;
		this.temp = newTemp; 
	}
	
	/**
	 * Returns year as integer
	 * @return year
	 */
	public int getYear() {
		return year;
	}
	
	/**
	 * Returns month as integer
	 * @return month
	 */
	public int getMonth() {
		return month;
	}

	/**
	 * Returns day as integer
	 * @return day
	 */
	public int getDay() {
		return day;
	}

	/**
	 * Returns hour as integer
	 * @return hour
	 */
	public int getHour() {
		return hour;
	}

	/**
	 * Returns wind speed as double
	 * @return wind speed
	 */
	public double getWind() {
		return wind;
	}

	/**
	 * Returns temperature as double
	 * @return temperature
	 */
	public double getTemp() {
		return temp;
	}
	
	/**
	 * Rewrites method to higher class.
	 * @return year, month, day, hour, wind, temperature
	 */
	public String toString() {
		return year + ", " + month + ", " + day + ", " + hour + ", " + wind + ", " + temp;
	}
}
