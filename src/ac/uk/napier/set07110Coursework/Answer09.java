package ac.uk.napier.set07110Coursework;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.openstreetmap.gui.jmapviewer.Coordinate;

import ac.uk.napier.set07110Object.WeatherStation;
import mapgui.MapGui;
import weather.WeatherData;

public class Answer09 {
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
		
		ArrayList<WeatherStation> fillteredStations = new ArrayList<WeatherStation>();
		
		// Gets highest temperature out of all of the stations and compares it to the temperature needed and adds the once needed to my new array list
		for (int i = 0; i < stations.size(); i++) {
			if(stations.get(i).getHighestTemperature() >= 25) {
				fillteredStations.add(stations.get(i));
			}
		}
				
		//Prints number of station in array list
		System.out.println("There are " + fillteredStations.size() + " stations.");
		
		ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();

		// Finds coordinates of each station in array list
		for(int i = 0; i < fillteredStations.size(); i++) {
			coordinates.add(fillteredStations.get(i).getCoordinate());
		}
		
		// Prints out a map with all of the locations needed 
		MapGui.showMap(coordinates);
		JOptionPane.showMessageDialog(null, "Clicking on the map will save a screenshot using the current system time as the filename.");
	}	
}
