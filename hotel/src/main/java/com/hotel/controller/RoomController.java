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
import com.hotel.controller.UpdateRoomMenu;

public class RoomController {
    // private RoomDB allRooms;
    static RoomDB allRooms = new RoomDB();
    Scanner sc = new Scanner(System.in);


    
    // public static void main(String[] args) throws IOException {
    //     RoomController test = new RoomController();
    //     ArrayList hello = test.getAllRooms();
    //     // test.updateRoom("02-04");
    //     // test.getVacantRooms();
    //     test.printAllRooms();
        
        
    // }

    public void createRoom() throws IOException {

        String roomId = UpdateRoomMenu.updateRoomId();
        String[] parts = roomId.split("-");
        String roomFloor = parts[0];
        Integer roomNumber = Integer.valueOf(parts[1]);

        RoomType roomType = UpdateRoomMenu.updateRoomType();
        BedType bedType = UpdateRoomMenu.updateBedType();
        boolean withView = UpdateRoomMenu.updateWithView();
        RoomStatus roomStatus = UpdateRoomMenu.updateRoomStatus();
        Float roomRate = UpdateRoomMenu.updateRoomRate();
        boolean wifiEnabled = UpdateRoomMenu.updateWifiEnabled();
        boolean smokingStatus = UpdateRoomMenu.updateSmokingStatus();


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
        saveData(allData);
        

    }

    public static void saveData(ArrayList toWrite) {
        
        try {
            allRooms.save(allRooms.getPath(), toWrite);
            System.out.println("Room successfully updated!");
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println("Room not added!");

        }
    }

    public void updateRoomStatus(String roomId) throws IOException {
        ArrayList allData = getAllRooms();
        Room room = getSpecificRoom(roomId);
        if (room == null) {
            System.out.println("Invalid string");
            return;
        }

        room.setRoomType(UpdateRoomMenu.updateRoomType());

        saveSpecificRoomByRoomId(room);

        // return room;
    }

    public static void saveSpecificRoomByRoomId(Room toChange) throws IOException {
        ArrayList allData = getAllRooms();

        for (int i=0; i<allData.size(); i++) {
            Room r = (Room) allData.get(i);
            if (toChange.getRoomId().equals(r.getRoomId())) {
                allData.set(i, r);
                saveData(allData);
                return;
            }
        }
        System.out.println("couldnt save reservation");
    }  



    public void updateRoom(String roomId) throws IOException {
        ArrayList allData = getAllRooms();
        Room room = getSpecificRoom(roomId);
        // // Room room = (Room)
        if (room == null) {
            System.out.println("Invalid Room");
            return;
        }


        System.out.println("\nPlease choose room details to update \n" + 
                           "(1) Room Type \n"+
                           "(2) Bed Type \n"+ 
                           "(3) View Type \n"+
                           "(4) Room Status \n"+
                           "(5) Room Rate \n"+
                           "(6) Wifi Enabled \n"+
                           "(7) Room Smoking Status \n"+
                           "(8) All details");

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

        switch (choice) {
            case 1:
                room.setRoomType(UpdateRoomMenu.updateRoomType());
                break;
            case 2:
                room.setBedType(UpdateRoomMenu.updateBedType());
                break;
            case 3:
                room.setWithView(UpdateRoomMenu.updateWithView());
                break;
            case 4:
                room.setRoomStatus(UpdateRoomMenu.updateRoomStatus());
                break;
            case 5:
                room.setRoomRate(UpdateRoomMenu.updateRoomRate());
                break;
            case 6:
                room.setWifiEnabled(UpdateRoomMenu.updateWifiEnabled());
                break;
            case 7:
                room.setSmokingStatus(UpdateRoomMenu.updateSmokingStatus());
                break;
            case 8:
                room.setRoomType(UpdateRoomMenu.updateRoomType());
                room.setBedType(UpdateRoomMenu.updateBedType());
                room.setWithView(UpdateRoomMenu.updateWithView());
                room.setRoomStatus(UpdateRoomMenu.updateRoomStatus());
                room.setRoomRate(UpdateRoomMenu.updateRoomRate());
                room.setWifiEnabled(UpdateRoomMenu.updateWifiEnabled());
                room.setSmokingStatus(UpdateRoomMenu.updateSmokingStatus());
                break;
            
            default:
                break;
        }

        saveSpecificRoomByRoomId(room);


    }

