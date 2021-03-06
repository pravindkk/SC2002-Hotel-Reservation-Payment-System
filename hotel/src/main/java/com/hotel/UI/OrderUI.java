package com.hotel.UI;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import com.hotel.controller.MenuController;
import com.hotel.controller.OrderController;
import com.hotel.controller.ReservationController;
import com.hotel.controller.RoomController;
import com.hotel.system.Item;
import com.hotel.system.Order;
import com.hotel.system.Reservation;
import com.hotel.system.enums.OrderStatus;

/**
* Represents the class of OrderUI, which prints the UI for Order-related operations 
 * @author Melissa Ng Li Yun
 * @version 1.0
 * @since 1.0
 */

public class OrderUI {
    static Scanner sc = new Scanner(System.in);
    OrderController orderController = new OrderController();
    static SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

    
    /**
     * This method runs the relevant operations to create a new order.
     * It obtains all inputs then creates the new order
     */
    public void createNewOrder() {
        // gesc = new Scanner(System.in);
    	String roomId;
        String reservationNum=null;
        // sc.nextLine();
        System.out.println("Enter room number:");
        roomId = sc.nextLine();
        System.out.println("");
        try {
			Reservation r = ReservationController.getReservationByRoomId(roomId);
			if(r == null) {
				System.out.println("Room is not checked-in by anyone.");
				return;
			}
			else reservationNum = r.getReservationNum();
		} catch (IOException e) {
			e.printStackTrace();
		}
        // updateOrder(order);
        try {
            MenuController.printAllItems();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Integer choice=0;
        ArrayList<Item> itemArray = new ArrayList<Item>();
        System.out.println("\nEnter the ID of the item that you want (-1) to exit): ");
        do {
            try {
                choice = sc.nextInt();
                if (choice == -1) break;


                Item item = MenuController.getItem(choice);
                if (item == null) System.out.println("Item does not exist!");
                else itemArray.add(item);
            } catch (Exception e) {
                //TODO: handle exception
            }
            
        } while (true);
        sc.nextLine();
        System.out.print("Are there are remarks? ");
        String remarks = sc.nextLine();
        // Date date = new Date();
        // Date todayDate = new Date();
        Date todayDate = Calendar.getInstance().getTime();


        String today = df.format(todayDate);
        Date date=null;
        try {
            date = df.parse(today);
        } catch (ParseException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        // Integer orderId = ThreadLocalRandom.current().nextInt(1, 100 + 1);
        int a = (int) (Math.random()*(200-1)+1);
        String orderId = roomId.charAt(1) + "-" + reservationNum + "-" + String.valueOf(a);
        Order toAdd = new Order(orderId, roomId, reservationNum, itemArray, date, OrderStatus.PREPARING, remarks);

        try {
            OrderController.saveOneOrder(toAdd);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    /** 
     * This method runs the relevant operations to update an Order
     * It obtains all relevant inputs then updates the order
     */
    public static void updateOrder() {
        
        Order order=null;
        // sc.nextLine();
        do {
            String orderId=null;
            System.out.println("Enter the orderId to be updated?:  ");
            try {
                orderId = sc.next();
                if(orderId.equals("")) throw new Exception();
            } catch (Exception e) {
                //TODO: handle exception
                orderId = sc.next();
            }
            try {
                order = OrderController.getOrderById(orderId);
                if (order == null) System.out.println("order doesnt exist");
                else break;
            } catch (IOException e1) {
                orderId = sc.next();
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        } while (true);
        
        if(order.getOrderStatus()==OrderStatus.DELIVERED || order.getOrderStatus()==OrderStatus.PAID){
            System.out.println("Unable to make changes to Order");
            return;
        }
        order.viewOrder();
        int choice =0;
        do {
            System.out.print(
                "(1) Add item\n" +
                "(2) Remove item\n" + 
                "(3) Update Status\n" +
                "(4) Change Remarks\n" + 
                "(5) Back\n" +
                "Please Choose a option to Continue: "
            );

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    try {
                        MenuController.printAllItems();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    
                    try {
                        Integer itemId = sc.nextInt();
                        Item item=null;
                        item = MenuController.getItem(itemId);
                        if (item == null) break;
                        order.addItem(item);
                        // OrderController.saveOneOrder(order);
                        OrderController.updateOrder(order);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    
                    break;

                case 2:
                    try {
                        Integer itemId = sc.nextInt();
                        Item item=null;
                        item = MenuController.getItem(itemId);
                        if (item == null) break;
                        order.removeItem(item);
                        OrderController.updateOrder(order);
                    } catch (Exception e) {
                        //TODO: handle exception
                    }
                    break;

                case 3:
                    System.out.println(
                        "(1) Preparing" +
                        "(2) Prepared"+
                        "(3) Delivered"
                    );

                    try {
                        Integer newChoice = sc.nextInt();
                        switch (newChoice) {
                            case 1:
                                order.setOrderStatus(OrderStatus.PREPARING);
                                OrderController.updateOrder(order);
                                break;
                            case 2:
                                order.setOrderStatus(OrderStatus.PREPARED);
                                OrderController.updateOrder(order);
                                break;
                            case 3:
                                order.setOrderStatus(OrderStatus.DELIVERED);
                                OrderController.updateOrder(order);
                                break;
                        
                            default:
                                System.out.println("Invalid input");
                                break;
                        }

                    } catch (Exception e) {
                        //TODO: handle exception
                    }
                    break;
                
                case 4:
                    String remark =null;
                    try {
                        System.out.print("What is the remarks?:  ");
                        sc.nextLine();
                        remark = sc.nextLine();
                        if (remark != null || remark != "") order.setRemarks(remark);
                        OrderController.updateOrder(order);
                    } catch (Exception e) {
                        //TODO: handle exception
                    }
                    break;
                    
                case 5:
                    return;
            
                default:
                    System.out.println("Invalid Input");
                    break;
            }
        } while (choice != 5);
        



    }

    
    /** 
     * This method runs the relevant operations and deletes an order
     * It obtains the relevant inputs then deletes the user-specified order
     * @throws IOException
     */
    public static void deleteOrder() throws IOException {
        // ArrayList<Order> allOrders = OrderController.getAllOrders();
        
        String orderId=null;
        Order order=null;
        // sc.nextLine();
        do {
            try {
                System.out.println("Enter order ID to be deleted: ");
                orderId = sc.nextLine();
                // if(id <= 0) System.out.printf("Invalid input! ");
                order = OrderController.getOrderById(orderId);
                if (order != null) break;
                else if (order == null) {
                    System.out.println("Cannot find order");
                    return;
                }
            } catch (Exception e) {
                System.out.printf("Invalid input of orderId! ");
            }
            sc.nextLine();
        } while (true);

        OrderController.deleteOrder(order);

    }


    /**
     * This method displays the details of the order to the user
     */
    public static void displayOrder() {
        Order order=null;
        String orderId = null;
        System.out.print("What is the orderId?: ");
        try {
            orderId = sc.next();
            if(orderId.equals("")) throw new Exception();
        } catch (Exception e) {
            //TODO: handle exception
            orderId = sc.next();
        }
        try {
            order = OrderController.getOrderById(orderId);
            if (order == null) {
                System.out.println("ERROR!! order doesnt exist!!");
                return;
            }
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        try {
            OrderController.displayOrder(order.getOrderId());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }


    /**
     * This method displays the details of all orders to the user
     */
    public static void displayAllOrders() {
        try {
            ArrayList<Order> orders = OrderController.getAllOrders();
            for (Order order : orders) {
                if (order.getOrderStatus().equals(OrderStatus.PREPARING) || order.getOrderStatus().equals(OrderStatus.PREPARED))
                    order.viewOrder();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

}
