package com.hotel.controller;

import java.io.IOException;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hotel.db.ReadInFile;
import com.hotel.db.RoomDB;
import com.hotel.system.Room;
import com.hotel.system.enums.*;

public class RoomController {
    // private RoomDB allRooms;
    RoomDB allRooms = new RoomDB();
    Scanner sc = new Scanner(System.in);
    // ArrayList hello = test.getAllRooms();
    public static void main(String[] args) throws IOException {
        RoomController test = new RoomController();
        ArrayList hello = test.getAllRooms();
        
        
    }

    public void createRoom() throws IOException {

        String roomId = setRoomId();
        String[] parts = roomId.split("-");
        String roomFloor = parts[0];
        Integer roomNumber = Integer.valueOf(parts[1]);

        RoomType roomType = setRoomType();
        BedType bedType = setBedType();
        boolean withView = setWithView();
        RoomStatus roomStatus = setRoomStatus();
        Float roomRate = setRoomRate();
        boolean wifiEnabled = setWifiEnabled();
        boolean smokingStatus = setSmokingStatus();


        Room room = new Room(roomType,
                             roomStatus, 
                             bedType, 
                             roomId, 
                             withView, 
                             roomRate, 
                             roomFloor, 
                             roomNumber, 
                             wifiEnabled, 
                             smokingStatus);

        ArrayList allData = getAllRooms();
        allData.add(room);
        try {
            allRooms.save(allRooms.getPath(), allData);
            System.out.println("Room successfully added!");
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println("Room not added!");

        }
        

    }

    public void updateRoom(String roomId) throws IOException {
        ArrayList roomList = getSpecificRoom(roomId);
        // Room room = (Room)
        if (roomList == null) {
            System.out.println("Invalid Room");
            return;
        }
        Integer index = (Integer) roomList.get(0);
        Room room = (Room) roomList.get(1);

        System.out.println("\nPlease choose guest details to update \n(1) Room Type \n(2) Bed Type "
				+ "\n(3) View Type \n(4) Room Status \n(5) Room Rate \n(6) Wifi Enabled \n(7) Room Smoking Status \n(8) All details");

        int choice = 0;
        try {
            choice = sc.nextInt();
        } catch (Exception e) {
            //TODO: handle exception
        }

        if (choice < 1 || choice > 8) {
            System.out.println("Invalid input");
            return;
        }

        


    }

    public boolean checkRoomIDExists(String roomId) throws IOException {
        ArrayList allData = getAllRooms();
        for (int i=0; i<allData.size(); i++) {
            Room r = (Room) allData.get(i);
            if (r.getRoomId() == roomId) return true;
        }

        return false;

    }

    public ArrayList getAllRooms() throws IOException {
        ArrayList allData = allRooms.read(allRooms.getPath());
        return allData;
    }

    public ArrayList getSpecificRoom(String roomId) throws IOException {
        ArrayList toReturn = new ArrayList();

        ArrayList allData = getAllRooms();

        for (int i=0; i<allData.size(); i++) {
            Room r = (Room) allData.get(i);
            if (r.getRoomId() == roomId) {
                toReturn.add(i);
                toReturn.add(r);
                break;
            }
        }

        return toReturn;
    }

    public String setRoomId() throws IOException {
        String roomId;
        // String roomFloor;
        // Integer roomNumber;
        String roomRegExp = "[0][2-7][-][0][1-8]";
        Pattern roomIdPattern = Pattern.compile(roomRegExp);
        do {
            System.out.println("Please enter Room ID(E.g 02-04):");
			System.out.println("*Format xx-yy where xx is Floor Number and yy is Room Number.");
			System.out.println("*Floor number from 02 - 07");
			System.out.println("*Room number from 01 - 08");

            roomId = sc.nextLine();
			// Matcher matcher = roomIdPattern.matcher(roomId);
            if(roomId.length() != 5 || !roomIdPattern.matcher(roomId).matches()) {
				roomId = "";
				System.out.println("You have entered a invalid Room Id. Please try again. (E.g. 02-04)");
            } else {
                // room.setRoomId(roomId);
				// String[] parts = roomId.split("-");
				// roomFloor = parts[0];
				// roomNumber = Integer.valueOf(parts[1]);

                if (!checkRoomIDExists(roomId)) break;
                else {
                    System.out.println("The Room Id you have entered exist. Please enter another Room Id.");
                }
            }

        } while (true);
        return roomId;
    }

    public RoomType setRoomType() {
        RoomType roomType = RoomType.SINGLE;
        do {
            System.out.println("Please enter Room Type: ");
			System.out.println("(1) Single");
			System.out.println("(2) Double");
			System.out.println("(3) Deluxe");
			System.out.println("(4) VIP Suite");

            int choice = 0;
            try {
                choice = sc.nextInt();
            } catch (Exception e) {
                //TODO: handle exception
            }

            if (choice < 1 || choice > 4) {
                System.out.println("Enter a valid option");
            } else {
                switch (choice) {
                    case 1:
                        roomType = RoomType.SINGLE;
                        break;
                    case 2:
                        roomType = RoomType.DOUBLE;
                        break;
                    case 3:
                        roomType = RoomType.DELUXE;
                        break;
                    case 4:
                        roomType = RoomType.VIP_SUITE;
                        break;
                }
                break;
            }
        } while (true);
        return roomType;
    }


