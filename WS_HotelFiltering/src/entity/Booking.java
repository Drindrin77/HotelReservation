package entity;

import java.util.Date;

public class Booking {
	
	private int idHotel;
	private int idClient;
	private int idRoom;
	private Date startDate;
	private Date endDate;
	
	public Booking(int idHotel, int idClient, int idRoom, Date startDate, Date endDate) {
		this.idHotel = idHotel;
		this.idClient = idClient;
		this.idRoom = idRoom;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public Booking() {
		
	}
	
	public int getIdHotel() {
		return idHotel;
	}
	public void setIdHotel(int idHotel) {
		this.idHotel = idHotel;
	}
	public int getIdClient() {
		return idClient;
	}
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
	public int getIdRoom() {
		return idRoom;
	}
	public void setIdRoom(int idRoom) {
		this.idRoom = idRoom;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
