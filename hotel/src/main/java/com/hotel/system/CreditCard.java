package com.hotel.system;

import com.hotel.system.enums.CreditCardType;

/**
 * Represents the class of CreditCard, which allows a user to input a guest's credit card details
 * @author Vignesh Ezhil
 * @version 1.0
 * @since 1.0
 */

public class CreditCard {


    private String guestID;
    private String Name;
    private String CardNo;
    private String expiry;
    private String CVV;
    public CreditCardType cardType;


    /**
     * Constructor for CreditCard object
     * @param guestID   ID number of the Guest
     * @param Name      Name of Guest who owns the CreditCard
     * @param CardNo    Card Number to uniquely identify the CreditCard
     * @param expiry    Expiry date of the card
     * @param CVV       Security number for the card
     * @param cardType  Type of card (VISA, MASTERCARD, AMEX)
    */
    public CreditCard(String guestID , String Name , String CardNo , String expiry, String CVV , CreditCardType cardType ){
        this.guestID = guestID;
        this.Name=Name;
        this.CardNo = CardNo;
        this.expiry = expiry;
        this.CVV= CVV;
        this.cardType = cardType;
    }

    
    /** 
     * Returns Name as a String
     * @return String
     */
    public String getName(){
        return this.Name;
    }
    

    /** 
     * Takes a String Name and sets it as the new Name 
     * @param Name is the registered name of the guest who owns the card
     */
    public void setName(String Name){
        this.Name=Name;
    }

    
    /** 
     * Returns Card Number as a String
     * @return String
     */
    public String getCardNo(){
        return this.CardNo;
    }
    

    /** 
     * Takes a String CardNo and sets it as the new Card Number
     * @param CardNo uniquely identifies the card 
     */
    public void setCardNo(String CardNo){
        this.CardNo=CardNo;
    }

    
    /** 
     * Returns the expiry date of the card as a String
     * @return String
     */
    public String getExpiry(){
        return this.expiry;
    }
    

    /** 
     * Takes a String expiry and sets it as the new expiry date
     * @param expiry date of the card determines whether it is valid or not
     */
    public void setExpiry(String expiry){
        this.expiry=expiry;
    }

    
    /** 
     * Returns the CVV Security Code of the card as a String
     * @return String
     */
    public String getCVV(){
        return this.CVV;
    }
    
    /** 
     * Takes a String CVV and sets it as the new CVV Security Code
     * @param CVV must be given in order for an online transaction to be successful
     */
    public void setCVV(String CVV){
        this.CVV = CVV;
    }

    
    /** 
     * Returns the CardType as a CreditCardType
     * @return CreditCardType
     */
    public CreditCardType getCardType(){
        return this.cardType;
    }

    
    /** 
     * Takes a CreditCardType argument and sets it as the new CardType
     * @param cardType determines what type of card this is (VISA, MASTERCARD, AMEX)
     */
    public void setCardType(CreditCardType cardType){
        this.cardType=cardType;
    }

    
    /** 
     * Returns GuestID as a String
     * @return String
     */
    public String getGuestID(){
        return this.guestID;
    }
    

    /** 
     * Takes a String guestID and sets it as the guestID
     * @param guestID uniquely identifies the guest
     */
    public void setGuestID(String guestID){
        this.guestID=guestID;
    }
}
