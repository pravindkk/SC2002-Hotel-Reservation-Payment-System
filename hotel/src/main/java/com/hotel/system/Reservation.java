package com.hotel.system;

import java.util.Date;

// import system.enums.Enums.*;
// import system.enums.*;
import com.hotel.system.enums.*;


public class Reservation {

    private ReservationStatus reservationStatus;
    private String reservationNum;
    private String guestId;
    private String roomId;
    private Date checkInDate;
    private Date checkOutDate;
    private Integer numOfAdults;
    private Integer numOfChilden;

    public Reservation(ReservationStatus reservationStatus, String reservationNum, String guestId, String roomId, Date checkIn, 
        Date checkOut, Integer numOfAdults, Integer numOfChilden) {

        // this.roomStatus = roomStatus;
        this.reservationNum = reservationNum;
        this.guestId = guestId;
        this.roomId = roomId;
        this.checkInDate = checkIn;
        this.checkOutDate = checkOut;
        this.numOfAdults = numOfAdults;
        this.numOfChilden = numOfChilden;

    }


    public ReservationStatus getReservationStatus() {
        return this.reservationStatus;
    }
    public void setReservationStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public String getReservationNum() {
        return this.reservationNum;
    }
    public void setReservationNum(String reservationNum) {
        this.reservationNum = reservationNum;
    }

    public String getGuestId() {
        return this.guestId;
    }
    public void setGuestId(String guestId) {
        this.guestId = guestId;
    }

    public String getRoomId() {
        return this.roomId;
    }
    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public Date getCheckInDate() {
        return this.checkInDate;
    }
    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return this.checkOutDate;
    }
    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public Integer getNumOfAdults() {
        return this.numOfAdults;
    }
    public void setNumOfAdults(Integer numOfAdults) {
        this.numOfAdults = numOfAdults;
    }

    public Integer getNumOfChildren() {
        return this.numOfChilden;
    }
    public void setNumOfChildren(Integer numOfChildren) {
        this.numOfChilden = numOfChildren;
    }

}
