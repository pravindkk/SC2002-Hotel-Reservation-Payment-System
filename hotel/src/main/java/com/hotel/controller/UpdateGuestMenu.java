package com.hotel.controller;

import java.io.IOException;
import java.util.Scanner;
import org.apache.commons.lang3.StringUtils;
import com.hotel.system.enums.*;

/**
 * Represents the class which enables Guest to be updated through user input
 * @author Vignesh Ezhil
 * @version 1.0
 * @since 1.0
 */

public class UpdateGuestMenu {

    static Scanner sc = new Scanner(System.in);

    
     /** 
     * This method updates the guestID tagged to the Guest
     * @return Updated GuestID is returned as a String output
     * @throws IOException Due to communication with the DataBase IOexception is required
     */
    public static String UpdateGuestId() throws IOException{
        String guestId = null;
        String licencePattern = "((?i)^[STFG]\\d{7}[A-JZ]$)";
        do{
            System.out.println("Please Choose Identity Type");
            System.out.println("(1) Driving License");
            System.out.println("(2) Passport ");

            int choice =0;
            try{
                choice = sc.nextInt();
            } catch(Exception e){
                //TODO: handle exception
            }

            if(choice<1 || choice>2){
                System.out.println("Enter a valid option!!");
            }
            else{
                sc.nextLine();
                switch(choice){
                    case 1:
                    do{
                        System.out.print("Enter Driving License Number:");
                        guestId = sc.nextLine();
                        if (!guestId.matches(licencePattern)) {
                            System.out.println("ERROR!! The format for the license is SXXXXXXXA");
                        }
                    }while(guestId==null || !guestId.matches(licencePattern));
                    break;
                    
                    case 2:
                    do{
                        System.out.print("Enter passport Number:");
                        guestId = sc.nextLine();
                    }while(guestId==null);

                        
                }
                break;
                // if (GuestController.RetrieveGuest(guestId) == null) break;
                // else System.out.println("ERROR!!User with the same ID exists!!");
            }
            // sc.nextLine();
            
        }while(true);
        return guestId;



    }

     /** 
     * This method creates the guestID tagged to the Guest
     * @return Created GuestID is returned as a String output
     * @throws IOException Due to communication with the DataBase IOexception is required
     */
    public static String CreateGuestId() throws IOException{
        String guestId = null;
        String licencePattern = "((?i)^[STFG]\\d{7}[A-JZ]$)";
        String passportPattern = "((?i)^[STFG]\\d{8}[A-JZ]$)";
        do{
            System.out.println("Please Choose Identity Type");
            System.out.println("(1) Driving License");
            System.out.println("(2) Passport ");

            int choice =0;
            try{
                choice = sc.nextInt();
            } catch(Exception e){
                //TODO: handle exception
            }

            if(choice<1 || choice>2){
                System.out.println("Enter a valid option!!");
            }
            else{
                sc.nextLine();
                switch(choice){
                    case 1:
                    do{
                        System.out.print("Enter Driving License Number:");
                        guestId = sc.nextLine();
                        if (!guestId.matches(licencePattern)) {
                            System.out.println("ERROR!! The format for the license is SXXXXXXXA. (It has 7 digits)");
                        }
                    }while(guestId==null || !guestId.matches(licencePattern));
                    break;
                    
                    case 2:
                    do{
                        System.out.print("Enter passport Number:");
                        guestId = sc.nextLine();
                        if (!guestId.matches(passportPattern)) {
                            System.out.println("ERROR!! The format for the passport is SXXXXXXXXA. (It has 8 digits)");
                        }
                    }while(guestId==null);

                        
                }
                // break;
                if (GuestController.RetrieveGuest(guestId) == null) break;
                else System.out.println("ERROR!!User with the same ID exists!!");
            }
            // sc.nextLine();
            
        }while(true);
        return guestId;



    }

    
     /** 
     * This method updates the Name tagged to the Guest
     * @return Updated Name is returned as a String output
     * @throws IOException Due to communication with the DataBase IOexception is required
     */
    public static String UpdateName() throws IOException{
        String name = null;
        do{
            System.out.println("Enter Name");
            // name = sc.nextLine();
            try {
                name = sc.nextLine();
                if (name.equals("")) throw new Exception();
            } catch (Exception e) {
                //TODO: handle exception
                name = sc.nextLine();
            }
        }while (name == null);

        return name;
    }

    
 /** 
     * This method updates the Gender tagged to the Guest
     * @return Updated Gender is returned as a Gender output
     * @throws IOException Due to communication with the DataBase IOexception is required
     */
    public static Gender UpdateGender() throws IOException{
        Gender gender = Gender.OTHERS;
        do{
            System.out.println("Please enter your Gender:");
            System.out.println("(1) Male");
            System.out.println("(2) Female");
            System.out.println("(3) Others");
            int choice =0;
            try{
                choice = sc.nextInt();
            } catch(Exception e){
                //TODO: handle exception
            }

            if(choice<1 || choice>3){
                System.out.println("Enter a valid option!!");
            }
            else{
                switch(choice){
                    case 1:
                        return Gender.MALE;
                        // break;
                    case 2:
                        return Gender.FEMALE;
                        
                    case 3:
                        return Gender.OTHERS;
                    default:
                        break;
                    
                }
            }
            // break;
            sc.nextLine();
        }while (true);

        
        
    }

    
    /** 
     * This method updates the Nationality tagged to the Guest
     * @return Updated Nationality is returned as a String output
     * @throws IOException Due to communication with the DataBase IOexception is required
     */
    public static String UpdateNationality() throws IOException{
        String nationality = null;
        do{
            System.out.println("Enter Nationality:");
            nationality = sc.next();
        }while(nationality == null);

        return nationality;
    }

    
    /** 
     * This method updates the Country tagged to the Guest
     * @return Updated Country is returned as a String output
     * @throws IOException Due to communication with the DataBase IOexception is required
     */
    public static String UpdateCountry() throws IOException{
        String country = null;
        do{
            System.out.println("Enter the country you are from:");
            country = sc.next();
        }while(country == null);

        return country;
    }

    
    /** 
     * This method updates the phone number tagged to the Guest
     * @return Updated phone number is returned as a Integer output
     * @throws IOException Due to communication with the DataBase IOexception is required
     */
    public static Integer UpdatePhoneNumber() throws IOException{
        Integer phoneNumber = null;
        do{
            System.out.println("Enter a contact number:");
            
            try {
                phoneNumber = sc.nextInt();
                
            } catch (Exception e) {
                //TODO: handle exception
            }
            break;
        }while(true);
        return phoneNumber;
    }

    
    /** 
     * @return String
     * @throws IOException
     */
    public static String UpdateCrediCardNo() throws IOException{
        String creditcard = null;
        do{
            System.out.println("Enter Credit Card Number:");
            creditcard = sc.next();
        }while (creditcard==null );

        return creditcard;
    }



   
    
    
}
