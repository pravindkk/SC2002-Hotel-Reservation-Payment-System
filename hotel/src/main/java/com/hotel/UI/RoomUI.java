package com.hotel.UI;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import com.hotel.system.Room;
import com.hotel.system.enums.*;
import com.hotel.controller.RoomController;

public class RoomUI {

    RoomController menu = new RoomController();
    static Scanner sc = new Scanner(System.in);

    public void displayOptions() throws IOException {
    	int choice;
    	do {
            RoomController.printAllRooms();
            System.out.println("\n==================================================");
			System.out.println(" Menu Room Management: ");
			System.out.println("==================================================");
			System.out.println("(1) Create Room Record\t(2) Update Room Record");
			System.out.println("(3) Remove Room Record\t(4) Back");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                	createNewRoom();
                    break;
                case 2:
                	// updateIn();
                    updateRoomRecord();
                    break;
                case 3:
                	// removeIn();
                    deleteRoomRecord();
                    break;
                case 4:
                    return;
                default:
                	System.out.println("Please enter a valid option!");
					choice = 0;
            }
        } while (choice < 4);
    }

    public static void createNewRoom(){

        RoomType roomType = getRoomType();
        RoomStatus roomStatus = getRoomStatus();
        BedType bedType = getBedType();
        boolean withView = getWithView();
        float roomRate = getRoomRate();
        boolean wifiEnabled = getWifiEnabled();
        boolean smokingStatus = getSmokingStatus();




    }

    public static void updateRoomRecord() throws IOException{
        String roomId = null;
        do{
            System.out.println("Enter the room ID of the room that you want to add");
            try {
                roomId = sc.next();
            } catch (Exception e) {
                //TODO: handle exception
                System.out.println("Wrong id input");
            }
            try {
                if(RoomController.getSpecificRoom(roomId)!=null)break;
                else System.out.println("Room doesnt exist");
            } catch (Exception e) {
                //TODO: handle exception
                e.printStackTrace();
            }
        }while(true);

        Room newRoom = RoomController.getSpecificRoom(roomId);
        int choice =0;
        System.out.print("(1) Room Type\n"+
        "(2) Room status\n"+
        "(3) Bed Type\n"+
        "(4) With view\n"+
        "(5) Room rate\n"+
        "(6) Wifi Enabled\n"+
        "(7) Smoking Status\n"+
        "What do you want to change? :");

        do {
            try {
                choice = sc.nextInt();
            } catch (Exception e) {
                //TODO: handle exception
            }
        } while (choice<1 || choice >7);

        switch (choice) {
            case 1:
                newRoom.setRoomType(getRoomType());
                break;
            case 2:
                newRoom.setRoomStatus(getRoomStatus());
                break;
            case 3:
                newRoom.setBedType(getBedType());
                break;
            case 4:
                newRoom.setWithView(getWithView());
                break;
            case 5:
                newRoom.setRoomRate(getRoomRate());
                break;
            case 6:
                newRoom.setWifiEnabled(getWifiEnabled());
                break;
            case 7:
                newRoom.setSmokingStatus(getSmokingStatus());
        
            default:
                break;
        }

        RoomController.saveSpecificRoomByRoomId(newRoom);

    }

    public static void deleteRoomRecord() throws IOException {
        String roomId=null;
        do {
            System.out.println("Enter the id of the item to add:  ");
            try {
                roomId = sc.next();
            } catch (Exception e) {
                //TODO: handle exception
                System.out.println("Wrong id input!");
            }
            try {
                if (RoomController.getSpecificRoom(roomId) != null) break;
                else {
                    System.out.println("Room doesnt exist");
                    return;
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } while (true);
        // Item newItem = MenuController.getItem(itemdId);

        Room newRoom = RoomController.getSpecificRoom(roomId);
        newRoom.setRoomStatus(RoomStatus.VACANT);
        RoomController.saveSpecificRoomByRoomId(newRoom);
    }








}
