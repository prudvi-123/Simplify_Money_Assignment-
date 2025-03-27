This is a Spring Boot + MongoDB based web application that allows users to view and purchase insurance policies. The project follows MVC architecture and integrates Thymeleaf for the frontend.

🚀 Features

📜 View Available Insurance Policies

🛒 Buy Insurance (Success page with receipt download)

📄 Generate and Download Receipt as PDF

🎨 Styled UI for better user experience

🏗️ Tech Stack

Backend: Spring Boot, Spring MVC, Spring Data MongoDB

Frontend: Thymeleaf, HTML, CSS

Database: MongoDB

🔧 Setup & Installation

Prerequisites:

Java 17+ installed

MongoDB running on localhost:27017

Maven installed

insurance-management/ ├── src/main/java/insurance/intern/ │ ├── controller/ (Handles web requests) │ ├── model/ (Insurance entity) │ ├── repository/ (MongoDB integration) │ ├── service/ (Business logic) ├── src/main/resources/ │ ├── static/css/ (Styling files) │ ├── templates/ (Thymeleaf templates) │ ├── application.properties (Configuration) ├── pom.xml (Maven dependencies)
