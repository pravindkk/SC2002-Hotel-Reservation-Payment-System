package com.hotel.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import com.hotel.db.MenuDB;
import com.hotel.system.Item;
import com.hotel.system.enums.FoodType;

import org.apache.commons.lang3.ArrayUtils;

public class MenuController {
    static Scanner sc = new Scanner(System.in);
    static SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    static MenuDB allMenus = new MenuDB();


    public static ArrayList<Item> getAllItems() throws IOException {
        ArrayList<Item> allData = allMenus.read(allMenus.getPath());
        return allData;
    }

    public static void printAllItems() throws IOException {
        ArrayList<Item> items = getAllItems();

        for (int i=0; i<items.size(); i++) {
            Item curr = (Item) items.get(i);
            if (curr.getType().equals(FoodType.STARTER)) {
                System.out.println(
                        curr.getItemId() +
                        curr.getName() +
                        curr.getPrice() + 
                        curr.getDescription() + 
                        curr.getType()
                );
            }

        }
        
    }

    public static void saveAllItems(ArrayList toWrite){
        try {
            allMenus.save(allMenus.getPath(), toWrite);
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println("Item not added!");
            System.out.println(e);
        }
    }



    public static void createItem(Item item) throws IOException {
        ArrayList<Item> allData = getAllItems();
        allData.add(item);
        saveAllItems(allData);
    }

    public static Item getItem(Integer itemId) throws IOException {
        ArrayList<Item> allData = getAllItems();

        for (int i=0; i<allData.size(); i++) {
            // if ()
            Item item = (Item) allData.get(i);
            if (itemId.equals(item.getItemId())) {
                return item;
            }
        }
        return null;
    }

    public static void deleteItem(Integer itemId) throws IOException {
        ArrayList<Item> allData = getAllItems();

        for (int i=0; i<allData.size(); i++) {
            // if ()
            Item item = (Item) allData.get(i);
            if (itemId.equals(item.getItemId())) {
                // return item;

                // ArrayList.remove(allData, i);
                allData.remove(i);
                saveAllItems(allData);
                return;
            }
        }
        System.out.println("cannot delete item");
    }

    public static void updateItem(Item newItem) throws IOException {
        ArrayList<Item> allData = getAllItems();

        for (int i=0; i<allData.size(); i++) {
            // if ()
            Item item = (Item) allData.get(i);

            
            if (newItem.getItemId() == item.getItemId()) {
                allData.set(i, newItem);
                saveAllItems(allData);
                return;
            }
        }
        System.out.println("cannot delete item");
    }
}