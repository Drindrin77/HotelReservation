package serverconfig;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

import action.AuthentificationAction;

import ressources.AuthentificationResource;
 
public class RouterApplication extends Application{
	/**
	 * Creates a root Restlet that will receive all incoming calls.
	 */
	@Override
	public synchronized Restlet createInboundRoot() {
		// Create a router Restlet that routes each call to a new respective instance of resource.
		
		Router router = new Router(getContext());
		AuthentificationAction.setUsers();

		router.attach("/auth", AuthentificationResource.class);
		
		return router;
	}
}