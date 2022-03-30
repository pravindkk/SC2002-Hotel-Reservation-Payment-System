package com.hotel.system;

import com.hotel.system.enums.Gender;



public class Guest {
    private String guestId;
    private String name;
    private Gender gender;
    private String nationality;
    private String country;
    private Integer phoneNumber;
    private String creditCardNumber;

    public Guest(String guestId, String name, Gender gender, String nationality, String country, Integer phoneNumber, String creditCardNumber) {
        this.guestId = guestId;
        this.name = name;
        this.gender = gender;
        this.nationality = nationality;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.creditCardNumber = creditCardNumber;
    }

    public String getGuestId() {
        return guestId;
    }
    public void setGuestId(String guestId) {
        this.guestId = guestId;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return this.gender;
    }
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getNationality() {
        return this.nationality;
    }
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getCountry() {
        return this.country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getPhoneNumber() {
        return this.phoneNumber;
    }
    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCreditCardNumber() {
        return this.creditCardNumber;
    }
    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }
}
