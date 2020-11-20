package actions;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import entity.Hotel;
import entity.Booking;
import entity.Room;
import helper.DateHelper;

public class HotelManager {
	private static ArrayList<Hotel> hotels;
	private static ArrayList<Booking> bookings;
	
	public static void setHotelAndReservation() {
		hotels = HotelJSONReader.readJSONHotels();
		bookings = BookingJSONAction.readJSONReservation();
	}
	
	public static void updateReservations() {
		bookings = BookingJSONAction.readJSONReservation();
	}
	
	public static ArrayList<Booking> getBooking(int idClient){
		ArrayList<Booking> books = new ArrayList<Booking>();
		for(Booking b: bookings) {
			if(b.getIdClient() == idClient) {
				books.add(b);
			}
		}
		return books;
	}
	
	public static boolean cancelBooking(int idHotel, int idClient, Date arrivalDate, Date endDate, int nbRoom) {		
		int nbRoomRemoved = 0;

		for (int i = bookings.size() - 1; i >= 0 ; i--)
		{
			Booking b = bookings.get(i);
			if(b.getIdHotel() == idHotel 
					&& b.getIdClient() == idClient
					&& b.getEndDate().equals(endDate) 
					&& b.getStartDate().equals(arrivalDate)
					&& nbRoomRemoved < nbRoom) {
				
				bookings.remove(i);
				nbRoomRemoved++;
			}
		}

		BookingJSONAction.writeJSONBooking(bookings);	
		if(nbRoomRemoved == nbRoom)
			return true;
		return false;
	}

	
	public static boolean bookHotel(int idHotel, int idClient, Date arrivalDate, int nbNight, int nbRoom) {
		//CHECK IF HOTEL EXIST
		Optional<Hotel> hotel = getHotelByID(idHotel); 
		if(!hotel.isPresent()) {
			return false;
		}
		Hotel h = hotel.get();
		
		//CHECK IF THERE ARE ENOUGH AVAILABLE ROOMS
		ArrayList<Room> rooms = getAvailableRooms(h, arrivalDate, nbNight);
		if(rooms.size()<nbRoom) {
			return false;
		}
		
		//WRITE JSON 
		Date endDate = DateHelper.addDays(arrivalDate, nbNight);
		for(int i = 0 ; i < nbRoom ; i++) {			
			bookings.add(new Booking(idHotel, idClient, rooms.get(i).getID(), arrivalDate, endDate));
		}
		
		BookingJSONAction.writeJSONBooking(bookings);
		
		return true;
	}
	
	public static ArrayList<Hotel> getAvailableHotels(Date arrivalDate, int nbNight, int nbRoom) {
	
		ArrayList<Hotel> availableHotels = new ArrayList<Hotel>();
		int nbRoomAvailable;
		for(Hotel hotel: hotels) {
			if((nbRoomAvailable = getAvailableRooms(hotel, arrivalDate, nbNight).size())>=nbRoom) {
				hotel.setNbRoomAvailable(nbRoomAvailable);
				availableHotels.add(hotel);
			}
		}
		return availableHotels;
	}
	
	private static ArrayList<Room> getAvailableRooms(Hotel hotel, Date arrivalDate, int nbNight){
		ArrayList<Room> availableRooms = new ArrayList<Room>();
		for(Room room: hotel.getRooms()) {
			if(isRoomAvailable(hotel.getID(), room.getID(), arrivalDate, nbNight)) {
				availableRooms.add(room);
			}				
		}
		return availableRooms;
	}

	private static boolean isRoomAvailable(int idHotel, int idRoom, Date startDate, int nbNight) {
		Date endDate = DateHelper.addDays(startDate, nbNight);
		boolean isAvailable = true;
		int i = 0 ;
		while(i<bookings.size() && isAvailable) {
			Booking r = bookings.get(i);
			if(r.getIdHotel() == idHotel && r.getIdRoom() == idRoom) {
				isAvailable = !DateHelper.intervalReservationOverlap(startDate, endDate, r.getStartDate(), r.getEndDate());
			}
			i++;
		}
		
		return isAvailable;
	}
	
	private static Optional<Hotel> getHotelByID(int idHotel) {
		for(Hotel h : hotels) {
			if(h.getID() == idHotel)
				return Optional.of(h);
		}
		return Optional.empty();
	}

}
