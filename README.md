# FAST-NUCES Transport Management System
---
This project is a Java-based Transport Management System designed to digitize and automate the daily pick and drop service operations for faculty and students at FAST-NUCES. Built with a strong foundation in Object-Oriented Programming (OOP) principles, this system replaces the previously manual management process, taking on the key responsibilities of a transport manager.

## 🚀 Project Overview

The FAST-NUCES transport service outsources its operations to two third-party providers: **Nadeem Transporter** and **Zulfiqar Transporter**. Each provider manages a fleet of vehicles, a pool of qualified drivers, and a set of defined routes. The system models these entities and their interactions to provide an efficient pick-and-drop service.

The system's core purpose is to:
*   Manage user registrations (students and faculty).
*   Oversee vehicle fleets, driver assignments, and route definitions.
*   Facilitate seat bookings with specific allocation rules.
*   Calculate transportation fares accurately.
*   Ensure data persistence through file handling.
*   Implement robust error management via custom exceptions.

## ✨ Key Features

*   **User Management:**
    *   Supports registration for distinct user roles: **Students** and **Faculty**.
    *   Applies differentiated fare structures based on user type.

*   **Vehicle Fleet Management:**
    *   Manages two types of vehicles: **Buses** (with 52 seats) and **Coasters** (with 32 seats).
    *   Identifies **Air-Conditioned (AC)** vehicles, which incur an additional fare.
    *   Each vehicle is intrinsically linked to a specific **Driver** (composition) and follows a fixed **Route** (aggregation).

*   **Driver Pool Management:**
    *   Stores driver information, including personal details and valid license credentials.
    *   Enforces a rule: each driver can be assigned to only one vehicle at any given time.

*   **Route Definition:**
    *   Defines routes with attributes like start location, end location, and total distance.
    *   Flags routes as **Long Distance** based on a configurable threshold.

*   **Seat Allocation & Booking:**
    *   Seats within vehicles are specifically designated for either **Students** or **Faculty** to prevent inter-role conflicts.
    *   Bookings are exclusive: **one seat per user per month**.
    *   Reservations are confirmed only after successful **payment**.
    *   Each booking meticulously records the associated vehicle, route, user, and the specific seat number.

*   **Fare Calculation:**
    *   Calculates fares dynamically, considering the user type (Student/Faculty) and vehicle amenities (AC/Non-AC).
    *   Applies a standard surcharge of **Rs. 2000** for all AC vehicle bookings.

*   **Data Persistence:**
    *   Leverages **file handling** to save and load critical application data (user profiles, booking records, vehicle states) ensuring data continuity across application sessions.

*   **Exception Handling:**
    *   Implements a comprehensive exception handling strategy to manage common operational issues, such as:
        *   `PaymentRequiredException`: For booking attempts prior to payment completion.
        *   `SeatUnavailableException`: When a desired seat is already occupied.
        *   `RoleViolationException`: For users attempting to book seats not designated for their role.
        *   `EntityNotFoundException`: For requests involving non-existent users, vehicles, or routes.

*   **Generic Data Structures:**
    *   Utilizes **generic data structures** to manage collections of entities (e.g., lists of users, vehicles, bookings), promoting code reusability and maintainability.

## 🧱 OOP Concepts in Action

This system is a practical demonstration of core Object-Oriented Programming principles:

*   **Encapsulation:** Data members and methods within classes are bundled, protecting internal state and controlling access.
*   **Abstraction:** Complex internal workings are hidden, presenting a simplified interface for interactions.
*   **Inheritance:** (Likely used for creating specialized classes like `StudentUser` and `FacultyUser` from a base `User` class, or specific vehicle types inheriting from a common `Vehicle` class).
*   **Polymorphism:** Objects of different types can respond to the same method call in their own specific ways (e.g., `calculateFare()` might behave differently for a student on an AC bus vs. faculty on a non-AC coaster).
*   **Composition:** Models strong "has-a" relationships. Crucially, a `Vehicle` **has** a `Driver`; the driver is integral to the vehicle's existence within the system.
*   **Aggregation:** Represents weaker "has-a" or "uses-a" relationships. `Vehicle`s **are associated with** `Route`s and `Transporter`s, which can exist independently.

## 💻 Technologies Used

*   **Language:** Java
*   **Libraries:** Standard Java Development Kit (JDK) libraries for file I/O, collections, and core functionalities.

## 📁 Data Persistence

To ensure data integrity and continuity, the system implements file persistence. Key data, including user details, booking information, and the current state of vehicles, is regularly saved to and loaded from files. This allows the system state to be preserved even after the application is closed and reopened.

## ⚠️ Error Handling Strategy

The system is designed to be resilient through robust exception handling. It anticipates and manages potential runtime errors using custom-defined exceptions, providing clear feedback for situations such as:
*   Attempts to book a seat without completing the required payment.
*   Requests for seats that are already occupied.
*   Violations of seat designation rules (e.g., student booking a faculty-only seat).
*   Queries for entities that do not exist within the system.

## 🚀 Getting Started

Follow these steps to set up and run the project:

1.  **Clone the Repository:**
    ```bash
    git clone <your-repository-url>
    ```
    Replace `<your-repository-url>` with the actual URL of your GitHub repository.

2.  **Navigate to Project Directory:**
    ```bash
    cd <your-project-directory>
    ```
    Replace `<your-project-directory>` with the name of the cloned repository folder.

3.  **Compile the Java Code:**
    Ensure you have a Java Development Kit (JDK) installed. Compile all `.java` files in the source directory.
    ```bash
    javac src/**/*.java
    ```
    *(Adjust `src/**/*.java` if your source files are organized differently, e.g., `src/*.java` or specify the main source directory).*

4.  **Run the Application:**
    Execute the main class that initializes the transport management system.
    ```bash
    java -cp .:src MainApplication
    ```
    *(Replace `MainApplication` with the actual name of your main class. The `-cp .:src` part might need adjustment based on your classpath setup and operating system - e.g., use `.;src` on Windows).*

---
