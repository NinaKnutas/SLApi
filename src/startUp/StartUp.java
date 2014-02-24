package startUp;

import network.StationRequest;
import network.SubwayRequest;

public class StartUp {


	public static void main(String[] args) throws Exception {
		StationRequest.request("klubbacken%20(stockholm)");
		int stationNumber = StationRequest.request("skanstull");
		SubwayRequest.request(stationNumber);

	}

}
