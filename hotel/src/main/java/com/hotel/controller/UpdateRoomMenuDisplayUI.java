package com.hotel.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.hotel.system.enums.*;

/**
 * Represents the class which enables Room to be updated through user input
 * @author Pravind
 * @version 1.0
 * @since 1.0
 */

public class UpdateRoomMenuDisplayUI {
    static Scanner sc = new Scanner(System.in);
    
    /** 
     * This method updates the roomID tagged to the Room
     * @return Updated roomID is returned as a String output
     * @throws IOException Due to communication with the DataBase IOexception is required
     */
    public static String updateRoomId() throws IOException {
        String roomId;
        String roomRegExp = "[0][2-7][0][1-8]";
        String thrash = null;
        Pattern roomIdPattern = Pattern.compile(roomRegExp);
        do {
            
			System.out.println("*Format xx-yy where xx is Floor Number and yy is Room Number.");
			System.out.println("*Floor number from 02 - 07");
			System.out.println("*Room number from 01 - 08");
            System.out.print("Please enter Room ID(E.g 0204):  ");

            roomId = sc.nextLine();
            System.out.println();
            System.out.println();
			// Matcher matcher = roomIdPattern.matcher(roomId);
            if(roomId.length() != 4 || !roomIdPattern.matcher(roomId).matches()) {
				roomId = "";
				System.out.println("You have entered a invalid Room Id. Please try again. (E.g. 0204)");
            } else {

                if (RoomController.checkRoomIDExists(roomId) == true) break;
                else {
                    System.out.println("The Room Id you have entered is invalid. Please enter another Room Id.");
                }
            }

        } while (true);
        return roomId;
    }

    
    /** 
     * This method creates a roomID tagged to the room
     * @return Updated roomID is returned as a String output
     * @throws IOException Due to communication with the DataBase IOexception is required
     */
    public static String createRoomId() throws IOException {
        String roomId;
        String roomRegExp = "[0][2-7][0][1-8]";
        Pattern roomIdPattern = Pattern.compile(roomRegExp);
        do {
            
			System.out.println("*Format xx-yy where xx is Floor Number and yy is Room Number.");
			System.out.println("*Floor number from 02 - 07");
			System.out.println("*Room number from 01 - 08");
            System.out.print("Please enter Room ID(E.g 0204):  ");

            roomId = sc.nextLine();
            System.out.println();
			// Matcher matcher = roomIdPattern.matcher(roomId);
            if(roomId.length() != 4 || !roomIdPattern.matcher(roomId).matches()) {
				roomId = "";
				System.out.println("You have entered a invalid Room Id. Please try again. (E.g. 0204)");
            } else {

                if (RoomController.checkRoomIDExists(roomId) != true) break;
                else {
                    System.out.println("The Room Id you have entered is invalid. Please enter another Room Id.");
                }
            }

        } while (true);
        return roomId;
    }

    
     /** 
     * This method updates the Room Type tagged to the Room
     * @return Updated RoomType is returned as a RoomType output
     */
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


    
     /** 
     * This method updates the bed Type tagged to the Room
     * @return Updated bedType is returned as a bedType output
     */
    public static BedType updateBedType(RoomType roomType) {
        
        if (roomType == RoomType.SINGLE) {
            do {
                System.out.println("Please enter Bed Type: ");
                System.out.println("(1) Single");
                System.out.println("(2) Super Single");
            
            int choice=0;
                try {
                    choice = sc.nextInt();
                } catch (Exception e) {
                    //TODO: handle exception
                }

                if (choice < 1 || choice > 2) {
                    System.out.println("Enter a valid option");
                } else {
                    switch(choice){
                        case 1:
                            return BedType.SINGLE;
                        case 2:
                            return BedType.SUPER_SINGLE;
                    }
                }
            } while (true);
        }
        if (roomType == RoomType.DOUBLE) {
            do {
                System.out.println("Please enter Bed Type: ");
                System.out.println("(1) Single");
                System.out.println("(2) Double");
                System.out.println("(3) Queen");
            
            int choice=0;
                try {
                    choice = sc.nextInt();
                } catch (Exception e) {
                    //TODO: handle exception
                }

                if (choice < 1 || choice > 3) {
                    System.out.println("Enter a valid option");
                } else {
                    switch(choice){
                        case 1:
                            return BedType.SINGLE;
                        case 2:
                            return BedType.DOUBLE;
                        case 3:
                            return BedType.QUEEN;
                    }
                }
            } while (true);
        }
        if (roomType == RoomType.DELUXE || roomType == RoomType.VIP_SUITE) {
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
                            return BedType.SINGLE;

                        case 2:
                            return BedType.DOUBLE;

                        case 3:
                            return BedType.SUPER_SINGLE;

                        case 4:
                            return BedType.QUEEN;

                        case 5:
                            return BedType.KING;

                    }
                }
            } while (true);
        }
        return BedType.SINGLE;
    }


    
    /** 
     * This method updates the withView status tagged to the Room
     * @return True is returned if the room has a view
     */
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


    
     /** 
     * This method updates the Room status tagged to the Room
     * @return Updated Room Status is returned as a roomStatus output
     */
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

    
    /** 
     * This method updates the room rate tagged to the room
     * @return Updated room rate is returned as a float output
     */
    public static Float updateRoomRate() {
        String roomRate = null;
        String priceFormat = "(\\d+\\.\\d{1,2})" ;

        do {
            System.out.println("Please enter Room Rate(E.g. 154.40):");
			System.out.println("*Enter amount in 2 decimal places.");
            // roomRate = sc.nextFloat();
            roomRate = sc.next();
            

            if (roomRate.equals("")|| !roomRate.matches(priceFormat) || Float.valueOf(roomRate)<=0) {
                System.out.println("ERROR!! Please enter 2 decimal places!!");
            }
            else if (new BigDecimal(String.valueOf(roomRate)).scale() != 2) {
                System.out.println("ERROR!! Please enter 2 decimal places!!");
            }
            else break;

        } while(true);
        sc.nextLine();

        return Float.valueOf(roomRate);
    }

    
    /** 
     * This method updates the wifiEnabled status tagged to the Room
     * @return True is returned if the room has wifi enabled
     */
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

    
   /** 
     * This method updates the smoking status tagged to the Room
     * @return True is returned if the room allows smoking
     */
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
