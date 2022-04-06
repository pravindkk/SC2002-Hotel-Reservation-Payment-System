package com.hotel.controller;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


import com.hotel.db.ReservationDB;
import com.hotel.system.Guest;
import com.hotel.system.Reservation;
import com.hotel.system.Room;
import com.hotel.system.enums.*;

/**
 * Represents the controller function of Guest
 * @author Vignesh Ezhil
 * @version 1.0
 * @since 1.0
 */


public class ReservationController {
    static Scanner sc = new Scanner(System.in);
    static SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    static ReservationDB allReservations = new ReservationDB();

    
    /** 
     * @return returns an ArrayList of all the Guests stored in the database
     * @throws IOException Due to communication with the DataBase IOexception is required
     */
    public static ArrayList getAllReservations() throws IOException {
        ArrayList allData = allReservations.read(allReservations.getPath());
        return allData;
    }

    
    /** 
     * @param toWrite Contains an ArrayList of all the Reservation that is going to be stored in the database
     */
    public static void saveReservationData(ArrayList toWrite) {
        
        try {
            allReservations.save(allReservations.getPath(), toWrite);
            System.out.println("Reservation successfully updated!");
        } catch (Exception e) {
            //TODO: handle exception

            System.out.println("Reservation not added!");
            System.out.println(e);

        }
    }

    
    /** 
     * @param toChange Contains an object of the class Reservation that is going to be stored in the database
     * @throws IOException Due to communication with the DataBase IOexception is required
     */
    public static void saveSpecificReservationByGuestId(Reservation toChange) throws IOException {
        ArrayList allData = getAllReservations();

        for (int i=0; i<allData.size(); i++) {
            Reservation r = (Reservation) allData.get(i);
            if (toChange.getGuestId().equals(r.getGuestId())) {
                allData.set(i, toChange);
                saveReservationData(allData);
                return;
            }
        }
        System.out.println("couldnt save reservation");


    }

    
    /** 
     * @param toAdd Contains an object of the class Reservation that is going to be added into the database
     * @throws IOException Due to communication with the DataBase IOexception is required
     */
    public void createReservation(Reservation toAdd) throws IOException {
        changeRoomStatus(toAdd.getRoomId(), RoomStatus.RESERVED);
        ArrayList allReserve = getAllReservations();
        allReserve.add(toAdd);
        saveReservationData(allReserve);

    }


    
    /** 
     * @param reservation Contains an object of the class Reservation that is needed to be updated
     * Once the object is updated , it is stored in the database
     * @throws IOException Due to communication with the DataBase IOexception is required
     */
    public static void updateReservation(Reservation reservation) throws IOException {

        saveSpecificReservationByGuestId(reservation);
        
    }

    
    /** 
     * @param reservationId
     * @return Reservation
     * @throws IOException Due to communication with the DataBase IOexception is required
     */
    public static Reservation getReservationByNum(String reservationId) throws IOException {

        ArrayList allData = getAllReservations();

        for (int i=0; i<allData.size(); i++) {
            Reservation r = (Reservation) allData.get(i);
            // System.out.print(r.getRoomId());
            if (reservationId.equals(r.getReservationNum())) {
                // System.out.print("hello");
                return r;
            }
        }

        return null;
    }


    
    /** 
     * Method prints out all the reservations in the database
     * @throws IOException Due to communication with the DataBase IOexception is required
     */
    public static void printAllReservations() throws IOException {
        ArrayList allData = getAllReservations();
        System.out.println("\n====================================");
        System.out.println("ALL RESERVATIONS");
        System.out.println("====================================");
        System.out.printf("%-16s %-10s %-5s %-13s %-13s %-15s %-10s %-10s", "Reservation No.", "Guest ID", "Room ID",
				"Status", "Check-In Date", "Check-Out Date", "No. of Adults", "No. of children");
        System.out.println();
        for (int i=0; i<allData.size(); i++) {
            Reservation r = (Reservation) allData.get(i);

            System.out.printf("%-16s %-10s %-7s %-14s %-13s %-19s %-14s %-10s", 
                        r.getReservationNum(), r.getGuestId(), r.getRoomId(),
						String.valueOf(r.getReservationStatus()), r.getCheckInDate(), r.getCheckOutDate(), r.getNumOfAdults(), r.getNumOfChildren());
            System.out.println("");
        }
    }


    
    /** 
     * @param reservationId
     * @param reservationStatus
     * @return Reservation
     * @throws IOException Due to communication with the DataBase IOexception is required
     */
    public static Reservation getReservationByNum(String reservationId, ReservationStatus reservationStatus) throws IOException {
        // ArrayList allReservations = getAllReservations();

        // check the search term "R" for search by ReservationNum while "G" is 
        ArrayList allData = getAllReservations();

        for (int i=0; i<allData.size(); i++) {
            Reservation r = (Reservation) allData.get(i);
            // System.out.print(r.getRoomId());
            // if (reservationId.equals(r.getReservationNum())) {
            if (reservationId.equals(r.getReservationNum()) && reservationStatus == r.getReservationStatus()) {
                // System.out.print(r.getReservationStatus());
                return r;
            }
        }


        return null;
    }
    
