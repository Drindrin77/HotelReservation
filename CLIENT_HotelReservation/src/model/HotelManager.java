package model;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.axis2.AxisFault;

import helper.DateHelper;
import helper.JSONHelper;
import tps.ws.deployment.HotelReservationStub;
import tps.ws.deployment.HotelReservationStub.BookHotel;
import tps.ws.deployment.HotelReservationStub.CancelBooking;
import tps.ws.deployment.HotelReservationStub.GetAvailableHotels;
import tps.ws.deployment.HotelReservationStub.GetBookings;

public class HotelManager {
	
	private int idClient;
	private ArrayList<Hotel> hotels;
	private ArrayList<Booking> bookings;
	
	public HotelManager(int idClient) {
		this.hotels = new ArrayList<Hotel>();
		this.bookings = new ArrayList<Booking>();
		this.idClient = idClient;
		initializeBookings();
	}
	
	public void initializeBookings() {
		try {
			HotelReservationStub stub = new HotelReservationStub();
			GetBookings getBookings = new GetBookings();
			getBookings.setIdClient(this.idClient);
			
			String result = stub.getBookings(getBookings).get_return();
						
			this.bookings = JSONHelper.bookingListToJSON(result);
			
		} catch (AxisFault e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void getAvailableHotels(Date arrivalDate, int nbNight, int nbRoom){
		try {
			HotelReservationStub stub = new HotelReservationStub();
			GetAvailableHotels getHotels = new GetAvailableHotels();
			getHotels.setArrivalDate(DateHelper.dateToString(arrivalDate));
			getHotels.setNbNight(nbNight);
			getHotels.setNbRoom(nbRoom);
			
			String result = stub.getAvailableHotels(getHotels).get_return();
						
			this.hotels = JSONHelper.hotelJSONToObject(result);
			
		} catch (AxisFault e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
	}


	public String bookHotel(int idHotel, Date arrivalDate, int nbNight, int nbRoom) {
		try {
			HotelReservationStub stub = new HotelReservationStub();
			BookHotel bookHotels = new BookHotel();
			bookHotels.setArrivalDate(DateHelper.dateToString(arrivalDate));
			bookHotels.setNbNight(nbNight);
			bookHotels.setNbRoom(nbRoom);
			bookHotels.setIdClient(this.idClient);
			bookHotels.setIdHotel(idHotel);
			
			String result = stub.bookHotel(bookHotels).get_return();
			
			if(result.equals("Reservation successful")) {
				Booking bookHotel = new Booking(idHotel, nbRoom, arrivalDate, DateHelper.addDays(arrivalDate, nbNight));
				this.bookings.add(bookHotel);				
			}
			return result; 
			
		} catch (AxisFault e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return "";
		
	}
	
	public String cancelBooking(Booking booking) {
		try {
			HotelReservationStub stub = new HotelReservationStub();
			CancelBooking cancelBooking = new CancelBooking();
			cancelBooking.setArrivalDate(DateHelper.dateToString(booking.getStartDate()));
			cancelBooking.setEndDate(DateHelper.dateToString(booking.getEndDate()));
			cancelBooking.setIdClient(this.idClient);
			cancelBooking.setIdHotel(booking.getIdHotel());
			cancelBooking.setNbRoom(booking.getNbRoom());
			
			String result = stub.cancelBooking(cancelBooking).get_return();
			
			if(result.equals("Cancellation successful")) {				
				bookings.remove(booking);
			}
			
			return result; 
			
		} catch (AxisFault e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return "";
	}
	
	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
	
	public ArrayList<Hotel> getHotels() {
		return hotels;
	}

	public ArrayList<Booking> getBookings() {
		return bookings;
	}
}
