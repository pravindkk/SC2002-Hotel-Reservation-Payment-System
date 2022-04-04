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

public class PaymentController {

    static PaymentDB allPayments = new PaymentDB();

    static OrderController orderController = new OrderController();

    static private double serviceCharge = 0.10;
    static private double gst = 0.07;


   

    public static ArrayList getAllPayments() throws IOException {
        ArrayList allData = allPayments.read(allPayments.getPath());
        return allData;
    }


    public static void saveAllPayments(ArrayList toWrite) throws IOException {
        try {
            allPayments.save(allPayments.getPath(), toWrite);
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println("Payment not added!");
            System.out.println(e);
        }
    }

    public static void createPayment(Payment payment) throws IOException {
        ArrayList paymentList = getAllPayments();
        paymentList.add(payment);

        saveAllPayments(paymentList);
    }

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

    public static double getSubTotal(Payment payment, String roomId) throws IOException {
    	double subTotal = getTotalWithOrders(payment, roomId);
        double total = subTotal * (1 + gst) * (1 + serviceCharge);
    	payment.setTotal(total);
        return total;
    }


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