    /** 
     * @param roomId
     * @return Reservation
     * @throws IOException Due to communication with the DataBase IOexception is required
     */
    public static Reservation getReservationByRoomId(String roomId) throws IOException {
        // ArrayList allReservations = getAllReservations();

        // check the search term "R" for search by ReservationNum while "G" is 
        ArrayList allData = getAllReservations();

        for (int i=0; i<allData.size(); i++) {
            Reservation r = (Reservation) allData.get(i);
            // System.out.print(r.getRoomId());
            // if (reservationId.equals(r.getReservationNum())) {
            if (roomId.equals(r.getRoomId())) {
                // System.out.print(r.getReservationStatus());
                return r;
            }
        }


        return null;
    }

    
    /** 
     * @param guestId String input of GuestID is entered so that the corresponding reservation object is retrieved fron the database
     * @param reservationStatus
     * @return Reservation
     * @throws IOException Due to communication with the DataBase IOexception is required
     */
    public static Reservation getReservationByGuest(String guestId, ReservationStatus reservationStatus) throws IOException {
        ArrayList allReservations = getAllReservations();

        // check the search term "R" for search by ReservationNum while "G" is 
        ArrayList allData = getAllReservations();

        for (int i=0; i<allData.size(); i++) {
            Reservation r = (Reservation) allData.get(i);
            // System.out.print(r.getRoomId());
            if (guestId.equals(r.getGuestId()) && reservationStatus == r.getReservationStatus()) {
                // System.out.print("hello");
                return r;
            }
        }


        return null;
    }


    
    /** 
     * @param id String input of either GuestID or reservationID
     * @param whatNum String input to identify what ID is used
     * @throws IOException Due to communication with the DataBase IOexception is required
     */
    public static void checkInGuest(String id, String whatNum) throws IOException {
        Reservation r = null;
        if (whatNum.equalsIgnoreCase("R")) {
			// Reservation
			try {
				r = getReservationByNum(id, ReservationStatus.CONFIRMED);
			} catch (IOException e) {
				System.out.println("IOException > " + e.getMessage());
			}

		} else if (whatNum.equalsIgnoreCase("G")) {
			// guest
			try {
				r = getReservationByGuest(id, ReservationStatus.CONFIRMED);
			} catch (IOException e) {
				System.out.println("IOException > " + e.getMessage());
			}
		}

        if (r == null) {
            System.out.println("Could not find the reservation");
            return;
        }

        Date today = new Date();

        String todayDF = df.format(today);
			try {
				today = df.parse(todayDF);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (r.getCheckInDate().equals(today)) {
				// r.setStatus("CHECKED-IN");
                
                r.setReservationStatus(ReservationStatus.CHECKED_IN);
                changeRoomStatus(r.getRoomId(), RoomStatus.OCCUPIED);
                System.out.println("Check In success");


			} else if (r.getCheckInDate().before(today)) {
				System.out.println("Reservation expired");
				r.setReservationStatus(ReservationStatus.EXPIRED);
				changeRoomStatus(r.getRoomId(), RoomStatus.VACANT);
			} else if (r.getCheckInDate().after(today)) {
				System.out.println("Please check in on " + df.format(r.getCheckInDate()));
			}

            saveSpecificReservationByGuestId(r);



    }


    
    /** 
     * @param checkOutRoomId
     * @throws IOException Due to communication with the DataBase IOexception is required
     */
    public static void checkOutGuest(String checkOutRoomId) throws IOException {

        Reservation r = getReservationByNum(checkOutRoomId, ReservationStatus.CHECKED_IN);

        if (r == null) {
            System.out.println("Reservation is not checked in");
            return;
        }

        r.setReservationStatus(ReservationStatus.CHECKED_OUT);
        changeRoomStatus(r.getRoomId(), RoomStatus.VACANT);
        saveSpecificReservationByGuestId(r);

    }


    
    /** 
     * This method takes into account walkIn as mentioned in the assignment brief
     * @throws IOException Due to communication with the DataBase IOexception is required
     */
    public static void walkIn() throws IOException {
        Date checkInDate = new Date();
        Date checkOutDate = UpdateReservationDetailsDisplayUI.updateCheckOutDate();
        String roomId = UpdateReservationDetailsDisplayUI.updateRoomId();
        String guestId = UpdateReservationDetailsDisplayUI.updateGuestId();
        Integer numOfAdults = UpdateReservationDetailsDisplayUI.updateNumberOfAdults();
        Integer numOfChildren = UpdateReservationDetailsDisplayUI.updateNumberOfChildren();

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
            changeRoomStatus(roomId, RoomStatus.OCCUPIED);
			String reservationDate = df.format(checkInDate).replaceAll("\\D", "");
			String reservationRoomId = roomId.replaceAll("\\D", "");

			String reservationNum = guestId.charAt(0) + "-" + reservationDate + "-" + reservationRoomId;

			Reservation reservation = new Reservation(ReservationStatus.CHECKED_IN,reservationNum, guestId, roomId, checkInDate, checkOutDate,
					numOfAdults, numOfChildren);

			ArrayList allReserve = getAllReservations();
            allReserve.add(reservation);
            saveReservationData(allReserve);

		} else {
			System.out.println("Reservation not confirmed!");
		}

    }


    
    /** 
     * This method changes the room status of a particular room
     * @param newRoomId String input of roomID in which the room status needs to be changed
     * @param roomStatus Corresponding status of room input
     * @throws IOException Due to communication with the DataBase IOexception is required
     */
    public static void changeRoomStatus(String newRoomId, RoomStatus roomStatus) throws IOException {
        Room r = RoomController.getSpecificRoom(newRoomId);
        r.setRoomStatus(roomStatus);
        System.out.println(r.getRoomId());
        RoomController.saveSpecificRoomByRoomId(r);
    }

    
    /** 
     * This method switched the roomID in the reservation in the event of a room swap
     * @param oldRoomId String input of old room ID is entered
     * @param newRoomId String input of new room ID is entered
     * @throws IOException Due to communication with the DataBase IOexception is required
     */
    public static void switchRoomStatus(String oldRoomId, String newRoomId) throws IOException {
        // ArrayList allRoom = RoomController.getAllRooms();
        Room oldRoom = RoomController.getSpecificRoom(oldRoomId);
        oldRoom.setRoomStatus(RoomStatus.VACANT);
        RoomController.saveSpecificRoomByRoomId(oldRoom);
        
        Room newRoom = RoomController.getSpecificRoom(newRoomId);
        newRoom.setRoomStatus(RoomStatus.RESERVED);
        RoomController.saveSpecificRoomByRoomId(newRoom);

    }


    
    /** 
     * @param reservationId String input of reservationID is entered so that the corresponding reservation object could be obtained from the database
     * @throws IOException Due to communication with the DataBase IOexception is required
     */
    public static void printReservation(String reservationId) throws IOException {
        Reservation r = getReservationByNum(reservationId);


        if (r == null) {
            System.out.println("Cannot find the reservation");;
            return;
        }

			Guest g = (Guest) GuestController.RetrieveGuest(r.getGuestId());
			System.out.println("\n==================================================");
			System.out.println(" Reservation Details ");
			System.out.println("==================================================");
			System.out.println("Reservation No: " + r.getReservationNum());
			System.out.println("Guest Id: " + r.getGuestId());
			System.out.println("Guest Name: " + g.getName());
			System.out.println("Reservation Status: " + r.getReservationStatus());
			System.out.println("Check-in Date:" + df.format(r.getCheckInDate()));
			System.out.println("Check-out Date:" + df.format(r.getCheckOutDate()));
			System.out.println("==================================================");
			RoomController.getSpecificRoom(r.getRoomId());
    }


    
    /** 
     * @param reservationId String input of reservationID is entered so that the corresponding reservation object could be deleted from the database
     * @throws IOException Due to communication with the DataBase IOexception is required
     */
    public static void cancelReservation(String reservationId) throws IOException {

        Reservation r = getReservationByNum(reservationId);
        if (r == null) {
            System.out.println("cannot find reservation");
            return;
        }


        r.setReservationStatus(ReservationStatus.CANCELLED);
        changeRoomStatus(r.getRoomId(), RoomStatus.VACANT);


        try {
            saveSpecificReservationByGuestId(r);
            System.out.println("Cancellation successful");
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println("Cancellation unsuccessful");
        }

    }
}



