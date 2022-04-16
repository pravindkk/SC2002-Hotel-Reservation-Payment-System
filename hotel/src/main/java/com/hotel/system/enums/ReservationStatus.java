package com.hotel.system.enums;

/**
 *  ReservationStatus that can be used
 *  {@link #CONFIRMED}
 *  {@link #CHECKED_IN}
 *  {@link #CHECKED_OUT}
 *  {@link #EXPIRED}
 *  {@link #CANCELLED}
 */


public enum ReservationStatus {

    /**
     * CONFIRMED RESERVATION_STATUS
     */
    CONFIRMED, 

    /**
     * CHECKED_IN RESERVATION_STATUS
     */

    CHECKED_IN,

    /**
     * CHECKED_OUT RESERVATION_STATUS
     */
    CHECKED_OUT, 

    /**
     * EXPIRED RESERVATION_STATUS
     */
    EXPIRED,
     
    /**
     * CANCELLED RESERVATION_STATUS
     */
    CANCELLED;
}
