package action;

import org.json.JSONException;
import org.json.JSONObject;

import entity.User;

public class JSONHelper {
	
	public static User parseUserObject(JSONObject userObject) {

		User user = new User();

		try {
			user.setId(Integer.parseInt(userObject.get("id").toString()));
			user.setUsername(userObject.get("username").toString());
			user.setPassword(userObject.get("password").toString());

		} catch (JSONException e) {
			e.printStackTrace();
		}
		return user;
	}

}
