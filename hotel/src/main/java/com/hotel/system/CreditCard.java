package com.hotel.system;

import com.hotel.system.enums.CreditCardType;

public class CreditCard {

    private int guestID;
    private String Name;
    private int CardNo;
    private String expiry;
    private int CVV;
    public CreditCardType cardType;

    public CreditCard(int guestID , String Name , int CardNo , String expiry, int CVV , CreditCardType cardType ){
        this.guestID = guestID;
        this.Name=Name;
        this.CardNo = CardNo;
        this.expiry = expiry;
        this.CVV= CVV;
        this.cardType = cardType;
    }

    public String getName(){
        return this.Name;
    }
    public void setName(String Name){
        this.Name=Name;
    }

    public int getCardNo(){
        return this.CardNo;
    }
    public void setCardNo(int CardNo){
        this.CardNo=CardNo;
    }

    public String getExpiry(){
        return this.expiry;
    }
    public void setExpiry(String expiry){
        this.expiry=expiry;
    }

    public int getCVV(){
        return this.CVV;
    }
    public void setCVV(int CVV){
        this.CVV = CVV;
    }

    public CreditCardType getCardType(){
        return this.cardType;
    }

    public void setCardType(CreditCardType cardType){
        this.cardType=cardType;
    }

    public int getGuestID(){
        return this.guestID;
    }
    public void setGuestID(int guestID){
        this.guestID=guestID;
    }







}
