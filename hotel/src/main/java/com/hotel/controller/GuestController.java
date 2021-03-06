package com.hotel.controller;

import java.io.IOException;
import java.text.ParseException;
import com.hotel.system.enums.*;
import java.util.Scanner;
import com.hotel.db.GuestDB;
import com.hotel.system.CreditCard;
import com.hotel.system.Guest;
import com.hotel.db.ReadInFile;
import java.util.ArrayList;
import com.hotel.controller.UpdateGuestMenu;
import org.apache.commons.lang3.StringUtils;

/**
 * Represents the controller function of Guest
 * @author Pravind
 * @version 1.0
 * @since 1.0
 */


public class GuestController {

    Scanner sc = new Scanner(System.in);
    static GuestDB allGuests = new GuestDB();

    
    /** 
     * Creates and returns a Guest object
     * @return returns an Object of the class Guest
     * @throws IOException Due to communication with the DataBase IOexception is required
     */
    public static Guest CreateGuest() throws IOException{

        String name = UpdateGuestMenu.UpdateName();
        String guestId = UpdateGuestMenu.CreateGuestId();
        Gender gender = UpdateGuestMenu.UpdateGender();
        String nationality = UpdateGuestMenu.UpdateNationality();
        String country = UpdateGuestMenu.UpdateCountry();
        Integer phoneNumber=UpdateGuestMenu.UpdatePhoneNumber();
        CreditCard c = CreditCardController.createCreditCard(guestId, name);

        Guest guest = new Guest(guestId, name, gender, nationality, country, phoneNumber, c.getCardNo());
        
        // System.out.println(guest.getGuestId());
        // System.out.println(guest.getName());
        // System.out.println(guest.getNationality());
        // System.out.println(guest.getCountry());
        // System.out.println(guest.getPhoneNumber());
        // System.out.println(guest.getCreditCardNumber());
        ArrayList allData = getAllGuests();
        allData.add(guest);
        saveGuests(allData);
        return guest;
    }

    
    /** 
     * Saves all Guest objects
     * @param toWrite Contains an ArrayList of all the Guests that is going to be stored in the database
     */
    public static void saveGuests(ArrayList toWrite) {
        try {
            allGuests.save(allGuests.getPath(), toWrite);
            System.out.println("Guests successfully updated!");
        } catch (Exception e) {
            //TODO: handle exception

            System.out.println("Guest not added!");
            System.out.println(e);

        }
    }

    
    /** 
     * Saves a Guest object
     * @param guest Contains an object of the class Guest that is going to be stored in the database
     * @throws IOException Due to communication with the DataBase IOexception is required
     */
    public static void saveGuestByID(Guest guest) throws IOException {
        ArrayList allData = getAllGuests();

        for (int i=0; i<allData.size(); i++) {
            Guest g = (Guest) allData.get(i);
            if (guest.getGuestId().equals(g.getGuestId())) {
                allData.set(i, guest);
                saveGuests(allData);
                return;
            }
        }
        System.out.println("Not updated guest");
    }

    
    /** 
     * Updates a Guest object's details
     * @param guestId String input of GuestID is entered so that the corresponding Guest object is returned
     * @throws IOException due to communication with the DataBase IOexception is required
     */
    public void UpdateGuest(String guestId) throws IOException{

        // ArrayList allGuests = getAllGuests();
        Guest guest = RetrieveGuest(guestId);

        if(guest==null){
            System.out.println("Invalid guestID");
            return;
        }

        // Integer index = (Integer) guestList.get(0);
        // Guest guest = (Guest) guestList.get(1);

        System.out.println("\nPlease choose guest details to update \n" + 
        "(1) Name \n"+
        "(2) Gender \n"+ 
        "(3) Nationality \n"+
        "(4) Country \n"+
        "(5) Phone Number \n"+
        "(6) Credit Card \n"+
        "(7) All details");

        int choice =0;

        try {
            choice = sc.nextInt();
        } catch (Exception e) {
            //TODO: handle exception
        }

        if (choice < 1 || choice > 7) {
            System.out.println("Invalid input");
            return;
        }
        CreditCard c=null;

        switch(choice){

            case 1:
                guest.setName(UpdateGuestMenu.UpdateName());
                break;

            case 2:
                guest.setGender(UpdateGuestMenu.UpdateGender());
                break;

            case 3:
                guest.setNationality(UpdateGuestMenu.UpdateNationality());
                break;

            case 4:
                guest.setCountry(UpdateGuestMenu.UpdateCountry());
                break;

            case 5:
                guest.setPhoneNumber(UpdateGuestMenu.UpdatePhoneNumber());
                break;

            case 6:
                c = CreditCardController.updateCreditCard(guestId);
                if (c == null) {
                    System.out.println("Cannot find credit card!!");
                    return;
                }
                guest.setCreditCardNumber(c.getCardNo());
                break;
            case 7:
                guest.setName(UpdateGuestMenu.UpdateName());
                guest.setGender(UpdateGuestMenu.UpdateGender());
                guest.setNationality(UpdateGuestMenu.UpdateNationality());
                guest.setCountry(UpdateGuestMenu.UpdateCountry());
                guest.setPhoneNumber(UpdateGuestMenu.UpdatePhoneNumber());
                c = CreditCardController.updateCreditCard(guestId);
                if (c == null) {
                    System.out.println("Cannot find credit card!!");
                    return;
                }
                guest.setCreditCardNumber(c.getCardNo());
                break;
        }


        // allGuests.set(index, guest);
        saveGuestByID(guest);

        // saveData(allGuests);
    }

    
    /** 
     * Deletes a Guest object
     * @param guestId String input of GuestID is entered so that the corresponding guest object is deleted
     * @throws IOException Due to communication with the DataBase IOexception is required
     */
    public void DeleteGuest(String guestId) throws IOException{
        ArrayList allData = getAllGuests();
        // Guest guestToDelete = RetrieveGuest(guestId);
        // if(guestToDelete==null){
        //     System.out.println("Invalid guestID");
        //     return;
        // }
        
        for (int i=0; i<allData.size(); i++) {
            Guest test = (Guest) allData.get(i);
            if (test.getGuestId().equals(guestId)) {
                // System.out.println("hello");
                allData.remove(i);
                CreditCardController.deleteCreditCard(guestId);
                saveGuests(allData);
                return;
            }
        }
        System.out.println("cannot find the guest");
        // allData.remove(guestToDelete);
        
        // if (RetrieveGuest(guestToDelete.getGuestId()) == null) System.out.println("Hello");
        

        // allData.removeIf(g -> ((Guest)g).getGuestId() == guestId);
        // System.out.println("Guest Details Successfully removed.");
        // saveGuests(allData);


    }

    
    /** 
     * This method returns an ArrayList of all the Guests stored in the database
     * @return Returns an ArrayList of all the Guests stored in the database
     * @throws IOException Due to communication with the DataBase IOexception is required
     */
    public static ArrayList getAllGuests() throws IOException {
        ArrayList<Guest> allData = allGuests.read(allGuests.getPath());
        return allData;
    }

    
    /** 
     * Prints out all the guest details in the database
     * @throws IOException due to communication with the DataBase IOexception is required
     */
    public void PrintAllGuestDetails() throws IOException{
        // print out guestDetails
        ArrayList allGuests = getAllGuests();
        System.out.println("\n====================================");
        System.out.println("ALL Guests");
        System.out.println("====================================");
        System.out.printf("%-8s %-9s %-10s %-12s %-10s %-13s %-15s", "GuestID", "Name", "Gender", "Nationality", 
        "Country", "Phone Number", "Credit Card Number");
        System.out.println();

        for(int i=0;i<allGuests.size();i++){
            Guest g = (Guest) allGuests.get(i);
            System.out.printf("%-8s %-9s %-10s %-12s %-10s %-13s %-15s\n", g.getGuestId(), g.getName(), g.getGender(), g.getNationality(), 
            g.getCountry(), g.getPhoneNumber(), g.getCreditCardNumber());
        }
        System.out.println("");

    }

    

