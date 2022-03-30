package com.hotel.system;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Payment {

    private Integer paymentId;
    private double subTotal;
    private double total;
    private String reservationNum;
    private SimpleDateFormat date;
    private ArrayList<Order> orders;

    Payment(Integer paymentId , ArrayList<Order> orders , String reservationNum , SimpleDateFormat date){
        this.paymentId = paymentId;
        this.orders = orders;
        this.reservationNum=reservationNum;
        this.date=date;
        this.total = 0.00;
        this.subTotal = 0.00;

    }

    Payment(Integer paymentId , ArrayList<Order> orders , String reservationNum , SimpleDateFormat date, double total, double subTotal){
        this.paymentId = paymentId;
        this.orders = orders;
        this.reservationNum=reservationNum;
        this.date=date;
        this.total = total;
        this.subTotal = subTotal;

    }



    public int getPaymentId(){
        return this.paymentId;
    }

    public void setPaymentId(Integer paymentId){
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

    public SimpleDateFormat getDate(){
        return this.date;
    }
    public void setDate(SimpleDateFormat date){
        this.date = date;
    }


    








    
}
