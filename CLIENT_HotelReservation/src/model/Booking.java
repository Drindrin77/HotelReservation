package model;

import java.util.Date;

public class Booking {
	private int idHotel;
	private int nbRoom;
	private Date startDate;
	private Date endDate;

	public Booking(int idHotel, int nbRoom, Date startDate, Date endDate) {
		this.endDate = endDate;
		this.startDate = startDate;
		this.idHotel = idHotel;
		this.nbRoom = nbRoom;
	}
	
	public Booking() {
	}

	public int getIdHotel() {
		return idHotel;
	}

	public void setIdHotel(int idHotel) {
		this.idHotel = idHotel;
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

	public int getNbRoom() {
		return this.nbRoom;
	}
	
	public void setNbRoom(int nbRoom) {
		this.nbRoom = nbRoom;
	}
	public void addRoom(int nbRoom) {
		this.nbRoom += nbRoom;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + idHotel;
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Booking other = (Booking) obj;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (idHotel != other.idHotel)
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		return true;
	}
	
	

}
