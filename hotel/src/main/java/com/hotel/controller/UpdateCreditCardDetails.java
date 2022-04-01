package com.hotel.controller;

import com.hotel.system.enums.CreditCardType;


import java.io.IOException;
import java.util.Scanner;
import org.apache.commons.lang3.StringUtils;

public class UpdateCreditCardDetails {

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
                        guestId = sc.next();
                    }while(guestId==null);
                    break;
                    
                    case 2:
                    do{
                        System.out.println("Enter passport Number:");
                        guestId = sc.next();
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
            name = sc.next();
        }while (name == null);

        return name;
    }

    public static Integer UpdateCardNo() throws IOException{
        Integer cardno = null;
        do{
            System.out.println("Enter card number:");
            try {
                cardno = sc.nextInt();
                
            } catch (Exception e) {
                //TODO: handle exception
            }
            break;
        }while(true);
        return cardno;
    }

    public static Integer UpdateCVV() throws IOException{
        Integer cvv = null;
        do{
            System.out.println("Enter CVV:");
            try {
                cvv = sc.nextInt();
                
            } catch (Exception e) {
                //TODO: handle exception
            }
            break;
        }while(true);
        return cvv;
    }

    public static String UpdateExpiry() throws IOException{
        String expiry = null;
        do{
            System.out.println("Enter Expiry:");
            expiry = sc.next();
        }while(expiry==null);

        return expiry;
    }



    public static CreditCardType updateCreditCardType() throws IOException{
        CreditCardType type = CreditCardType.AMEX;
        do{
            System.out.println("Please enter your type of Credit Card:");
            System.out.println("(1) Amex");
            System.out.println("(2) Visa");
            System.out.println("(3) MasterCard");
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
                        type = CreditCardType.AMEX;
                        break;
                    case 2:
                        type = CreditCardType.VISA;
                        break;
                    case 3:
                        type = CreditCardType.MASTERCARD;
                        break;
                }
            }
            break;
        }while (true);

        return type;
        
    }









    
}
