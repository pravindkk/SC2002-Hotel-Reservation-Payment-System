package com.hotel.UI;

import java.util.Scanner;

public class MainDisplayUI {
    static Scanner sc = new Scanner(System.in);
    public static int displayReservationOptions() {
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

    public static int displayRoomOptions() {
        int choice;
        do {
            System.out.println("========================\n" + 
                               "Rooms\n"+
                               "========================\n"+
                               "(1) Create Rooms\n"+
                               "(2) Update Rooms\n"+
                               "(3) Remove Rooms\n"+
                               "(4) Print Room\n"+
                               "(5) View Occupancy Rate\n"+
                               "(6) View All Rooms\n"+
                               "(7) Back\n"+
                               "========================\n"
            );

            System.out.print("What is your choice (1-6)?: ");
            try {
                choice = sc.nextInt();
                if (choice >0 && choice <8) break;
                else System.out.println("Enter a number between (1-6)!!");
            } catch (Exception e) {
                //TODO: handle exception
                
            }
        } while (true);

        return choice;
    }
}
