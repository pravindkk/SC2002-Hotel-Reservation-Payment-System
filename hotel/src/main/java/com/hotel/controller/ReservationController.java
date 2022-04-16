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
 * Represents the controller function of Reservation
 * @author Pravind
 * @version 1.0
 * @since 1.0
 */


public class ReservationController {
    static Scanner sc = new Scanner(System.in);
    static SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    static ReservationDB allReservations = new ReservationDB();

    
    /** 
     * Gets and returns all Guest data in the database
     * @return returns an ArrayList of all the Guests stored in the database
     * @throws IOException Due to communication with the DataBase IOexception is required
     */
    public static ArrayList getAllReservations() throws IOException {
        ArrayList allData = allReservations.read(allReservations.getPath());
        return allData;
    }

    
    /** 
     * Saves Reservation objects into the database
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
     * Saves a specific Reservation, specified by GuestID
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
     * Saves a specific Reservation, specified by Reservation Number
     * @param toChange Contains an object of the class Reservation that is going to be stored in the database
     * @throws IOException Due to communication with the DataBase IOexception is required
     */
    public static void saveSpecificReservationByResNum(Reservation toChange) throws IOException {
        ArrayList allData = getAllReservations();

        for (int i=0; i<allData.size(); i++) {
            Reservation r = (Reservation) allData.get(i);
            if (toChange.getReservationNum().equals(r.getReservationNum())) {
                allData.set(i, toChange);
                saveReservationData(allData);
                return;
            }
        }
        System.out.println("couldnt save reservation");


    }

    
    /** 
     * Creates and saves a Reservation
     * @param toAdd Contains an object of the class Reservation that is going to be added into the database
     * @throws IOException Due to communication with the DataBase IOexception is required
     */
    public void createReservation(Reservation toAdd) throws IOException {
        ArrayList allReserve = getAllReservations();
        allReserve.add(toAdd);
        saveReservationData(allReserve);

    }


    
    /** 
     * Updates and saves a Reservation
     * @param reservation Contains an object of the class Reservation that is needed to be updated
     * Once the object is updated, it is stored in the database
     * @throws IOException Due to communication with the DataBase IOexception is required
     */
    public static void updateReservation(Reservation reservation) throws IOException {

        saveSpecificReservationByGuestId(reservation);
        
    }

    
    /** 
     * Gets and returns a Reservation specified by reservationID
     * @param reservationId String input of reservation ID is entered
     * The database is queried and if the corrrsponding reservation object is found, the corresponding reservation object is returned
     * @return If the resevration object that corresponds to the reservationID is found in the database it is returned. Else, null is returned
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
            if(r.getReservationStatus()==ReservationStatus.EXPIRED){
                continue;
            }

            System.out.printf("%-16s %-10s %-7s %-14s %-13s %-19s %-14s %-10s", 
                        r.getReservationNum(), r.getGuestId(), r.getRoomId(),
						String.valueOf(r.getReservationStatus()), df.format(r.getCheckInDate()), df.format(r.getCheckOutDate()), r.getNumOfAdults(), r.getNumOfChildren());
            System.out.println("");
        }
    }

    
    /** 
     * Gets and returns a Reservation by reservationID
     * @param reservationId String input of reservationId is required to query the database
     * @param reservationStatus ReservationStatus is required to see if there is a confirmed reservastion tagged to the room
     * @return If there is a reservation based on the reservationID and reservationStatus, a reservation record is returned. Else null is returned
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
            if (reservationId.equals(r.getReservationNum()) && reservationStatus.equals(r.getReservationStatus())) {
                // System.out.print(r.getReservationStatus());
                return r;
            }
        }


        return null;
    }

    
    /** 
     * Gets and returns a Reservation by roomID
     * @param roomId String input of reservationId is required to query the database
     * @param reservationStatus the reservationStatus needed to check if there is a checked in reservation under the roomId
     * @return If there is a reservation based on the reservationID and reservationStatus, a reservation record is returned. Else null is returned
     * @throws IOException Due to communication with the DataBase IOexception is required
     */
    public static Reservation getReservationByRoom(String roomId, ReservationStatus reservationStatus) throws IOException {
        // ArrayList allReservations = getAllReservations();

        // check the search term "R" for search by ReservationNum while "G" is 
        ArrayList allData = getAllReservations();

        for (int i=0; i<allData.size(); i++) {
            Reservation r = (Reservation) allData.get(i);
            // System.out.print(r.getRoomId());
            // if (reservationId.equals(r.getReservationNum())) {
            if (roomId.equals(r.getRoomId()) && reservationStatus.equals(r.getReservationStatus())) {
                // System.out.print(r.getReservationStatus());
                return r;
            }
        }


        return null;
    }
    
    /** 
     * Gets and returns a Reservation by roomID
     * @param roomId String input of roomID is required to query the database for the reservation record
     * @return If there is a resrvation, a reservation record is returned. Else null is returned
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
     * This method uses the guestID to check if there is a confirmed reservation with the guestId
     * @param guestId String input of GuestID is entered so that the corresponding reservation object is retrieved fron the database
     * @param reservationStatus Reservation status is required to query the database
     * @return If there is a confirmed resrvation, a reservation record is returned. Else null is returned
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
     * Checks in a guest
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
     * Checks out a guest
     * @param checkOutRoomId String input of roomID is required so that they can check out out of the rooom
     * @return returns false if the reservation is not checked in or has already been checked out. returns true if completely checked out
     * @throws IOException Due to communication with the DataBase IOexception is required
     */
    public static boolean checkOutGuest(String checkOutRoomId) throws IOException {

        Reservation r = getReservationByRoom(checkOutRoomId, ReservationStatus.CHECKED_IN);

        if (r == null) {
            System.out.println("Reservation is not checked in");
            return false;
        }
        if(r.getReservationStatus()==ReservationStatus.CHECKED_OUT){
            System.out.println("Room has been checked out");
            return false;
        }

        r.setReservationStatus(ReservationStatus.CHECKED_OUT);
        changeRoomStatus(r.getRoomId(), RoomStatus.VACANT);
        // saveSpecificReservationByGuestId(r);
        saveSpecificReservationByResNum(r);
        // save
        return true;

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
        // System.out.println(r.getRoomId());
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
     * Prints the Reservation specified by reservationID
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
     * Cancels the Reservation specified by reservtionID
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
            saveSpecificReservationByResNum(r);
            System.out.println("Cancellation successful");
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println("Cancellation unsuccessful");
        }

    }

    
    /** 
     * This method changes the ReservationStatus to expired if the date of Reservation is todays date
     * @throws IOException Due to communication with the DataBase IOexception is required
     */
    public static void expireReservation() throws IOException {
        Date todaysDate = new Date();

        ArrayList<Reservation> resList = getAllReservations();
        for (Reservation res : resList) {
            if (res.getCheckInDate().before(todaysDate) && res.getReservationStatus().equals(ReservationStatus.CONFIRMED)) {
                // checkOutGuest(res.getRoomId());
                res.setReservationStatus(ReservationStatus.EXPIRED);
                changeRoomStatus(res.getRoomId(), RoomStatus.VACANT);
            }
        }

        saveReservationData(resList);

    }
}



