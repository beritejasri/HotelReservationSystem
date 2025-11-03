
package com.example.hotel.model;

public class Reservation
{
    private int id;
    private int hotelId;
    private String guestName;
    private String checkIn;
    private String checkOut;
    private int roomsBooked;

    public Reservation() {}

    public Reservation(int id, int hotelId, String guestName, String checkIn, String checkOut, int roomsBooked)
    {
        this.id = id;
        this.hotelId = hotelId;
        this.guestName = guestName;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.roomsBooked = roomsBooked;
    }

    public int getHotelId() { return hotelId; }
    public int getRoomsBooked() { return roomsBooked; }
    public String getCheckIn() { return checkIn; }
    public String getCheckOut() { return checkOut; }

    @Override
    public String toString()
    {
        return "Reservation[id=" + id + ", hotelId=" + hotelId + ", guest=" + guestName +
                ", " + checkIn + "->" + checkOut + ", rooms=" + roomsBooked + "]";
    }
}
