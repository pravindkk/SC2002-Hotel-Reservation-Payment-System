package com.hotel.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.hotel.db.OrderDB;
import com.hotel.system.Item;
import com.hotel.system.Order;

public class OrderController {
    static OrderDB allOrders = new OrderDB();
    Scanner sc = new Scanner(System.in);

    
    /** 
     * @return ArrayList<Order>
     * @throws IOException
     */
    public static ArrayList<Order> getAllOrders() throws IOException {
        return allOrders.read(allOrders.getPath());
    }

    
    /** 
     * @param order
     * @param itemId
     * @throws IOException
     */
    public void createOrderItem(Order order, Integer itemId) throws IOException {
        Item item = MenuController.getItem(itemId);
        if (item != null) order.addItem(item);
        else System.out.println("This item does not exist");
    }

    
    /** 
     * @param order
     * @throws IOException
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
     * @param order
     * @throws IOException
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
     * @param orderID
     * @return Order
     * @throws IOException
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
     * @param roomID
     * @return ArrayList<Order>
     * @throws IOException
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
     * @param orderID
     * @throws IOException
     */
    public static void displayOrder(String orderID) throws IOException {
        Order order;
        order = getOrderById(orderID);
        // if (order == null) System.out.println("nope");
        order.viewOrder();
    }

    
    /** 
     * @param item
     * @throws IOException
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
     * @param toWrite
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
     * @param newOrder
     * @throws IOException
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