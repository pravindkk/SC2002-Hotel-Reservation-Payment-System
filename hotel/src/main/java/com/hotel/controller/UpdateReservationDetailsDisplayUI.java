package com.hotel.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.hotel.system.Guest;
import com.hotel.system.Room;
import com.hotel.system.enums.*;

import org.apache.commons.lang3.time.DateUtils;

/**
 * Represents the class which enables Reservation Details to be updated through user input
 * @author Vignesh Ezhil
 * @version 1.0
 * @since 1.0
 */

public class UpdateReservationDetailsDisplayUI {
    static Scanner sc = new Scanner(System.in);

    
    /** 
     * This method updates the checkin date tagged to the reservation detail
     * @return Updated check in date is returned as a Date output
     * @throws ParseException
     */
    public static Date updateCheckInDate() {
        Date todaysdate = DateUtils.addDays(new Date(),-1);

        // LocalDateTime ldt = LocalDateTime.ofInstant(todaysdate.toInstant(), ZoneId.systemDefault());
        // Date todaysDate2 = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());


        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String dateValidation = "^([0-2][0-9]||3[0-1])/(0[0-9]||1[0-2])/([0-9][0-9])?[0-9][0-9]$";
        String checkInDateString = null;
        Date checkInDate = null;
        
        do {                                                                                                // for check-in date input
            System.out.print("Enter Check-In Date (DD/MM/YYYY):  ");

            try {
                // sc.nextLine();
                checkInDateString = String.valueOf(sc.next());
                // checkInDate = df.parse(checkInDateString);
            } catch(Exception e) {
                // System.out.println(e);
            }
            try {
                checkInDate = df.parse(checkInDateString);
            } catch (ParseException e) {
                //TODO: handle exception
                System.out.println("Invalid Date!! ABORT!!!");
            }
            
            if (checkInDate.before(todaysdate)) {
                System.out.println("Pick a date before the today's date (DD/MM/YYYY)");
            }

        } while(checkInDate.before(todaysdate) || !checkInDateString.matches(dateValidation));

        return checkInDate;
    }
    
    
    /** 
     * This method updates the check out date tagged to the reservation detail
     * @return Updated check out date is returned as a Date output
     */
    public static Date updateCheckOutDate(Date checkInDate) {
        // Date todaysdate = new Date();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String dateValidation = "^([0-2][0-9]||3[0-1])/(0[0-9]||1[0-2])/([0-9][0-9])?[0-9][0-9]$";
        String checkInDateString = null;
        Date checkOutDate = null;
        
        do {                                                                                                // for check-in date input
            System.out.print("Enter Check-Out Date (DD/MM/YYYY):  ");
            try {
                checkInDateString = String.valueOf(sc.next());
                // checkInDate = df.parse(checkInDateString);
            } catch(Exception e) {
                // System.out.println(e);
            }
            try {
                checkOutDate = df.parse(checkInDateString);
            } catch (ParseException e) {
                //TODO: handle exception
                System.out.println("Invalid Date!! ABORT!!!");
            }

            if (checkOutDate.before(checkInDate)) {
                System.out.println("Please a date after check-in date");
            }

        } while(checkOutDate.before(checkInDate) || !checkInDateString.matches(dateValidation));

        return checkOutDate;
    }


    /** 
     * This method updates the roomID tagged to the Reservation Detail
     * @return Updated roomID is returned as a String output
     * @throws IOException Due to communication with the DataBase IOexception is required
     */
    public static String updateRoomId() throws IOException {
        String roomId;
        do {
            ArrayList vacantRooms = RoomController.getVacantRooms();


            System.out.println("==================================");
            System.out.println("ALL VACANT ROOMS WITH DETAILS");
            System.out.println("==================================");
            RoomController.printRooms(vacantRooms);
            roomId = UpdateRoomMenuDisplayUI.updateRoomId();
            if (ReservationController.getReservationByRoom(roomId, ReservationStatus.CONFIRMED) == null) break;
            else {
                System.out.println("Choose a empty room!!");
            }
        
        } while (true);
        
        

        return roomId;
    }

    
    /** 
     * This method updates the guestID tagged to the Reservation detail
     * @return Updated GuestID is returned as a String output
     * @throws IOException Due to communication with the DataBase IOexception is required
     */
    public static String updateGuestId() throws IOException {
        // for now i just return normal string
        do {
            System.out.print("Are you a new Guest (Y/N)?:  ");
            String choice = sc.next();
            if (choice.equalsIgnoreCase("y")) {
                Guest newGuest = GuestController.CreateGuest();
                return newGuest.getGuestId();

            } else if (choice.equalsIgnoreCase("n")) {
                String guestId = UpdateGuestMenu.UpdateGuestId();
                Guest guest = GuestController.RetrieveGuest(guestId);
                if(guest==null){
                    System.out.println("Unable to find record!. New Record will be created");
                    guest = GuestController.CreateGuest();
                }
                return guest.getGuestId();
		}
        } while(true);
        // return "Jane Watson";
    }

    
    /** 
     * This method updates the number of adults tagged to the reservation detail
     * @return Updated number of adults is returned as a Integer output
     */
    public static Integer updateNumberOfAdults() {
        Integer numOfAdults;
        do {
			System.out.print("Enter total Number of Adults: ");
			while (!sc.hasNextInt()) {
		        System.out.println("Please enter numbers only.");
		        sc.next(); // this is important!
		    }
			numOfAdults = sc.nextInt();
			if(numOfAdults <= 0) {
				System.out.println("Numer of Adults cannot be less than 0.");
			}
            else if (numOfAdults > 4) {
                System.out.println("Too many people");
            }
            else {
                break;
            }
			
		}while(true);

        return numOfAdults;
    }

    
    /** 
     * This method updates the number of children tagged to the reservation detail
     * @return Updated number of chilren is returned as a Integer output
     */
    public static Integer updateNumberOfChildren() {
        Integer numOfChildren;
        do {
			System.out.print("Enter total Number of Children: ");
			while (!sc.hasNextInt()) {
		        System.out.println("Please enter numbers only.");
		        sc.next(); // this is important!
		    }
			numOfChildren = sc.nextInt();
			if(numOfChildren < 0) {
				System.out.println("Please enter a valid number.");
			}
            else if (numOfChildren > 5) {
                System.out.println("Too many people");
            }
            else {
                break;
            }
		}while(true);

        return numOfChildren;
    }
}
