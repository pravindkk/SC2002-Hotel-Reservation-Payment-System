package com.hotel.UI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.hotel.controller.MenuController;
import com.hotel.controller.OrderController;
import com.hotel.system.Item;
import com.hotel.system.Order;

public class OrderUI {
    static Scanner sc = new Scanner(System.in);

    public OrderUI () {
        int choice  = displayOptions();

        switch (choice) {
            case 1:
                createNewOrder();
                break;
        
            default:
                break;
        }

    }


    public int displayOptions() {
        int choice;
        do {
            System.out.println("========================\n" + 
                               "Room Service Orders\n"+
                               "========================\n"+
                               "(1) Create Order\n"+
                               "(2) Update Order\n"+
                               "(3) Remove Order\n"+
                               "(4) View Order\n"+
                               "========================\n"
            );

            System.out.print("What is your choice (1-4)?: ");
            try {
                choice = sc.nextInt();
                if (choice >0 && choice <5) break;
            } catch (Exception e) {
                //TODO: handle exception
                System.out.println("Enter a number between (1-4)!!");
            }
        } while (true);

        return choice;
    }

    public void createNewOrder() {
        // get all the items
        Integer choice;
        ArrayList<Item> items = new ArrayList<Item>();

        do {
            // print all the items
            try {
                MenuController.printAllItems();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            System.out.println("What item do want (enter the item id) (enter -1 to exit)?: ");
            try {
                choice = sc.nextInt();
                if (choice == -1) break;
                Item item = MenuController.getItem(choice);
                items.add(item);
                // items.add(new Item(itemid, name, description, price, foodType))
                
            } catch (Exception e) {
                //TODO: handle exception
                System.out.println("Wrong value entered!! Please enter a value (enter the item id)!!");
            }
        } while (true);

        // new Order(orderId, roomId, reservationNum, items, date, orderStatus, remarks)
        // OrderController.saveOrders(toWrite);
    }




}
