package org.sandeep;

import org.sandeep.models.Flight;
import org.sandeep.models.User;
import org.sandeep.services.FlightService;
import org.sandeep.services.UserService;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final UserService userService = new UserService();
    private static final FlightService flightService = new FlightService();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("1. User Login");
            System.out.println("2. User Signup");
            System.out.println("3. Admin Login");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (option) {
                case 1:
                    userLogin();
                    break;
                case 2:
                    userSignup();
                    break;
                case 3:
                    adminLogin();
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void userLogin() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (userService.login(username, password)) {
            System.out.println("Login successful!");
            userMenu();
        } else {
            System.out.println("Invalid credentials. Please try again.");
        }
    }

    private static void userSignup() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole("user");

        userService.signup(user);
        System.out.println("Signup successful! Please log in.");
    }

    private static void adminLogin() {
        System.out.print("Enter admin username: ");
        String username = scanner.nextLine();
        System.out.print("Enter admin password: ");
        String password = scanner.nextLine();

        if (userService.adminLogin(username, password)) {
            System.out.println("Admin login successful!");
            adminMenu();
        } else {
            System.out.println("Invalid credentials. Please try again.");
        }
    }

    private static void userMenu() {
        while (true) {
            System.out.println("1. Search Flights");
            System.out.println("2. Book Tickets");
            System.out.println("3. Logout");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (option) {
                case 1:
                    searchFlights();
                    break;
                case 2:
                    bookTickets();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void adminMenu() {
        while (true) {
            System.out.println("1. Add Flight");
            System.out.println("2. View Bookings");
            System.out.println("3. Logout");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (option) {
                case 1:
                    addFlight();
                    break;
                case 2:
                    viewBookings();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void searchFlights() {
        System.out.print("Enter keyword to search for flights: ");
        String keyword = scanner.nextLine();
        List<Flight> flights = flightService.searchFlights(keyword);

        if (flights.isEmpty()) {
            System.out.println("No flights found.");
        } else {
            flights.forEach(flight -> {
                System.out.println("Flight Name: " + flight.getFlightName());
                System.out.println("Flight Number: " + flight.getFlightNumber());
                System.out.println("Date: " + flight.getDate());
                System.out.println("Seats Available: " + flight.getSeatsAvailable());
                System.out.println();
            });
        }
    }

    private static void bookTickets() {
        System.out.print("Enter Flight Number: ");
        int flightNumber = scanner.nextInt();
        System.out.print("Enter number of seats: ");
        int seatCount = scanner.nextInt();
        if (seatCount <= 0) {
            System.out.println("Number of seats booked must be greater than 0. Please try again.");
            return;
        }
        scanner.nextLine();

        Flight booked = flightService.bookFlight(flightNumber, userService.getLoggedInUser().getId(), seatCount);
        if (booked != null) {
            System.out.println("Booked flight successful!");
            System.out.println("Booked Flight Details:");
            System.out.println("Flight Name: " + booked.getFlightName());
            System.out.println("Flight Number: " + booked.getFlightNumber());
            System.out.println("Date: " + booked.getDate());
            System.out.println("Number of seats Booked : " + seatCount);
            System.out.println("Happy Flying !!");
        } else {
            System.out.println("Booking flight failed!");
        }
    }

    private static void addFlight() {
        System.out.print("Enter Flight Name: ");
        String flightName = scanner.nextLine();
        System.out.print("Enter Flight Number: ");
        String flightNumber = scanner.nextLine();
        System.out.print("Enter Date (YYYY-MM-DD): ");
        String date = scanner.nextLine();

        Flight flight = new Flight();
        flight.setFlightName(flightName);
        flight.setFlightNumber(flightNumber);
        flight.setDate(date);
        flight.setSeatsAvailable(60);

        flightService.addFlight(flight);
        System.out.println("Flight added successfully!");
    }

    private static void viewBookings() {
        System.out.println("Bookings viewed successfully!");
    }
}
