package com.hotel.UI;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

public class OrderUI {
    static Scanner sc = new Scanner(System.in);
    OrderController orderController = new OrderController();
    static SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

    public OrderUI () {
        int choice  = displayOptions();

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
        
            default:
                System.out.println("Invalid input");
                break;
        }

    }


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
                               "========================\n"
            );

            System.out.print("What is your choice (1-4)?: ");
            try {
                choice = sc.nextInt();
                if (choice >0 && choice <5) break;
            } catch (Exception e) {
                //TODO: handle exception
                System.out.println("Enter a number between (1-4)!!");
            }
        } while (true);

        return choice;
    }

    public void createNewOrder() {
        // gesc = new Scanner(System.in);
    	String roomId;
        String reservationNum=null;
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
        System.out.println("Enter the ID of the item that you want (-1) to exit): ");
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
        System.out.print("Are there are remarks? ");
        String remarks = sc.nextLine();
        Date date = new Date();
        
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
        String orderId=null;
        Order order=null;
        do {
            try {
                System.out.println("Enter order ID to be updated: ");
                orderId = sc.nextLine();
                // if(id <= 0) System.out.printf("Invalid input! ");
                order = OrderController.getOrderById(orderId);
                if (order == null) orderId=null;
            } catch (Exception e) {
                System.out.printf("Invalid input of orderId! ");
            }
            sc.nextLine();
        } while (orderId==null);
        // Order order = OrderController.retrieveOrder(orderId);
        // if (order != null) updateOrder(order);
        // else System.out.println("Order does not exist!");
        order.viewOrder();
        int choice =0;
        do {
            System.out.println("Please Choose a option to Continue: \n" + 
                "(1) Add item" +
                "(2) Remove item" + 
                "(3) Update Status" +
                "(4) Change Remarks" 
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
                        OrderController.saveOneOrder(order);
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
                        OrderController.saveOneOrder(order);
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
                                OrderController.saveOneOrder(order);
                                break;
                            case 2:
                                order.setOrderStatus(OrderStatus.PREPARED);
                                OrderController.saveOneOrder(order);
                                break;
                            case 3:
                                order.setOrderStatus(OrderStatus.DELIVERED);
                                OrderController.saveOneOrder(order);
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
                        remark = sc.nextLine();
                        if (remark != null || remark != "") order.setRemarks(remark);
                        OrderController.saveOneOrder(order);
                    } catch (Exception e) {
                        //TODO: handle exception
                    }
                    break;
                    

            
                default:
                    System.out.println("Invalid Input");
                    break;
            }
        } while (choice != 5);
        



    }


    public static void deleteOrder() throws IOException {
        ArrayList<Order> allOrders = OrderController.getAllOrders();
        
        String orderId=null;
        Order order=null;
        do {
            try {
                System.out.println("Enter order ID to be updated: ");
                orderId = sc.nextLine();
                // if(id <= 0) System.out.printf("Invalid input! ");
                order = OrderController.getOrderById(orderId);
                if (order == null) orderId=null;
            } catch (Exception e) {
                System.out.printf("Invalid input of orderId! ");
            }
            sc.nextLine();
        } while (orderId==null);

        allOrders.remove(order);
        MenuController.saveAllItems(allOrders);

    }


    







}