    public static boolean checkRoomIDExists(String roomId) throws IOException {
        ArrayList allData = getAllRooms();
        for (int i=0; i<allData.size(); i++) {
            Room r = (Room) allData.get(i);
            if (roomId.equals(r.getRoomId())) return true;
        }

        return false;

    }

    public static ArrayList getAllRooms() throws IOException {
        ArrayList allData = allRooms.read(allRooms.getPath());
        return allData;
    }


    public static Room getSpecificRoom(String roomId) throws IOException {

        ArrayList allData = getAllRooms();

        for (int i=0; i<allData.size(); i++) {
            Room r = (Room) allData.get(i);
            // System.out.print(r.getRoomId());
            if (roomId.equals(r.getRoomId())) {
                // System.out.print("hello");
                return r;
            }
        }

        return null;
    }

    public static void printOneRoom(String roomId) throws IOException {
        Room r = (Room) getSpecificRoom(roomId);


        System.out.println();
        // for (int i=0; i<allData.size(); i++) {
            // Room r = (Room) ;
        String wifi;
        if (r.getWifiEnabled()) wifi = "Enabled";
        else wifi = "Disabled";

        String smoke;
        if (r.getSmokingStatus()) smoke = "Allowed";
        else smoke = "Not Allowed";

        System.out.printf("%-8s %-13s %-18s %-11s %-19s %-15s %-12s %-13s %-13s %-10s", "RoomID", "Room Type", "Bed Type", "With View", 
        "Room Status", "Room Rate(S$)", "Room Floor","Room Number","Wifi Status","Smoking Status");
        System.out.println("");
        System.out.printf("%-8s %-13s %-18s %-11s %-19s %-15s %-12s %-13s %-13s %-10s", 
            r.getRoomId(), r.getRoomType(), r.getBedType(), r.getWithView(), r.getRoomStatus(), 
            r.getRoomRate(),r.getRoomFloor(),r.getRoomNumber(), wifi, smoke);
        System.out.println("");
        // }
        
    }


    public static ArrayList getVacantRooms() throws IOException {
        ArrayList allData = getAllRooms();
        
        allData.removeIf(r -> ((Room)r).getRoomStatus() != RoomStatus.VACANT);


        return allData;
    }

    public static ArrayList getRoomsByRoomType(RoomType roomType) throws IOException {
        ArrayList allData = getAllRooms();
        
        allData.removeIf(r -> ((Room)r).getRoomType() != roomType);

        return allData;
    }

    public static ArrayList getRoomsByRoomType(ArrayList allData, RoomType roomType) throws IOException {
        
        allData.removeIf(r -> ((Room) r).getRoomType() != roomType);

        return allData;
    }

    public ArrayList getRoomsByBedType(BedType bedType) throws IOException {
        ArrayList allData = getAllRooms();
        
        allData.removeIf(r -> ((Room)r).getBedType() != bedType);
        
        return allData;
    }

    public static ArrayList getRoomsByBedType(ArrayList allData, BedType bedType) throws IOException {
        
        allData.removeIf(r -> ((Room)r).getBedType() != bedType);
        
        return allData;
    }




