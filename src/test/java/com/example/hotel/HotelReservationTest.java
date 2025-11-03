
package com.example.hotel;

import com.example.hotel.model.Hotel;
import com.example.hotel.service.HotelService;
import com.example.hotel.service.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class HotelReservationTest {

    private HotelService hotelService;
    private ReservationService reservationService;

    @BeforeEach
    void setup() {
        hotelService = new HotelService();
        reservationService = new ReservationService();
    }

    @Test
    void testListHotels_NotEmpty() throws Exception {
        List<Hotel> hotels = hotelService.listHotels();
        assertNotNull(hotels, "Hotel list should not be null");
        assertTrue(hotels.size() > 0, "Hotel list should contain at least one hotel");
    }

    @Test
    void testGetHotelById_ValidId() throws Exception {
        Hotel hotel = hotelService.getHotelById(1);
        assertNotNull(hotel, "Hotel with ID 1 should exist");
        assertEquals(1, hotel.getId());
    }

    @Test
    void testGetHotelById_InvalidId() throws Exception {
        Hotel hotel = hotelService.getHotelById(999);
        assertNull(hotel, "Hotel with invalid ID should return null");
    }

    @Test
    void testAvailableRooms_WhenNoBookings() throws Exception {
        int available = reservationService.getAvailableRooms(1, "2025-12-01", "2025-12-05");
        assertTrue(available > 0, "Available rooms should be greater than zero when no bookings");
    }

    @Test
    void testReserveRoom_ValidData() {
        assertDoesNotThrow(() -> {
            reservationService.reserveRoom(1, "Alice", "2025-12-10", "2025-12-12", 2);
        }, "Reservation should succeed with valid data");
    }


}
