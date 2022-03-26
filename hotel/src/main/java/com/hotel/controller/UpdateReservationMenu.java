package com.hotel.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.hotel.system.Room;
import com.hotel.system.enums.*;

public class UpdateReservationMenu {
    static Scanner sc = new Scanner(System.in);

    public static Date updateCheckInDate() {
        Date todaysdate = new Date();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String dateValidation = "^([0-2][0-9]||3[0-1])/(0[0-9]||1[0-2])/([0-9][0-9])?[0-9][0-9]$";
        String checkInDateString = null;
        Date checkInDate = null;
        
        do {                                                                                                // for check-in date input
            System.out.println("Enter Check-In Date");

            try {
                checkInDateString = sc.nextLine();
                checkInDate = df.parse(checkInDateString);
            } catch(ParseException e) {
                System.out.println(e);
            }

        } while(!checkInDate.before(todaysdate) && !checkInDateString.matches(dateValidation));

        return checkInDate;
    }
    
    public static Date updateCheckOutDate() {
        Date todaysdate = new Date();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String dateValidation = "^([0-2][0-9]||3[0-1])/(0[0-9]||1[0-2])/([0-9][0-9])?[0-9][0-9]$";
        String checkInDateString = null;
        Date checkOutDate = null;
        
        do {                                                                                                // for check-in date input
            System.out.println("Enter Check-Out Date");

            try {
                checkInDateString = sc.nextLine();
                checkOutDate = df.parse(checkInDateString);
            } catch(ParseException e) {
                System.out.println(e);
            }

        } while(!checkOutDate.before(todaysdate) && !checkInDateString.matches(dateValidation));

        return checkOutDate;
    }


