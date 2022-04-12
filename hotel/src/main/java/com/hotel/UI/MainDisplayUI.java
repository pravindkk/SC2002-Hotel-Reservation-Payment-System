package com.hotel.UI;

import java.util.Scanner;

import com.hotel.controller.MenuController;
/**
 * Represents the class of MainDisplayUI, which prints the UI for HRPS
 * @version 1.0
 * @since 1.0
 */

public class MainDisplayUI {
    static Scanner sc = new Scanner(System.in);

    
    /** 
     * This method prints out the reservation options for HRPS. It then asks the user to select an option
     * @return Choice is returned as a int output
     */
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

    
    /** 
     * This method prints out the room options for HRPS. It then asks the user to select an option
     * @return Choice is returned as a int output
     */
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
                               "(5) View Available Rooms\n"+
                               "(6) View All Rooms\n"+
                               "(7) Back\n"+
                               "========================\n"
            );

            System.out.print("What is your choice (1-7)?: ");
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

    
    /** 
     * This method prints out the menu options for HRPS. It then asks the user to select an option
     * @return Choice is returned as a int output
     */
    public static int displayMenuOptions() {
        int choice=0;
        do {
            
            try {
                MenuController.printAllItems();
                System.out.println("\n==================================================");
                System.out.println(" Menu item Management: ");
                System.out.println("==================================================");
                System.out.println("(1) Create Menu item\t(2) Update Menu item");
                System.out.println("(3) Remove Menu item\t(4) Back");
                choice = sc.nextInt();
                if (choice >0 && choice <5) break;
                else System.out.println("Enter a number between (1-6)!!");
            } catch (Exception e) {
                //TODO: handle exception
            }
            
            
        } while(true);

        return choice;
    }


    
    /** 
     * This method prints out the order options for HRPS. It then asks the user to select an option
     * @return Choice is returned as a int output
     */
    public static int displayOrderOptions() {
        int choice;
        do {
            System.out.println("========================\n" + 
                               "Room Service Orders\n"+
                               "========================\n"+
                               "(1) Create Order\n"+
                               "(2) Update Order\n"+
                               "(3) Remove Order\n"+
                               "(4) View Order\n"+
                               "(5) View All Orders\n"+
                               "(6) Back\n"+
                               "========================\n"
            );

            System.out.print("What is your choice (1-5)?: ");
            try {
                choice = sc.nextInt();
                if (choice >0 && choice <6) break;
            } catch (Exception e) {
                //TODO: handle exception
                System.out.println("Enter a number between (1-5)!!");
            }
        } while (true);

        return choice;
    }

    public static int displayGuestOptions() {
        int choice;
        do {
            System.out.println("========================\n" + 
                               "Guest Menu\n"+
                               "========================\n"+
                               "(1) Create Guest\n"+
                               "(2) Update Guest\n"+
                               "(3) Remove Guest\n"+
                               "(4) View Guests\n"+
                               "(5) Search Guest\n"+
                               "(6) Back\n"+
                               "========================\n"
            );

            System.out.print("What is your choice (1-6)?: ");
            try {
                choice = sc.nextInt();
                if (choice >0 && choice <7) break;
            } catch (Exception e) {
                //TODO: handle exception
                System.out.println("Enter a number between (1-6)!!");
            }
        } while (true);

        return choice;
    }
    public static int displayCheckInOptions() {
        int choice;
        do {
            System.out.println("========================\n" + 
                               "Check In Menu\n"+
                               "========================\n"+
                               "(1) Check In with Guest Id\n"+
                               "(2) Check In with Reservation Id\n"+
                               "(3) Walk In\n"+
                               "(4) Back\n"+
                               "========================\n"
            );

            System.out.print("What is your choice (1-4)?: ");
            try {
                choice = sc.nextInt();
                if (choice >0 && choice <5) break;
            } catch (Exception e) {
                //TODO: handle exception
                System.out.println("Enter a number between (1-4)!!");
            }
        } while (true);

        return choice;
    }

    
}
