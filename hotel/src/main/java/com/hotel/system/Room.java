package com.hotel.system;
// print hello there

import com.hotel.system.enums.*;
// import system.enums.RoomStatus;
// import system.enums.RoomType;

/**
 * Represents the class of Payment, which holds the information at the instance the payment is made
 * @author Melissa Ng Li Yun
 * @version 1.0
 * @since 1.0
 */

public class Room {

    private RoomType roomType;
    private RoomStatus roomStatus;
    private BedType bedType;

    private String roomId;
	private boolean withView;
	private Float roomRate;
	private String roomFloor;
	private Integer roomNumber;
	private boolean wifiEnabled;
	private boolean smokingStatus; 

    /**
     * Constructor for Room object
     * @param roomType          defines the type of room it is (SINGLE, DOUBLE, DELUXE, VIP_SUITE)
     * @param roomStatus        defines the status of room (VACANT, OCCUPIED, RESERVED, UNDER_MAINTAINANCE)
     * @param bedType           defines the bed type (SINGLE, DOUBLE, SUPER_SINGLE, QUEEN, KING)
     * @param roomId            uniquely identifies the room 
     * @param withView          determines if the room has a view or not
     * @param roomRate          the pricing of the room
     * @param roomFloor         the floor at which the room is at
     * @param roomNumber        the unique room number
     * @param wifiEnabled       determines if the room has wifi or not
     * @param smokingStatus     determines if the room allows smoking or not
     */
    public Room(RoomType roomType, RoomStatus roomStatus, BedType bedType, String roomId, Boolean withView, Float roomRate, 
        String roomFloor, Integer roomNumber, Boolean wifiEnabled, Boolean smokingStatus) {

            this.roomType = roomType;
            this.roomStatus = roomStatus;
            this.bedType = bedType;

            this.roomId = roomId;
            this.withView = withView;
            this.roomRate = roomRate;
            this.roomFloor = roomFloor;
            this.roomNumber = roomNumber;
            this.wifiEnabled = wifiEnabled;
            this.smokingStatus = smokingStatus;
    }


    
    /** 
     * Returns roomType as RoomType
     * @return RoomType
     */
    public RoomType getRoomType() {
        return this.roomType;
    }
    
    /** 
     * Takes RoomType roomType and sets it as the new roomType
     * @param roomType defines the type of room it is (SINGLE, DOUBLE, DELUXE, VIP_SUITE)
     */
    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }


    
    /** 
     * Returns roomStatus as RoomStatus
     * @return RoomStatus
     */
    public RoomStatus getRoomStatus() {
        return this.roomStatus;
    }
    
    /** 
     * Takes RoomStatus roomStatus as sets it as the new roomStatus
     * @param roomStatus defines the status of room (VACANT, OCCUPIED, RESERVED, UNDER_MAINTAINANCE)
     */
    public void setRoomStatus(RoomStatus roomStatus) {
        this.roomStatus = roomStatus;
    }


    
    /** 
     * Returns bedType as BedType
     * @return BedType
     */
    public BedType getBedType() {
        return this.bedType;
    }
    
    /** 
     * Takes BedType bedType and sets it as the new bedType
     * @param bedType defines the bed type (SINGLE, DOUBLE, SUPER_SINGLE, QUEEN, KING)
     */
    public void setBedType(BedType bedType) {
        this.bedType = bedType;
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
     * @param roomId uniquely identifies the room
     */
    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    
    /** 
     * Returns withView and boolean
     * @return boolean
     */
    public boolean getWithView() {
        return this.withView;
    }
    
    /** 
     * Takes boolean withView and sets it as the new withView
     * @param withView determines if the room has a view or not
     */
    public void setWithView(boolean withView) {
        this.withView = withView;
    }

    
    /** 
     * Returns roomRate as Float
     * @return Float
     */
    public Float getRoomRate() {
        return this.roomRate;
    }
    
    /** 
     * Takes roomRate as Float and sets it as the new roomRate
     * @param roomRate describes the pricing of the room
     */
    public void setRoomRate(Float roomRate) {
        this.roomRate = roomRate;
    }

    
    /** 
     * Returns roomFloor as String
     * @return String
     */
    public String getRoomFloor() {
        return this.roomFloor;
    }
    
    /** 
     * Takes String roomFloor and sets it as the new roomFloor
     * @param roomFloor describes the floor at which the room is at
     */
    public void setRoomFloor(String roomFloor) {
        this.roomFloor = roomFloor;
    }

    
    /** 
     * Returns roomNumber as Integer
     * @return Integer
     */
    public Integer getRoomNumber() {
        return this.roomNumber;
    }
    
    /** 
     * Takes Intger roomNumber and sets it as the new roomNumber
     * @param roomNumber describes the unique room number
     */
    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    
    /** 
     * Returns wifiEnabled as boolean
     * @return boolean
     */
    public boolean getWifiEnabled() {
        return this.wifiEnabled;
    }
    
    /** 
     * Takes boolean wifiEnabled and sets it as the new wifiEnabled
     * @param wifiEnabled determines if the room has wifi or not
     */
    public void setWifiEnabled(boolean wifiEnabled) {
        this.wifiEnabled = wifiEnabled;
    }

    
    /** 
     * Returns smokingStatus as boolean
     * @return boolean
     */
    public boolean getSmokingStatus() {
        return this.smokingStatus;
    }
    
    /** 
     * Takes boolean smokingStatus and sets it as the new smokingStatus
     * @param smokingStatus determines if the room allows smoking or not
     */
    public void setSmokingStatus(boolean smokingStatus) {
        this.smokingStatus = smokingStatus;
    }
}