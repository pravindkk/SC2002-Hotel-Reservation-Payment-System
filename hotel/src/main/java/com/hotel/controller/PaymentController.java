package com.hotel.controller;

import com.hotel.db.PaymentDB;
import com.hotel.system.Payment;
import com.hotel.system.Order;
import com.hotel.system.Item;

import java.io.IOException;
import java.util.ArrayList;

public class PaymentController {

    static PaymentDB allPayments= new PaymentDB();

    static private double serviceCharge;
    static private double gst;

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

    public static void main(String[] args) throws IOException {
        PaymentController p = new PaymentController();
        p.getAllPayments();
        
    }
    
}
