//package com.example.hotel.model;
//
//public class Hotel {
//}
package com.example.hotel.model;

public class Hotel {
    private int id;
    private String name;
    private String city;
    private int totalRooms;
    private double pricePerNight;

    public Hotel() {
    }

    public Hotel(int id, String name, String city, int totalRooms, double pricePerNight) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.totalRooms = totalRooms;
        this.pricePerNight = pricePerNight;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public int getTotalRooms() {
        return totalRooms;
    }
    public double getPricePerNight()
    {
        return pricePerNight;
    }

    @Override

    public String toString()
    {

        return id + " - " + name + " (" + city + ") - Rooms: " + totalRooms + " - Price: " + pricePerNight;

    }
}
