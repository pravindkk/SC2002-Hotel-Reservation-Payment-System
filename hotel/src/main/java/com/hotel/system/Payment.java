package com.hotel.system;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Payment {

    private String guestId;
    private double subTotal;
    private double total;
    private String reservationNum;
    private SimpleDateFormat date;
    private ArrayList<String> orders; 

    public Payment(String guestId , ArrayList<String> orders , String reservationNum , SimpleDateFormat date, double total, double subTotal){
        this.guestId = guestId;     
        this.orders = orders;
        this.reservationNum=reservationNum;
        this.date=date;
        this.total = total;
        this.subTotal = subTotal;

    }


    public String getGuestId(){
        return this.guestId;
    }

    public void setGuestId(String guestId){
        this.guestId=guestId;
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
