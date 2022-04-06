package com.hotel.UI;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.hotel.controller.OrderController;
import com.hotel.controller.PaymentController;
import com.hotel.controller.ReservationController;
import com.hotel.controller.RoomController;
import com.hotel.controller.UpdateRoomMenuDisplayUI;
import com.hotel.system.Order;
import com.hotel.system.Payment;
import com.hotel.system.Reservation;
import com.hotel.system.Room;
import com.hotel.system.enums.OrderStatus;

/**
 * Represents the class of PaymentUI, which prints the UI for Payment-related operations
 * @author Melissa Ng Li Yun
 * @version 1.0
 * @since 1.0
 */

public class PaymentUI {
    Scanner sc = new Scanner(System.in);
    PaymentController paymentController = new PaymentController();

    /**
     * This method prints the UI for Payment related operations
     * and runs the user through the relevant operations
     */
    public void printPayment() {
        int method = 2;
        try {
            String roomId = UpdateRoomMenuDisplayUI.updateRoomId();
            if (ReservationController.getReservationByRoomId(roomId) == null) {
                System.out.println("Room not Occupied");
                return;
            }
            Reservation r = ReservationController.getReservationByRoomId(roomId);
            do {
                try {
                    System.out.println("Enter payment method \n"+
                                "(0 - Cash, 1 - Credit Card):");
                    method = sc.nextInt();
                } catch (InputMismatchException e) {
                    System.out.printf("Invalid input! ");
                }
                sc.nextLine(); // clears the buffer
            } while (method != 0 && method != 1);

            double cash = -1;
            ArrayList<Order> orders = OrderController.getOrderListByRoomId(roomId);
            ArrayList<String> newOrders = new ArrayList<String>();
            if(orders != null) { 
                for(Order order : orders) {
                    if (!order.getOrderStatus().equals(OrderStatus.DELIVERED)) {
                        newOrders.add(order.getOrderId());
                    }
                }
            } else newOrders = null;


            // Payment payment = new Payment(paymentId, guestId, orders, reservationNum, date, total, subTotal)


            Payment payment=null;
            try {
                payment = new Payment(newOrders, r.getReservationNum());
            } catch (ParseException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return;
            }
            if(method == 0) {
                do {
                    try {
                        System.out.println("Please enter cash amount:");
                        cash = sc.nextDouble();
                        if(cash <= 0.0) System.out.printf("Invalid input! ");
                    } catch (InputMismatchException e) {
                        System.out.printf("Invalid input! ");
                    }
                    sc.nextLine(); // clears the buffer
                } while (cash <= 0.0);
                double diff = PaymentController.getSubTotal(payment, roomId) - cash;
                double add = -1;
                while(diff > 0) {
                    do {
                        try {
                            System.out.printf("You're short %.2f! Enter cash amount (additional):\n", diff);
                            add = sc.nextDouble();
                        } catch (InputMismatchException e) {
                            System.out.printf("Invalid input! ");
                        }
                        sc.nextLine();
                    } while (add < 0.0);
                    cash += add;
                    diff = PaymentController.getSubTotal(payment, roomId) - cash;
                }
            }

            PaymentController.getPayment(payment, roomId, method, cash);
            PaymentController.createPayment(payment);
            if(orders != null) {
                for(Order order : orders) {
                    order.setOrderStatus(OrderStatus.PAID);
                    OrderController.updateOrder(order);
                }
                // OrderController.savetoDB();
                
            }
            ArrayList payList = PaymentController.getAllPayments();
            payList.add(payment);
            PaymentController.saveAllPayments(payList);
            // PaymentController.getInstance().savetoDB();


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        
    }
}
