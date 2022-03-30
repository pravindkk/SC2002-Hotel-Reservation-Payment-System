package com.hotel.system;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Payment {

    private int paymentId;
    private double subTotal;
    private double total;
    private String reservationNum;
    private ArrayList<Order> orders;
    private SimpleDateFormat date;

    Payment(int paymentId , ArrayList<Order> orders , String reservationNum , SimpleDateFormat date){
        this.paymentId = paymentId;
        this.orders = orders;
        this.reservationNum=reservationNum;
        this.date=date;
        this.total = 0.00;
        this.subTotal = 0.00;

    }

    public int getPaymentId(){
        return this.paymentId;
    }

    public void setPaymentId(int paymentId){
        this.paymentId=paymentId;
    }

    public double getSubTotal(){
        return this.subTotal;
    }
    public void setSubTotal(double subTotal){
        this.subTotal = subTotal;
    }

    public double getTotal(){
        return this.total;
    }
    public void setTotal(double total){
        this.total=total;
    }

    public String getReservationNum(){
        return this.reservationNum;
    }
    public void setReservationNum(String reservationNum){
        this.reservationNum=reservationNum;
    }

    public ArrayList<Order> getOrders(){
        return this.orders;
    }

    public void setOrders(ArrayList<Order> order){
        this.orders = order;
    }


    








    
}
