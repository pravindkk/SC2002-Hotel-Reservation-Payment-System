package com.hotel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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

        // ReservationUI res = new ReservationUI();
        // ReservationController resControl = new ReservationController();
        // resControl.createReservation();
        // RoomController roomCon = new RoomController();

        // RoomUI room = new RoomUI();


        int choice = mainDisplayOptions();
        do {
            switch (choice) {
                case 1:
                    new ReservationUI();
                    break;
    
                case 2:
                    RoomUI roomUI = new RoomUI();
                    break;
                
                case 3:
                    MenuUI menuUI = new MenuUI();
                    break;
                
                case 6:
                    return;
                default:
                    break;
            }
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
}
