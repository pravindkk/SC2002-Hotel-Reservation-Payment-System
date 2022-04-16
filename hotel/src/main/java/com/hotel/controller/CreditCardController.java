package com.hotel.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;
import com.hotel.db.CreditCardDB;
import com.hotel.system.CreditCard;
import com.hotel.db.ReadInFile;
import java.util.ArrayList;
import com.hotel.controller.UpdateCreditCardDetails;
import com.hotel.system.enums.*;

/**
 * Represents the controller function of CredtCard
 * @author Vignesh Ezhil
 * @version 1.0
 * @since 1.0
 */

import org.apache.commons.lang3.StringUtils;

public class CreditCardController {

    Scanner sc = new Scanner(System.in);
    static CreditCardDB allCreditCards = new CreditCardDB();

    
    /** 
     * This method creates a credit card object and returns it
     * @return returns an Object of the class CreditCard
     * @throws IOException Due to communication with the DataBase IOexception is required
     */
    public static CreditCard createCreditCard(String guestId, String name) throws IOException{

        String cardno = UpdateCreditCardDetails.UpdateCardNo();
        String cvv = UpdateCreditCardDetails.UpdateCVV();
        String expiry = UpdateCreditCardDetails.UpdateExpiry();
        CreditCardType type = UpdateCreditCardDetails.updateCreditCardType();

        CreditCard card = new CreditCard(guestId , name , cardno , expiry, cvv , type);

        ArrayList allData = getAllCreditCards();
        allData.add(card);
        saveCards(allData);
        return card;
    }
    
    /**
     * This method makes use of relevant functions from  
     * UpdateCreditCardDetails to update credit card details
     * @param guestId String input of guestId is passed as input
     * @return returns an Object of the class CreditCard
     * @throws IOException Due to communication with the DataBase IOexception is required
     */
    public static CreditCard updateCreditCard(String guestId) throws IOException{
        // String guestId = UpdateCreditCardDetails.UpdateGuestId();
        // String name = UpdateCreditCardDetails.UpdateName();

        CreditCard card = RetrieveCreditCard(guestId);
        card.setCardNo(UpdateCreditCardDetails.UpdateCardNo());
        card.setCVV(UpdateCreditCardDetails.UpdateCVV());
        card.setExpiry(UpdateCreditCardDetails.UpdateExpiry());
        card.setCardType(UpdateCreditCardDetails.updateCreditCardType());


        ArrayList allData = getAllCreditCards();

        for (int i=0; i<allData.size(); i++) {
            CreditCard c = (CreditCard) allData.get(i);
            if (c.getGuestID().equals(card.getGuestID())) {
                allData.set(i, card);
                saveCards(allData);
                return card;
            }
        }

        // CreditCard card = new CreditCard(guestId , name , cardno , expiry, cvv , type);
        return null; 
    }


