package org.sandeep.services;

import org.sandeep.dao.FlightDAO;
import org.sandeep.models.Flight;

import java.util.List;

public class FlightService {
    private FlightDAO flightDAO = new FlightDAO();

    public void addFlight(Flight flight) {
        flightDAO.addFlight(flight);
    }

    public List<Flight> searchFlights(String keyword) {
        return flightDAO.searchFlights(keyword);
    }

    public Flight bookFlight(int flightNumber, int userId, int seatCount) {
        return flightDAO.bookFlight(flightNumber, userId, seatCount);
    }
}