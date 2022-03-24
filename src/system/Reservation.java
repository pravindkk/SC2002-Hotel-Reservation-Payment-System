package system;

import java.util.Date;

// import system.enums.Enums.*;
import system.enums.*;


public class Reservation {

    private RoomStatus roomStatus;
    private String reservationNum;
    private String guestId;
    private String roomId;
    private Date checkInDate;
    private Date checkOutDate;
    private Integer numOfAdults;
    private Integer numOfChilden;

    public Reservation(RoomStatus roomStatus, String reservationNum, String guestId, String roomId, Date checkInDate, 
        Date checkOutDate, Integer numOfAdults, Integer numOfChilden) {

        this.roomStatus = roomStatus;
        this.reservationNum = reservationNum;
        this.guestId = guestId;
        this.roomId = roomId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.numOfAdults = numOfAdults;
        this.numOfChilden = numOfChilden;

    }


    public RoomStatus getRoomStatus() {
        return this.roomStatus;
    }
    public void setRoomStatus(RoomStatus roomStatus) {
        this.roomStatus = roomStatus;
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