    public static String updateRoomId() throws IOException {
        
        ArrayList vacantRooms = RoomController.getVacantRooms();

        // // for (int i=0; i<vacantRooms.size())
        // int choice = 5;
        // do {
            
        //     System.out.println("1)  Single - " + RoomController.getRoomsByRoomType(RoomController.getVacantRooms(), RoomType.SINGLE).size() + " rooms available");
        //     System.out.println("2)  Double - " + RoomController.getRoomsByRoomType(RoomController.getVacantRooms(), RoomType.DOUBLE).size() + " rooms available");
        //     System.out.println("3)  Deluxe - " + RoomController.getRoomsByRoomType(RoomController.getVacantRooms(), RoomType.DELUXE).size() + " rooms available");
        //     System.out.println("4)  VIP Suite - " + RoomController.getRoomsByRoomType(RoomController.getVacantRooms(), RoomType.VIP_SUITE).size() + " rooms available");
        //     System.out.print("Enter the Room Type: ");
        //     try {
        //         choice = sc.nextInt();
        //     } catch (Exception e) {
        //         //TODO: handle exception
        //     }
        // } while (choice > 4 || choice <1);

        // RoomType roomType = RoomType.SINGLE;
        // switch (choice) {
        //     case 1:
        //         roomType = RoomType.SINGLE;
        //         break;
        //     case 2:
        //         roomType = RoomType.DOUBLE;
        //         break;
        //     case 3:
        //         roomType = RoomType.DELUXE;
        //         break;
        //     case 4:
        //         roomType = RoomType.VIP_SUITE;
        //         break;
        
        //     default:
        //         break;
        // }
        
        // ArrayList choosen = RoomController.getRoomsByRoomType(vacantRooms, roomType);
        // System.out.println(choosen.size());
        // ArrayList test = choosen;

        // choice = 6;

        // int numOfSingle = RoomController.getRoomsByBedType(vacantRooms, BedType.SINGLE).size();
        // choosen = RoomController.getRoomsByRoomType(vacantRooms, roomType);
        // System.out.println(choosen.size());
        // int numOfDouble = RoomController.getRoomsByBedType(vacantRooms, BedType.DOUBLE).size();
        // choosen = RoomController.getRoomsByRoomType(vacantRooms, roomType);
        // System.out.println(choosen.size());
        // int numOfSuperSingle = RoomController.getRoomsByBedType(vacantRooms, BedType.SUPER_SINGLE).size();
        // choosen = RoomController.getRoomsByRoomType(vacantRooms, roomType);
        // System.out.println(choosen.size());
        // int numOfQueen = RoomController.getRoomsByBedType(vacantRooms, BedType.QUEEN).size();
        // choosen = RoomController.getRoomsByRoomType(vacantRooms, roomType);
        // System.out.println(choosen.size());
        // int numOfKing = RoomController.getRoomsByBedType(vacantRooms, BedType.KING).size();
        // choosen = RoomController.getRoomsByRoomType(vacantRooms, roomType);
        

        // do {
            
        //     System.out.println("1)  Single - " + numOfSingle + " rooms available");
        //     System.out.println("2)  Double - " + numOfDouble+ " rooms available");
        //     System.out.println("3)  Super Single - " + numOfSuperSingle+ " rooms available");
        //     System.out.println("4)  Queen - " + numOfQueen + " rooms available");
        //     System.out.println("5)  King - " + numOfKing + " rooms available");
        //     System.out.print("Enter the Bed Type: ");
        //     try {
        //         choice = sc.nextInt();
        //     } catch (Exception e) {
        //         //TODO: handle exception
        //     }
        // } while (choice > 5 || choice <1);
        
        // BedType bedType = BedType.SINGLE;
        // switch (choice) {
        //     case 1:
        //         bedType = BedType.SINGLE;
        //         break;
        //     case 2:
        //         bedType = BedType.DOUBLE;
        //         break;
        //     case 3:
        //         bedType = BedType.SUPER_SINGLE;
        //         break;
        //     case 4:
        //         bedType = BedType.QUEEN;
        //         break;
            
        //     case 5:
        //         bedType = BedType.KING;
        //         break;
        
        //     default:
        //         break;
        // }

        // vacantRooms = RoomController.getRoomsByBedType(choosen, bedType);
        
        // System.out.println("==================================");
        // System.out.println("ALL VACANT ROOMS");
        // System.out.println("==================================");
        // RoomController.printVacantRoom();
        // System.out.println();


        System.out.println("==================================");
        System.out.println("ALL VACANT ROOMS WITH DETAILS");
        System.out.println("==================================");
        RoomController.printRooms(vacantRooms);

        // RoomController.printAllRooms();


        // System.out.println("Which room do you want?:  ");
        // String roomId=null;
        String roomId = UpdateRoomMenu.updateRoomId();

        // String roomId;
        // // String roomFloor;
        // // Integer roomNumber;
        // String roomRegExp = "[0][2-7][-][0][1-8]";
        // Pattern roomIdPattern = Pattern.compile(roomRegExp);
        // do {
            
		// 	System.out.println("*Format xx-yy where xx is Floor Number and yy is Room Number.");
		// 	System.out.println("*Floor number from 02 - 07");
		// 	System.out.println("*Room number from 01 - 08");
        //     System.out.print("Please enter Room ID(E.g 02-04):  ");

        //     roomId = sc.nextLine();
        //     System.out.println();
		// 	// Matcher matcher = roomIdPattern.matcher(roomId);
        //     if(roomId.length() != 5 || !roomIdPattern.matcher(roomId).matches()) {
		// 		roomId = "";
		// 		System.out.println("You have entered a invalid Room Id. Please try again. (E.g. 02-04)");
        //     } else {
        //         // room.setRoomId(roomId);
		// 		// String[] parts = roomId.split("-");
		// 		// roomFloor = parts[0];
		// 		// roomNumber = Integer.valueOf(parts[1]);

        //         if (RoomController.checkRoomIDExists(roomId)) break;
        //         else {
        //             System.out.println("The Room Id you have entered exist. Please enter another Room Id.");
        //         }
        //     }

        // } while (true);

        return roomId;
    }

    public static String updateGuestId() {
        // for now i just return normal string

        return "Jane Watson";
    }

    public static Integer updateNumberOfAdults() {
        Integer numOfAdults;
        do {
			System.out.println("Enter total Number of Adults: ");
			while (!sc.hasNextInt()) {
		        System.out.println("Please enter numbers only.");
		        sc.next(); // this is important!
		    }
			numOfAdults = sc.nextInt();
			if(numOfAdults < 0) {
				System.out.println("Please enter a valid number.");
			}
			
		}while(numOfAdults <= -1);

        return numOfAdults;
    }

    public static Integer updateNumberOfChildren() {
        Integer numOfChildren;
        do {
			System.out.println("Enter total Number of Children: ");
			while (!sc.hasNextInt()) {
		        System.out.println("Please enter numbers only.");
		        sc.next(); // this is important!
		    }
			numOfChildren = sc.nextInt();
			if(numOfChildren < 0) {
				System.out.println("Please enter a valid number.");
			}
		}while(numOfChildren <= -1);

        return numOfChildren;
    }
}
