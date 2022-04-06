package com.hotel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.hotel.UI.MainDisplayUI;
import com.hotel.UI.MenuUI;
import com.hotel.UI.OrderUI;
import com.hotel.UI.ReservationUI;
import com.hotel.UI.RoomUI;
import com.hotel.controller.*;
import com.hotel.system.Guest;
import com.hotel.system.Order;
import com.hotel.system.Room;
import com.hotel.system.enums.RoomStatus;


public class HRPS {
    static Scanner sc = new Scanner(System.in);
    static ReservationUI res = new ReservationUI();
    static RoomUI room = new RoomUI();
    static MenuUI menu = new MenuUI();
    static OrderUI order = new OrderUI();
    public static void main(String[] args) throws IOException {
        
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
                
                case 7:
                    return;
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
                               "(1) Reservation\n"+
                               "(2) Rooms\n"+
                               "(3) Menu\n"+
                               "(4) Order\n"+
                               "(5) Payment\n"+
                               "(6) Guest\n"+
                               "(7) Back");
            try {
                System.out.print("What is your choice:  ");
                choice = sc.nextInt();
            } catch (Exception e) {
                //TODO: handle exception
            }

        } while (choice<1 || choice >7);

        return choice;
    }

    

    public static void chooseReservation () {
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
                    room.printAllRooms();
                    break;
                
                case 7:
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
                    return;
            
                default:
                    System.out.println("Invalid input");
                    break;
            }
        } while (true);
        

        

    }



}
