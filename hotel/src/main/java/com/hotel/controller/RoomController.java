package com.hotel.controller;

import java.io.IOException;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hotel.db.ReadInFile;
import com.hotel.db.RoomDB;
import com.hotel.system.Room;
import com.hotel.system.enums.*;
import com.hotel.controller.SetRoom;

public class RoomController {
    // private RoomDB allRooms;
    static RoomDB allRooms = new RoomDB();
    Scanner sc = new Scanner(System.in);
    // ArrayList hello = test.getAllRooms();
    public static void main(String[] args) throws IOException {
        RoomController test = new RoomController();
        ArrayList hello = test.getAllRooms();
        
        
    }

    public void createRoom() throws IOException {

        String roomId = SetRoom.setRoomId();
        String[] parts = roomId.split("-");
        String roomFloor = parts[0];
        Integer roomNumber = Integer.valueOf(parts[1]);

        RoomType roomType = SetRoom.setRoomType();
        BedType bedType = SetRoom.setBedType();
        boolean withView = SetRoom.setWithView();
        RoomStatus roomStatus = SetRoom.setRoomStatus();
        Float roomRate = SetRoom.setRoomRate();
        boolean wifiEnabled = SetRoom.setWifiEnabled();
        boolean smokingStatus = SetRoom.setSmokingStatus();


        Room room = new Room(roomType,
                             roomStatus, 
                             bedType, 
                             roomId, 
                             withView, 
                             roomRate, 
                             roomFloor, 
                             roomNumber, 
                             wifiEnabled, 
                             smokingStatus);

        ArrayList allData = getAllRooms();
        allData.add(room);
        try {
            allRooms.save(allRooms.getPath(), allData);
            System.out.println("Room successfully added!");
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println("Room not added!");

        }
        

    }

    public void updateRoom(String roomId) throws IOException {
        ArrayList roomList = getSpecificRoom(roomId);
        // Room room = (Room)
        if (roomList == null) {
            System.out.println("Invalid Room");
            return;
        }
        Integer index = (Integer) roomList.get(0);
        Room room = (Room) roomList.get(1);

        System.out.println("\nPlease choose guest details to update \n(1) Room Type \n(2) Bed Type "
				+ "\n(3) View Type \n(4) Room Status \n(5) Room Rate \n(6) Wifi Enabled \n(7) Room Smoking Status \n(8) All details");

        int choice = 0;
        try {
            choice = sc.nextInt();
        } catch (Exception e) {
            //TODO: handle exception
        }

        if (choice < 1 || choice > 8) {
            System.out.println("Invalid input");
            return;
        }




    }

    public static boolean checkRoomIDExists(String roomId) throws IOException {
        ArrayList allData = getAllRooms();
        for (int i=0; i<allData.size(); i++) {
            Room r = (Room) allData.get(i);
            if (r.getRoomId() == roomId) return true;
        }

        return false;

    }

    public static ArrayList getAllRooms() throws IOException {
        ArrayList allData = allRooms.read(allRooms.getPath());
        return allData;
    }

    public ArrayList getSpecificRoom(String roomId) throws IOException {
        ArrayList toReturn = new ArrayList();

        ArrayList allData = getAllRooms();

        for (int i=0; i<allData.size(); i++) {
            Room r = (Room) allData.get(i);
            if (r.getRoomId() == roomId) {
                toReturn.add(i);
                toReturn.add(r);
                break;
            }
        }

        return toReturn;
    }
}
