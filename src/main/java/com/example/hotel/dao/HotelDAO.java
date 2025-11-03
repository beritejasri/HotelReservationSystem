
package com.example.hotel.dao;

import com.example.hotel.model.Hotel;
import java.sql.*;
import java.util.*;

public class HotelDAO
{
    public List<Hotel> findAll() throws SQLException
    {
        List<Hotel> hotels = new ArrayList<>();
        String sql = "SELECT * FROM hotels";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql))
        {
            while (rs.next())
            {
                hotels.add(new Hotel(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("city"),
                        rs.getInt("total_rooms"),
                        rs.getDouble("price_per_night")
                ));
            }
        }
        return hotels;
    }

    public Hotel findById(int id) throws SQLException
    {
        String sql = "SELECT * FROM hotels WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                return new Hotel(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("city"),
                        rs.getInt("total_rooms"),
                        rs.getDouble("price_per_night")
                );
            }
        }
        return null;
    }
}

