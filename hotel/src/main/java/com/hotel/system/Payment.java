package com.hotel.system;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Payment {

    private Integer paymentId;
    private double subTotal;
    private double total;
    private String reservationNum;
    private SimpleDateFormat date;
    private ArrayList<String> orders; // use string to have an array of orderId

    public Payment(Integer paymentId , ArrayList<String> orders , String reservationNum , SimpleDateFormat date, double total, double subTotal){
        this.paymentId = paymentId;     // change the get set and this also
        this.orders = orders;
        this.reservationNum=reservationNum;
        this.date=date;
        this.total = total;
        this.subTotal = subTotal;

    }

    public Payment(Integer paymentId , ArrayList<String> orders , String reservationNum , SimpleDateFormat date){
        this.paymentId = paymentId;
        this.orders = orders;
        this.reservationNum=reservationNum;
        this.date=date;
        this.total = 0.00;
        this.subTotal = 0.00;

    }





    public Integer getPaymentId(){
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

    public ArrayList<String> getOrders(){
        return this.orders;
    }

    public void setOrders(ArrayList<String> order){
        this.orders = order;
    }

    public SimpleDateFormat getDate(){
        return this.date;
    }
    public void setDate(SimpleDateFormat date){
        this.date = date;
    }


    








    
}
