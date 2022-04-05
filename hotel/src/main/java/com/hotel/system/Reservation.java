package com.hotel.system;

import java.util.Date;

// import system.enums.Enums.*;
// import system.enums.*;
import com.hotel.system.enums.*;

/**
 * Represents the class of Payment, which holds the information at the instance the payment is made
 * @author Melissa Ng Li Yun
 * @version 1.0
 * @since 1.0
 */

public class Reservation {

    private ReservationStatus reservationStatus;
    private String reservationNum;
    private String guestId;
    private String roomId;
    private Date checkInDate;
    private Date checkOutDate;
    private Integer numOfAdults;
    private Integer numOfChilden;


    /**
     * Constructor for Reservation object
     * @param reservationStatus identifies the status of the reservation (CONFIRMED, CHECKED_IN, CHECKED_OUT, EXPIRED, CANCELLED)
     * @param reservationNum    uniquely identifies the reservation
     * @param guestId           uniquely identifies the guest that is related to the reservation
     * @param roomId            uniquely identifies the room that is related to the reservation
     * @param checkIn           the date at which the guest checks in
     * @param checkOut          the date at which the guest checks out
     * @param numOfAdults       the number of adults in the room
     * @param numOfChilden      the number of children in the room
     */
    public Reservation(ReservationStatus reservationStatus, String reservationNum, String guestId, String roomId, Date checkIn, 
        Date checkOut, Integer numOfAdults, Integer numOfChilden) {

        // this.roomStatus = roomStatus;
        this.reservationStatus = reservationStatus;
        this.reservationNum = reservationNum;
        this.guestId = guestId;
        this.roomId = roomId;
        this.checkInDate = checkIn;
        this.checkOutDate = checkOut;
        this.numOfAdults = numOfAdults;
        this.numOfChilden = numOfChilden;

    }


    
    /** 
     * Returns reservationStatus as enum ReservationStatus (CONFIRMED, CHECKED_IN, CHECKED_OUT, EXPIRED, CANCELLED)
     * @return ReservationStatus
     */
    public ReservationStatus getReservationStatus() {
        return this.reservationStatus;
    }
    
    /** 
     * Takes reservationStatus as enum ReservationStatus (CONFIRMED, CHECKED_IN, CHECKED_OUT, EXPIRED, CANCELLED)
     * @param reservationStatus describes the status of the reservation
     */
    public void setReservationStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    
    /** 
     * Returns reservationNum as String
     * @return String
     */
    public String getReservationNum() {
        return this.reservationNum;
    }
    
    /** 
     * Takes String reservationNum and sets it as the new reservationNum
     * @param reservationNum uniquely identifies the reservation
     */
    public void setReservationNum(String reservationNum) {
        this.reservationNum = reservationNum;
    }

    
    /** 
     * Returns guestId as String
     * @return String
     */
    public String getGuestId() {
        return this.guestId;
    }
    
    /** 
     * Takes String guestId and sets it as the new guestId
     * @param guestId uniquely identifies the guest that is related to the reservation
     */
    public void setGuestId(String guestId) {
        this.guestId = guestId;
    }

    
    /** 
     * Returns roomId as String
     * @return String
     */
    public String getRoomId() {
        return this.roomId;
    }
    
    /** 
     * Takes String roomId and sets it as the new roomId
     * @param roomId uniquely identifies the room that is related to the reservation
     */
    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    
    /** 
     * Returns checkInDate as Date
     * @return Date
     */
    public Date getCheckInDate() {
        return this.checkInDate;
    }
    
    /** 
     * Takes Date checkInDate and sets it as the new checkInDate
     * @param checkInDate is the date at which the guest checks in
     */
    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    
    /** 
     * Returns checkOutDate as Date
     * @return Date
     */
    public Date getCheckOutDate() {
        return this.checkOutDate;
    }
    
    /** 
     * Takes Date checkOutDate and sets it as the new checkOutDate
     * @param checkOutDate is the date at which the guest checks in
     */
    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    
    /** 
     * Returns numofAdults as Integer
     * @return Integer
     */
    public Integer getNumOfAdults() {
        return this.numOfAdults;
    }
    
    /** 
     * Takes Integer numOfAdults and sets it as the new numOfAdults
     * @param numOfAdults is the number of adults in the room
     */
    public void setNumOfAdults(Integer numOfAdults) {
        this.numOfAdults = numOfAdults;
    }

    
    /** 
     * Returns numofChildren as Integer
     * @return Integer
     */
    public Integer getNumOfChildren() {
        return this.numOfChilden;
    }
    
    /** 
     * Takes Integer numOfChildren and sets it as the new numOfChildren
     * @param numOfChildren is the number of children in the room
     */
    public void setNumOfChildren(Integer numOfChildren) {
        this.numOfChilden = numOfChildren;
    }

}
