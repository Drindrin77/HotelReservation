package helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import model.Booking;
import model.Hotel;

public class JSONHelper {

	public static ArrayList<Hotel> hotelJSONToObject(String hotelsJson) {
		ArrayList<Hotel> hotels = new ArrayList<Hotel>();

		try {
			if (hotelsJson != null && !hotelsJson.isEmpty()) {
				JSONArray hotelList = new JSONArray(hotelsJson);
				for (int i = 0; i < hotelList.length(); i++) {
					hotels.add(parseHotelObject(hotelList.getJSONObject(i)));
				}
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return hotels;
	}

	private static Hotel parseHotelObject(JSONObject hotelObject) {

		Hotel hotel = new Hotel();

		try {
			hotel.setID(Integer.parseInt(hotelObject.get("idHotel").toString()));
			hotel.setAddress((String) hotelObject.get("address"));
			hotel.setName((String) hotelObject.get("name"));
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return hotel;
	}

	private static Booking parseBookingObject(JSONObject jsonObject) {
		Booking booking = new Booking();

		try {
			booking.setIdHotel(Integer.parseInt(jsonObject.get("idHotel").toString()));
			booking.setStartDate(DateHelper.stringToDate((String) jsonObject.get("startDate")));
			booking.setEndDate(DateHelper.stringToDate((String) jsonObject.get("endDate")));
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return booking;
	}

	public static ArrayList<Booking> bookingListToJSON(String bookingsJSON) {
		Map<Booking, Integer> bookingMap = new HashMap<Booking, Integer>();
		ArrayList<Booking> bookings = new ArrayList<Booking>();
		if (bookingsJSON != null && !bookingsJSON.isEmpty()) {

			try {
				JSONArray bookingList = new JSONArray(bookingsJSON);
				for (int i = 0; i < bookingList.length(); i++) {
					Booking parsedBooking = parseBookingObject(bookingList.getJSONObject(i));
					if (bookingMap.containsKey(parsedBooking)) {
						bookingMap.put(parsedBooking, bookingMap.get(parsedBooking) + 1);
					} else {
						bookingMap.put(parsedBooking, 1);
					}

				}

				for (Map.Entry<Booking, Integer> entry : bookingMap.entrySet()) {
					Booking b = entry.getKey();
					b.setNbRoom(entry.getValue());
					bookings.add(b);
				}

			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		return bookings;
	}

}
