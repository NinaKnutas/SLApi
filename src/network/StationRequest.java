package network;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;

import station.Station;

import com.google.gson.Gson;



public class StationRequest {

	public static int request (String stationName) throws Exception{
		BufferedReader in = null;
		try {
			URL url = new URL("https://api.trafiklab.se/sl/realtid/GetSite.json?stationSearch=" +stationName +"&key=6e3ff19450084a9f318482d8e8016573");
			URLConnection con = url.openConnection();
			InputStream ins = con.getInputStream();
			in = new BufferedReader(new InputStreamReader(ins));
			String inputLine;
			
			while((inputLine = in.readLine()) != null) {
				Gson gson = new Gson();
				Station station = gson.fromJson(inputLine, Station.class);
				
				System.out.println(station.getHafas().getSites().getSite().getNumber());
				return station.getHafas().getSites().getSite().getNumber();
			}
			return -1;		
			
		} finally {
			in.close();
		}

	}

}
