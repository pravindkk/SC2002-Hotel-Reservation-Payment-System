package com.hotel.controller;

import java.io.IOException;
import java.text.ParseException;
import com.hotel.system.enums.*;
import java.util.Scanner;
import com.hotel.db.GuestDB;
import com.hotel.system.Guest;
import com.hotel.db.ReadInFile;
import java.util.ArrayList;
import com.hotel.controller.UpdateGuestMenu;
import org.apache.commons.lang3.StringUtils;

/**
 * Represents the abstract class of DB , which is used to read and write all data to the text file.
 * @author Pravind
 * @version 1.0
 * @since 1.0
 */


public class GuestController {

    Scanner sc = new Scanner(System.in);
    static GuestDB allGuests = new GuestDB();

    public static Guest CreateGuest() throws IOException{

        String name = UpdateGuestMenu.UpdateName();
        String guestId = UpdateGuestMenu.UpdateGuestId();
        Gender gender = UpdateGuestMenu.UpdateGender();
        String nationality = UpdateGuestMenu.UpdateNationality();
        String country = UpdateGuestMenu.UpdateCountry();
        Integer phoneNumber=UpdateGuestMenu.UpdatePhoneNumber();
        String creditCardNumber = UpdateGuestMenu.UpdateCrediCardNo();

        Guest guest = new Guest(guestId, name, gender, nationality, country, phoneNumber, creditCardNumber);
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
        "(6) Credit Card Number \n"+
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
                guest.setCreditCardNumber(UpdateGuestMenu.UpdateCrediCardNo());
                break;
            case 7:
                guest.setName(UpdateGuestMenu.UpdateName());
                guest.setGender(UpdateGuestMenu.UpdateGender());
                guest.setNationality(UpdateGuestMenu.UpdateNationality());
                guest.setCountry(UpdateGuestMenu.UpdateCountry());
                guest.setPhoneNumber(UpdateGuestMenu.UpdatePhoneNumber());
                guest.setCreditCardNumber(UpdateGuestMenu.UpdateCrediCardNo());
                break;
        }


        // allGuests.set(index, guest);
        saveGuestByID(guest);

        // saveData(allGuests);
    }

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

    public static ArrayList getAllGuests() throws IOException {
        ArrayList<Guest> allData = allGuests.read(allGuests.getPath());
        return allData;
    }

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
            System.out.printf("%-8s %-9s %-10s %-12s %-10s %-13s %-15s", g.getGuestId(), g.getName(), g.getGender(), g.getNationality(), 
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
