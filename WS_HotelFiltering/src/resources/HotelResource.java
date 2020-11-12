package resources;


import java.util.ArrayList;
import java.util.Date;


import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import actions.HotelManager;
import entity.Hotel;
import helper.DateHelper;
import helper.JSONHelper;

public class HotelResource extends ServerResource  {

 	@Get  
	public Representation getAvailableHotels(Representation entity) {
        
        String stringArrivalDate = getQuery().getValues("arrivalDate");  
        int nbNight = Integer.parseInt(getQuery().getValues("nbNight"));  
        int nbRoom = Integer.parseInt(getQuery().getValues("nbRoom")); 

        Date arrivalDate = DateHelper.stringToDate(stringArrivalDate);

		ArrayList<Hotel> hotels = HotelManager.getAvailableHotels(arrivalDate, nbNight, nbRoom) ;
		
		return new StringRepresentation(JSONHelper.hotelListToString(hotels),  
	            MediaType.TEXT_PLAIN);
	       		
	} 
	
}  