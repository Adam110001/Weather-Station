package ac.uk.napier.set07110Coursework;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import ac.uk.napier.set07110Object.WeatherReading;
import ac.uk.napier.set07110Object.WeatherStation;
import mapgui.MapGui;
import weather.WeatherData;

public class Answer08 {
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

		WeatherStation rhyl = null;
		
		//This for loop adds stations to "rhyl," however, they have to have id 3313
		for (int i = 0; i < stations.size(); i++) {
			if (stations.get(i).getId().equals("3313")) {
				rhyl = stations.get(i);
			}
		}
		
		ArrayList<WeatherReading> filteredReadings = rhyl.findReadings(2015, 7, 0, 11);
		
		double maxTemp = -99.0;
		double minTemp = 99.0;
		double sum = 0;
		
		//Finds the stations with highest and lowest temperature within station "RHYL," and finds the mean temperature of all of the temperature data. 
 		for(int j = 0; j < filteredReadings.size(); j++) {

			double currentTemp = filteredReadings.get(j).getTemp();
			
			if(currentTemp > maxTemp) {
				maxTemp = currentTemp;
			}
			if(currentTemp < minTemp) {
				minTemp = currentTemp;
			}
			
			sum += currentTemp;
		}

		sum /= filteredReadings.size();
		
		System.out.println(rhyl.getIdentity() + " maximum temperature is " + maxTemp + ", minimum temperature is " + minTemp + ", avarage temperature is " + sum + "." + " Number of readings in Rhyl: " + filteredReadings.size() + ".");
		
		MapGui.showMap(rhyl.getCoordinate());
		JOptionPane.showMessageDialog(null, "Clicking on the map will save a screenshot using the current system time as the filename.");
	}
}