    public BedType setBedType() {
        BedType bedType = BedType.SINGLE;
        do {
            System.out.println("Please enter Room Type: ");
			System.out.println("(1) Single");
			System.out.println("(2) Double");
			System.out.println("(3) Super Single");
			System.out.println("(4) Queen");
			System.out.println("(5) King");

            int choice = 0;
            try {
                choice = sc.nextInt();
            } catch (Exception e) {
                //TODO: handle exception
            }

            if (choice < 1 || choice > 5) {
                System.out.println("Enter a valid option");
            } else {
                switch (choice) {
                    case 1:
                        bedType = BedType.SINGLE;
                        break;
                    case 2:
                        bedType = BedType.DOUBLE;
                        break;
                    case 3:
                        bedType = BedType.SUPER_SINGLE;
                        break;
                    case 4:
                        bedType = BedType.QUEEN;
                        break;
                    case 5:
                        bedType = BedType.KING;
                        break;
                }
                break;
            }
        } while (true);
        return bedType;
    }


    public boolean setWithView() {
        boolean withView=false;
        do {
            System.out.println("Does it have a view?:  ");
            System.out.println("(1) Yes");
            System.out.println("(2) No");

            int choice = 0;
            try {
                choice = sc.nextInt();
            } catch (Exception e) {
                //TODO: handle exception
            }

            if (choice < 1 || choice > 2) {
                System.out.println("Enter a valid option");
            } else {
                switch (choice) {
                    case 1:
                        withView = true;
                        break;
                
                    case 2:
                        withView = false;
                        break;
                }
                break;
            }
        } while (true);
        return withView;
    }


    public RoomStatus setRoomStatus() {
        RoomStatus roomStatus = RoomStatus.VACANT;

        do {
            System.out.println("Please enter Room Status: ");
			System.out.println("(1) Vacant");
			System.out.println("(2) Reserved");
			System.out.println("(3) Occupied");
			System.out.println("(4) Under Maintenance");

            int choice = 0;
            try {
                choice = sc.nextInt();
            } catch (Exception e) {
                //TODO: handle exception
            }
            
            if (choice < 1 || choice > 4) {
                System.out.println("Enter a valid option");
            } else {
                switch (choice) {
                    case 1:
                        roomStatus = RoomStatus.VACANT;
                        break;
                    case 2:
                        roomStatus = RoomStatus.RESERVED;
                        break;
                    case 3:
                        roomStatus = RoomStatus.OCCUPIED;
                        break;
                    case 4:
                        roomStatus = RoomStatus.UNDER_MAINTAINANCE;
                        break;
                }
                break;
            }
        } while (true);
        return roomStatus;
    }

    public Float setRoomRate() {
        Float roomRate = 0.0f;

        do {
            System.out.println("Please enter Room Rate(E.g. 154.40):");
			System.out.println("*Enter amount in 2 decimal places.");
            // roomRate = sc.nextFloat();
            try {
                roomRate = sc.nextFloat();
            } catch (Exception e) {
                //TODO: handle exception
                System.out.println("Enter a valid rate");
            }
            if (roomRate < 0.0) System.out.println("Enter a positive rate");

        } while (roomRate <= 0.0);

        return roomRate;
    }

    public boolean setWifiEnabled() {
        boolean wifiEnabled=false;
        do {
            System.out.println("Does it have wifi?:  ");
            System.out.println("(1) Yes");
            System.out.println("(2) No");

            int choice = 0;
            try {
                choice = sc.nextInt();
            } catch (Exception e) {
                //TODO: handle exception
            }

            if (choice < 1 || choice > 2) {
                System.out.println("Enter a valid option");
            } else {
                switch (choice) {
                    case 1:
                        wifiEnabled = true;
                        break;
                
                    case 2:
                        wifiEnabled = false;
                        break;
                }
                break;
            }
        } while (true);
        return wifiEnabled;
    }

    public boolean setSmokingStatus() {
        boolean smokingStatus=false;
        do {
            System.out.println("Is smoking allowed in the room?:  ");
            System.out.println("(1) Yes");
            System.out.println("(2) No");

            int choice = 0;
            try {
                choice = sc.nextInt();
            } catch (Exception e) {
                //TODO: handle exception
            }

            if (choice < 1 || choice > 2) {
                System.out.println("Enter a valid option");
            } else {
                switch (choice) {
                    case 1:
                        smokingStatus = true;
                        break;
                
                    case 2:
                        smokingStatus = false;
                        break;
                }
                break;
            }
        } while (true);
        return smokingStatus;
    }
}
