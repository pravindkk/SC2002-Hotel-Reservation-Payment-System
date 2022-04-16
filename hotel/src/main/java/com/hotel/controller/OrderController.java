package com.hotel.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.hotel.db.OrderDB;
import com.hotel.system.Item;
import com.hotel.system.Order;

/**
 * Represents the controller function of Order
 * @author Vignesh Ezhil
 * @version 1.0
 * @since 1.0
 */


public class OrderController {
    static OrderDB allOrders = new OrderDB();
    Scanner sc = new Scanner(System.in);

    
    /** 
     * Gets and returns all Orders
     * @return ArrayList returns an ArrayList of all the Orders stored in the database
     * @throws IOException Due to communication with the DataBase IOexception is required
     */
    public static ArrayList<Order> getAllOrders() throws IOException {
        return allOrders.read(allOrders.getPath());
    }

    
    /** 
     * Creates an Order object
     * @param order Order object in which the Item needs to be added in 
     * @param itemId Integer input of itemtID is entered so that the corresponding item is added to the Order object
     * @throws IOException IOException Due to communication with the DataBase IOexception is required
     */
    public void createOrderItem(Order order, Integer itemId) throws IOException {
        Item item = MenuController.getItem(itemId);
        if (item != null) order.addItem(item);
        else System.out.println("This item does not exist");
    }

    
    /** 
     * Updates an Order object
     * @param order Order object in which the order needs to be updated. 
     * Once order is updated, it is stored in the database
     * @throws IOException IOException Due to communication with the DataBase IOexception is required
     */
    public static void updateOrder(Order order) throws IOException {
        ArrayList<Order> orderList = getAllOrders();
        for (int i=0; i<orderList.size(); i++) {
            Order curr = (Order) orderList.get(i);
            if (curr.getOrderId().equals(order.getOrderId())){
                orderList.set(i, order);
            }
        }
        saveAllItems(orderList);
    }

    
    /** 
     * Deletes an Order object
     * @param order Order object in which the order needs to be updated. 
     * @throws IOException OException Due to communication with the DataBase IOexception is required
     */
    public static void deleteOrder(Order order) throws IOException {
        ArrayList<Order> orderList = getAllOrders();
        for (int i=0; i<orderList.size(); i++) {
            Order curr = (Order) orderList.get(i);
            if (curr.getOrderId().equals(order.getOrderId())){
                orderList.remove(i);
            }
        }
        saveAllItems(orderList);
    }

    
    /** 
     * Gets and returns an Order with the corresponding ID parameter
     * @param orderID String input of orderID is entered so that the corresponding order object is retrieved
     * @return Corresponding record for the input OrderID
     * @throws IOException IOException OException Due to communication with the DataBase IOexception is required
     */
    public static Order getOrderById(String orderID) throws IOException {
        ArrayList<Order> orderList = getAllOrders();
        for (Order order : orderList) {
            if (order.getOrderId().equals(orderID))
                return order;
        }
        return null;
    }

    
    /** 
     * Gets the list of Orders related to a roomID
     * @param roomID String input of roomID is entered so that the corresponding order object is retrieved
     * @return Returns an ArrayList<Order> of corresponding orders from the databse
     * @throws IOException IOException OException Due to communication with the DataBase IOexception is required
     */
    public static ArrayList<Order> getOrderListByRoomId(String roomID) throws IOException {
    	ArrayList<Order> ol = new ArrayList<Order>();
        ArrayList<Order> orderList = getAllOrders();
    	for (Order order : orderList) {
            if (order.getRoomId().equals(roomID))
                ol.add(order);
        }
    	if(ol.size() > 0) return ol;
    	else return null;
    }

    
    /** 
     * Displays all details of one specified Order, specified by orderID
     * @param orderID String input of orderID is entered so that the corresponding order object could be displayed
     * @throws IOException IOException OException Due to communication with the DataBase IOexception is required
     */
    public static void displayOrder(String orderID) throws IOException {
        Order order;
        order = getOrderById(orderID);
        // if (order == null) System.out.println("nope");
        order.viewOrder();
    }

    
    /** 
     * Adds an Item into an Order
     * @param item Item is passed in as input so that the item in the orders could be updated
     * @throws IOException IOException OException Due to communication with the DataBase IOexception is required
     */
    public void updateItemInOrders(Item item) throws IOException {
        ArrayList<Order> orderList = getAllOrders();
        for (Order order : orderList) {
            ArrayList<Item> items = order.getItem();
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).getItemId() == item.getItemId()) {
                    items.set(i, item);
                    System.out.println(String.format("Order %d was updated.", order.getOrderId()));
                }
            }
        }
        // this.savetoDB();
        saveAllItems(orderList);
        
    }

    
    /** 
     * Saves all Items into the database
     * @param toWrite Contains an ArrayList of all the Orders that is going to be stored in the database
     */
    public static void saveAllItems(ArrayList toWrite){
        try {
            allOrders.save(allOrders.getPath(), toWrite);
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println("Item not added!");
            System.out.println(e);
        }
    }

    
    /** 
     * Saves one Order into database
     * @param newOrder Order is passed as input so that it can be written to the database
     * @throws IOException IOException OException Due to communication with the DataBase IOexception is required
     */
    public static void saveOneOrder(Order newOrder) throws IOException {
        ArrayList<Order> orders = getAllOrders();
        orders.add(newOrder);
        try {
            allOrders.save(allOrders.getPath(), orders);
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println("Item not added!");
            System.out.println(e);
        }
    }
    



}