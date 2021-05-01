package ac.uk.napier.set07110Object;

import java.util.ArrayList;

import org.openstreetmap.gui.jmapviewer.Coordinate;

public class WeatherStation {
	
	private String id;
	private String identity;
	private double latitude;
	private double longtitude;
	private ArrayList<WeatherReading> readings = new ArrayList<WeatherReading>();
	
	public WeatherStation(String newID, String newIdentity, double newLatitude, double newLongtitude) {
		this.id = newID;
		this.identity = newIdentity;
		this.latitude = newLatitude;
		this.longtitude = newLongtitude;
	}
	
	/**
	 * Adds additional information to station needed. 
	 * @param year
	 * @param month
	 * @param date
	 * @param hour
	 * @param wind
	 * @param temp
	 */
	public void addReading(int year, int month, int date, int hour, double wind, double temp) {
		WeatherReading newReading = new WeatherReading(year, month, date, hour, wind, temp);
		readings.add(newReading);
	}
	
	/**
	 * Returns year, month, day, hour in form of String. It allows you to get more information regarding time. 
	 * @return
	 */
	public String getNeededInfo() {
		int year = 0;
		int month = 0;
		int day = 0;
		
		for (int i = 0; i < readings.size(); i++) {
			year = readings.get(i).getYear(); 
			month = readings.get(i).getMonth(); 
			day = readings.get(i).getDay(); 
		}
		
		return year + ", " + month + ", " + day;
	}
	
	/**
	 * Uses readings to get information needed.
	 * @param yearNeeded
	 * @param monthNeeded
	 * @param dayNeeded
	 * @param hourNeeded
	 * @return
	 */
	public ArrayList<WeatherReading> findReadings (int yearNeeded, int monthNeeded, int dayNeeded, int hourNeeded) {
		int year, month, day, hour;
		ArrayList<WeatherReading> readingsFiltered = new ArrayList<WeatherReading>();
		
		for (int i = 0; i < readings.size(); i++) {
			year = readings.get(i).getYear(); 
			month = readings.get(i).getMonth(); 
			day = readings.get(i).getDay(); 
			hour = readings.get(i).getHour();
			if((year == yearNeeded || yearNeeded == 0) && (month == monthNeeded || monthNeeded == 0) && (day == dayNeeded || dayNeeded == 0) && (hour == hourNeeded || hourNeeded == 0)) {
				
				readingsFiltered.add(readings.get(i));
			}
		}
		
		return readingsFiltered;
	}
	
	/**
	 * Finds the highest temperature of the given station.
	 * @return maxTemp
	 */
	public double getHighestTemperature() {
		double max = 0;
		for (int i = 0; i < readings.size(); i++) {
			if (readings.get(i).getTemp() > max) {
				max = readings.get(i).getTemp();
			}
		}
		return max;
	}
	
	/**
	 * Finds the lowest temperature of given station.
	 * @return minTemp
	 */
	public double getLowestTemperature() {
		double min = 0;
		for (int i = 0; i < readings.size(); i++) {
			if (readings.get(i).getTemp() < min) {
				min = readings.get(i).getTemp();
			}
		}
		return min;
	}
	
	/**
	 * Imports coordinates and finds the location of station/s provided. 
	 * @return Coordinate
	 */
	public Coordinate getCoordinate() {
		Coordinate coord = new Coordinate(latitude, longtitude);
		return coord;
	}
	
	/**
	 * @return ID of a station
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * @return Gives name of a station
	 */
	public String getIdentity() {
		return identity;
	}

	/**
	 * @return Latitude of a station
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * @return Longitude of a station
	 */
	public double getLongtitude() {
		return longtitude;
	}

	/**
	 * @return Gets all the readings of a station
	 */
	public ArrayList<WeatherReading> getReadings() {
		return readings;
	}
	
	/**
	 * @return Calculates the size of data.
	 */
	public int getNumberOfReadings() {	
		return readings.size();
	}
}