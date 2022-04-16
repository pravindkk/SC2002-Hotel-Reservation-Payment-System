package com.hotel.controller;

import com.hotel.db.PaymentDB;
import com.hotel.system.Payment;
import com.hotel.system.Reservation;
import com.hotel.system.Order;
import com.hotel.system.CreditCard;
import com.hotel.system.Guest;
import com.hotel.system.Item;
import com.hotel.system.Room;
import com.hotel.controller.RoomController;
import com.hotel.controller.OrderController;


import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.time.Period;
import java.util.Arrays ;
import org.apache.commons.lang3.ArrayUtils;

/**
 * Represents the controller function of Payment
 * @author Pravind
 * @version 1.0
 * @since 1.0
 */


public class PaymentController {

    static PaymentDB allPayments = new PaymentDB();

    static OrderController orderController = new OrderController();

    static private double serviceCharge = 0.10;
    static private double gst = 0.07;


   

    
	/** 
	 * Gets and returns all Payments
	 * @return Returns an ArrayList of all the Payments stored in the database
	 * @throws IOException Due to communication with the DataBase IOexception is required
	 */
	public static ArrayList getAllPayments() throws IOException {
        ArrayList allData = allPayments.read(allPayments.getPath());
        return allData;
    }


    
	/** 
	 * Saves all payments into database
	 * @param toWrite Contains an ArrayList of all the Payments that is going to be stored in the database
	 * @throws IOException Due to communication with the DataBase IOexception is required
	 */
	public static void saveAllPayments(ArrayList toWrite) throws IOException {
        try {
            allPayments.save(allPayments.getPath(), toWrite);
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println("Payment not added!");
            System.out.println(e);
        }
    }

    
	/** 
	 * Creates a Payment object 
	 * @param payment Payment object is passed in as parameter so that the payment could be added to the database
	 * @throws IOException Due to communication with the DataBase IOexception is required
	 */
	public static void createPayment(Payment payment) throws IOException {
        ArrayList paymentList = getAllPayments();
        paymentList.add(payment);

        saveAllPayments(paymentList);
    }

    
	/** 
	 * Calculates the room total cost and returns it. If unable to calculate, -1 is returned
	 * @param roomId String input of GuestID is entered so that the room total could be calculated
	 * @return returns the room total for the corresponding roomId
	 */
	public static double getRoomTotal(String roomId) {
    	Reservation r;
		try {
			r = ReservationController.getReservationByRoomId(roomId);
			double rate = Double.valueOf(RoomController.getSpecificRoom(roomId).getRoomRate());
			long diff = r.getCheckOutDate().getTime() - r.getCheckInDate().getTime();
			double nights = (diff / (1000*60*60*24));
			if(diff == 0) nights = 1.0;
	    	double roomTotal = rate * nights;
	    	return roomTotal;
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return -1;
    }

    
	/** 
	 * Calculates the total cost summed with the cost of orders and returns the sum
	 * @param payment Payment object is passed as parameter so that the subtotal field could be updated
	 * @param roomId String input of roomID is entered so that the room total could be calculated
	 * @return the total cost of the orders
	 * @throws IOException Due to communication with the DataBase IOexception is required
	 */
	public static double getTotalWithOrders(Payment payment, String roomId) throws IOException {
    	double subTotal = getRoomTotal(roomId);
    	ArrayList<String> orders = payment.getOrders();
    	if(orders != null) {
            for (int i=0; i<orders.size(); i++) {
                Order order = OrderController.getOrderById(orders.get(i));
                for (Item item : order.getItem()) {
                    subTotal += item.getPrice();
                }
            }
    	}
        payment.setSubTotal(subTotal);
        return subTotal;
    }

    
	/** 
	 * Calculates the subtotal (Price + SVC + GST) and returns it
	 * @param payment Payment object is passed as parameter so that the total field could be updated
	 * @param roomId String input of roomID is entered so that the totalcost could be calculated
	 * @return total cost of the room is returned
	 * @throws IOException Due to communication with the DataBase IOexception is required
	 */
	public static double getSubTotal(Payment payment, String roomId) throws IOException {
    	double subTotal = getTotalWithOrders(payment, roomId);
        double total = subTotal * (1 + gst) * (1 + serviceCharge);
    	payment.setTotal(total);
        return total;
    }


    
	/** 
	 * Gets and returns a Payment object
	 * @param payment Payment object is passed as parameter so that the total field could be used to display the total
	 * @param roomId String input of roomID is entered so that the correct Payment record is retireved from the database
	 * @param method Integer input is passed to detemine if the payment is made using cash or card
	 * @param cash Amount of money that the Guest pays
	 * @throws IOException Due to communication with the DataBase IOexception is required
	 */
	public static void getPayment(Payment payment, String roomId, int method, double cash) throws IOException {
    	Double totalWithOrders = getTotalWithOrders(payment, roomId);
    	Double total = getSubTotal(payment, roomId);
    	Double currServiceCharge = totalWithOrders * serviceCharge;
    	Double GST = totalWithOrders * (gst * (1 + serviceCharge));
    	Reservation r;



		try {
			r = ReservationController.getReservationByRoomId(roomId);
			String guestId = r.getGuestId();
			Guest guest = GuestController.RetrieveGuest(guestId);
			CreditCard credit = CreditCardController.RetrieveCreditCard(guestId);
			System.out.printf("                                     Payment                     #%s             \n", payment.getReservationNum());
			System.out.println("---------------------------------------------------------------------------------");
			System.out.printf("GUEST: %s                                                                        \n", guest.getName());
	    	System.out.println("---------------------------------------------------------------------------------");
	    	System.out.printf("ROOM CHARGE                                                             %.2f\n", getRoomTotal(roomId));
	    	System.out.println("---------------------------------------------------------------------------------");
	    	ArrayList<String> orders = payment.getOrders();
	    	if(orders != null) {
		    	for(int i = 0; i < orders.size(); i++){
                    Order order = OrderController.getOrderById(orders.get(i));
		    		System.out.printf("Room Service #%d                                                                 \n", i+1);
		        	System.out.println("---------------------------------------------------------------------------------");
		        	order.viewOrder();
		    	}
	    	}
	        System.out.printf("SUBTOTAL                                                                %.2f\n", totalWithOrders);
	        System.out.printf("10%% SVC CHG                                                             %.2f\n", currServiceCharge);
	        System.out.printf("7%% GST                                                                  %.2f\n", GST);
	        System.out.println("---------------------------------------------------------------------------------");
	        System.out.printf("TOTAL                                                                   %.2f\n", total);
	        if(method == 0) {
	        	System.out.printf("CASH                                                                    %.2f\n", cash);
	        	System.out.printf("CHANGE                                                                  %.2f\n", cash - total);
	        	System.out.println("---------------------------------------------------------------------------------");
	        }
	        else {
	        	System.out.println("---------------------------------------------------------------------------------");
	            System.out.printf("CARD TYPE                                                               %s\n", credit.getCardType());
	            System.out.printf("CARD NUMBER                                                %s\n", credit.getCardNo());
	            System.out.printf("CARD EXPIRY                                                             %s\n", credit.getExpiry());
	        }
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
}



