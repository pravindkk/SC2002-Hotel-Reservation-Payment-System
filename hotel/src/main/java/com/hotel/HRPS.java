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
    public static void main(String[] args) throws IOException {
        // ReservationController test = new ReservationController();

        // test.createReservation();
        // RoomController room = new RoomController();
        // Room r = room.getSpecificRoom("02-04");
        // r.setRoomStatus(RoomStatus.OCCUPIED);
        // RoomController.saveSpecificRoomByRoomId(r);

        // GuestController g = new GuestController();
        // // Guest hotel = g.CreateGuest();
        // // g.UpdateGuest("1231");
        // g.DeleteGuest("1231");
        // Guest hotel = g.RetrieveGuest("1231");
        // System.out.println(hotel.getGuestId());

        // OrderUI test = new OrderUI();

        // OrderController order = new OrderController();
        // order.displayOrder("0-J-01042022");
        // Order hello = order.getOrderById("0-J-01042022");
        // System.out.print(hello.getItem().get(0).getItemId());
        // for (int i=0; i<hello.getItem().size(); i++) {
        //     System.out
        // }
        // for (int i=0; i<)
        // MenuUI menu = new MenuUI();
        // // menu.createNewItem();
        // menu.displayOptions();
        // test.displayOptions();

        
        // ReservationController resControl = new ReservationController();
        // resControl.createReservation();
        // RoomController roomCon = new RoomController();

        // RoomUI room = new RoomUI();



        
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
                    MenuUI menuUI = new MenuUI();
                    break;
                
                case 6:
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
                               "(4) Payment\n"+
                               "(5) Guest\n"+
                               "(6) Back");
            try {
                choice = sc.nextInt();
            } catch (Exception e) {
                //TODO: handle exception
            }

        } while (choice<1 || choice >6);

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
                    // createRes();
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
}