    public static void printVacantRoom() throws IOException {

        
        int singleRoomCount = getRoomsByRoomType(RoomType.SINGLE).size();
        int doubleRoomCount = getRoomsByRoomType(RoomType.DOUBLE).size();
        int deluxeRoomCount = getRoomsByRoomType(RoomType.DELUXE).size();
        int vipRoomCount = getRoomsByRoomType(RoomType.VIP_SUITE).size();


        ArrayList singleRoom = getRoomsByRoomType(getVacantRooms(), RoomType.SINGLE);
        ArrayList doubleRoom = getRoomsByRoomType(getVacantRooms(), RoomType.DOUBLE);
        ArrayList deluxeRoom = getRoomsByRoomType(getVacantRooms(), RoomType.DELUXE);
        ArrayList vipRoom = getRoomsByRoomType(getVacantRooms(), RoomType.VIP_SUITE);

        System.out.println("Single Room: " + singleRoom.size()+ " out of " + singleRoomCount);
        System.out.print("\t\t\tRooms: ");
        for (int i=0; i<singleRoom.size(); i++) {
            System.out.print(((Room) singleRoom.get(i)).getRoomId() + ", ");
        } 
        System.out.println();

        System.out.println("Double Room: " + doubleRoom.size()+ " out of " + doubleRoomCount);
        System.out.print("\t\t\tRooms: ");
        for (int i=0; i<doubleRoom.size(); i++) {
            System.out.print(((Room) doubleRoom.get(i)).getRoomId() + ", ");
        } 
        System.out.println();

        System.out.println("Deluxe Room: " + deluxeRoom.size()+ " out of " + deluxeRoomCount);
        System.out.print("\t\t\tRooms: ");
        for (int i=0; i<deluxeRoom.size(); i++) {
            System.out.print(((Room) deluxeRoom.get(i)).getRoomId() + ", ");
        } 
        System.out.println();

        System.out.println("VIP Suite: " + vipRoom.size()+ " out of " + vipRoomCount);
        System.out.print("\t\t\tRooms: ");
        for (int i=0; i<vipRoom.size(); i++) {
            System.out.print(((Room) vipRoom.get(0)).getRoomId() + ", ");
        } 
        System.out.println();
    }


    public static void printAllRooms() throws IOException {
        ArrayList allData = getAllRooms();
        System.out.println("\n====================================");
        System.out.println("ALL ROOMS");
        System.out.println("====================================");
        System.out.printf("%-8s %-13s %-18s %-11s %-19s %-15s %-12s %-13s %-13s %-10s", "RoomID", "Room Type", "Bed Type", "With View", 
                            "Room Status", "Room Rate(S$)", "Room Floor","Room Number","Wifi Status","Smoking Status");
        System.out.println();
        for (int i=0; i<allData.size(); i++) {
            Room r = (Room) allData.get(i);
            String wifi;
            if (r.getWifiEnabled()) wifi = "Enabled";
            else wifi = "Disabled";

            String smoke;
            if (r.getSmokingStatus()) smoke = "Allowed";
            else smoke = "Not Allowed";


            System.out.printf("%-8s %-13s %-18s %-11s %-19s %-15s %-12s %-13s %-13s %-10s", 
                r.getRoomId(), r.getRoomType(), r.getBedType(), r.getWithView(), r.getRoomStatus(), 
                r.getRoomRate(),r.getRoomFloor(),r.getRoomNumber(), wifi, smoke);
            System.out.println("");
        }
    }

    public static void printRooms(ArrayList allData) throws IOException {
        // ArrayList allData = getAllRooms();
        System.out.printf("%-8s %-13s %-18s %-11s %-19s %-15s %-12s %-13s %-13s %-10s", "RoomID", "Room Type", "Bed Type", "With View", 
                            "Room Status", "Room Rate(S$)", "Room Floor","Room Number","Wifi Status","Smoking Status");
        System.out.println();
        for (int i=0; i<allData.size(); i++) {
            Room r = (Room) allData.get(i);
            String wifi;
            if (r.getWifiEnabled()) wifi = "Enabled";
            else wifi = "Disabled";

            String smoke;
            if (r.getSmokingStatus()) smoke = "Allowed";
            else smoke = "Not Allowed";


            System.out.printf("%-8s %-13s %-18s %-11s %-19s %-15s %-12s %-13s %-13s %-10s", 
                r.getRoomId(), r.getRoomType(), r.getBedType(), r.getWithView(), r.getRoomStatus(), 
                r.getRoomRate(),r.getRoomFloor(),r.getRoomNumber(), wifi, smoke);
            System.out.println("");
        }
    }
}
