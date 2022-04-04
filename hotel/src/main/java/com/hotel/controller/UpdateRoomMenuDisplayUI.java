package com.hotel.controller;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.hotel.system.enums.*;

public class UpdateRoomMenuDisplayUI {
    static Scanner sc = new Scanner(System.in);
    public static String updateRoomId() throws IOException {
        String roomId;
        String roomRegExp = "[0][2-7][-][0][1-8]";
        Pattern roomIdPattern = Pattern.compile(roomRegExp);
        do {
            
			System.out.println("*Format xx-yy where xx is Floor Number and yy is Room Number.");
			System.out.println("*Floor number from 02 - 07");
			System.out.println("*Room number from 01 - 08");
            System.out.print("Please enter Room ID(E.g 02-04):  ");

            roomId = sc.nextLine();
            System.out.println();
			// Matcher matcher = roomIdPattern.matcher(roomId);
            if(roomId.length() != 5 || !roomIdPattern.matcher(roomId).matches()) {
				roomId = "";
				System.out.println("You have entered a invalid Room Id. Please try again. (E.g. 02-04)");
            } else {

                if (RoomController.checkRoomIDExists(roomId) == true) break;
                else {
                    System.out.println("The Room Id you have entered does not exist. Please enter another Room Id.");
                }
            }

        } while (true);
        return roomId;
    }

    public static String createRoomId() throws IOException {
        String roomId;
        String roomRegExp = "[0][2-7][-][0][1-8]";
        Pattern roomIdPattern = Pattern.compile(roomRegExp);
        do {
            
			System.out.println("*Format xx-yy where xx is Floor Number and yy is Room Number.");
			System.out.println("*Floor number from 02 - 07");
			System.out.println("*Room number from 01 - 08");
            System.out.print("Please enter Room ID(E.g 02-04):  ");

            roomId = sc.nextLine();
            System.out.println();
			// Matcher matcher = roomIdPattern.matcher(roomId);
            if(roomId.length() != 5 || !roomIdPattern.matcher(roomId).matches()) {
				roomId = "";
				System.out.println("You have entered a invalid Room Id. Please try again. (E.g. 02-04)");
            } else {

                if (RoomController.checkRoomIDExists(roomId) != true) break;
                else {
                    System.out.println("The Room Id you have entered does not exist. Please enter another Room Id.");
                }
            }

        } while (true);
        return roomId;
    }

    public static RoomType updateRoomType() {
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


    public static BedType updateBedType() {
        BedType bedType = BedType.SINGLE;
        do {
            System.out.println("Please enter Bed Type: ");
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


    public static boolean updateWithView() {
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


    public static RoomStatus updateRoomStatus() {
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

    public static Float updateRoomRate() {
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

    public static boolean updateWifiEnabled() {
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

    public static boolean updateSmokingStatus() {
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