    /**
     * This method deletes a CreditCard object
     * @param guestId String input of guestId is passed as input
     * @throws IOException Due to communication with the DataBase IOexception is required
     */
    public static void deleteCreditCard(String guestId) throws IOException {
        CreditCard card = RetrieveCreditCard(guestId);

        ArrayList allData = getAllCreditCards();

        for (int i=0; i<allData.size(); i++) {
            CreditCard c = (CreditCard) allData.get(i);
            if (c.getGuestID().equals(guestId)) {
                allData.remove(i);
                saveCards(allData);
            }
        }


    }


    
    /** 
     * @return returns an ArrayList of all the CreditCard stored in the database
     * @throws IOException Due to communication with the DataBase IOexception is required
     */
    public static ArrayList getAllCreditCards() throws IOException {
        ArrayList<CreditCard> allData = allCreditCards.read(allCreditCards.getPath());
        return allData;
    }


    
    /** 
     * @param toWrite  Contains an ArrayList of all the creditcard that is going to be stored in the database
     */
    public static void saveCards(ArrayList toWrite) {
        try {
            allCreditCards.save(allCreditCards.getPath(), toWrite);
            System.out.println("Credit Card successfully updated!");
        } catch (Exception e) {
            //TODO: handle exception

            System.out.println("Credit Card not added!");
            System.out.println(e);

        }
    }

    
    /** 
     * @param card Contains an object of the class CreditCard that is going to be stored in the database
     * @throws IOException Due to communication with the DataBase IOexception is required
     */
    public static void saveCardByID(CreditCard card) throws IOException {
        ArrayList allData = getAllCreditCards();

        for (int i=0; i<allData.size(); i++) {
            CreditCard c = (CreditCard) allData.get(i);
            if (c.getGuestID().equals(c.getGuestID())) {
                allData.set(i, c);
                saveCards(allData);
                return;
            }
        }
        System.out.println("Not updated CreditCard");
    }

    
    /** 
     * @param GuestID String input of GuestID is entered so that the corresponding CreditCard is returned
     * @return An object of creditcard that matches the GuestID is returned . If no record is found , null value is returned
     * @throws IOException Due to communication with the DataBase IOexception is required
     */
    public static CreditCard RetrieveCreditCard(String GuestID) throws IOException{
        ArrayList allData = getAllCreditCards();
        // ArrayList toReturn = new ArrayList();

        for(int i =0 ;i< allData.size();i++){
            CreditCard guestSearch = (CreditCard) allData.get(i);
            if(GuestID.equals(guestSearch.getGuestID())){
                return guestSearch;
            }
        }
        return null;

    }

    
    /** 
     * @param guestId String input of GuestID is entered so that the corresponding CreditCard object is deleted
     * @throws IOException Due to communication with the DataBase IOexception is required
     */
    public void DeleteGuest(String guestId) throws IOException{
        ArrayList allData = getAllCreditCards();
        for (int i=0; i<allData.size(); i++) {
            CreditCard test = (CreditCard) allData.get(i);
            if (test.getGuestID().equals(guestId)) {
                allData.remove(i);
                saveCards(allData);
                return;
            }
        }
        System.out.println("cannot find the guest");
    }

    
    /** 
     * @param guestId String input of GuestID is entered so that the corresponding CreditCard object could be updated
     * @throws IOException Due to communication with the DataBase IOexception is required
     */
    public void UpdateCreditCard(String guestId) throws IOException{

        // ArrayList allGuests = getAllGuests();
        CreditCard card = RetrieveCreditCard(guestId);

        if(card==null){
            System.out.println("Invalid guestID");
            return;
        }

        System.out.println("\nPlease choose Credit Card details to update \n" + 
        "(1) Name \n"+
        "(2) Creditcard Number \n"+ 
        "(3) Expiry \n"+
        "(4) CVV \n"+
        "(5) Credit card Type \n"+
        "(6) All details");

        int choice =0;

        try {
            choice = sc.nextInt();
        } catch (Exception e) {
            //TODO: handle exception
        }

        if (choice < 1 || choice > 6) {
            System.out.println("Invalid input");
            return;
        }

        switch(choice){

            case 1:
                card.setName(UpdateCreditCardDetails.UpdateName());
                break;

            case 2:
                card.setCardNo(UpdateCreditCardDetails.UpdateCardNo());
                break;

            case 3:
                card.setExpiry(UpdateCreditCardDetails.UpdateExpiry());
                break;

            case 4:
                card.setCVV(UpdateCreditCardDetails.UpdateCVV());
                break;

            case 5:
                card.setCardType(UpdateCreditCardDetails.updateCreditCardType());
                break;
            
            case 6:
                card.setName(UpdateCreditCardDetails.UpdateName());
                card.setCardNo(UpdateCreditCardDetails.UpdateCardNo());
                card.setExpiry(UpdateCreditCardDetails.UpdateExpiry());
                card.setCVV(UpdateCreditCardDetails.UpdateCVV());
                card.setCardType(UpdateCreditCardDetails.updateCreditCardType());
                break;
        }
        saveCardByID(card);
    }


    

    // public static void main(String[] args) throws IOException {
    //     CreditCardController c = new CreditCardController();
    //     c.createCreditCard();
 
    // }

    
}
