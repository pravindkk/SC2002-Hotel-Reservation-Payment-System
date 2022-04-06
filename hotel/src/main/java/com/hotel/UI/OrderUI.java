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
 * Represents the class of Payment, which holds the information at the instance the payment is made
 * @author Melissa Ng Li Yun
 * @version 1.0
 * @since 1.0
 */

public class OrderUI {
    static Scanner sc = new Scanner(System.in);
    OrderController orderController = new OrderController();
    static SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

    public OrderUI () {
        int choice =0;
        do {
            choice  = displayOptions();
            switch (choice) {
                case 1:
                    createNewOrder();
                    break;
                case 2:
                    updateOrder();
                    break;
                
                case 3:
                    try {
                        deleteOrder();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    displayOrder();
                    break;

                case 5:
                    return;
            
                default:
                    System.out.println("Invalid input");
                    break;
            }
        } while (true);
        

        

    }


    
    /** 
     * @return int
     */
    public int displayOptions() {
        int choice;
        do {
            System.out.println("========================\n" + 
                               "Room Service Orders\n"+
                               "========================\n"+
                               "(1) Create Order\n"+
                               "(2) Update Order\n"+
                               "(3) Remove Order\n"+
                               "(4) View Order\n"+
                               "(5) Back\n"+
                               "========================\n"
            );

            System.out.print("What is your choice (1-5)?: ");
            try {
                choice = sc.nextInt();
                if (choice >0 && choice <6) break;
            } catch (Exception e) {
                //TODO: handle exception
                System.out.println("Enter a number between (1-5)!!");
            }
        } while (true);

        return choice;
    }

    public void createNewOrder() {
        // gesc = new Scanner(System.in);
    	String roomId;
        String reservationNum=null;
        sc.nextLine();
        System.out.println("Enter room number:");
        roomId = sc.nextLine();
        System.out.println("");
        // OrderController.checkID();
        // Order order = new Order(roomId);
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
        String orderId = roomId.charAt(0) + "-" + reservationNum.charAt(0) + "-" + df.format(date).replaceAll("\\D", "");;
        Order toAdd = new Order(orderId, roomId, reservationNum, itemArray, date, OrderStatus.PREPARING, remarks);

        try {
            OrderController.saveOneOrder(toAdd);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // new Order(orderId, roomId, reservationNum, items, date, orderStatus, remarks)

        // new Order(orderId, roomId, reservationNum, items, date, orderStatus, remarks)
        // OrderController.saveOrders(toWrite);
    }

    public static void updateOrder() {
        // String orderId=null;
        Order order=null;
        sc.nextLine();
        try {
            System.out.print("Enter the orderId to be updated?:  ");
            String orderId = sc.nextLine();
            order = OrderController.getOrderById(orderId);
            if (order == null) System.out.println("order doesnt exit");
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        // do {
        //     try {
                
        //         System.out.println("Enter order ID to be updated: ");
        //         orderId = sc.nextLine();
        //         // if(id <= 0) System.out.printf("Invalid input! ");
        //         order = OrderController.getOrderById(orderId);
        //         // if (order == null) orderId=null;
        //     } catch (Exception e) {
        //         System.out.println("Invalid input of orderId! ");
        //     }
        //     // sc.nextLine();
        // } while (order==null);
        // Order order = OrderController.retrieveOrder(orderId);
        // if (order != null) updateOrder(order);
        // else System.out.println("Order does not exist!");
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
     * @throws IOException
     */
    public static void deleteOrder() throws IOException {
        // ArrayList<Order> allOrders = OrderController.getAllOrders();
        
        String orderId=null;
        Order order=null;
        sc.nextLine();
        do {
            try {
                System.out.println("Enter order ID to be deleted: ");
                orderId = sc.nextLine();
                // if(id <= 0) System.out.printf("Invalid input! ");
                order = OrderController.getOrderById(orderId);
                if (order != null) break;
            } catch (Exception e) {
                System.out.printf("Invalid input of orderId! ");
            }
            sc.nextLine();
        } while (true);

        OrderController.deleteOrder(order);

    }

    public static void displayOrder() {
        Order order=null;
        sc.nextLine();
        try {
            System.out.print("What is the orderId?: ");
            String orderId = sc.nextLine();
            order = OrderController.getOrderById(orderId);
            if (order == null) System.out.println("order doesnt exit");
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


    







}
