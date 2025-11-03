//package com.example.hotel.service;
//
//public class HotelService {
//}
package com.example.hotel.service;

import com.example.hotel.dao.HotelDAO;
import com.example.hotel.model.Hotel;

import java.sql.SQLException;
import java.util.List;

public class HotelService {
    private final HotelDAO hotelDAO = new HotelDAO();

    public List<Hotel> listHotels() throws SQLException {
        return hotelDAO.findAll();
    }
    public Hotel getHotelById(int id)throws SQLException {
        List<Hotel> hotels = hotelDAO.findAll();
        for (Hotel h : hotels) {
            if (h.getId() == id) {
                return h;
            }
        }
        return null;
    }

    public Hotel getHotel(int id) throws SQLException {
        return hotelDAO.findById(id);
    }
}
