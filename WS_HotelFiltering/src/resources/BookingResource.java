package resources;

import java.util.ArrayList;
import java.util.Date;

import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import actions.HotelManager;
import entity.Booking;
import entity.Hotel;
import helper.DateHelper;
import helper.JSONHelper;

public class BookingResource extends ServerResource{
	
	@Post
	public Representation bookHotel(Representation entity) {  
		Representation result = null;  
		Form form = new Form(entity);
        int idClient = Integer.parseInt(form.getFirstValue("idClient"));  
        int idHotel = Integer.parseInt(form.getFirstValue("idHotel"));  
        Date arrivalDate = DateHelper.stringToDate(form.getFirstValue("arrivalDate"));  
        int nbNight = Integer.parseInt(form.getFirstValue("nbNight"));  
        int nbRoom = Integer.parseInt(form.getFirstValue("nbRoom"));      
        
        if(HotelManager.bookHotel(idHotel, idClient, arrivalDate, nbNight, nbRoom)) {
        	result = new StringRepresentation("true",  
                    MediaType.TEXT_PLAIN);         
        }else {
        	result = new StringRepresentation("false",  
                    MediaType.TEXT_PLAIN);
        }
        return result;  
    }
	
	@Get  
	public Representation getBookings(Representation entity) {
        
        int idClient = Integer.parseInt(getQuery().getValues("idClient")); 

		ArrayList<Booking> bookings = HotelManager.getBooking(idClient);
		return new StringRepresentation(JSONHelper.bookingListToJSON(bookings),  
	            MediaType.TEXT_PLAIN);
	       		
	}

}
