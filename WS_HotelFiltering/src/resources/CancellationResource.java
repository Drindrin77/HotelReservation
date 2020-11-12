package resources;

import java.util.Date;

import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import actions.HotelManager;
import helper.DateHelper;

public class CancellationResource extends ServerResource{
	
	@Post
	public Representation cancelBooking(Representation entity) {  
		Representation result = null;  

		Form form = new Form(entity);
        int idClient = Integer.parseInt(form.getFirstValue("idClient"));  
        int idHotel = Integer.parseInt(form.getFirstValue("idHotel"));  
        Date arrivalDate = DateHelper.stringToDate(form.getFirstValue("arrivalDate"));  
        Date endDate = DateHelper.stringToDate(form.getFirstValue("endDate"));  
        int nbRoom = Integer.parseInt(form.getFirstValue("nbRoom"));      
        
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
