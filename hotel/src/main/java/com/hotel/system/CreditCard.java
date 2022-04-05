package com.hotel.system;

import com.hotel.system.enums.CreditCardType;

/**
 * Represents the class of CreditCard , which would then allow a guest to input their credit card details
 * @author Vignesh Ezhil
 * @version 1.0
 * @since 2022-04-52
 */

public class CreditCard {

    private String guestID;
    private String Name;
    private String CardNo;
    private String expiry;
    private String CVV;
    public CreditCardType cardType;

    /**
     * 
     * @param guestID
     * @param Name
     * @param CardNo
     * @param expiry
     * @param CVV
     * @param cardType
    */

    public CreditCard(String guestID , String Name , String CardNo , String expiry, String CVV , CreditCardType cardType ){
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

    public String getCardNo(){
        return this.CardNo;
    }
    public void setCardNo(String CardNo){
        this.CardNo=CardNo;
    }

    public String getExpiry(){
        return this.expiry;
    }
    public void setExpiry(String expiry){
        this.expiry=expiry;
    }

    public String getCVV(){
        return this.CVV;
    }
    public void setCVV(String CVV){
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









}
