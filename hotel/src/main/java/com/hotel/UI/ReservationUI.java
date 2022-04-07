package com.hotel.UI;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import com.hotel.controller.ReservationController;
import com.hotel.controller.RoomController;
import com.hotel.controller.UpdateReservationDetailsDisplayUI;
import com.hotel.controller.UpdateRoomMenuDisplayUI;
import com.hotel.system.Reservation;
import com.hotel.system.enums.ReservationStatus;
import com.hotel.system.enums.RoomStatus;

/**
 * Represents the class of ReservationUI, which prints the UI for Reservation-related operations
 * @author Melissa Ng Li Yun
 * @version 1.0
 * @since 1.0
 */

public class ReservationUI {
    static Scanner sc = new Scanner(System.in);
    ReservationController reservationController = new ReservationController();
    static SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * This method prints the UI for the creation of a reservation
     * then obtains the relevant inputs and creates the reservation
     */
    public void createRes() {
        try {
            // reservationController.createReservation();
            Date checkInDate = UpdateReservationDetailsDisplayUI.updateCheckInDate();
            Date checkOutDate = UpdateReservationDetailsDisplayUI.updateCheckOutDate(checkInDate);
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
                reservationController.changeRoomStatus(roomId, RoomStatus.RESERVED);
                String reservationDate = df.format(checkInDate).replaceAll("\\D", "");
                String reservationRoomId = roomId.replaceAll("\\D", "");
                int a = (int) (Math.random()*(200-1)+1);
                String reservationNum = guestId.charAt(0) + "-" + reservationDate + "-" + reservationRoomId + String.valueOf(a);
                Reservation reservation = new Reservation(ReservationStatus.CONFIRMED,reservationNum, guestId, roomId, checkInDate, checkOutDate,
                        numOfAdults, numOfChildren);
                reservationController.createReservation(reservation);   

            } else {
                System.out.println("Reservation not confirmed!");
            }


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    
    public void walkIn() {
        try {
            Date checkInDate = new Date();
            Date checkOutDate = UpdateReservationDetailsDisplayUI.updateCheckOutDate(checkInDate);
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
                reservationController.changeRoomStatus(roomId, RoomStatus.OCCUPIED);
                String reservationDate = df.format(checkInDate).replaceAll("\\D", "");
                String reservationRoomId = roomId.replaceAll("\\D", "");
                int a = (int) (Math.random()*(200-1)+1);
                String reservationNum = guestId.charAt(0) + "-" + reservationDate + "-" + reservationRoomId + String.valueOf(a);
                Reservation reservation = new Reservation(ReservationStatus.CHECKED_IN,reservationNum, guestId, roomId, checkInDate, checkOutDate,
                        numOfAdults, numOfChildren);
                reservationController.createReservation(reservation);   

            } else {
                System.out.println("Reservation not confirmed!");
            }


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    




    /**
     * This method prints the UI for the updating of a reservation
     * then obtains the relevant inputs and updates the reservation
     */
    public void updateRes() {
        try {

            System.out.print("Enter Reservation Number:  ");

            String reservationId = sc.next();

            Reservation reservation = reservationController.getReservationByNum(reservationId);

            if (reservation == null) {
                System.out.println("Wrong Reservation Number");
                return;
            }
            


            int choice = 5;
            do {
                System.out.println("Please choose Reservation details to update\n"+
                                "(1) Room Type\n"+
                                "(2) Check-in Date\n"+
                                "(3) Check-out Date\n"+
                                "(4) Number of People\n");
                choice = sc.nextInt();
            } while(choice< 1 || choice > 4);

            switch (choice) {
                case 1:
                    RoomController.printRooms(RoomController.getVacantRooms());
                    String roomId = UpdateRoomMenuDisplayUI.updateRoomId();
                    reservationController.switchRoomStatus(reservation.getRoomId(), roomId);
                    reservation.setRoomId(roomId);
                    break;
                case 2:
                    Date checkIn = UpdateReservationDetailsDisplayUI.updateCheckInDate();
                    reservation.setCheckInDate(checkIn);
                    break;
                case 3:
                    Date checkOut = UpdateReservationDetailsDisplayUI.updateCheckOutDate(reservation.getCheckInDate());
                    reservation.setCheckOutDate(checkOut);
                    break;
                case 4:
                    Integer numOfAdults = UpdateReservationDetailsDisplayUI.updateNumberOfAdults();
                    Integer numOfChild = UpdateReservationDetailsDisplayUI.updateNumberOfChildren();
                    reservation.setNumOfAdults(numOfAdults);
                    reservation.setNumOfChildren(numOfChild);
                    break;
                default:
                    break;
            }

            reservationController.updateReservation(reservation);


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * This method prints the UI for the removal of a reservation
     * then obtains the relevant inputs and removes the reservation
     */
    public void removeRes() {
        sc.nextLine();
        try {
            String reservationId = sc.nextLine();
            reservationController.cancelReservation(reservationId);
        } catch (Exception e) {
            //TODO: handle exception
        }
        
    }

    /**
     * This method prints the UI for the printing of a reservation's details
     * then obtains the relevant inputs and prints the details
     */
    public void printRese() {
        sc.nextLine();
        try {
            System.out.print("What is your reservation number: ");
            String reservationId = sc.nextLine();
            reservationController.printReservation(reservationId);
        } catch (Exception e) {
            //TODO: handle exception
        }
        
    }

    /**
     * This method prints the all details of all reservations
     */
    public void printAllRes() {
        try {
            reservationController.printAllReservations();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
