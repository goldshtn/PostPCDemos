package il.ac.huji.threadingandweb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Geocoding {

	private String address;
	private double latitude;
	private double longitude;
	
	public Geocoding(String address) {
		this.address = address;
	}
	
	private static String readStream(InputStream in) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		StringBuffer buffer = new StringBuffer();
		for (String line = reader.readLine(); line != null; line = reader.readLine()) {
			buffer.append(line);
			buffer.append('\n');
		}
		return buffer.toString();
	}
	
	public void geocode() throws IOException, JSONException {
		URL url = new URL(String.format(
				"https://maps.googleapis.com/maps/api/geocode/json?address=%s&sensor=false",
				URLEncoder.encode(address)));
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		String response = readStream(conn.getInputStream());
		
		JSONObject json = new JSONObject(response);
		JSONArray arr = json.getJSONArray("results");
		JSONObject res = arr.getJSONObject(0);
		JSONObject geo = res.getJSONObject("geometry");
		JSONObject loc = geo.getJSONObject("location");
		latitude = loc.getDouble("lat");
		longitude = loc.getDouble("lng");
	}
	
	public double getLatitude() {
		return latitude;
	}
	
	public double getLongitude() {
		return longitude;
	}
	
}
