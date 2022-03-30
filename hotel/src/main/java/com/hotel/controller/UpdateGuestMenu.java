package com.hotel.controller;

import java.io.IOException;
import java.util.Scanner;
import org.apache.commons.lang3.StringUtils;
import com.hotel.system.enums.*;

public class UpdateGuestMenu {

    static Scanner sc = new Scanner(System.in);

    public static String UpdateGuestId() throws IOException{
        String guestId = null;
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
                switch(choice){
                    case 1:
                    do{
                        System.out.println("Enter Driving License Number:");
                        sc.next();
                        guestId = sc.nextLine();
                    }while(guestId==null);
                    break;
                    
                    case 2:
                    do{
                        System.out.println("Enter passport Number:");
                        sc.next();
                        guestId = sc.nextLine();
                    }while(guestId==null);

                        
                }
                break;
            }
            
        }while(true);
        return guestId;



    }

    public static String UpdateName() throws IOException{
        String name = null;
        do{
            System.out.println("Enter Name");
            sc.next();
            name = sc.nextLine();
        }while (name == null);

        return name;
    }

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
                        gender = Gender.MALE;
                        break;
                    case 2:
                        gender = Gender.FEMALE;
                        break;
                    case 3:
                        gender = Gender.OTHERS;
                        break;
                }
            }
            break;
        }while (true);

        return gender;
        
    }

    public static String UpdateNationality() throws IOException{
        String nationality = null;
        do{
            System.out.println("Enter Nationality:");
            sc.next();
            nationality = sc.nextLine();
        }while(nationality == null);

        return nationality;
    }

    public static String UpdateCountry() throws IOException{
        String country = null;
        do{
            System.out.println("Enter the country you are from:");
            sc.next();
            country = sc.nextLine();
        }while(country == null);

        return country;
    }

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

    public static String UpdateCrediCardNo() throws IOException{
        String creditcard = null;
        do{
            System.out.println("Enter Credit Card Number:");
            sc.next();
            creditcard = sc.nextLine();
        }while (creditcard==null);

        return creditcard;
    }



   
    
    
}
