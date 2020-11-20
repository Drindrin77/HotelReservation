package helper;

import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import entity.Hotel;
import entity.Booking;
import entity.Room;

public class JSONHelper {

	public static Booking parseReservationObject(JSONObject reservationObject) {

		Booking booking = new Booking();

		try {
			booking.setIdClient(Integer.parseInt(reservationObject.get("idClient").toString()));
			booking.setIdHotel(Integer.parseInt(reservationObject.get("idHotel").toString()));
			booking.setIdRoom(Integer.parseInt(reservationObject.get("idRoom").toString()));
			booking.setStartDate(DateHelper.stringToDate((String) reservationObject.get("startDate")));
			booking.setEndDate(DateHelper.stringToDate((String) reservationObject.get("endDate")));

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return booking;
	}

	public static JSONObject createBookingObject(int idClient, int idHotel, int idRoom, Date startDate, Date endDate) {
		JSONObject bookingObject = new JSONObject();
		try {
			bookingObject.put("idClient", idClient);
			bookingObject.put("idHotel", idHotel);
			bookingObject.put("idRoom", idRoom);
			bookingObject.put("startDate", DateHelper.dateToString(startDate));
			bookingObject.put("endDate", DateHelper.dateToString(endDate));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return bookingObject;
	}

	public static String hotelListToString(ArrayList<Hotel> hotels) {

		JSONArray hotelList = new JSONArray();
		try {
			for (int i = 0; i < hotels.size(); i++) {
				Hotel h = hotels.get(i);
				JSONObject hotelObject = new JSONObject();
				hotelObject.put("idHotel", h.getID());
				hotelObject.put("name", h.getName());
				hotelObject.put("address", h.getAddress());
				hotelObject.put("nbRoom", h.getNbRoomAvailable());
				hotelList.put(hotelObject);
			}

		} catch (JSONException e1) {
			e1.printStackTrace();
		}

		return hotelList.toString();
	}

	public static Hotel parseHotelObject(JSONObject hotelObject) {

		Hotel hotel = new Hotel();

		try {
			hotel.setID(Integer.parseInt(hotelObject.get("id").toString()));
			hotel.setAddress((String) hotelObject.get("address"));
			hotel.setName((String) hotelObject.get("name"));

			JSONArray rooms = (JSONArray) hotelObject.get("rooms");
			ArrayList<Room> roomsHotel = hotel.getRooms();
			for (int i = 0; i < rooms.length(); i++) {
				roomsHotel.add(parseRoomObject((JSONObject) rooms.get(i)));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return hotel;
	}

	private static Room parseRoomObject(JSONObject roomObject) {

		Room room = new Room();
		try {
			room.setID(Integer.parseInt(roomObject.get("id").toString()));
			room.setName((String) roomObject.get("name"));
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return room;
	}

	public static String bookingListToJSON(ArrayList<Booking> bookings) {
		JSONArray bookingsList = new JSONArray();
		for (int i = 0; i < bookings.size(); i++) {
			Booking b = bookings.get(i);
			JSONObject bookingObject = createBookingObject(b.getIdClient(), b.getIdHotel(), b.getIdRoom(),
					b.getStartDate(), b.getEndDate());
			bookingsList.put(bookingObject);
		}

		return bookingsList.toString();
	}

}
