package restServer;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

import actions.HotelManager;
import resources.BookingResource;
import resources.CancellationResource;
import resources.HotelResource;
 
public class RouterApplication extends Application{
	/**
	 * Creates a root Restlet that will receive all incoming calls.
	 */
	@Override
	public synchronized Restlet createInboundRoot() {
		// Create a router Restlet that routes each call to a new respective instance of resource.
		Router router = new Router(getContext());
		HotelManager.setHotelAndReservation();
		// Defines only three routes
		
		router.attach("/hotels", HotelResource.class);
		router.attach("/book", BookingResource.class);
		router.attach("/cancel", CancellationResource.class);
		
		return router;
	}
}