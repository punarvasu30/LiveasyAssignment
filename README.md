# LiveasyAssignment
# Load and Booking Management System 

## Features
- **Load Management**: Create, read, update, and delete load entries
- **Booking Management**: Handle the booking process between shippers and transporters
- **Status Tracking**: Track load and booking statuses throughout their lifecycle
- **Validation**: Input validation for all API endpoints
- **Error Handling**: Comprehensive exception handling with user-friendly error messages
- **API Documentation**: Swagger/OpenAPI integration for API documentation

## Tech Stack
- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL 
- Lombok
- Validation API
- Swagger/OpenAPI

## Project Structure
The application follows a standard layered architecture:
- **Controller**: REST endpoints for handling HTTP requests
- **Service**: Business logic implementation
- **Repository**: Data access layer
- **Model**: Entity classes representing database tables
- **DTO**: Data Transfer Objects for validation and request/response handling
- **Exception**: Custom exceptions and global exception handling

## API Endpoints

### Load API
- `POST /load` - Create a new load
- `GET /load` - Get all loads (with optional filtering by shipperId and truckType)
- `GET /load/{loadId}` - Get a specific load by ID
- `PUT /load/{loadId}` - Update a load
- `DELETE /load/{loadId}` - Delete a load

### Booking API
- `POST /booking` - Create a new booking
- `GET /booking` - Get all bookings (with optional filtering by shipperId and transporterId)
- `GET /booking/{bookingId}` - Get a specific booking by ID
- `PUT /booking/{bookingId}` - Update a booking
- `DELETE /booking/{bookingId}` - Delete a booking

## Load Lifecycle
Loads can have the following statuses:
- `POSTED`: Initial state when a load is created
- `BOOKED`: When a booking for the load is accepted
- `CANCELLED`: When a load is no longer available for booking

## Booking Lifecycle
Bookings can have the following statuses:
- `PENDING`: Initial state when a booking is created
- `ACCEPTED`: When the booking is approved by the shipper
- `REJECTED`: When the booking is rejected by the shipper


## API Documentation
Once the application is running, you can access the Swagger UI at:
```
http://localhost:9090/swagger-ui.html
```

## Error Handling
The application includes a global exception handler that provides consistent error responses:
- Resource not found (404)
- Invalid request (400)
- Validation errors (400)
- Unexpected errors (500)

Error responses follow a standard format:
```json
{
  "status": "NOT_FOUND",
  "timestamp": "05-04-2025 10:30:45",
  "message": "Load not found with id: '12345'",
  "debugMessage": "Optional debug information",
  "errors": ["Optional list of specific errors"]
}
```
