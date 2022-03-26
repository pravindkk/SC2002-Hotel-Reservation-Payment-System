package com.hotel.controller;

import java.io.IOException;
import java.util.Scanner;
import org.apache.commons.lang3.StringUtils;
import com.hotel.system.enums.*;

public class UpdateGuestMenu {

    static Scanner sc = new Scanner(System.in);

    public static String UpdateName() throws IOException{
        String name = null;
        do{
            System.out.println("Enter Name");
            name = sc.nextLine();
        }while(name == null || name.trim().isEmpty() || StringUtils.isNumeric(name)==true);

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
            nationality = sc.nextLine();
        }while(nationality == null || nationality.trim().isEmpty() || StringUtils.isNumeric(nationality)==true);

        return nationality;
    }

    public static String UpdateCountry() throws IOException{
        String country = null;
        do{
            System.out.println("Enter the country you are from:");
            country = sc.nextLine();
        }while(country == null || country.trim().isEmpty() || StringUtils.isNumeric(country)==true);

        return country;
    }

   
    
    
}
