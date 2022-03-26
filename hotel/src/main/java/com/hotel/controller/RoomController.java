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
        // Room toAddRoom = new Room();


        // creating roomID
        String regExp = "[0-9]+([.][0-9]{2})";
		String roomRegExp = "[0][2-7][-][0][1-8]";
        Pattern pattern = Pattern.compile(regExp);
		Pattern roomIdPattern = Pattern.compile(roomRegExp);

        

        do {
            System.out.println("Please enter Room ID(E.g 02-04):");
			System.out.println("*Format xx-yy where xx is Floor Number and yy is Room Number.");
			System.out.println("*Floor number from 02 - 07");
			System.out.println("*Room number from 01 - 08");

            String roomId = sc.nextLine();
			// Matcher matcher = roomIdPattern.matcher(roomId);
            if(roomId.length() != 5 || !roomIdPattern.matcher(roomId).matches()) {
				roomId = "";
				System.out.println("You have entered a invalid Room Id. Please try again. (E.g. 02-04)");
            } else {
                // room.setRoomId(roomId);
				String[] parts = roomId.split("-");
				String roomFloor = parts[0];
				String roomNumber = parts[1];

                if (checkRoomIDExists(roomId)) break;
                else {
                    System.out.println("The Room Id you have entered exist. Please enter another Room Id.");
                }
            }

        } while (true);


        // set RoomType
        RoomType roomType;
        do {
            System.out.println("Please enter Room Type: ");
			System.out.println("(1) Single");
			System.out.println("(2) Double");
			System.out.println("(3) Deluxe");
			System.out.println("(4) VIP Suite");

            int choice = sc.nextInt();

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
        
        
        // set BedType
        BedType bedType;
        do {
            System.out.println("Please enter Room Type: ");
			System.out.println("(1) Single");
			System.out.println("(2) Double");
			System.out.println("(3) Super Single");
			System.out.println("(4) Queen");
			System.out.println("(5) King");

            int choice = sc.nextInt();

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


        // setting viewType
        






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
}
