package com.hotel.system;

import com.hotel.system.enums.CreditCardType;

public class CreditCard {

    private String guestID;
    private String Name;
    private Integer CardNo;
    private String expiry;
    private Integer CVV;
    public CreditCardType cardType;

    public CreditCard(String guestID , String Name , Integer CardNo , String expiry, Integer CVV , CreditCardType cardType ){
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

    public Integer getCardNo(){
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

    public Integer getCVV(){
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

    public String getGuestID(){
        return this.guestID;
    }
    public void setGuestID(String guestID){
        this.guestID=guestID;
    }

    // do we need to validate creditcard







}
