package system;
// print hello there


public class Room {
    public enum RoomType {
        SINGLE, DOUBLE, DELUXE, VIP_SUITE
    }

    public enum RoomStatus {
        VACANT, OCCUPIED, RESERVED, UNDER_MAINTAINANCE
    }

    public enum BedType {
        SINGLE, DOUBLE, MASTER 
    }

    RoomType roomType;
    RoomStatus roomStatus;
    BedType bedType;

    private String roomId;
	private boolean withView;
	private Float roomRate;
	private String roomFloor;
	private Integer roomNumber;
	private boolean wifiEnabled;
	private boolean smokingStatus; 

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


    public RoomType getRoomType() {
        return this.roomType;
    }
    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }


    public RoomStatus getRoomStatus() {
        return this.roomStatus;
    }
    public void setRoomStatus(RoomStatus roomStatus) {
        this.roomStatus = roomStatus;
    }


    public BedType getBedType() {
        return this.bedType;
    }
    public void setBedType(BedType bedType) {
        this.bedType = bedType;
    }

    public String getRoomId() {
        return this.roomId;
    }
    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public boolean getWithView() {
        return this.withView;
    }
    public void withView(boolean withView) {
        this.withView = withView;
    }

    public Float getRoomRate() {
        return this.roomRate;
    }
    public void setRoomRate(Float roomRate) {
        this.roomRate = roomRate;
    }

    public String getRoomFloor() {
        return this.roomFloor;
    }
    public void setRoomFloor(String roomFloor) {
        this.roomFloor = roomFloor;
    }

    public Integer getRoomNumber() {
        return this.roomNumber;
    }
    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public boolean getWifiEnabled() {
        return this.wifiEnabled;
    }
    public void setWifiEnabled(boolean wifiEnabled) {
        this.wifiEnabled = wifiEnabled;
    }

    public boolean getSmokingStatus() {
        return this.smokingStatus;
    }
    public void setSmokingStatus(boolean smokingStatus) {
        this.smokingStatus = smokingStatus;
    }


    


}