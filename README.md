# 📝 Blogging Platform

A full-stack, secure blogging web application built with **Java**, **Spring Boot**, **Spring Security**, **Spring Data JPA**, **Thymeleaf**, and **MySQL**. Containerized with **Docker** and deployed live on **Render**.

---

## 🚀 Live Demo

- **Repository:** [GitHub - Krish-Mohan-Verma/blogging-platform](https://github.com/Krish-Mohan-Verma/blogging-platform)
- **Deployment Platform:** [Render](https://render.com)
- **Live Website:** [Render Live App](https://blogging-platform-i9yq.onrender.com)

---

## ✨ Features

- 🔐 **Authentication & Security:** User registration and login powered by Spring Security with BCrypt password hashing.
- ✍️ **Blog Creation:** Authenticated users can write and publish blog posts linked to their profile.
- 📖 **Public Feed:** Explore all blogs published by the community.
- 🔍 **Detailed Post View:** Read individual blog posts in full with author attribution.
- 📊 **User Dashboard:** Dedicated dashboard for logged-in users with quick navigation.
- 🐳 **Dockerized & Cloud-Ready:** Multi-stage Docker build optimized for production hosting on Render.

---

## 🛠️ Tech Stack

| Layer                | Technology                                       |
| -------------------- | ------------------------------------------------ |
| **Backend**          | Java 17, Spring Boot, Spring Data JPA, Hibernate |
| **Security**         | Spring Security, BCrypt                          |
| **Frontend / View**  | Thymeleaf, HTML5                                 |
| **Database**         | MySQL                                            |
| **Containerization** | Docker (Multi-stage build)                       |
| **Build Tool**       | Apache Maven                                     |
| **Hosting & CI/CD**  | Render                                           |

---

## 📂 Project Structure

```text
blogging/
├── Dockerfile                        # Multi-stage Docker build for Render deployment
├── pom.xml                           # Maven dependencies & build configuration
├── mvnw / mvnw.cmd                   # Maven wrapper scripts
└── src/
    ├── main/
    │   ├── java/com/anon/blogging/
    │   │   ├── BloggingApplication.java       # Spring Boot main entry point
    │   │   ├── Controllers/
    │   │   │   ├── LoginController.java       # Home, auth (login/register), dashboard routes
    │   │   │   └── PostController.java        # Blog feed, post view, create post routes
    │   │   ├── Entity/
    │   │   │   ├── User.java                  # User JPA entity (One-to-Many with Post)
    │   │   │   └── Post.java                  # Post JPA entity (Many-to-One with User)
    │   │   ├── Repository/
    │   │   │   ├── UserRepository.java        # Spring Data JPA repository for Users
    │   │   │   └── PostRepository.java        # Spring Data JPA repository for Posts
    │   │   ├── Security/
    │   │   │   ├── CustomUserDetailsService.java # UserDetailsService implementation
    │   │   │   └── UserSecurity.java          # Security filter chain & password encoder
    │   │   └── Service/
    │   │       ├── UserService.java           # User service interface
    │   │       ├── UserServiceImpl.java       # User service implementation
    │   │       └── PostService.java           # Post service business logic
    │   └── resources/
    │       ├── application.properties         # App & datasource configurations
    │       └── templates/                     # Thymeleaf server-side templates
    │           ├── index.html                 # Landing page
    │           ├── login.html                 # Login page
    │           ├── register.html              # Registration page
    │           ├── dashboard.html             # Authenticated user dashboard
    │           ├── posts.html                 # All posts feed
    │           ├── post-view.html             # Single post full view
    │           └── create-post.html           # New post creation form
    └── test/
        └── java/com/anon/blogging/            # Unit and integration tests
```

---

## ⚙️ Environment Variables

The application is configured to read database connection details and server settings from environment variables:

| Variable               | Description             | Example / Default            |
| ---------------------- | ----------------------- | ---------------------------- |
| `PORT`                 | Application server port | `8080`                       |
| `MYSQL_ADDON_HOST`     | MySQL database host     | `localhost` or cloud DB host |
| `MYSQL_ADDON_PORT`     | MySQL database port     | `3306`                       |
| `MYSQL_ADDON_DB`       | Database name           | `blogging`                   |
| `MYSQL_ADDON_USER`     | MySQL database username | `root`                       |
| `MYSQL_ADDON_PASSWORD` | MySQL database password | `your_password`              |

---

## 🚦 Endpoints & Access Control

| Endpoint        | Method        | Access        | Description                           |
| --------------- | ------------- | ------------- | ------------------------------------- |
| `/`             | `GET`         | Public        | Landing / Welcome page                |
| `/register`     | `GET`, `POST` | Public        | User registration page & submission   |
| `/login`        | `GET`, `POST` | Public        | User authentication page & submission |
| `/dashboard`    | `GET`         | Authenticated | User dashboard                        |
| `/posts`        | `GET`         | Public        | Browse all published blog posts       |
| `/posts/{id}`   | `GET`         | Public        | View a specific blog post by ID       |
| `/posts/new`    | `GET`         | Authenticated | Form to compose a new blog post       |
| `/posts/create` | `POST`        | Authenticated | Submit new blog post                  |
| `/logout`       | `POST`        | Authenticated | Log out current user session          |

---

## 💻 Local Development Setup

### Prerequisites

- **Java JDK 17+** installed
- **MySQL** instance running locally or accessible remotely
- **Git**

### 1. Clone the repository

```bash
git clone https://github.com/Krish-Mohan-Verma/blogging-platform.git
cd blogging-platform
```

### 2. Set up the Database

Create a MySQL database:

```sql
CREATE DATABASE blogging;
```

### 3. Configure Environment Variables

Set environment variables in your terminal or configure your IDE run profile:

**Linux / macOS:**

```bash
export PORT=8080
export MYSQL_ADDON_HOST=localhost
export MYSQL_ADDON_PORT=3306
export MYSQL_ADDON_DB=blogging
export MYSQL_ADDON_USER=root
export MYSQL_ADDON_PASSWORD=your_password
```

**Windows (PowerShell):**

```powershell
$env:PORT="8080"
$env:MYSQL_ADDON_HOST="localhost"
$env:MYSQL_ADDON_PORT="3306"
$env:MYSQL_ADDON_DB="blogging"
$env:MYSQL_ADDON_USER="root"
$env:MYSQL_ADDON_PASSWORD="your_password"
```

### 4. Run the Application

Using Maven wrapper:

```bash
# Linux / macOS
./mvnw spring-boot:run

# Windows
.\mvnw.cmd spring-boot:run
```

Open `http://localhost:8080` in your browser.

---

## 🐳 Running with Docker

You can build and run the Docker image locally:

```bash
# Build Docker image
docker build -t blogging-app .

# Run container
docker run -p 8080:8080 \
  -e PORT=8080 \
  -e MYSQL_ADDON_HOST=host.docker.internal \
  -e MYSQL_ADDON_PORT=3306 \
  -e MYSQL_ADDON_DB=blogging \
  -e MYSQL_ADDON_USER=root \
  -e MYSQL_ADDON_PASSWORD=your_password \
  blogging-app
```

---

## ☁️ Deployment on Render

This project is deployed on **Render** as a **Web Service** utilizing the included multi-stage `Dockerfile`:

1. **Create Web Service:** Connect your GitHub repository to Render and choose **Docker** runtime.
2. **Configure Environment Variables:** Add the required variables (`MYSQL_ADDON_HOST`, `MYSQL_ADDON_PORT`, `MYSQL_ADDON_DB`, `MYSQL_ADDON_USER`, `MYSQL_ADDON_PASSWORD`, `PORT`) in the Render Environment tab.
3. **Deploy:** Render automatically builds the Docker image and spins up the live service.

---

## 👤 Author

**Krish Mohan Verma**

- GitHub: [@Krish-Mohan-Verma](https://github.com/Krish-Mohan-Verma)
- LinkedIn: [Krish Mohan Verma](https://www.linkedin.com/in/krish-mohan-072442344/)
