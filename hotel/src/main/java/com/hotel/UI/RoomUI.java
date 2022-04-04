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

    public static RoomType getRoomType() {
        RoomType roomType=null;
        do {
            System.out.println("(1) Single\n"+
                                "(2) Double\n"+
                                "(3) Deluxe\n"+
                                "(4) VIP SUITE");
            System.out.println("Enter the RoomType of the room to add:  ");
            int choice=0;
            try {
                    choice = sc.nextInt();

            } catch (Exception e) {
                //TODO: handle exception
                System.out.println("Wrong input!");
            }
            if (choice >0 && choice < 5) {
                switch (choice) {
                    case 1:
                        roomType = RoomType.SINGLE;
                        break;
                    case 2:
                        roomType = RoomType.DOUBLE;
                        break;
                    case 3:
                        roomType = RoomType.DELUXE;
                        break;
                    case 4:
                        roomType = RoomType.VIP_SUITE;
                        break;
                    default:
                        break;
                }
            }
            else System.out.println("wrong choice");


        } while (roomType == null);

        return roomType;
    }


    public static RoomStatus getRoomStatus() {
        RoomStatus roomStatus=null;
        do {
            System.out.println("(1) Occupied\n"+
                                "(2) Reserved\n"+
                                "(3) Under Maintance\n"+
                                "(4) Vacant");
            System.out.println("Enter the RoomStatus of the room to add:  ");
            int choice=0;
            try {
                    choice = sc.nextInt();

            } catch (Exception e) {
                //TODO: handle exception
                System.out.println("Wrong input!");
            }
            if (choice >0 && choice < 5) {
                switch (choice) {
                    case 1:
                        roomStatus = RoomStatus.OCCUPIED;
                        break;
                    case 2:
                        roomStatus = RoomStatus.RESERVED;
                        break;
                    case 3:
                        roomStatus = RoomStatus.UNDER_MAINTAINANCE;
                        break;
                    case 4:
                        roomStatus = RoomStatus.VACANT;
                        break;
                    default:
                        break;
                }
            }
            else System.out.println("wrong choice");


        } while (roomStatus == null);

        return roomStatus;
    }


    public static BedType getBedType() {
        BedType bedType=null;
        do {
            System.out.println("(1) Single\n"+
                                "(2) Super - Single\n"+
                                "(3) Double\n"+
                                "(4) Queen\n"+
                                "(5) King");
            System.out.println("Enter the BedType of the room to add:  ");
            int choice=0;
            try {
                    choice = sc.nextInt();

            } catch (Exception e) {
                //TODO: handle exception
                System.out.println("Wrong input!");
            }
            if (choice >0 && choice < 6) {
                switch (choice) {
                    case 1:
                        bedType = BedType.SINGLE;
                        break;
                    case 2:
                        bedType = BedType.SUPER_SINGLE;
                        break;
                    case 3:
                        bedType = BedType.DOUBLE;
                        break;
                    case 4:
                        bedType = BedType.QUEEN;
                        break;
                    case 5:
                        bedType = BedType.KING;
                        break;
                    default:
                        break;
                }
            }
            else System.out.println("wrong choice");


        } while (bedType == null);

        return bedType;
    }

    public static boolean getWithView() {
        Boolean withView = null;
        do {
            System.out.println("(1) Yes\n"+
                                "(2) No");
            System.out.println("Does this room have a view:  ");
            int choice=0;
            try {
                    choice = sc.nextInt();

            } catch (Exception e) {
                //TODO: handle exception
                System.out.println("Wrong input!");
            }
            if (choice >0 && choice < 3) {
                switch (choice) {
                    case 1:
                        withView = true;
                        break;
                    case 2:
                        withView = false;
                        break;
                    default:
                        break;
                }
            }
            else System.out.println("wrong choice");


        } while (withView == null);

        return withView;
    }

    public static boolean getWifiEnabled() {
        Boolean wifiEnabled = null;
        do {
            System.out.println("(1) Yes\n"+
                                "(2) No");
            System.out.println("Does this room have a wifi:  ");
            int choice=0;
            try {
                    choice = sc.nextInt();

            } catch (Exception e) {
                //TODO: handle exception
                System.out.println("Wrong input!");
            }
            if (choice >0 && choice < 3) {
                switch (choice) {
                    case 1:
                        wifiEnabled = true;
                        break;
                    case 2:
                        wifiEnabled = false;
                        break;
                    default:
                        break;
                }
            }
            else System.out.println("wrong choice");


        } while (wifiEnabled == null);

        return wifiEnabled;
    }


    public static boolean getSmokingStatus() {
        Boolean smokingStatus = null;
        do {
            System.out.println("(1) Yes\n"+
                                "(2) No");
            System.out.println("Does this room have allow smoking:  ");
            int choice=0;
            try {
                    choice = sc.nextInt();

            } catch (Exception e) {
                //TODO: handle exception
                System.out.println("Wrong input!");
            }
            if (choice >0 && choice < 3) {
                switch (choice) {
                    case 1:
                        smokingStatus = true;
                        break;
                    case 2:
                        smokingStatus = false;
                        break;
                    default:
                        break;
                }
            }
            else System.out.println("wrong choice");


        } while (smokingStatus == null);

        return smokingStatus;
    }

    public static float getRoomRate() {
        float price=0.0f;
        do {
            System.out.println("Enter the price of the Room (e.g 26.5):  ");
            try {
                price = sc.nextFloat();
                if (price != 0.0) break;
            } catch (Exception e) {
                //TODO: handle exception
                System.out.println("Wrong price input!");
            }
        } while (true);
        return price;
    }






    








}
