package com.hotel.system;

import java.util.ArrayList;
import java.util.Date;

import com.hotel.system.enums.OrderStatus;

public class Order {
    private String orderId;
    private String roomId;
    private String reservationNum;
    private ArrayList<Item> items;
    private Date date;
    private OrderStatus orderStatus;
    private String remarks;

    public Order(String orderId, String roomId, String reservationNum, ArrayList<Item> items, Date date, OrderStatus orderStatus, String remarks) {
        this.orderId = orderId;
        this.roomId = roomId;
        this.reservationNum = reservationNum;
        this.items = items;
        this.date = date;
        this.orderStatus = orderStatus;
        this.remarks = remarks;
    }

    public String getOrderId() {
        return this.orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getRoomId() {
        return this.roomId;
    }
    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getReservationNum() {
        return this.reservationNum;
    }
    public void setReservationNum(String reservationNum) {
        this.reservationNum = reservationNum;
    }

    public ArrayList<Item> getItem() {
        return this.items;
    }
    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public OrderStatus getOrderStatus() {
        return this.orderStatus;
    }
    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getDate() {
        return this.date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public String getRemarks() {
        return this.remarks;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }


}