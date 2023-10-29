# Used Libraries and Their Versions
  - Spring Boot Starter Parent: 3.1.2
  - Spring Boot Starter Data JPA: Latest (inherited from parent)
  - Spring Boot Starter Web: Latest (inherited from parent)
  - MySQL Connector: Runtime scope
  - Spring Boot Starter Test: Test scope
  - Spring Boot Starter Thymeleaf: Latest (inherited from parent)
  - Spring Boot Devtools: Optional
  - Springdoc OpenAPI Starter WebMVC UI: 2.1.0
  - Spring Boot Starter AOP: Latest (inherited from parent)
  - JUnit: Test scope

# Working Functionalities
  - User Management
    - Registration: Users may register their accounts as stall holders or market organizers.
    - Login: Users can log into the system using their email and password.
    - User authentication: To keep our users safe when booking, organizers need to get approval from the administrator to activate their account.
  - Stall Management
    - Addition: Market organizers can add stalls to their venue.
  - Venue Management
    - Update information: Organizers can edit their venue information. Administrators can edit all venues' information.
  - Booking Management
    - Addition: Stall holders can reserve available stalls.
    - Cancellation: Stall holders can cancel their bookings. 
    - Management: Administrators can manage all bookings.
    - Booking History: Stall holders can review their past bookings. Administrators can review all past bookings.

# Quick Guide on How to Run the Application
  1. Make sure you have MySQL installed and running.
  2. Update the application.properties file with your MySQL credentials.
  3. Make sure you have a schema name 'elec5619' in your localhost.
  4. Run the Spring Boot application 'Elec5619Application.java'.
  5. Open your browser and navigate to our [Home page](http://localhost:8080).
