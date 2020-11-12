package actions;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

import entity.Hotel;
import helper.FileHelper;
import helper.JSONHelper;

public class HotelJSONReader {

	public static ArrayList<Hotel> readJSONHotels() {
		ArrayList<Hotel> hotels = new ArrayList<Hotel>();
		// Read JSON file
		String contentFile = FileHelper.readFiles("src/data/hotels.json");
		try {
			JSONArray hotelList = new JSONArray(contentFile);

			for (int i = 0; i < hotelList.length(); i++) {
				hotels.add(JSONHelper.parseHotelObject(hotelList.getJSONObject(i)));
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return hotels;
	}

}
