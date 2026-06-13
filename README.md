# Student Records Management System

A simple Spring Boot REST API application for managing student records. This project demonstrates the use of Spring Boot framework, RESTful web services, and layered architecture (Controller, Service, Repository, Model).

## Course Information

- **Course:** Software Construction Engineering (SCSJ4383)
- **University:** Universiti Teknologi Malaysia (UTM)
- **Author:** Daney

## Technologies Used

- Java 25
- Spring Boot 3.x
- Maven (build tool)
- Postman (API testing)

## Project Structure
studentrecords/

└── src/main/java/com/daney/studentrecords/

├── StudentrecordsApplication.java   (Main entry point)

├── model/

│   └── Student.java                  (Data model)

├── repository/

│   ├── StudentRepository.java        (Interface)

│   └── StudentRepositoryImpl.java    (Implementation with @Repository)

├── service/

│   ├── StudentService.java           (Interface)

│   └── StudentServiceImpl.java       (Business logic with @Service)

└── controller/

└── StudentController.java        (REST endpoints with @RestController)

## Five Functionalities

| # | Functionality | HTTP Method | Endpoint |
|---|---|---|---|
| 1 | Create a new student | POST | `/api/students` |
| 2 | Get all students | GET | `/api/students` |
| 3 | Get a student by ID | GET | `/api/students/{id}` |
| 4 | Update a student | PUT | `/api/students/{id}` |
| 5 | Delete a student | DELETE | `/api/students/{id}` |

## REST API Endpoints Exposed (Required Two)

The following two functionalities are highlighted as REST API endpoints, tested using Postman:

1. **Create Student** — `POST /api/students`
2. **Get All Students** — `GET /api/students`

All five functionalities use REST API conventions and return JSON responses.

## How to Run

### Prerequisites

- JDK 17 or higher (this project uses JDK 25)
- Maven (included via Maven Wrapper, no separate install needed)
- An IDE such as IntelliJ IDEA

### Steps

1. Clone the repository:
   git clone https://github.com/Abdulrahman-Daney/studentrecords.git

2. Open the project in IntelliJ IDEA (File → Open → select the folder)

3. Wait for Maven to download dependencies

4. Run the main class `StudentrecordsApplication.java`

5. The server will start on port 8080: `http://localhost:8080`

## Testing With Postman

### Example: Create a Student (POST)

**URL:** `http://localhost:8080/api/students`
**Method:** POST
**Body (JSON):**
```json
{
    "id": 1,
    "name": "Ali Ahmad",
    "email": "ali@graduate.utm.my",
    "course": "Software Engineering",
    "gpa": 3.85
}
```

**Expected Response:** `201 Created` with the created student in JSON format.

### Example: Get All Students (GET)

**URL:** `http://localhost:8080/api/students`
**Method:** GET
**Expected Response:** `200 OK` with an array of all students in JSON format.

## Business Logic

The Service layer (`StudentServiceImpl`) includes a validation rule: GPA must be between 0 and 4. Invalid values will throw an `IllegalArgumentException`.

## Architecture Notes

This project follows the standard Spring Boot layered architecture:

- **Controller layer** handles HTTP requests and responses
- **Service layer** contains business logic
- **Repository layer** handles data storage (in-memory HashMap)
- **Model** is a plain POJO holding student data

Dependency Injection is achieved using the `@Autowired` annotation, demonstrating Spring's Inversion of Control (IoC) principle.