package com.hotel.UI;

import java.io.IOException;
import java.util.Scanner;

import com.hotel.controller.ReservationController;
import com.hotel.controller.UpdateReservationDetailsDisplayUI;

/**
 * Represents the class of Payment, which holds the information at the instance the payment is made
 * @author Melissa Ng Li Yun
 * @version 1.0
 * @since 1.0
 */

public class ReservationUI {
    static Scanner sc = new Scanner(System.in);
    ReservationController reservationController = new ReservationController();

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
            reservationController.createReservation();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void updateRes() {
        try {
            reservationController.updateReservation();
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
