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
import com.hotel.controller.UpdateRoomController;

public class RoomController {
    // private RoomDB allRooms;
    static RoomDB allRooms = new RoomDB();
    Scanner sc = new Scanner(System.in);
    // ArrayList hello = test.getAllRooms();
    // public static void main(String[] args) throws IOException {
    //     RoomController test = new RoomController();
    //     ArrayList hello = test.getAllRooms();
    //     // test.updateRoom("02-04");
    //     // test.getVacantRooms();
    //     test.printAllRooms();
        
        
    // }

    public void createRoom() throws IOException {

        String roomId = UpdateRoomController.updateRoomId();
        String[] parts = roomId.split("-");
        String roomFloor = parts[0];
        Integer roomNumber = Integer.valueOf(parts[1]);

        RoomType roomType = UpdateRoomController.updateRoomType();
        BedType bedType = UpdateRoomController.updateBedType();
        boolean withView = UpdateRoomController.updateWithView();
        RoomStatus roomStatus = UpdateRoomController.updateRoomStatus();
        Float roomRate = UpdateRoomController.updateRoomRate();
        boolean wifiEnabled = UpdateRoomController.updateWifiEnabled();
        boolean smokingStatus = UpdateRoomController.updateSmokingStatus();


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

    public void saveData(ArrayList toWrite) {
        
        try {
            allRooms.save(allRooms.getPath(), toWrite);
            System.out.println("Room successfully added!");
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println("Room not added!");

        }
    }

    public void updateRoomStatus(String roomId) throws IOException {
        ArrayList allData = getAllRooms();
        ArrayList returned = getSpecificRoom(roomId);
        if (returned == null) {
            System.out.println("Invalid string");
            return;
        }
        Room room = (Room) returned.get(1);

        room.setRoomType(UpdateRoomController.updateRoomType());
        allData.set((Integer) returned.get(0), room);

        saveData(allData);


        // return room;
    }



    public void updateRoom(String roomId) throws IOException {
        ArrayList allData = getAllRooms();
        ArrayList roomList = getSpecificRoom(roomId);
        // // Room room = (Room)
        if (roomList == null) {
            System.out.println("Invalid Room");
            return;
        }
        Integer index = (Integer) roomList.get(0);
        Room room = (Room) roomList.get(1);

        System.out.println("\nPlease choose guest details to update \n" + 
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
                room.setRoomType(UpdateRoomController.updateRoomType());
                break;
            case 2:
                room.setBedType(UpdateRoomController.updateBedType());
                break;
            case 3:
                room.setWithView(UpdateRoomController.updateWithView());
                break;
            case 4:
                room.setRoomStatus(UpdateRoomController.updateRoomStatus());
                break;
            case 5:
                room.setRoomRate(UpdateRoomController.updateRoomRate());
                break;
            case 6:
                room.setWifiEnabled(UpdateRoomController.updateWifiEnabled());
                break;
            case 7:
                room.setSmokingStatus(UpdateRoomController.updateSmokingStatus());
                break;
            case 8:
                room.setRoomType(UpdateRoomController.updateRoomType());
                room.setBedType(UpdateRoomController.updateBedType());
                room.setWithView(UpdateRoomController.updateWithView());
                room.setRoomStatus(UpdateRoomController.updateRoomStatus());
                room.setRoomRate(UpdateRoomController.updateRoomRate());
                room.setWifiEnabled(UpdateRoomController.updateWifiEnabled());
                room.setSmokingStatus(UpdateRoomController.updateSmokingStatus());
                break;
            
            default:
                break;
        }

        allData.set(index, room);

        saveData(allData);


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
            // System.out.print(r.getRoomId());
            if (roomId.equals(r.getRoomId())) {
                // System.out.print("hello");
                toReturn.add(i);
                toReturn.add(r);
                return toReturn;
            }
        }

        return null;
    }


    public ArrayList getVacantRooms() throws IOException {
        ArrayList allData = getAllRooms();
        
        allData.removeIf(r -> ((Room)r).getRoomStatus() != RoomStatus.VACANT);
        
        // System.out.println(allData.size());
        // for (int i=0; i<allData.size(); i++) {
        //     System.out.print(((Room)allData.get(i)).getRoomFloor());
        // }
        return allData;
    }

    public ArrayList getRoomsByRoomType(RoomType roomType) throws IOException {
        ArrayList allData = getAllRooms();
        
        allData.removeIf(r -> ((Room)r).getRoomType() != roomType);

        return allData;
    }

    public ArrayList getRoomsByRoomType(ArrayList allData, RoomType roomType) throws IOException {
        // ArrayList allData = getAllRooms();
        
        allData.removeIf(r -> ((Room) r).getRoomType() != roomType);

        return allData;
    }

    public ArrayList getRoomsByBedType(BedType bedType) throws IOException {
        ArrayList allData = getAllRooms();
        
        allData.removeIf(r -> ((Room)r).getBedType() != bedType);
        
        return allData;
    }




    public void printVacantRoom() throws IOException {
        // ArrayList vacantRooms = getVacantRooms();
        // System.out.println(((Room) vacantRooms.get(0)).getRoomId());
        
        int singleRoomCount = getRoomsByRoomType(RoomType.SINGLE).size();
        int doubleRoomCount = getRoomsByRoomType(RoomType.DOUBLE).size();
        int deluxeRoomCount = getRoomsByRoomType(RoomType.DELUXE).size();
        int vipRoomCount = getRoomsByRoomType(RoomType.VIP_SUITE).size();


        ArrayList singleRoom = getRoomsByRoomType(getVacantRooms(), RoomType.SINGLE);
        ArrayList doubleRoom = getRoomsByRoomType(getVacantRooms(), RoomType.DOUBLE);
        ArrayList deluxeRoom = getRoomsByRoomType(getVacantRooms(), RoomType.DELUXE);
        ArrayList vipRoom = getRoomsByRoomType(getVacantRooms(), RoomType.VIP_SUITE);
        // System.out.println(((Room) vacantRooms.get(0)).getRoomId());

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


    public void printAllRooms() throws IOException {
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
}
