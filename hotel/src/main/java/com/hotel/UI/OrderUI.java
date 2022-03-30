package com.hotel.UI;

import java.util.ArrayList;
import java.util.Scanner;

import com.hotel.system.Item;

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
        int choice;
        ArrayList<Item> items = new ArrayList<Item>();

        do {
            // print all the items
            System.out.println("What item do want (1-XX) (enter -1 to exit)?: ");
            try {
                choice = sc.nextInt();
                // items.add(new Item(itemid, name, description, price, foodType))
                if (choice == -1) break;
            } catch (Exception e) {
                //TODO: handle exception
                System.out.println("Wrong value entered!! Please enter a value (1-4)!!");
            }
        } while (true);

    }




}
