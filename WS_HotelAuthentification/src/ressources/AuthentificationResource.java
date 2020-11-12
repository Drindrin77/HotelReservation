package ressources;

import java.util.Date;
import java.util.Optional;

import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import action.AuthentificationAction;

public class AuthentificationResource extends ServerResource{
	
	@Post
	public Representation auth(Representation entity) {  
		Representation result = null;  

		Form form = new Form(entity);
        String username = form.getFirstValue("username");  
        String password = form.getFirstValue("password");  

        Optional<Integer> optID = AuthentificationAction.userExist(username, password); 
        if(optID.isPresent()) {
        	result = new StringRepresentation(Integer.toString(optID.get()),  
                    MediaType.TEXT_PLAIN);         
        }else {
        	result = new StringRepresentation("",  
                    MediaType.TEXT_PLAIN);
        }
        return result;  
    }

}
