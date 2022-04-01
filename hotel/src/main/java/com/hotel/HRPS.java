package com.hotel;

import java.io.IOException;

import com.hotel.UI.MenuUI;
import com.hotel.UI.OrderUI;
import com.hotel.controller.*;
import com.hotel.system.Guest;
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
        // MenuUI menu = new MenuUI();
        // menu.createNewItem();
        // menu.displayOptions();
        // test.displayOptions();
    }
}
