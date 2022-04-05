package com.hotel.system;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Represents the class of Payment, which holds the information at the instance the payment is made
 * @author Quek Kar Min
 * @version 1.0
 * @since 1.0
 */

public class Payment {
    private String paymentId;
    private String guestId;
    private double subTotal;
    private double total;
    private String reservationNum;
    private Date date;
    private ArrayList<String> orders; 
    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * Constructor for Payment with all fields filled
     * @param paymentId         uniquely identifies the payment instance
     * @param guestId           uniquely identifies the guest that made the payment
     * @param orders            an array of room service orders that were made in relation to this reservation
     * @param reservationNum    uniquely identifies the reservation that this payment is made for
     * @param date              the date that the payment was made
     * @param total             the total price to pay
     * @param subTotal          the subtotal price to pay
     */
    public Payment(String paymentId, String guestId , ArrayList<String> orders , String reservationNum , Date date, double total, double subTotal){
        this.paymentId = paymentId;
        this.guestId = guestId;     
        this.orders = orders;
        this.reservationNum=reservationNum;
        this.date=date;
        this.total = total;
        this.subTotal = subTotal;

    }

    /**
     * Constructor for Payment with only order array and reservation number
     * @param orders            an array of room service orders that were made in relation to this reservation
     * @param reservationNum    uniquely identifies the reservation that this payment is made for
     * @throws ParseException
     */
    public Payment(ArrayList<String> orders, String reservationNum) throws ParseException {
    	this.paymentId = paymentId;
    	this.reservationNum = reservationNum;
        this.orders = orders;
        // Calendar c = Calendar.getInstance();
        String d = df.format(Calendar.getInstance().getTime());
        this.date = df.parse(d);
    }


    
    /** 
     * Returns paymentId as String
     * @return String
     */
    public String getPaymentId() {
        return this.paymentId;
    }

    
    /** 
     * Takes String paymentId and sets it as the new paymentId
     * @param paymentId uniquely identifies the instance of payment
     */
    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    
    /** 
     * Returns guestId as String
     * @return String
     */
    public String getGuestId(){
        return this.guestId;
    }

    
    /** 
     * Takes String guestId and sets it as the new guestId
     * @param guestId uniquely identifies the guest that is making the payment
     */
    public void setGuestId(String guestId){
        this.guestId=guestId;
    }

    
    /** 
     * Returns subTotal as double
     * @return double
     */
    public double getSubTotal(){
        return this.subTotal;
    }
    
    /** 
     * Takes double subTotal and sets it as the new subTotal
     * @param subTotal is the subtotal price to pay
     */
    public void setSubTotal(double subTotal){
        this.subTotal = subTotal;
    }

    
    /** 
     * Returns total as double
     * @return double
     */
    public double getTotal(){
        return this.total;
    }
    
    /** 
     * Takes double total and sets it as the new total
     * @param total is the total price to pay
     */
    public void setTotal(double total){
        this.total=total;
    }

    
    /** 
     * Returns reservationNum as String
     * @return String
     */
    public String getReservationNum(){
        return this.reservationNum;
    }
    
    /** 
     * Takes String reservationNum and sets it as the new reservationNum
     * @param reservationNum uniquely identifies the reservation that this payment is related to
     */
    public void setReservationNum(String reservationNum){
        this.reservationNum=reservationNum;
    }

    
    /** 
     * Returns the array of orders that is related to this payment
     * @return ArrayList<String>
     */
    public ArrayList<String> getOrders(){
        return this.orders;
    }

    
    /** 
     * Takes an ArrayList<String> order and sets it as the new orders
     * @param order is a list of all the orders that are related to the payment
     */
    public void setOrders(ArrayList<String> order){
        this.orders = order;
    }

    
    /** 
     * Returns date as Date
     * @return Date
     */
    public Date getDate(){
        return this.date;
    }
    
    /** 
     * Takes Date date and sets it as the new date
     * @param date is the date at which the payment is made
     */
    public void setDate(Date date){
        this.date = date;
    }





    








    
}
