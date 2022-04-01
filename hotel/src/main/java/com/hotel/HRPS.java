package com.hotel;

import java.io.IOException;
import java.util.ArrayList;

import com.hotel.UI.MenuUI;
import com.hotel.UI.OrderUI;
import com.hotel.controller.*;
import com.hotel.system.Guest;
import com.hotel.system.Order;
import com.hotel.system.Room;
import com.hotel.system.enums.RoomStatus;


public class HRPS {
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

        OrderUI test = new OrderUI();

        OrderController order = new OrderController();
        // order.displayOrder("0-J-01042022");
        // Order hello = order.getOrderById("0-J-01042022");
        // System.out.print(hello.getItem().get(0).getItemId());
        // for (int i=0; i<hello.getItem().size(); i++) {
        //     System.out
        // }
        // for (int i=0; i<)
        // MenuUI menu = new MenuUI();
        // menu.createNewItem();
        // menu.displayOptions();
        // test.displayOptions();
    }
}
