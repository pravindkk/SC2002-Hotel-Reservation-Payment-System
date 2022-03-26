package com.hotel.controller;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import com.hotel.db.ReservationDB;
import com.hotel.system.Reservation;
import com.hotel.system.Room;
import com.hotel.system.enums.*;
// import com.hotel.controller.UpdateReservationMenu;

public class ReservationController {
    static Scanner sc = new Scanner(System.in);
    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    static ReservationDB allReservations = new ReservationDB();

    public static void main(String[] args) throws IOException {
        ReservationController test = new ReservationController();
        test.createReservation();


    }

    public static ArrayList getAllReservations() throws IOException {
        ArrayList allData = allReservations.read(allReservations.getPath());
        // System.out.printf("%-8s %-13s %-18s %-11s %-19s %-15s %-12s %-13s %-13s %-10s", "RoomID", "Room Type", "Bed Type", "With View", 
        //                     "Room Status", "Room Rate(S$)", "Room Floor","Room Number","Wifi Status","Smoking Status");
        return allData;
    }

    public void saveData(ArrayList toWrite) {
        
        try {
            allReservations.save(allReservations.getPath(), toWrite);
            System.out.println("Reservation successfully updated!");
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println("Room not added!");

        }
    }

    public void createReservation() throws IOException {

        Date checkInDate = UpdateReservationMenu.updateCheckInDate();
        Date checkOutDate = UpdateReservationMenu.updateCheckOutDate();
        String roomId = UpdateReservationMenu.updateRoomId();
        String guestId = UpdateReservationMenu.updateGuestId();
        Integer numOfAdults = UpdateReservationMenu.updateNumberOfAdults();
        Integer numOfChildren = UpdateReservationMenu.updateNumberOfChildren();

        System.out.println("");
		System.out.println("Check-in Date: " + df.format(checkInDate));
		System.out.println("Check-out Date: " + df.format(checkOutDate));
		System.out.println("--------------------------------------------");
		RoomController.printOneRoom(roomId);
        System.out.println("");
		System.out.println("Guest Id: " + guestId);
		System.out.println("Number of adults: " + numOfAdults);
		System.out.println("Number of children: " + numOfChildren);
		System.out.println("Confirm Reservation? (y/n)");
        System.out.println("");
        String confirmation = sc.next();

        if (confirmation.equalsIgnoreCase("y")) {
			// String type = RoomController.retrieveRoomType(roomId);
            ArrayList toUse = RoomController.getSpecificRoom(roomId);

            Integer index = (Integer) toUse.get(0);
            Room r = (Room) toUse.get(1);

			String type = String.valueOf(r.getRoomType());
			String reservationDate = df.format(checkInDate).replaceAll("\\D", "");
			String reservationRoomId = roomId.replaceAll("\\D", "");

			String reservationNum = type.charAt(0) + "-" + reservationDate + "-" + reservationRoomId;

            r.setRoomStatus(RoomStatus.RESERVED);
            ArrayList allRoom = RoomController.getAllRooms();
            allRoom.set(index, r);
            RoomController.saveData(allRoom);
			// status = "CONFIRMED";



			Reservation reservation = new Reservation(ReservationStatus.CONFIRMED,reservationNum, guestId, roomId, checkInDate, checkOutDate,
					numOfAdults, numOfChildren);

			ArrayList allReserve = getAllReservations();
            allReserve.add(reservation);
            saveData(allReserve);

		} else {
			System.out.println("Reservation not confirmed!");
		}

    }

    public static ArrayList getSpecificReservation(String reservationId) throws IOException {
        ArrayList toReturn = new ArrayList();

        ArrayList allData = getAllReservations();

        for (int i=0; i<allData.size(); i++) {
            Reservation r = (Reservation) allData.get(i);
            // System.out.print(r.getRoomId());
            if (reservationId.equals(r.getReservationNum())) {
                // System.out.print("hello");
                toReturn.add(i);
                toReturn.add(r);
                return toReturn;
            }
        }

        return null;
    }


    public static void updateReservation() throws IOException {
        System.out.print("Enter Reservation Number");
        String reservationId = sc.next();
        ArrayList toChange = getSpecificReservation(reservationId);

        if (toChange == null) {
            System.out.println("Wrong Reservation Number");
            return;
        }

        Integer index = (Integer) toChange.get(0);
        Reservation r = (Reservation) toChange.get(1);
        int choice = 5;
        do {
            System.out.println("Please choose Reservation details to update\n"+
                            "(1) Room Type\n"+
                            "(2) Check-in Date\n"+
                            "(3) Check-out Date\n"+
                            "(4) Number of People\n");
            choice = sc.nextInt();
        } while(choice< 1 || choice > 4);

        // switch (choice) {
        //     case 1:
                
        //         break;
        
        //     default:
        //         break;
        // }

        
    }
}
