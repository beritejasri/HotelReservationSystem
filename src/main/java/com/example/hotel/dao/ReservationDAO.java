
package com.example.hotel.dao;

import com.example.hotel.model.Reservation;
import java.sql.*;
import java.util.*;

public class ReservationDAO
{

    public void save(Reservation r) throws SQLException
    {
        String sql = "INSERT INTO reservations (hotel_id, guest_name, check_in, check_out, rooms_reserved) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setInt(1, r.getHotelId());
            ps.setString(2, r.toString());
            ps.setString(3, r.getCheckIn());
            ps.setString(4, r.getCheckOut());
            ps.setInt(5, r.getRoomsBooked());
            ps.executeUpdate();
        }
    }
    public void reserveRoom(int hotelId, String guestName, String checkIn, String checkOut, int rooms)
    {
        String sql = "INSERT INTO reservations (hotel_id, guest_name, check_in, check_out, rooms_reserved) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql))
        {
            stmt.setInt(1, hotelId);
            stmt.setString(2, guestName);
            stmt.setString(3, checkIn);
            stmt.setString(4, checkOut);
            stmt.setInt(5, rooms);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Reservation> findByHotel(int hotelId) throws SQLException
    {
        List<Reservation> list = new ArrayList<>();
        String sql = "SELECT * FROM reservations WHERE hotel_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setInt(1, hotelId);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                list.add(new Reservation(
                        rs.getInt("id"),
                        rs.getInt("hotel_id"),
                        rs.getString("guest_name"),
                        rs.getString("check_in"),
                        rs.getString("check_out"),
                        rs.getInt("rooms_reserved")
                ));
            }
        }
        return list;
    }
}
