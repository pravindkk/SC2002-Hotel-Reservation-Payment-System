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

    public static ArrayList<Order> getAllOrders() throws IOException {
        return allOrders.read(allOrders.getPath());
    }

    public void createOrderItem(Order order, Integer itemId) throws IOException {
        Item item = MenuController.getItem(itemId);
        if (item != null) order.addItem(item);
        else System.out.println("This item does not exist");
    }

    public void updateOrder(Order order) throws IOException {
        ArrayList<Order> orderList = getAllOrders();
        orderList.remove(order);
        // checkID(); 
        orderList.add(order);
        saveAllItems(orderList);
    }

    public void deleteOrder(Order order) throws IOException {
        ArrayList<Order> orderList = getAllOrders();
        orderList.remove(order);
        saveAllItems(orderList);
    }

    public static Order getOrderById(String orderID) throws IOException {
        ArrayList<Order> orderList = getAllOrders();
        for (Order order : orderList) {
            if (order.getOrderId().equals(orderID))
                return order;
        }
        return null;
    }

    public ArrayList<Order> getOrderListByRoomId(String roomID) throws IOException {
    	ArrayList<Order> ol = new ArrayList<Order>();
        ArrayList<Order> orderList = getAllOrders();
    	for (Order order : orderList) {
            if (order.getRoomId().equals(roomID))
                ol.add(order);
        }
    	if(ol.size() > 0) return ol;
    	else return null;
    }

    public void displayOrder(String orderID) throws IOException {
        Order order;
        order = getOrderById(orderID);
        // if (order == null) System.out.println("nope");
        order.viewOrder();
    }

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

    public static void saveAllItems(ArrayList toWrite){
        try {
            allOrders.save(allOrders.getPath(), toWrite);
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println("Item not added!");
            System.out.println(e);
        }
    }

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