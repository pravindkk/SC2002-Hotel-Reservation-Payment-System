package com.hotel.UI;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import com.hotel.system.Room;
import com.hotel.system.enums.*;
import com.hotel.controller.ReservationController;
import com.hotel.controller.RoomController;
import com.hotel.controller.UpdateRoomMenuDisplayUI;

/**
 * Represents the class of Payment, which holds the information at the instance the payment is made
 * @author Vignesh Ezhil
 * @version 1.0
 * @since 1.0
 */

public class RoomUI {
    static Scanner sc = new Scanner(System.in);
    RoomController roomController = new RoomController();
    
    /** 
     * This method calls the relevant method in another class to create a room
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
     * This method calls the relevant method in another class to update a room
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
     * This method calls the relevant method in another class to delete a room
     */
    public void deleteRoom() {
        try {
            String roomId = UpdateRoomMenuDisplayUI.updateRoomId();
            if (ReservationController.getReservationByRoom(roomId, ReservationStatus.CONFIRMED) != null){
                System.out.println("CANNOT DELETE!! There is a reservation booked in the room!!!");
                return;
            }
            roomController.deleteRoom(roomId);
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    /** 
     * This method calls the relevant method in another class to print a room
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
     * This method calls the relevant method in another class to print all rooms
     */
    public void printAllRooms() {
        try {
            roomController.printAllRooms();
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    /** 
     * This method calls the relevant method in another class 
     * to see how many rooms are occpied
     */
    public void viewOccupancyRate() {
        try {
            roomController.printVacantRoom();
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

    /** 
     * This method calls the relevant method in another class to view a room's details
     */
    public void viewRoomStatus() {
        try {
            roomController.printRoomStatus();
        } catch (Exception e) {
            //TODO: handle exception
        }
    }

}