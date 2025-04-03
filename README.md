# SpringBoot
Presentation App

Overview

The Presentation App is designed to manage student presentations efficiently. It allows admins to assign presentations, students to update their status, and admins to rate and score presentations. The application ensures role-based access control and maintains user activity status.

Features

User Management

Register: Users can register with their details. Duplicate email validation is enforced.

Login: Users can log in using their email and password.

Fetch Details: Active users can retrieve their details.

Admin Controls:

Get all users.

Change user status between active and inactive.



Presentation Management
Assign: Admins can assign presentations to students.

Fetch Presentation:

Retrieve a specific presentation by ID.

Students can get all their assigned presentations.


Update Status: Students can update the status of their presentations.

Score: Admins can assign scores to presentations.


Rating System

Rate: Admins can rate a presentation based on communication, confidence, content, innovation, and user props.

Fetch Ratings:

Retrieve ratings for a specific presentation.

Get an overview of ratings for a student’s presentations.



Database Schema

The database consists of the following entities:
User: Stores user details, roles, and status.

Presentation: Contains information about presentations assigned to students.

Rating: Holds ratings given by admins for student presentations.

Enums:

Role: Defines user roles (ADMIN, STUDENT).

Status: Indicates if a user is ACTIVE or INACTIVE.

PresentationStatus: Tracks the status of a presentation (ASSIGNED, ONGOING, COMPLETED).



Installation

1. Clone the repository:

git clone https://github.com/yourusername/presentation-app.git


2. Navigate to the project directory:

cd presentation-app


3. Install dependencies:
npm install  # or pip install -r requirements.txt (depending on tech stack)


4. Run the application:

npm start  # or python app.py (depending on tech stack)



Usage

Admins can log in, assign presentations, rate them, and manage users.

Students can log in, update their presentation status, and view ratings.


Contribution

Contributions are welcome! Feel free to submit a pull request or open an issue.

License

This project is licensed under the MIT License.


---

Author: Harishkumar Pal
GitHub: HarishkumarPal
