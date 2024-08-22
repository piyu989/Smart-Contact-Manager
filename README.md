# Smart Contact Manager

**Smart Contact Manager** is a web-based application designed to securely manage and store contacts in the cloud. It provides features like user authentication, encrypted password storage, and the ability to send emails directly from the application. The frontend is developed using **Thymeleaf**, and the backend is powered by **Spring Boot**.

## Table of Contents

- [Features](#features)
- [Tech Stack](#tech-stack)
- [Installation](#installation)
- [Usage](#usage)
- [Screenshots](#screenshots)
- [Contributing](#contributing)
- [License](#license)

## Features

- **User Authentication**: Supports Google and GitHub login for secure access.
- **Contact Management**: Add, edit, delete, and view contacts with ease.
- **Cloud Storage**: Contacts are securely stored in the cloud.
- **Password Encryption**: Ensures secure storage of user passwords and password ecryption.
- **Email Integration**: Send emails directly from the application.
- **Responsive Design**: Fully responsive interface built with Thymeleaf.

## Tech Stack

- **Frontend**: Thymeleaf, HTML, CSS, Bootstrap
- **Backend**: Spring Boot, Spring Security, Hibernate
- **Database**: MySQL
- **Authentication**: OAuth2 (Google and GitHub login)
- **Email**: JavaMail API
- **Build Tools**: Maven

## Installation

### Prerequisites

- **Java 17+**
- **Maven**
- **MySQL** or **PostgreSQL**
- **Google** and **GitHub** OAuth credentials

### Steps

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/smart-contact-manager.git
   cd smart-contact-manager
   ```

2. **Database Setup**
   - Create a new database in MySQL/PostgreSQL.
   - Update `application.properties` with your database credentials.

3. **Configure OAuth2**
   - Obtain OAuth2 credentials from Google and GitHub.
   - Update `application.properties` with your OAuth2 client ID and secret.

4. **Build and Run**
   - Build the project:
     ```bash
     mvn clean install
     ```
   - Run the Spring Boot application:
     ```bash
     mvn spring-boot:run
     ```

5. **Access the Application**
   - Open your browser and navigate to `http://localhost:8080`.

## Usage

- **Register/Login**: Sign up using Google or GitHub.
- **Manage Contacts**: Add, update, or delete your contacts.
- **Send Emails**: Use the integrated email feature to send messages directly from the app.
- **View Contacts**: Browse your contacts with a user-friendly interface.
