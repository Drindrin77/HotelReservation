package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import helper.DateHelper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import model.Booking;
import model.Hotel;
import model.HotelManager;

public class HotelReservationController implements Initializable {

	private HotelManager hotelManager;
	private ObservableList<Hotel> hotelObservableList;
	private ObservableList<Booking> bookingObservableList;

	public HotelReservationController(int idClient) {
		hotelObservableList = FXCollections.observableArrayList();
		bookingObservableList = FXCollections.observableArrayList();
		hotelManager = new HotelManager(idClient);
	}
	
	@FXML
    private Label labelHotel;
    @FXML
    private Label labelBooking;

	@FXML
	private TableView<Hotel> tableHotels;
	@FXML
	private TableColumn<Hotel, Number> columnHotelID;
	@FXML
	private TableColumn<Hotel, String> columnHotelAddress;
	@FXML
	private TableColumn<Hotel, Void> columnActionHotel;
	@FXML
	private TableColumn<Hotel, String> columnNameHotel;

	@FXML
	private DatePicker dateArrivalDate;
	@FXML
	private TextField textNbNight;
	@FXML
	private TextField textNbRoom;

	@FXML
	private TableView<Booking> tableBooking;
	@FXML
	private TableColumn<Booking, Number> columnBookingID;
	@FXML
	private TableColumn<Booking, String> columnStartDate;
	@FXML
	private TableColumn<Booking, String> columnEndDate;
	@FXML
	private TableColumn<Booking, Void> columnActionBooking;
	@FXML
	private TableColumn<Booking, Number> columnBookingNbRoom;

	@FXML
	void searchHotels(ActionEvent event) {
		hotelObservableList.clear();
		//if setting available hotels is successful, set new observable list hotel
		if(setAvailableHotels()) {			
			setHotelTable();
		}
	}

	
	private void setBookingTable() {
		bookingObservableList.clear();
		bookingObservableList.addAll(hotelManager.getBookings());
	}

	private void setHotelTable() {
		ArrayList<Hotel> hotels = hotelManager.getHotels();
		if(hotels.size()==0) {
			labelHotel.setText("Hotel not found");
		}else {			
			hotelObservableList.addAll(hotels);
			labelHotel.setText("");
		}
	}

	private boolean isInputValuesEmpty() {
		return textNbNight.getText().isEmpty() 
				|| textNbRoom.getText().isEmpty() 
				|| dateArrivalDate.getValue() == null;
	}
	
	private boolean setAvailableHotels() {

		// CHECK EMPTY VALUES
		if(isInputValuesEmpty()) {
			labelHotel.setText("Veuillez remplir le formulaire");
			return false;
		}
		
		else {
			try {
				Date arrivalDate = DateHelper.localDatetoDate(dateArrivalDate.getValue());
				int nbNight = Integer.parseInt(textNbNight.getText());
				int nbRoom = Integer.parseInt(textNbRoom.getText());
				hotelManager.getAvailableHotels(arrivalDate, nbNight, nbRoom);
			}
			//IF nbNight or nbRoom are not in number format
			catch(NumberFormatException e) {
				labelHotel.setText("Veuillez rentrer des données numériques");
				return false;
			}
			return true;
		}
		
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		columnBookingID.setCellValueFactory(value -> {
			return new SimpleIntegerProperty(value.getValue().getIdHotel());
		});
		columnEndDate.setCellValueFactory(value -> {
			return new SimpleStringProperty(DateHelper.dateToString(value.getValue().getEndDate()));
		});
		columnStartDate.setCellValueFactory(value -> {
			return new SimpleStringProperty(DateHelper.dateToString(value.getValue().getStartDate()));
		});
		columnBookingNbRoom.setCellValueFactory(value -> {
			return new SimpleIntegerProperty(value.getValue().getNbRoom());
		});
		columnHotelID.setCellValueFactory(value -> {
			return new SimpleIntegerProperty(value.getValue().getID());
		});
		columnHotelAddress.setCellValueFactory(value -> {
			return new SimpleStringProperty(value.getValue().getAddress());
		});
		columnNameHotel.setCellValueFactory(value -> {
			return new SimpleStringProperty(value.getValue().getName());
		});

		tableBooking.setItems(bookingObservableList);
		tableHotels.setItems(hotelObservableList);
		setBookingTable();
		setActionBookingButton();
		setActionCancelButton();
		setColorRow();
	}

	private void setActionBookingButton() {

		Callback<TableColumn<Hotel, Void>, TableCell<Hotel, Void>> cellFactory = new Callback<TableColumn<Hotel, Void>, TableCell<Hotel, Void>>() {
			@Override
			public TableCell<Hotel, Void> call(final TableColumn<Hotel, Void> param) {
				final TableCell<Hotel, Void> cell = new TableCell<Hotel, Void>() {
					private final Button btn = new Button("Réserver");
					{
						// BOOKING
						btn.setOnAction((ActionEvent event) -> {
							Hotel hotel = getTableView().getItems().get(getIndex());
							Date arrivalDate = DateHelper.localDatetoDate(dateArrivalDate.getValue());
							int nbNight = Integer.parseInt(textNbNight.getText());
							int nbRoom = Integer.parseInt(textNbRoom.getText());
							
							labelHotel.setText(hotelManager.bookHotel(hotel.getID(), arrivalDate, nbNight, nbRoom));
							int nbRoomAvailable = hotel.getNbRoomAvailable();

							//If there are not enough rooms to book again the same hotel
							if(nbRoomAvailable-nbRoom<nbRoom) {
								hotelObservableList.remove(getIndex());
							}
							hotel.setNbRoomAvailable(nbRoomAvailable-nbRoom);
							setBookingTable();
						});
					}

					@Override
					public void updateItem(Void item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
						} else {
							setGraphic(btn);
						}
					}
				};
				return cell;
			}
		};

		columnActionHotel.setCellFactory(cellFactory);
	}

	private void setActionCancelButton() {

		Callback<TableColumn<Booking, Void>, TableCell<Booking, Void>> cellFactory = new Callback<TableColumn<Booking, Void>, TableCell<Booking, Void>>() {
			@Override
			public TableCell<Booking, Void> call(final TableColumn<Booking, Void> param) {
				final TableCell<Booking, Void> cell = new TableCell<Booking, Void>() {
					private final Button btn = new Button("Annuler");
					{
						// CANCEL BOOKING
						btn.setOnAction((ActionEvent event) -> {
							Booking booking = getTableView().getItems().get(getIndex());
							labelBooking.setText(hotelManager.cancelBooking(booking));
							bookingObservableList.remove(booking);
						});
					}

					@Override
					public void updateItem(Void item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
						} else {
							setGraphic(btn);
						}
					}
				};
				return cell;
			}
		};

		columnActionBooking.setCellFactory(cellFactory);
	}

	/**
	 * Set background red to booking row if end date is outpassed
	 */
	private void setColorRow() {
		tableBooking.setRowFactory(row -> new TableRow<Booking>() {
			@Override
			public void updateItem(Booking item, boolean empty) {
				super.updateItem(item, empty);
				if (item == null || empty) {
					setStyle("");
				} else {
					Date today = new Date();
					if (item.getEndDate().before(today)) {
						setStyle("-fx-background-color: #ff4d4d;");
					}
				}
			}
		});
	}

}