    // public Guest RetrieveGuest(Guest g) throws IOException{
    //     // retrieve guest by guestID
    //     ArrayList allData = getAllGuests();
    //     // return guest object using search 

    //     for(int i =0 ;i< allData.size();i++){
    //         Guest guestSearch = (Guest) allData.get(i);
    //         if(guestSearch.getGuestId()==g.getGuestId()){
    //             return guestSearch;
    //         }
    //     }
    //     return null;

    // }

    /**
     * This method retrieves a guest object given a guestID
     * @param GuestID String input of GuestID is entered so that the corresponding guest object is retrieved
     * @return Returns the corresponding Guest object from the database
     * @throws IOException @throws IOException ue to communication with the DataBase IOexception is required
     */

    public static Guest RetrieveGuest(String GuestID) throws IOException{
        ArrayList allData = getAllGuests();
        // ArrayList toReturn = new ArrayList();

        for(int i =0 ;i< allData.size();i++){
            Guest guestSearch = (Guest) allData.get(i);
            if(GuestID.equals(guestSearch.getGuestId())){
                return guestSearch;
            }
        }
        return null;



    }

    // public static void saveData(ArrayList toWrite) {
        
    //     try {
    //         allGuests.save(allGuests.getPath(), toWrite);
    //         System.out.println("Guest successfully added!");
    //     } catch (Exception e) {
    //         //TODO: handle exception
    //         System.out.println("Guest not added!");

    //     }
    // }

    public static void main(String[] args) throws IOException {
        GuestController g = new GuestController();
        // g.CreateGuest();
        g.PrintAllGuestDetails();
    }


}
