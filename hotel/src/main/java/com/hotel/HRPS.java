package com.hotel;

import java.io.IOException;
import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.hotel.UI.MainDisplayUI;
import com.hotel.UI.MenuUI;
import com.hotel.UI.OrderUI;
import com.hotel.UI.PaymentUI;
import com.hotel.UI.ReservationUI;
import com.hotel.UI.RoomUI;
import com.hotel.controller.*;
import com.hotel.system.CreditCard;
import com.hotel.system.Guest;


public class HRPS {
    static Scanner sc = new Scanner(System.in);
    static ReservationUI res = new ReservationUI();
    static RoomUI room = new RoomUI();
    static MenuUI menu = new MenuUI();
    static OrderUI order = new OrderUI();
    static GuestController guestController = new GuestController();
    static PaymentUI pay = new PaymentUI();
    static ReservationController resController = new ReservationController();
    public static void main(String[] args) throws IOException {

        // Next run 3pm GMT - Singapore Time
        final ZonedDateTime now = ZonedDateTime.now(ZoneId.systemDefault());
        ZonedDateTime nextRun = now.withHour(16).withMinute(0).withSecond(0);

        // If in the past, add one day
        if (now.compareTo(nextRun) > 0) {
            nextRun = nextRun.plusDays(1);
        }

        // Get duration between now and midnight
        final Duration initialDelay = Duration.between(now, nextRun);

        // Schedule a task to run at midnight and then every day
        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> expireRes(),
                initialDelay.toMillis(),
                Duration.ofDays(1).toMillis(),
                TimeUnit.MILLISECONDS);

        main();

        
        

