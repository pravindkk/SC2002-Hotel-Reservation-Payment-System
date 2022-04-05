package com.hotel.system;

import com.hotel.system.enums.Gender;

/**
 * Represents the class of Guest, which is used to represent a person who has his records in the HRPS
 * @author Vignesh Ezhil
 * @version 1.0
 * @since 1.0
 */

public class Guest {
    private String guestId;
    private String name;
    private Gender gender;
    private String nationality;
    private String country;
    private Integer phoneNumber;
    private String creditCardNumber;

    /**
     * Constructor for Guest object
     * @param guestId           ID that uniquely identifies the guest
     * @param name              Name of guest
     * @param gender            Gender of guest
     * @param nationality       Nationality of guest
     * @param country           Country of origin of guest
     * @param phoneNumber       Phone number of guest
     * @param creditCardNumber  Credit card number of guest
     */
    public Guest(String guestId, String name, Gender gender, String nationality, String country, Integer phoneNumber, String creditCardNumber) {
        this.guestId = guestId;
        this.name = name;
        this.gender = gender;
        this.nationality = nationality;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.creditCardNumber = creditCardNumber;
    }

    
    /** 
     * Returns guestID as String
     * @return String
     */
    public String getGuestId() {
        return guestId;
    }
    
    /** 
     * Takes a String guestId and sets it as the new guestId
     * @param guestId uniquely identifies the guest
     */
    public void setGuestId(String guestId) {
        this.guestId = guestId;
    }

    
    /** 
     * Returns name as String
     * @return String
     */
    public String getName() {
        return this.name;
    }
    
    /** 
     * Takes a String name and sets it as the new name
     * @param name of guest
     */
    public void setName(String name) {
        this.name = name;
    }

    
    /** 
     * Returns gender as enumerated value Gender
     * @return Gender
     */
    public Gender getGender() {
        return this.gender;
    }
    
    /** 
     * Takes a Gender gender and sets it as the new gender
     * @param gender is either male or female
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    
    /** 
     * Returns nationality as String
     * @return String
     */
    public String getNationality() {
        return this.nationality;
    }
    
    /** 
     * Takes a String nationality and sets it as the new nationality
     * @param nationality of the guest
     */
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    
    /** 
     * Returns country as string
     * @return String
     */
    public String getCountry() {
        return this.country;
    }
    
    /** 
     * Takes a String country and sets it as the new country
     * @param country of origin of the guest
     */
    public void setCountry(String country) {
        this.country = country;
    }

    
    /** 
     * Returns phoneNumber as Integer
     * @return Integer
     */
    public Integer getPhoneNumber() {
        return this.phoneNumber;
    }
    
    /** 
     * Takes an Integer phoneNumber and sets it as the new phoneNumber
     * @param phoneNumber is the contact number of the guest
     */
    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    
    /** 
     * Returns creditCardNumber as a String
     * @return String
     */
    public String getCreditCardNumber() {
        return this.creditCardNumber;
    }
    
    /** 
     * Takes a String creditCardNumber and sets it as the new creditCardNumber
     * @param creditCardNumber uniquely identifies the CreditCard that belongs to the guest
     */
    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }
}
