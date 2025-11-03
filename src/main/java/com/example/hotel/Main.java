
package com.example.hotel;

import com.example.hotel.model.*;
import com.example.hotel.service.*;
import com.example.hotel.exception.HotelReservationException;
import com.example.hotel.Clr.CT;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class Main {
    public static void main(String[] args)
    {
        HotelService hotelService = new HotelService();
        ReservationService reservationService = new ReservationService();
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println(CT.BLUE+"***************************************************************************** Welcome to the Hotel Reservation System *****************************************************************************"+CT.RESET);

        while (true)
        {
            System.out.println("\n1) List hotels");
            System.out.println("2) Check available rooms");
            System.out.println("3) Make reservation");
            System.out.println("4) View reserved rooms");
            System.out.println("5) Exit");
            System.out.print("Choose the option: ");

            int choice = sc.nextInt();
            sc.nextLine();
            try
            {
                switch (choice)
                {
                    case 1 ->
                    {
                        System.out.println(CT.YELLOW+"                    ***** List of the hotels *****                       "+CT.RESET);
                        List<Hotel> hotels = hotelService.listHotels();

                        System.out.println("+--------------------------------------------------------------------------------+");
                        System.out.printf("| %-4s | %-25s | %-15s | %-10s | %-12s |%n",
                                "ID", "Hotel Name", "City", "Rooms", "Price (₹)");
                        System.out.println("+--------------------------------------------------------------------------------+");

                        for (Hotel hotel : hotels)
                        {
                            System.out.printf("| %-4d | %-25s | %-15s | %-10d | %-12.2f |%n",
                                    hotel.getId(),
                                    hotel.getName(),
                                    hotel.getCity(),
                                    hotel.getTotalRooms(),
                                    hotel.getPricePerNight());
                        }

                        System.out.println("+--------------------------------------------------------------------------------+");

                    }
                    case 2 ->
                    {
                        System.out.println(CT.GREEN+"                    ***** Available Rooms  *****       "+CT.RESET);
                        System.out.print("Hotel id: ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Check-in (yyyy-mm-dd): ");
                        String in = sc.nextLine();
                        System.out.print("Check-out (yyyy-mm-dd): ");
                        String out = sc.nextLine();
                        int available = reservationService.getAvailableRooms(id, in, out);
                        System.out.println("Available rooms: " + available);
                    }

                    case 3 ->
                    {
                        System.out.println(CT.BLUE+"                    ***** Reserve a Room ***** "+CT.RESET);
                        System.out.print(" Enter Hotel id: ");
                        int hotelId = sc.nextInt();
                        sc.nextLine();

                        Hotel selectedHotel = hotelService.getHotelById(hotelId);
                        if (selectedHotel == null)
                        {
                            System.out.println("Invalid Hotel ID....!");
                            break;
                        }

                        System.out.print("Enter Guest name: ");
                        String guestName = sc.nextLine();

                        System.out.print("Check-in (yyyy-mm-dd): ");
                        String checkIn = sc.nextLine();

                        System.out.print("Check-out (yyyy-mm-dd): ");
                        String checkOut = sc.nextLine();

                        System.out.print("Enter How many Rooms You want to Reserve: ");
                        int rooms = sc.nextInt();

                        double pricePerNight = selectedHotel.getPricePerNight();
                        LocalDate inDate = LocalDate.parse(checkIn);
                        LocalDate outDate = LocalDate.parse(checkOut);
                        long nights = ChronoUnit.DAYS.between(inDate, outDate);
                        double totalBill = pricePerNight * rooms * nights;
                        System.out.println();
                        System.out.println(CT.PURPLE+"        ***** Stay Cost Details ***** "+CT.RESET);
                        System.out.println("\n+---------------------------------------------------+");
                        System.out.printf("| %-25s | %-20s |%n", "Hotel", selectedHotel.getName());
                        System.out.printf("| %-25s | %-20s |%n", "Guest", guestName);
                        System.out.printf("| %-25s | %-20d |%n", "Rooms Reserved", rooms);
                        System.out.printf("| %-25s | %-20d |%n", "Nights Stayed", nights);
                        System.out.printf("| %-25s | %-20.2f |%n", "Price per Night (₹)", pricePerNight);
                        System.out.printf("| %-25s | %-20.2f |%n", "Total Bill (₹)", totalBill);
                        System.out.println("+---------------------------------------------------+");

                        System.out.print("\nConfirm booking (yes/no): ");
                        String confirm = sc.next();

                        if (confirm.equalsIgnoreCase("yes"))
                        {
                            reservationService.reserveRoom(hotelId, guestName, checkIn, checkOut, rooms);
                            System.out.println(CT.GREEN+"Reservation successful..........!"+CT.RESET);
                        } else
                        {
                            System.out.println(CT.RED+"Booking cancelled.....!"+CT.RESET);
                        }
                    }

                    case 4 ->
                    {
                        System.out.println(CT.PURPLE+"                    ----- View reserved rooms ----- "+CT.RESET);
                        System.out.print("Hotel id: ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        List<Reservation> reservations = reservationService.getReservationsByHotel(id);
                        reservations.forEach(System.out::println);
                    }
                    case 5 ->
                    {
                        System.out.println(CT.CYAN+"**************************************************************************** ThankYou For Using Hotel Reservation System!!! **************************************************************************** "+CT.RESET);
                        System.exit(0);
                    }
                    default -> System.out.println("Invalid choice");
                }
            } catch (HotelReservationException e)
            {
                System.err.println("Reservation error: " + e.getMessage());
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
