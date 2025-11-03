//package com.example.hotel.service;
//
//public class ReservationService {
//}
package com.example.hotel.service;

import com.example.hotel.dao.ReservationDAO;
import com.example.hotel.model.*;
import com.example.hotel.exception.HotelReservationException;

import java.sql.SQLException;
import java.util.List;

public class ReservationService {
    private final ReservationDAO reservationDAO = new ReservationDAO();
    private final HotelService hotelService = new HotelService();

    public void makeReservation(int hotelId, String guest, String in, String out, int rooms)
            throws SQLException, HotelReservationException {

        int available = getAvailableRooms(hotelId, in, out);
        if (rooms > available)
            throw new HotelReservationException("Not enough rooms available. Only " + available + " left.");

        Reservation reservation = new Reservation(0, hotelId, guest, in, out, rooms);
        reservationDAO.save(reservation);
        System.out.println("Reservation successful!");
    }
    public void reserveRoom(int hotelId, String guestName, String checkIn, String checkOut, int rooms) throws HotelReservationException {
        reservationDAO.reserveRoom(hotelId, guestName, checkIn, checkOut, rooms);
    }

    public int getAvailableRooms(int hotelId, String in, String out) throws SQLException {
        Hotel hotel = hotelService.getHotel(hotelId);
        List<Reservation> existing = reservationDAO.findByHotel(hotelId);
        int reserved = existing.stream()
                .filter(r -> !(r.getCheckOut().compareTo(in) <= 0 || r.getCheckIn().compareTo(out) >= 0))
                .mapToInt(Reservation::getRoomsBooked)
                .sum();
        return hotel.getTotalRooms() - reserved;
    }

    public List<Reservation> getReservationsByHotel(int hotelId) throws SQLException {
        return reservationDAO.findByHotel(hotelId);
    }

}
