# Airline-Reservation-System

## Features

- User-friendly interface for booking and managing reservations.
- Seat availability and management updates.
- Payment and Mail System
- Admin panel for managing flights, airports, users, and bookings.
- Email notifications for booking confirmations and updates.

## Functionalities

- **User Registration and Login**: Secure authentication for users and admins.
- **Flight Search**: Search for flights by destination, date, and time.
- **Booking Management**: Book, modify, or cancel reservations.
- **Payment Processing**: Seamless payment integration for ticket purchases.
- **Admin Controls**: Add, update, or remove flights and manage user accounts.

## API Endpoints

### User Endpoints
- `POST /api/register` - Register a new user.
- `POST /api/login` - Authenticate and log in a user.
- `GET /api/user/bookings` - Retrieve all bookings for the logged-in user.

### Flight Endpoints
- `GET /api/flights` - Get a list of available flights.
- `GET /api/flights/:id` - Get details of a specific flight.
- `POST /api/flights` - Add a new flight (Admin only).
- `PUT /api/flights/:id` - Update flight details (Admin only).
- `DELETE /api/flights/:id` - Delete a flight (Admin only).

### Booking Endpoints
- `POST /api/bookings` - Create a new booking.
- `GET /api/bookings/:id` - Get details of a specific booking.
- `PUT /api/bookings/:id` - Update an existing booking.
- `DELETE /api/bookings/:id` - Cancel a booking.

### Airport Endpoints
- `GET /api/airports` - Get a list of all airports.
- `GET /api/airports/:id` - Get details of a specific airport.
- `POST /api/airports` - Add a new airport (Admin only).
- `PUT /api/airports/:id` - Update airport details (Admin only).
- `DELETE /api/airports/:id` - Delete an airport (Admin only).

### Payment Endpoints
- `POST /api/pay` - Process a payment for a booking.

## Technologies Used

- **Frontend**: React.js
- **Backend**: Java, SpringBoot
- **Database**: SQL
- **Authentication**: JSON Web Tokens (JWT)
- **Testing Tool**: JUnit, MockMvc

![air](https://github.com/user-attachments/assets/8b1c10a7-ee09-4a35-8d95-8bb6a431dd31)

## Contributing

Contributions are welcome! Please follow these steps:
1. Fork the repository.
2. Create a new branch:
    ```bash
    git checkout -b feature-name
    ```
3. Commit your changes:
    ```bash
    git commit -m "Add feature-name"
    ```
4. Push to the branch:
    ```bash
    git push origin feature-name
    ```