        // PaymentUI pay = new PaymentUI();
        // pay.printPayment();
        


        
        
    }

    public static void main() {
        do {
            int choice = mainDisplayOptions();
            switch (choice) {
                case 1:
                    chooseReservation();
                    break;
    
                case 2:
                    chooseRoom();
                    break;
                
                case 3:
                    chooseMenu();
                    break;
                case 4:
                    chooseOrder();;
                    break;

                case 5:
                    chooseCheckIn();
                    break;
                
                case 6:
                    chooseCheckOut();
                    break;

                case 7:
                    chooseGuest();
                    break;
                
                case 8:
                    System.exit(0);;
                default:
                    break;
            }
            // choice
        } while (true);
    }

    public static int mainDisplayOptions() {

        int choice=0;

        do {
            System.out.println("======================\n"+
                               "        HRPS          \n"+
                               "======================\n"+
                               "(1) Reservation\t(2) Rooms\n"+
                               "(3) Menu\t(4) Room Service\n"+
                               "(5) Check In\t(6) Check Out\n"+
                               "(7) Guest\t(8) Exit\n");
            try {
                System.out.print("What is your choice:  ");
                choice = sc.nextInt();
            } catch (Exception e) {
                //TODO: handle exception
            }

        } while (choice<1 || choice >8);

        return choice;
    }

    public static void expireRes() {
        res.toExpireRes();
        main();
    }

    

    public static void chooseReservation() {
        
        int choice =0;
        do {
            choice  = MainDisplayUI.displayReservationOptions();
            switch (choice) {
                case 1:
                    res.createRes();
                    break;
                case 2:
                    res.updateRes();
                    break;
                
                case 3:
                    res.removeRes();
                    break;
                case 4:
                    // displayOrder();
                    res.printRese();
                    break;

                case 5:
                    res.printAllRes();
                    break;
                
                case 6:
                    return;
            
                default:
                    System.out.println("Invalid input");
                    return;
            }
        } while (true);
        
    }
    public static void chooseCheckIn() {
        int choice =0;
        do {
            choice  = MainDisplayUI.displayCheckInOptions();
            switch (choice) {
                case 1:
                    sc.nextLine();
                    try {
                        System.out.print("What is the Guest Id?:  ");
                        String guestId = sc.nextLine();
                        resController.checkInGuest(guestId, "G");
                    } catch (Exception e) {
                        //TODO: handle exception
                    }
                    break;
                case 2:
                    sc.nextLine();
                    try {
                        System.out.print("What is the Reservation Id?:  ");
                        String roomId = sc.nextLine();
                        resController.checkInGuest(roomId, "R");
                    } catch (Exception e) {
                        //TODO: handle exception
                    }
                    break;
                case 3:
                    res.walkIn();
                    break;
                case 4:
                    return;
            
                default:
                    System.out.println("Invalid input");
                    return;
            }
        } while (true);
        
    }

    public static void chooseCheckOut() {
        sc.nextLine();
        try {
            String roomId = UpdateRoomMenuDisplayUI.updateRoomId();
            boolean check = resController.checkOutGuest(roomId);
            if (check == true) pay.printPayment(roomId);
            else {
                System.out.println("ERROR!! The room is not checked in / room has already been checked out");
            }
            
            // pay.printPayment();
        } catch (Exception e) {
            //TODO: handle exception
        }
    }


    public static void chooseRoom() {
        int choice =0;
        do {
            choice  = MainDisplayUI.displayRoomOptions();
            switch (choice) {
                case 1:
                    room.createRoom();
                    break;
                case 2:
                    room.updateRoom();
                    break;
                
                case 3:
                    room.deleteRoom();
                    break;
                case 4:
                    room.printRoom();
                    break;
                case 5:
                    room.viewOccupancyRate();
                    break;

                case 6:
                    room.viewRoomStatus();
                    break;

                case 7:
                    room.printAllRooms();
                    break;
                
                case 8:
                    return;
            
                default:
                    System.out.println("Invalid input");
                    break;
            }
        } while (true);
        
    }

    public static void chooseMenu() {
        int choice =0;
        do {
            choice  = MainDisplayUI.displayMenuOptions();
            switch (choice) {
                case 1:
                    // createRes();
                    menu.createNewItem();
                    break;
                case 2:
                    try {
                        menu.updateMenuItem();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    break;
                
                case 3:
                    try {
                        menu.deleteMenuItem();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    
                    return;

                default:
                    System.out.println("Invalid input");
                    break;
            }
        } while (true);
        

    }


    public static void chooseOrder() {
        int choice =0;
        do {
            choice  = MainDisplayUI.displayOrderOptions();
            switch (choice) {
                case 1:
                    order.createNewOrder();
                    break;
                case 2:
                    order.updateOrder();
                    break;
                
                case 3:
                    try {
                        order.deleteOrder();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    order.displayOrder();
                    break;
                case 5:
                    order.displayAllOrders();
                    break;

                case 6:
                    return;
            
                default:
                    System.out.println("Invalid input");
                    break;
            }
        } while (true);
        
    }
    
    public static void chooseGuest() {
        int choice =0;
        String guestId = null;
        String trash;
        do {
            choice  = MainDisplayUI.displayGuestOptions();
            switch (choice) {
                case 1:
                    // order.createNewOrder();
                    try {
                        guestController.CreateGuest();
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    break;
                case 2:
                    // sc.nextLine();
                    try {
                        guestController.PrintAllGuestDetails();
                        System.out.print("What is the Guest Id?:  ");
                        guestId = sc.next();
                        guestController.UpdateGuest(guestId);
                    } catch (Exception e) {
                        //TODO: handle exception
                    }
                    trash = sc.nextLine();
                    break;
                
                case 3:
                    // sc.nextLine();
                    
                    try {
                        guestController.PrintAllGuestDetails();
                        System.out.print("What is the Guest Id?:  ");
                        guestId = sc.next();
                        guestController.DeleteGuest(guestId);
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                    }
                    trash = sc.nextLine();
                    break;
                case 4:
                    try {
                        guestController.PrintAllGuestDetails();
                    } catch (Exception e) {
                        //TODO: handle exception
                    }
                    break;

                case 5:
                    
                    try {
                        System.out.print("What is the Guest Id?:  ");
                        guestId = sc.next();
                        Guest g = guestController.RetrieveGuest(guestId);
                        CreditCard c = CreditCardController.RetrieveCreditCard(guestId);
                        System.out.printf("%-10s %-10s %-8s %-15s %-13s %-10s %-25s %-18s %-15s %-3s\n", "GuestID", "Name",
                        "Gender", "Nationality", "Country", "Contact", "CreditCard Number", "CreditCard Type",
                        "CreditCard Exp", "CreditCard Cvv ");

                        System.out.printf("%-10s %-10s %-8s %-15s %-13s %-10s %-25s %-18s %-15s %-3s\n", 
                                          g.getGuestId(), g.getName(), g.getGender(), g.getNationality(), 
                                          g.getCountry(), g.getPhoneNumber(), g.getCreditCardNumber(), 
                                          String.valueOf(c.getCardType()), c.getExpiry(), c.getCVV());

                        // System.out.println(g.getGuestId());

                        
                    } catch (Exception e) {
                        //TODO: handle exception
                        System.out.println("ERROR!! GUEST NOT FOUND!!");
                    }
                    trash = sc.nextLine();
                    break;
                case 6:
                    return;
            
                default:
                    System.out.println("Invalid input");
                    break;
            }
        } while (true);
        

        

    }



}
