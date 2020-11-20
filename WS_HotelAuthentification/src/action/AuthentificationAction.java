package action;

import java.util.ArrayList;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONException;

import entity.User;

public class AuthentificationAction {
	
	private static ArrayList<User> users;
	
	public static void setUsers() {
		String contentFile = FileHelper.readFiles("src/data/users.json");
		users = new ArrayList<User>();
		try {
			JSONArray userList;

			if(contentFile.isEmpty()) {
				userList = new JSONArray();
			}else {
				userList = new JSONArray(contentFile);
			}

			for (int i = 0; i < userList.length(); i++) {
				users.add(JSONHelper.parseUserObject(userList.getJSONObject(i)));
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	//If user exists, return id user
	public static Optional<Integer> userExist(String username, String password) {
		for(User user: users) {
			if(user.getPassword().equals(password) && user.getUsername().equals(username)) {
				return Optional.of(user.getId());
			}
		}
		return Optional.empty();
	}

}
