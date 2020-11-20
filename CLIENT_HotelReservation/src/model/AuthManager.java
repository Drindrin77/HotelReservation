package model;

import java.io.IOException;

import org.restlet.data.Form;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

public class AuthManager {

	private static String baseURLAuth = "http://localhost:8183";

	public static String authentification(String username, String password) {
		ClientResource authResource = new ClientResource(baseURLAuth + "/auth");
		String result = "";
		Form form = new Form();
		form.add("username", username);
		form.add("password", password);

		try {
			result = authResource.post(form).getText();
		} catch (ResourceException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		authResource.release();
		return result;
	}
}
