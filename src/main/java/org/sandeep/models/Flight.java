package org.sandeep.models;

public class Flight {
    private int id;
    private String flightName;
    private String flightNumber;
    private String date;
    private int seatsAvailable;

    public Flight() {
    }

    public Flight( String name, String flightNumber,String date, int seatsAvailable) {
        this.flightNumber = flightNumber;
        this.flightName = name;
        this.date = date;
        this.seatsAvailable = seatsAvailable;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFlightName() {
        return flightName;
    }

    public void setFlightName(String flightName) {
        this.flightName = flightName;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getSeatsAvailable() {
        return seatsAvailable;
    }

    public void setSeatsAvailable(int seatsAvailable) {
        this.seatsAvailable = seatsAvailable;
    }
}
