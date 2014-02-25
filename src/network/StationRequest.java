package network;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import org.json.JSONArray;
import org.json.JSONObject;


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
				//System.out.println(inputLine);
				JSONObject jobj = new JSONObject(inputLine);
				JSONObject ja = jobj.getJSONObject("Hafas").getJSONObject("Sites").getJSONObject("Site");
				if(ja.has("Number")) {
						System.out.println(ja.getString("Number"));
						return Integer.parseInt(ja.getString("Number"));
				}
			}
			return -1;		
			
		} finally {
			in.close();
		}
	}
}