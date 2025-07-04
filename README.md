---

```md
# Social Posts API

A Kotlin-based Spring Boot REST API for user registration, login, and creating posts (title and body). It uses JWT for authentication and MongoDB as the database.

---

## 🚀 Features

- ✅ User registration and login
- ✅ JWT-based authentication
- ✅ Authenticated users can create, view, and delete posts
- ✅ MongoDB integration

---

## 📦 Tech Stack

- [Kotlin](https://kotlinlang.org/)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Security](https://spring.io/projects/spring-security)
- [MongoDB](https://www.mongodb.com/)
- [JWT (JSON Web Token)](https://jwt.io/)
- [Gradle](https://gradle.org/)

---

## 📁 Project Structure

```

src/
├── main/
│   ├── kotlin/
│   │   └── com/socialpost/social\_posts/
│   │       ├── controllers/
│   │       ├── database/
│   │       │   ├── model/
│   │       │   └── repository/
│   │       └── security/
│   └── resources/
│       ├── application.properties
│       └── ...
├── test/
└── README.md

````

---

## ⚙️ Configuration

Make sure to set the following environment variables:

```env
MONGODB_CONNECTION_STRING=your_mongo_db_string
JWT_SECRET=your_jwt_secret_key
````

You can set these in your system environment, IntelliJ run config, or as a `.env` file (if you use a plugin to load it).

---

## 🛠️ Running the App

### Gradle (CLI)

```bash
./gradlew bootRun
```

### IntelliJ IDEA

1. Open the project.
2. Right-click on the main class (e.g., `SocialPostsApplication.kt`) and click **Run**.

---

## 🧪 API Endpoints

### 🔐 Auth

#### `POST /auth/register`

Register a new user.

```json
{
  "email": "user@example.com",
  "password": "password123"
}
```

#### `POST /auth/login`

Returns a JWT access token.

```json
{
  "email": "user@example.com",
  "password": "password123"
}
```

Response:

```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

---

### 📝 Posts

> 🔐 All `/posts` routes require a valid JWT token in the `Authorization` header.

#### `POST /posts`

Create a post.

Header:

```
Authorization: Bearer <token>
```

Body:

```json
{
  "title": "My First Post",
  "body": "This is the body of my post."
}
```

#### `GET /posts`

Get all posts of the authenticated user.

Header:

```
Authorization: Bearer <token>
```

#### `DELETE /posts/{id}`

Delete a post if it belongs to the authenticated user.

Header:

```
Authorization: Bearer <token>
```

---

## 🔐 Authentication Notes

* JWT token must be sent in the `Authorization` header as `Bearer <token>`.
* Token is validated by a custom Spring Security filter.

---

## 🧑‍💻 Author

Tirth Patel
Built for Kotlin + Spring Boot REST APIs.

---

## 📄 License

This project is open-source and free to use.
