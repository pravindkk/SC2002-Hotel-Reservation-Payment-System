package com.hotel.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import com.hotel.db.OrderDB;
import com.hotel.system.Order;

public class OrderController {
    static Scanner sc = new Scanner(System.in);
    static SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    static OrderDB allOrders= new OrderDB();

    public static ArrayList getAllOrders() throws IOException {
        ArrayList allData = allOrders.read(allOrders.getPath());
        return allData;
    }

    public static void saveOrders(ArrayList toWrite) {
        try {
            allOrders.save(allOrders.getPath(), toWrite);
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println("Order not added!");
            System.out.println(e);
        }
    }

    public void saveSpecificOrderById(Order newOrder) throws IOException {
        ArrayList allData = getAllOrders();

        for (int i=0; i<allData.size(); i++) {
            Order order = (Order) allData.get(i);
            if (newOrder.getOrderId().equals(order.getOrderId())) {
                allData.set(i, newOrder);
                saveOrders(allData);
                return;
            }
        }
        System.out.println("couldnt save order");
    }

    public Order getOrderById(String orderId) throws IOException {
        ArrayList allData = getAllOrders();

        for (int i=0; i<allData.size(); i++) {
            Order order = (Order) allData.get(i);
            if (orderId.equals(order.getOrderId())) {
                return order;
            }
        }
        return null;
    }

    public Order createOrder() {

        
        return null;
    }

}
