# Book Search Engine

## Overview
This project is a **Book Search Engine** built using **Java** and **Spring Boot**. It allows users to search for books based on different criteria. The project uses **PostgreSQL** as the database and is containerized using **Docker**. The build process is managed with **Maven**.

## Features
- Search for books by title, author, or genre.
- PostgreSQL database setup using Docker.
- RESTful API endpoints built with Spring Boot.
- Maven-based build system for project dependencies and management.

## Technologies Used
- **Java** (Spring Boot framework)
- **PostgreSQL** (Dockerized setup)
- **Docker** (for containerization)
- **Maven** (build automation)

## Prerequisites
Ensure you have the following installed on your machine:
- [Docker](https://www.docker.com/get-started)
- [Java 17+](https://adoptium.net/)
- [Maven](https://maven.apache.org/)

## Setup and Installation

### 1. Clone the Repository
```sh
git clone https://github.com/your-username/book-search-engine.git
cd book-search-engine
```

### 2. Start PostgreSQL with Docker
```sh
docker-compose up -d
```
This will start a PostgreSQL container in the background.

### 3. Configure the Application
Modify `application.properties` or `application.yml` to match your PostgreSQL settings if necessary.

### 4. Build and Run the Application
```sh
mvn clean install
mvn spring-boot:run
```

### 5. Access the API
Once the application is running, access the API at:
```
http://localhost:8080/api/books
```

## API Endpoints
| Method | Endpoint         | Description               |
|--------|-----------------|---------------------------|
| GET    | `/api/books`    | Get all books             |
| GET    | `/api/books/{id}` | Get book by ID            |
| POST   | `/api/books`    | Add a new book            |
| PUT    | `/api/books/{id}` | Update an existing book   |
| DELETE | `/api/books/{id}` | Delete a book             |

## Contributing
Feel free to contribute by opening an issue or a pull request.

## License
This project is licensed under the [MIT License](LICENSE).

---

Happy coding! 🚀
