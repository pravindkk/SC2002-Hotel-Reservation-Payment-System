package com.hotel.UI;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import com.hotel.system.Room;
import com.hotel.system.enums.*;
import com.hotel.controller.RoomController;
import com.hotel.controller.UpdateRoomMenuDisplayUI;

/**
 * Represents the class of RoomUI, which prints the UI for Room-related operations
 * @author Vignesh Ezhil
 * @version 1.0
 * @since 1.0
 */

public class RoomUI {
    static Scanner sc = new Scanner(System.in);
    RoomController roomController = new RoomController();

    /**
     * This constructor runs the relevant operations based on the user's input
     */
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

    
    /** 
     * This method prints the options available for the user to choose, 
     * and gets the input to return to the constructor
     * @return int
     */
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

    /**
     * This method calls another method to create a room
     */
    public void createRoom() {
        try {
            roomController.createRoom();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * This method calls another method to update a room
     */
    public void updateRoom() {
        try {
            String roomId = UpdateRoomMenuDisplayUI.updateRoomId();
            roomController.updateRoom(roomId);
        } catch (Exception e) {
            //TODO: handle exception
        }
        
    }

    /**
     * This method calls another method to update a room's ID
     */
    public void deleteRoom() {
        try {
            String roomId = UpdateRoomMenuDisplayUI.updateRoomId();
            roomController.deleteRoom(roomId);
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    /**
     * This method calls another method to print all details of a specified room
     */
    public void printRoom() {
        try {
            String roomId = UpdateRoomMenuDisplayUI.updateRoomId();
            roomController.printOneRoom(roomId);
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    /** 
     * This method calls another method to print all details of all rooms
     */
    public void printAllRooms() {
        try {
            roomController.printAllRooms();
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    /** 
     * This method calls another method to print all vacant rooms
     */
    public void viewOccupancyRate() {
        try {
            roomController.printVacantRoom();
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}