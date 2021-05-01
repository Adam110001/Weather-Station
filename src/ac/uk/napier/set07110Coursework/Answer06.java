package ac.uk.napier.set07110Coursework;

import weather.WeatherData;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import ac.uk.napier.set07110Object.WeatherStation;
import mapgui.MapGui;

public class Answer06 {
	public static void main(String[] args) {		
ArrayList<WeatherStation> stations = new ArrayList<WeatherStation>();
		
		//This part of a code adds information to "stations" in order needed
		String[] data = WeatherData.getData();
		String id, name;
		int year, month, date, hour;
		double lat, lon, windSpeed, temp;
		boolean match = false;
		for (int i = 1; i < data.length; i++) {
			String[] splittedData = data[i].split(",");
			id = splittedData[0];
			name = splittedData[1];
			lat = Double.parseDouble(splittedData[2]);
			lon = Double.parseDouble(splittedData[3]);
			year = Integer.parseInt(splittedData[4]);
			month = Integer.parseInt(splittedData[5]);
			date = Integer.parseInt(splittedData[6]);
			hour = Integer.parseInt(splittedData[7]);
			windSpeed = Double.parseDouble(splittedData[8]);
			temp = Double.parseDouble(splittedData[9]);
			
			//This adds readings to already existing station with id, identity, longitude and latitude
			for (int j = 0; j < stations.size(); j++) {
				if ( id.equals(stations.get(j).getId()) ) {
					match = true;
					stations.get(j).addReading(year, month, date, hour, windSpeed, temp);
				}
			}
			
			// This creates first station needed to make the for loop to work 
			if (!match) {
				WeatherStation newStation = new WeatherStation(id, name, lat, lon);
				newStation.addReading(year, month, date, hour, windSpeed, temp);
				stations.add(newStation);
			}
			
			match = false;
		}
		
		WeatherStation result = null;
		int total = 0;
		
		/*
		 * Takes size of stations compares the size of all of the weather stations with total and if number of readings in a station is greater than total readings in the other stations it adds the readings size in total.
		 * Additionally, I am using object weather station to be able to find id, identity, longitude, latitude. 
		*/
		for (int i = 0; i < stations.size(); i++) {
			if (stations.get(i).getNumberOfReadings() > total) {
				total = stations.get(i).getNumberOfReadings();
				result = stations.get(i);
			}
		}
		
		System.out.println("Weather station: " + result.getId() + ", " + result.getIdentity() + ", " + result.getLatitude() + ", " + result.getLongtitude() + ", " + total);
		
		MapGui.showMap(result.getCoordinate());
		JOptionPane.showMessageDialog(null, "Clicking on the map will save a screenshot using the current system time as the filename.");
	}
}