# Blogging Platform

A full-stack blogging web application built with Spring Boot. Users can register, log in, write blog posts, and browse posts published by everyone.

## Features

- User registration and login with session-based authentication
- Passwords hashed using BCrypt (via Spring Security)
- Create blog posts (title + content)
- View all published blogs
- View a single blog post in full

## Tech Stack

- **Backend:** Java, Spring Boot, Spring Security, Spring Data JPA
- **Templating:** Thymeleaf (server-rendered HTML)
- **Database:** MySQL
- **Build tool:** Maven

## Project Structure

```
src/main/java/com/anon/blogging/
├── Controllers/    # Handles incoming requests (login, register, posts)
├── Entity/         # Database models (User, Post)
├── Repository/     # Spring Data JPA interfaces for DB access
├── Security/       # Spring Security configuration
└── Service/        # Business logic layer
```

## Setup

1. Create a MySQL database:
   ```sql
   CREATE DATABASE blogging;
   ```

2. Set your database credentials as environment variables (or edit `application.properties` directly):
   ```
   DB_USERNAME=your_mysql_username
   DB_PASSWORD=your_mysql_password
   ```

3. Run the app:
   ```bash
   ./mvnw spring-boot:run
   ```

4. Open `http://localhost:9000` in your browser.

## Status

Core features (auth, create post, view posts) are working. Actively adding more functionality.

## Author

Krish Verma — [GitHub](https://github.com/Krish-Mohan-Verma) · [LinkedIn](https://www.linkedin.com/in/krish-mohan-072442344/)
