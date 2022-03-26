package com.hotel.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import com.hotel.system.enums.*;

public class ReservationController {
    Scanner sc = new Scanner(System.in);

    public void createReservation() {
        Date todaysdate = new Date();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String dateValidation = "^([0-2][0-9]||3[0-1])/(0[0-9]||1[0-2])/([0-9][0-9])?[0-9][0-9]$";
        String checkInDateString = null;
        Date checkInDate = null;
        String checkOutDateString = null;
        Date checkOutDate = null;
        
        do {                                                                                                // for check-in date input
            System.out.println("Enter Check-In Date");

            try {
                checkInDateString = sc.nextLine();
                checkInDate = df.parse(checkInDateString);
            } catch(ParseException e) {
                System.out.println(e);
            }

        } while(!checkInDate.before(todaysdate) && !checkInDateString.matches(dateValidation));

        do {                                                                                                // for check-out date input
            System.out.println("Enter Check-Out Date");

            try {
                checkOutDateString = sc.nextLine();
                checkOutDate = df.parse(checkOutDateString);
            } catch(ParseException e) {
                System.out.println(e);
            }

        } while(!checkOutDate.before(todaysdate) && !checkOutDateString.matches(dateValidation));
    }
}
