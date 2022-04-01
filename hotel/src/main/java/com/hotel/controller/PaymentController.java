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

public class PaymentController {

    static PaymentDB allPayments= new PaymentDB();

    static OrderController orderController = new OrderController();

    static private double serviceCharge = 0.10;
    static private double gst = 0.07;


   

    public static ArrayList getAllPayments() throws IOException {
        ArrayList allData = allPayments.read(allPayments.getPath());
        return allData;
    }


    public static void savePayments(ArrayList toWrite) throws IOException {
        try {
            allPayments.save(allPayments.getPath(), toWrite);
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println("Order not added!");
            System.out.println(e);
        }
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

    public static void main(String[] args) throws IOException {
        PaymentController p = new PaymentController();
        p.getAllPayments();
        
    }
    
}

// use order controller to calculate all the orders
// use reservation controller to get check in and check out date ---> no of days
// use reservation controller to get room id,
// use roomc controller to get room price


// display an array


