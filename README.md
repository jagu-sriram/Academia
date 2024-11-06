# CollegeERP

**CollegeERP** is a comprehensive Java EE-based application designed to streamline academic administration processes in a college or university. This system includes role-based functionalities for managing faculty, courses, and student registrations. It provides separate features for admins to manage faculty-course mappings, student enrollments, and other academic operations.

## Features

### Admin Functionalities
- **Faculty Management**: Add and manage faculty members.
- **Course Management**: Create and map courses to faculty.
- **Student Management**: Register students and assign courses.
- **Course Registration Control**: Enable or disable student course registrations.

### Faculty Functionalities
- **Course Overview**: View assigned courses and student enrollments.
- **Student Information Access**: Access student details for the courses they manage.

### Student Functionalities
- **Course Registration**: Register for courses within the permitted registration period.
- **Personalized Dashboard**: View registered courses and relevant faculty information.

## Tech Stack

- **Java EE**: Core development framework.
- **JSF (JavaServer Faces)**: Frontend framework for UI components.
- **EJB (Enterprise JavaBeans)**: Business logic and backend processes.
- **JPA (Java Persistence API)**: Data persistence layer with MySQL integration.
- **MySQL**: Database for storing course, faculty, and student data.
- **Maven**: Dependency management and build automation.

## Installation

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/yourusername/CollegeERP.git
2. **Configure Database**:
   - Set up a MySQL database for `CollegeERP` (e.g., create an empty database named `collegeerp`).
   - Update the database connection details in the application's configuration file (e.g., `persistence.xml`).
   - JPA will handle table creation automatically based on entity definitions when the application starts, as long as `hibernate.hbm2ddl.auto` is set to `update` or `create`.
3. **Build the Project**:
   - Navigate to the project directory and build with Maven:
     ```bash
     mvn clean install
     ```

4. **Deploy on Application Server**:
   - Deploy the `CollegeERP` application on a Java EE-compatible server (e.g., JBoss,Glassfish,etc..).

## Usage

- **Admin Login**: Access the Admin dashboard to manage faculty, students, and courses.
- **Faculty Login**: Faculty members can log in to view assigned courses and student information.
- **Student Login**: Students can log in to register for courses and view their academic dashboard.

## Project Structure

- `src/main/java`: Contains the Java source code for all backend components, including beans, services, and entity classes.
- `src/main/resources`: Stores configuration files such as `persistence.xml` for database setup.
- `src/main/webapp`: Includes all web resources, JSF pages, CSS, and JavaScript files.

## For Contribution

1. Fork the repository.
2. Create a new feature branch (`feature/YourFeature`).
3. Commit your changes and open a pull request.
- **Feel free to suggest any changes and modifications**
## License

This project is licensed under the MIT License.

