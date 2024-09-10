# Spring Security Client

## Overview
Spring Security Client is a robust Spring Boot application that demonstrates the implementation of user registration, email verification, and authentication using Spring Security. This project serves as a foundation for building secure web applications with user management features.

## Features

User registration with email verification
Secure password storage using BCrypt
Token-based authentication
RESTful API endpoints for user management
Customizable security configurations

## Prerequisites

Java JDK 11 or later
Maven 3.6+ or Gradle 6.8+
An SMTP server for sending verification emails

## Getting Started
### 1. Clone the repository
bashCopygit clone https://github.com/AadarshDontul/Spring-security-client.git
cd Spring-security-client
### 2. Configure application properties
Edit src/main/resources/application.properties to set up your database and email configurations:
propertiesC
*Database Configuration*
spring.datasource.url=jdbc:mysql://localhost:3306/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password

*Email Configuration*
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your_email@gmail.com
spring.mail.password=your_email_password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
### 3. Build the project
bashCopy./mvnw clean install
### 4. Run the application
bashCopy./mvnw spring-boot:run
The application will start running at http://localhost:8080.

## API Endpoints

POST /register - Register a new user
GET /verifyRegistration - Verify user registration
GET /resendVerifyToken - Resend verification token
POST /resetPassword - Request password reset
POST /savePassword - Save new password
GET /changePassword - Change password

## Usage

Register a new user:
bashCopycurl -X POST http://localhost:8080/register -H "Content-Type: application/json" -d '{"firstName":"John","lastName":"Doe","email":"john.doe@example.com","password":"securePassword123"}'

Check your email for the verification link and open it in a browser to verify your account.
You can now use the registered email and password to authenticate and access protected resources.

## Security Considerations

Ensure to use HTTPS in production environments.
Regularly update dependencies to patch any security vulnerabilities.
Implement rate limiting to prevent brute-force attacks.

## Contributing
Contributions are welcome! Please feel free to submit a Pull Request.

## License
This project is licensed under the MIT License - see the LICENSE.md file for details.

## Support
If you encounter any problems or have any questions, please open an issue in the GitHub repository.
