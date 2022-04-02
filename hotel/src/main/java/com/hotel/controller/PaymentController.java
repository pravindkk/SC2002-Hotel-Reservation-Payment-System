package com.hotel.controller;

import com.hotel.db.PaymentDB;
import com.hotel.system.Payment;
import com.hotel.system.Reservation;
import com.hotel.system.Order;
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

    static PaymentDB allPayments= new PaymentDB();

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

    public static Payment getPaymentByGuestId(String guestID) throws IOException {
        ArrayList<Payment> paymentList = getAllPayments();
        for (Payment payment : paymentList) {
            if (payment.getGuestId().equals(guestID))
                return payment;
        }
        return null;
    }

    public static double getOrderTotal(String roomId) throws IOException{

        //assumption here is that the room exists

        double total = 0.00;
        
        ArrayList<Order> orderList = orderController.getOrderListByRoomId(roomId);
        for(Order order: orderList){

            ArrayList<Item> itemList = order.getItem();
            for(Item item:itemList){
                total += item.getPrice();
            }

        }

        return total;

    }

    public static double getRoomTotal(String roomId, String guestId) throws IOException{

        double total =0.00;
        Room room = RoomController.getSpecificRoom(roomId);
        double roomRate = room.getRoomRate();

        // get checkin and check out date

        Reservation r = ReservationController.getReservationByRoomId(roomId);
        Date checkIn = r.getCheckInDate();
        Date checkOut = r.getCheckOutDate();


        long diff = checkOut.getTime() - checkIn.getTime();

        long difference_In_Days = (diff/ (1000 * 60 * 60 * 24))% 365;

        total  = roomRate * Double.valueOf(difference_In_Days);

        return total;

    }

    public double CalculateSubTotal(String roomId , String guestId) throws IOException{
        double subTotal = getOrderTotal(roomId) + getRoomTotal(roomId, guestId);
        return subTotal;
    }

    public double CalculateTotal(String roomId, String guestId) throws IOException{

        double subTotal = CalculateSubTotal(roomId, guestId);
        double total  = subTotal * (serviceCharge + gst);
        return total;

    }


    public static void updatePayment(Payment payment) throws IOException {
        ArrayList<Payment> PaymentList = getAllPayments();
        for (int i=0; i<PaymentList.size(); i++) {
            Payment curr = (Payment) PaymentList.get(i);
            if (curr.getGuestId().equals(payment.getGuestId())){
                PaymentList.set(i, payment);
            }
        }
        saveAllPayments(PaymentList);
    }

    public static void deletePayment(Payment payment) throws IOException {
        ArrayList<Payment> paymentList = getAllPayments();
        for (int i=0; i<paymentList.size(); i++) {
            Payment curr = (Payment) paymentList.get(i);
            if (curr.getGuestId().equals(payment.getGuestId())){
                paymentList.remove(i);
            }
        }
        saveAllPayments(paymentList);
    }


    public static ArrayList viewPayment(Payment payment) throws IOException{

        ArrayList<String[]> toDisplay = new ArrayList<String[]>();
        String[] toAddPayment = new String[]{
                payment.getGuestId(),
                String.valueOf(payment.getSubTotal()),
                String.valueOf(payment.getTotal()),
                payment.getReservationNum(),
                String.valueOf(payment.getDate()),
            };

            ArrayList<String> orders = payment.getOrders();

            for (int j=0; j<orders.size(); j++) {
                String[] toadd = new String[] {
                    orders.get(j)
                };
                toAddPayment = ArrayUtils.addAll(toAddPayment, toadd);
            }

        toDisplay.add(toAddPayment);
        return toDisplay;

    }




    public static void main(String[] args) throws IOException {
        PaymentController p = new PaymentController();
        p.getAllPayments();
        
    }
    
}



