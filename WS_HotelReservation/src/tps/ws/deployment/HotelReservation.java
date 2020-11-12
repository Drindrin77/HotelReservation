package tps.ws.deployment;

import java.io.IOException;

import org.restlet.data.Form;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;


public class HotelReservation {
		
	private String baseURL = "http://localhost:8182";
	
	public String getAvailableHotels(String arrivalDate, int nbNight, int nbRoom){
		// Create the client resource  
		ClientResource hotelResource = new ClientResource(baseURL+"/hotels");  
		hotelResource.getReference().addQueryParameter("arrivalDate", arrivalDate);
		hotelResource.getReference().addQueryParameter("nbNight", Integer.toString(nbNight));
		hotelResource.getReference().addQueryParameter("nbRoom", Integer.toString(nbRoom));

		try {
			return hotelResource.get().getText();
		} catch (ResourceException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  
		return "";
	}
	
	public String getBookings(int idClient){

		// Create the client resource  
		ClientResource hotelResource = new ClientResource(baseURL+"/book");  
		hotelResource.getReference().addQueryParameter("idClient", Integer.toString(idClient));

		try {
			return hotelResource.get().getText();
			
		} catch (ResourceException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  
		return "";
	}
	
	public String bookHotel(String arrivalDate, int nbNight, int nbRoom, int idClient, int idHotel){

		// Create the client resource  
		ClientResource bookingResource = new ClientResource(baseURL+"/book");  
 
		Form form = new Form();  
		form.add("arrivalDate", arrivalDate);  
		form.add("nbNight", Integer.toString(nbNight));  
		form.add("nbRoom", Integer.toString(nbRoom));  
		form.add("idClient", Integer.toString(idClient));  
		form.add("idHotel", Integer.toString(idHotel));  

		try {
			String resultBooking = bookingResource.post(form).getText();
			if(resultBooking.equals("true")) {
				return "Reservation successful";
			}
			else{
				return "Reservation error or the hotel " + idHotel+ " is not available";
			}
		} catch (ResourceException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  
		return "";
	}
	
	public String cancelBooking(String arrivalDate, String endDate, int nbRoom, int idClient, int idHotel){

		// Create the client resource  
		ClientResource bookingResource = new ClientResource(baseURL+"/cancel");  
 
		Form form = new Form();  
		form.add("arrivalDate", arrivalDate);  
		form.add("endDate", endDate);  
		form.add("nbRoom", Integer.toString(nbRoom));  
		form.add("idClient", Integer.toString(idClient));  
		form.add("idHotel", Integer.toString(idHotel));  

		try {
			String resultBooking = bookingResource.post(form).getText();
			if(resultBooking.equals("true")) {
				return "Cancellation successful";
			}
			else{
				return "Cancellation error of the hotel " + idHotel;
			}
		} catch (ResourceException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  
		return "";
	}

}


