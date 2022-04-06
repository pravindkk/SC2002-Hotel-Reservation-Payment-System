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

/**
 * Represents the class of Payment, which holds the information at the instance the payment is made
 * @author Melissa Ng Li Yun
 * @version 1.0
 * @since 1.0
 */

public class ReservationUI {
    static Scanner sc = new Scanner(System.in);
    ReservationController reservationController = new ReservationController();
    static SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

    public ReservationUI () {
        int choice =0;
        do {
            choice  = displayOptions();
            switch (choice) {
                case 1:
                    createRes();
                    break;
                case 2:
                    updateRes();
                    break;
                
                case 3:
                    removeRes();
                    break;
                case 4:
                    // displayOrder();
                    printRese();
                    break;

                case 5:
                    printAllRes();
                    break;
                
                case 6:
                    return;
            
                default:
                    System.out.println("Invalid input");
                    break;
            }
        } while (true);
        

        

    }

    
    /** 
     * @return int
     */
    public int displayOptions() {
        int choice;
        do {
            System.out.println("========================\n" + 
                               "Reservations Order\n"+
                               "========================\n"+
                               "(1) Create Reservation\n"+
                               "(2) Update Reservation\n"+
                               "(3) Remove Reservation\n"+
                               "(4) Print Reservation\n"+
                               "(5) View All Reservations\n"+
                               "(6) Back\n"+
                               "========================\n"
            );

            System.out.print("What is your choice (1-6)?: ");
            try {
                choice = sc.nextInt();
                if (choice >0 && choice <7) break;
                else System.out.println("Enter a number between (1-6)!!");
            } catch (Exception e) {
                //TODO: handle exception
                
            }
        } while (true);

        return choice;
    }

    public void createRes() {
        try {
            // reservationController.createReservation();
            Date checkInDate = UpdateReservationDetailsDisplayUI.updateCheckInDate();
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
                String reservationDate = df.format(checkInDate).replaceAll("\\D", "");
                String reservationRoomId = roomId.replaceAll("\\D", "");
                String reservationNum = guestId.charAt(0) + "-" + reservationDate + "-" + reservationRoomId;
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
                    Date checkOut = UpdateReservationDetailsDisplayUI.updateCheckOutDate();
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

    public void removeRes() {
        sc.nextLine();
        try {
            String reservationId = sc.nextLine();
            reservationController.cancelReservation(reservationId);
        } catch (Exception e) {
            //TODO: handle exception
        }
        
    }

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

    public void printAllRes() {
        try {
            reservationController.printAllReservations();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
}
