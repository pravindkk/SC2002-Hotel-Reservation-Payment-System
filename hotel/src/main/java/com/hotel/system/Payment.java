package com.hotel.system;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Payment {
    private String paymentId;
    private String guestId;
    private double subTotal;
    private double total;
    private String reservationNum;
    private Date date;
    private ArrayList<String> orders; 
    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

    public Payment(String paymentId, String guestId , ArrayList<String> orders , String reservationNum , Date date, double total, double subTotal){
        this.paymentId = paymentId;
        this.guestId = guestId;     
        this.orders = orders;
        this.reservationNum=reservationNum;
        this.date=date;
        this.total = total;
        this.subTotal = subTotal;

    }

    public Payment(ArrayList<String> orders, String reservationNum) throws ParseException {
    	this.paymentId = paymentId;
    	this.reservationNum = reservationNum;
        this.orders = orders;
        // Calendar c = Calendar.getInstance();
        String d = df.format(Calendar.getInstance().getTime());
        this.date = df.parse(d);
    }


    public String getPaymentId() {
        return this.paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
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

    public Date getDate(){
        return this.date;
    }
    public void setDate(Date date){
        this.date = date;
    }





    








    
}
