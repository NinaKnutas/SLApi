package network;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import station.Station;

import com.google.gson.Gson;

import departure.DepartureInfo;

public class SubwayRequest {
	
	public static String request (int stationNumber) throws Exception{
		BufferedReader in = null;
		try {
			URL url = new URL("https://api.trafiklab.se/sl/realtid/GetDepartures.json?siteId=" +stationNumber +"&key=6e3ff19450084a9f318482d8e8016573");
			URLConnection con = url.openConnection();
			InputStream ins = con.getInputStream();
			in = new BufferedReader(new InputStreamReader(ins));
			String inputLine;
			
			while((inputLine = in.readLine()) != null) {
				Gson gson = new Gson();
				DepartureInfo departure = gson.fromJson(inputLine, DepartureInfo.class);
				
				System.out.println(departure.getDeparture().getMetros().getMetro()[0].getDisplayRow1());
				return departure.getDeparture().getMetros().getMetro()[0].getDisplayRow1();
			}
			return "Not found";		
			
		} finally {
			in.close();
		}

	}

}
