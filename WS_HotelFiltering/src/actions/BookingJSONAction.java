package actions;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import entity.Booking;
import helper.FileHelper;
import helper.JSONHelper;

public class BookingJSONAction {

	public static ArrayList<Booking> readJSONReservation() {
		ArrayList<Booking> reservations = new ArrayList<Booking>();

		String contentFile = FileHelper.readFiles("src/data/reservation.json");

		try {
			JSONArray reservationList;

			if(contentFile.isEmpty()) {
				reservationList = new JSONArray();
			}else {
				reservationList = new JSONArray(contentFile);				
			}

			for (int i = 0; i < reservationList.length(); i++) {
				reservations.add(JSONHelper.parseReservationObject(reservationList.getJSONObject(i)));
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return reservations;
	}

	public static void writeJSONBooking(ArrayList<Booking> bookings) {

		String path = "src/data/reservation.json";
		try {
			JSONArray bookingList = new JSONArray();

			for (Booking b : bookings) {
				JSONObject bookingObject = JSONHelper.createBookingObject(b.getIdClient(), b.getIdHotel(),
						b.getIdRoom(), b.getStartDate(), b.getEndDate());
				bookingList.put(bookingObject);
			}
			FileWriter file = new FileWriter(path);
			file.append(bookingList.toString());
			file.flush();
			file.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
