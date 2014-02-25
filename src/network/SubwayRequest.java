package network;

import java.io.BufferedReader;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


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
				//System.out.println(inputLine);
				JSONObject jobj = new JSONObject(inputLine);
				JSONArray ja = jobj.getJSONObject("Departure").getJSONObject("Metros").getJSONArray("Metro");

				String rows ="";
				for(int i=0; i < ja.length(); i++) {
					if(((JSONObject)ja.get(i)).has("DisplayRow1")){
						System.out.println(((JSONObject)ja.get(i)).getString("DisplayRow1"));
						rows += ((JSONObject)ja.get(i)).getString("DisplayRow1");
					}
				}
				return rows;
			}
			return "Not found";		
		} finally {
			in.close();
		}
	}
}