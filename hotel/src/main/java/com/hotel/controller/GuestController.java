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

public class GuestController {

    Scanner sc = new Scanner(System.in);
    static GuestDB allGuests = new GuestDB();

    public void CreateGuest() throws IOException{

        String name = UpdateGuestMenu.UpdateName();
        String guestId = UpdateGuestMenu.UpdateGuestId();
        Gender gender = UpdateGuestMenu.UpdateGender();
        String nationality = UpdateGuestMenu.UpdateNationality();
        String country = UpdateGuestMenu.UpdateCountry();
        Integer phoneNumber=UpdateGuestMenu.UpdatePhoneNumber();
        String creditCardNumber = UpdateGuestMenu.UpdateCrediCardNo();

        Guest guest = new Guest(guestId, name, gender, nationality, country, phoneNumber, creditCardNumber);

        ArrayList allData = getAllGuests();
        allData.add(guest);
        saveData(allData);

    }

    public static ArrayList getAllGuests() throws IOException {
        ArrayList allData = allGuests.read(allGuests.getPath());
        return allData;
    }

    public void RetrieveGuestbyID() throws IOException{
        // print out guestDetails
        
    }

    public Guest RetrieveGuest(Guest guest) throws IOException{
        ArrayList allData = getAllGuests();
        // return guest object 

    }

    public Guest GetGuestDetails()

    public void saveData(ArrayList toWrite) {
        
        try {
            allGuests.save(allGuests.getPath(), toWrite);
            System.out.println("Guest successfully added!");
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println("Guest not added!");

        }
    }


}
