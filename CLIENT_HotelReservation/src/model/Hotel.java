package model;

public class Hotel {
	private int ID;
	private String address;
	private String name;
	private int nbRoomAvailable;
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNbRoomAvailable() {
		return nbRoomAvailable;
	}
	public void setNbRoomAvailable(int nbRoomAvailable) {
		this.nbRoomAvailable = nbRoomAvailable;
	}
}
