package com.hotel.system;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.hotel.system.enums.OrderStatus;

/**
 * Represents the class of Order, which represents an order for Room Service
 * @author Quek Kar Min
 * @version 1.0
 * @since 1.0
 */

public class Order {
    private String orderId;
    private String roomId;
    private String reservationNum;
    private ArrayList<Item> items;
    private Date date;
    private OrderStatus orderStatus;
    private String remarks;
    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");


    /**
     * Constructor for Order
     * An Order represents a room service order
     * @param orderId           uniquely identifies the order
     * @param roomId            uniquely identifies the room that the order is related to
     * @param reservationNum    uniquely identifies the reservation that the order is related to
     * @param items             an array of items ordered in this order
     * @param date              the date that the order is created
     * @param orderStatus       status of the order
     * @param remarks           any special remarks related to the order
     */
    public Order(String orderId, String roomId, String reservationNum, ArrayList<Item> items, Date date, OrderStatus orderStatus, String remarks) {
        
        this.orderId = orderId;
        this.roomId = roomId;
        this.reservationNum = reservationNum;
        this.items = items;
        this.date = date;
        this.orderStatus = orderStatus;
        this.remarks = remarks;
    }

    
    /** 
     * Returns orderId as String
     * @return String
     */
    public String getOrderId() {
        return this.orderId;
    }
    
    /** 
     * Takes a String orderId and sets it as the new orderId
     * @param orderId uniquely identifies each order
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    
    /** 
     * Returns roomId as String
     * @return String
     */
    public String getRoomId() {
        return this.roomId;
    }
    
    /** 
     * Takes a String roomId and sets it as the new roomId
     * @param roomId uniquely identifies the room that the order is related to
     */
    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    
    /** 
     * Returns reservationNum as String
     * @return String
     */
    public String getReservationNum() {
        return this.reservationNum;
    }
    
    /** 
     * Takes a String reservationNum and sets it as the new reservationNum
     * @param reservationNum uniquely identfies the reservation that the order is related to
     */
    public void setReservationNum(String reservationNum) {
        this.reservationNum = reservationNum;
    }

    
    /** 
     * Returns the array of items in the Order as ArrayList<Item>
     * @return ArrayList<Item>
     */
    public ArrayList<Item> getItem() {
        return this.items;
    }
    
    /** 
     * Takes an Item item and adds it into the array of items
     * @param item represents the Items that the guest has ordered for room service
     */
    public void setItems(Item item) {
        this.items.add(item);
    }

    
    /** 
     * Returns orderStatus as enum Orderstatus
     * @return OrderStatus
     */
    public OrderStatus getOrderStatus() {
        return this.orderStatus;
    }
    
    /** 
     * Takes an OrderStatus orderStatus and sets it as the new orderStatus
     * @param orderStatus identifies the current status of the order (PREPARING, PREPARED, DELIVERED, PAID)
     */
    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    
    /** 
     * Returns date as Date
     * @return Date
     */
    public Date getDate() {
        return this.date;
    }
    
    /** 
     * Takes Date date and sets it as the new date
     * @param date is the date at which the order is created
     */
    public void setDate(Date date) {
        this.date = date;
    }

    
    /** 
     * Returns remarks as String
     * @return String
     */
    public String getRemarks() {
        return this.remarks;
    }
    
    /** 
     * Takes String remarks and sets it as the new remarks
     * @param remarks will note if there are any special remarks related to the Order
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }


    
    /** 
     * Takes an Item item and adds it into the array of items
     * @param item represents the Items that the guest has ordered for room service
     */
    public void addItem(Item item) {
        this.items.add(item);
    }

    
    /** 
     * Takes an Item newitem and removes it from the array
     * If removal is successful, it will return Boolean true
     * If removal is unsuccessful, it will return Boolean false
     * @param newitem
     * @return boolean
     */
    public boolean removeItem(Item newitem) {
        for (Item item : items) {
            if (item.getItemId() == newitem.getItemId()) {
                System.out.println(item.getItemId());
                this.items.remove(item);
                return true;
            }
        }
        return false;
    }
    
    /**
     * This function prints out the items that are present in the order
     */
    public void viewOrder() {

        System.out.println("    ID          Room        Date                 Remarks                  Status   ");
        System.out.println(toString());
        System.out.println("=================================================================================");
        System.out.println("ID   Name                          Description                          Price(S$)");
        System.out.println("=================================================================================");
        for (Item item : items) {
        	System.out.println(item.toString());
        }
        System.out.println("=================================================================================");
    }
    
    
    /** 
     * Used to format printing and returns a String
     * @return String
     */
    public String toString() {
        String newDate = df.format(date);
        return (String.format("%-15s%-11s%-20s%-25s%-10s", orderId, roomId, newDate, remarks, orderStatus));
        // return (String.format("%-5s", orderId));
    }


}
