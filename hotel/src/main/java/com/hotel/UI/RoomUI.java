package com.hotel.UI;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import com.hotel.system.Room;
import com.hotel.system.enums.*;
import com.hotel.controller.RoomController;
import com.hotel.controller.UpdateRoomMenuDisplayUI;

public class RoomUI {
    static Scanner sc = new Scanner(System.in);
    RoomController roomController = new RoomController();


    public RoomUI () {
        int choice =0;
        do {
            choice  = displayOptions();
            switch (choice) {
                case 1:
                    // createRes();
                    createRoom();
                    break;
                case 2:
                    updateRoom();
                    break;
                
                case 3:
                    deleteRoom();
                    break;
                case 4:
                    printRoom();
                    break;

                case 5:
                    viewOccupancyRate();
                    break;
                
                case 6:
                    printAllRooms();
                    break;
                
                case 7:
                    return;
            
                default:
                    System.out.println("Invalid input");
                    break;
            }
        } while (true);
        

        

    }

    public int displayOptions() {
        int choice;
        do {
            System.out.println("========================\n" + 
                               "Rooms\n"+
                               "========================\n"+
                               "(1) Create Rooms\n"+
                               "(2) Update Rooms\n"+
                               "(3) Remove Rooms\n"+
                               "(4) Print Room\n"+
                               "(5) View Occupancy Rate\n"+
                               "(6) View All Rooms\n"+
                               "(7) Back\n"+
                               "========================\n"
            );

            System.out.print("What is your choice (1-6)?: ");
            try {
                choice = sc.nextInt();
                if (choice >0 && choice <7) break;
                else System.out.println("Enter a number between (1-6)!!");
            } catch (Exception e) {
                //TODO: handle exception
                
            }
        } while (true);

        return choice;
    }

    public void createRoom() {
        try {
            roomController.createRoom();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void updateRoom() {
        try {
            String roomId = UpdateRoomMenuDisplayUI.updateRoomId();
            roomController.updateRoom(roomId);
        } catch (Exception e) {
            //TODO: handle exception
        }
        
    }

    public void deleteRoom() {
        try {
            String roomId = UpdateRoomMenuDisplayUI.updateRoomId();
            roomController.deleteRoom(roomId);
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    public void printRoom() {
        try {
            String roomId = UpdateRoomMenuDisplayUI.updateRoomId();
            roomController.printOneRoom(roomId);
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
    public void printAllRooms() {
        try {
            roomController.printAllRooms();
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    public void viewOccupancyRate() {
        try {
            roomController.printVacantRoom();
        } catch (Exception e) {
            //TODO: handle exception
        }
    }





}