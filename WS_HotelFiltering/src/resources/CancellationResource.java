package resources;

import java.util.Date;

import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Delete;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import actions.HotelManager;
import helper.DateHelper;

public class CancellationResource extends ServerResource{
	
	@Delete
	public Representation cancelBooking(Representation entity) {  
		Representation result = null;  

		int nbRoom = Integer.parseInt(getQuery().getValues("nbRoom"));  
        int idClient = Integer.parseInt(getQuery().getValues("idClient"));  
        int idHotel = Integer.parseInt(getQuery().getValues("idHotel")); 
        Date arrivalDate = DateHelper.stringToDate(getQuery().getValues("arrivalDate"));
        Date endDate = DateHelper.stringToDate(getQuery().getValues("endDate")); 
           
        
        if(HotelManager.cancelBooking(idHotel, idClient, arrivalDate, endDate, nbRoom)) {
        	result = new StringRepresentation("true",
                    MediaType.TEXT_PLAIN);         
        }else {
        	result = new StringRepresentation("false",  
                    MediaType.TEXT_PLAIN);
        }
        return result;  
    }

}
