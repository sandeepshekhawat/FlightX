package org.sandeep.dao;

import org.sandeep.models.Flight;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FlightDAO {
    public void addFlight(Flight flight) {
        try (Connection connection = DBConnection.getConnection()) {
            String query = "INSERT INTO Flights (flight_name, flight_number, date, seats_available) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, flight.getFlightName());
            statement.setString(2, flight.getFlightNumber());
            statement.setString(3, flight.getDate());
            statement.setInt(4, 60);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Flight> searchFlights(String keyword) {
        List<Flight> flights = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection()) {
            String query = "SELECT * FROM Flights WHERE flight_name LIKE ? OR flight_number LIKE ? OR date LIKE ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "%" + keyword + "%");
            statement.setString(2, "%" + keyword + "%");
            statement.setString(3, "%" + keyword + "%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Flight flight = new Flight();
                flight.setId(rs.getInt("id"));
                flight.setFlightName(rs.getString("flight_name"));
                flight.setFlightNumber(rs.getString("flight_number"));
                flight.setDate(rs.getString("date"));
                flight.setSeatsAvailable(rs.getInt("seats_available"));
                flights.add(flight);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flights;
    }

    public Flight bookFlight(int flightNumber, int userId, int seatCount) {
        try(Connection connection = DBConnection.getConnection()){
            String flightDetails = "SELECT * FROM Flights WHERE id = ? AND seats_available > ?";
            String updateBooking = "INSERT INTO Bookings (flight_id, user_id, date, seat_count) VALUES (?, ?, ?, ?)";
            String updateSeats = "UPDATE Flights SET seats_available = seats_available - ? WHERE id = ?";

            PreparedStatement flightStatement = connection.prepareStatement(flightDetails);
            PreparedStatement updateBookingStatement = connection.prepareStatement(updateBooking);
            PreparedStatement updateSeatsStatement = connection.prepareStatement(updateSeats);

            flightStatement.setInt(1, flightNumber);
            flightStatement.setInt(2, seatCount);
            ResultSet rs = flightStatement.executeQuery();
            if (!rs.next()) {
                return null;  // flight not found
            }
            Flight flight = new Flight(
                    rs.getString("flight_name"),
                    rs.getString("flight_number"),
                    rs.getString("date"),
                    rs.getInt("seats_available")
            );

            updateSeatsStatement.setInt(1, seatCount);
            updateSeatsStatement.setInt(2, flightNumber);
            int isSeatsUpdated = updateSeatsStatement.executeUpdate();
            if (isSeatsUpdated<=0){
                return null;
            }

            // INSERT into booking if successful.
            updateBookingStatement.setInt(1, flightNumber);
            updateBookingStatement.setInt(2, userId);
            updateBookingStatement.setString(3, flight.getDate());
            updateBookingStatement.setInt(4, seatCount);
            int isBookingUpdated = updateBookingStatement.executeUpdate();
            if (isBookingUpdated<=0){return null;}

            return flight;

        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}