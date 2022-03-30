package com.hotel;

import java.io.IOException;

import com.hotel.controller.*;
import com.hotel.system.Room;
import com.hotel.system.enums.RoomStatus;


public class HRPS {
    public static void main(String[] args) throws IOException {
        ReservationController test = new ReservationController();

        test.createReservation();
        // RoomController room = new RoomController();
        // Room r = room.getSpecificRoom("02-04");
        // r.setRoomStatus(RoomStatus.OCCUPIED);
        // RoomController.saveSpecificRoomByRoomId(r);
    }
}
