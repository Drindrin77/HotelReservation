
/**
 * HotelReservationCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package tps.ws.deployment;

    /**
     *  HotelReservationCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class HotelReservationCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public HotelReservationCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public HotelReservationCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for getBookings method
            * override this method for handling normal response from getBookings operation
            */
           public void receiveResultgetBookings(
                    tps.ws.deployment.HotelReservationStub.GetBookingsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getBookings operation
           */
            public void receiveErrorgetBookings(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for bookHotel method
            * override this method for handling normal response from bookHotel operation
            */
           public void receiveResultbookHotel(
                    tps.ws.deployment.HotelReservationStub.BookHotelResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from bookHotel operation
           */
            public void receiveErrorbookHotel(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getAvailableHotels method
            * override this method for handling normal response from getAvailableHotels operation
            */
           public void receiveResultgetAvailableHotels(
                    tps.ws.deployment.HotelReservationStub.GetAvailableHotelsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getAvailableHotels operation
           */
            public void receiveErrorgetAvailableHotels(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for cancelBooking method
            * override this method for handling normal response from cancelBooking operation
            */
           public void receiveResultcancelBooking(
                    tps.ws.deployment.HotelReservationStub.CancelBookingResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from cancelBooking operation
           */
            public void receiveErrorcancelBooking(java.lang.Exception e) {
            }
                


    }
    