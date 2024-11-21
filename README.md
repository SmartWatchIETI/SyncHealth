# SyncHealth Application

**SyncHealth** is a web application designed to track and manage user health metrics. The system is divided into two parts:
1. A **Spring Boot** back-end for handling APIs, user authentication, and database operations.
2. A **React** front-end for providing a user-friendly interface styled with **Bootstrap**.

---

## Features

### Back-End (Spring Boot)
- User Management:
  - Create, update, delete, and fetch users.
- Authentication:
  - JWT-based secure login system.
- MongoDB Integration:
  - Data persistence for users and metrics.
- API Documentation:
  - Swagger UI enabled for easy API exploration.

### Front-End (React)
- Health Dashboard:
  - Displays health metrics like blood sugar, heart rate, and BMI.
- User Management:
  - Create and list users through dynamic forms.
- Login System:
  - Secure login interface to authenticate users.
- Responsive Design:
  - Styled using **Bootstrap** for better user experience across devices.

---

## Technologies Used

### Back-End
- **Spring Boot**: Core back-end framework.
- **Spring Security**: Secures API endpoints with JWT.
- **MongoDB**: NoSQL database for user and health data storage.
- **Swagger**: API documentation and testing.
- **Maven**: Dependency management.

### Front-End
- **React**: Front-end library for building user interfaces.
- **React Router**: For route-based navigation.
- **Axios**: For HTTP requests to the back-end.
- **Bootstrap**: For responsive and modern UI design.

---

## Prerequisites

### Back-End
- **Java 17** or higher.
- **Maven 3.8+**.
- **MongoDB Atlas** or a local MongoDB instance.
- **IDE** (e.g., IntelliJ IDEA, Eclipse).

### Front-End
- **Node.js** (v16 or higher) and npm.
- **Modern browser** (Chrome, Firefox, etc.).

---

## Installation and Execution

### 1. Clone the Repository
```bash
git clone https://github.com/SmartWatchIETI/SyncHealth.git
cd synchealth
```

---

### 2. Back-End Setup
1. Navigate to the back-end directory:
   ```bash
   cd synchealth-backend
   ```

2. Configure your `application.properties` file located in:
   ```
   src/main/resources/application.properties
   ```
   Example configuration:
   ```properties
   spring.application.name=SyncHealthBack

   # MongoDB Configuration
   spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
   spring.data.mongodb.uri=mongodb+srv://<username>:<password>@<cluster-url>/<database-name>
   spring.data.mongodb.database=SyncHealt

   # JWT Configuration
   jwt.secret=<your-secret-key>
   jwt.expiration=86400000

   # Swagger Configuration
   springdoc.api-docs.enabled=true
   springdoc.swagger-ui.enabled=true
   springdoc.swagger-ui.path=/swagger-ui.html
   ```

3. Run the application using Maven:
   ```bash
   mvn spring-boot:run
   ```

4. The back-end will be available at:
   ```
   http://localhost:8080
   ```

5. Explore API documentation at:
   ```
   http://localhost:8080/swagger-ui.html
   ```

---

### 3. Front-End Setup
1. Navigate to the front-end directory:
   ```bash
   cd synchealth-frontend
   ```

2. Install dependencies:
   ```bash
   npm install
   ```

3. Start the React development server:
   ```bash
   npm start
   ```

4. The front-end will be available at:
   ```
   http://localhost:3000
   ```

## Usage

1. **Access the application**:
   - Open the front-end in your browser: `http://localhost:3000`.

2. **Login**:
   - Use the `/login` route to authenticate a user.

3. **Create and Manage Users**:
   - Use `/create-user` to add new users.
   - Use `/list-users` to view all users.

4. **Health Dashboard**:
   - Access the main dashboard via `/` to view health metrics.

---

## Deployment

### Back-End
1. Package the Spring Boot application:
   ```bash
   mvn clean package
   ```
2. Deploy the `.jar` file to your desired hosting service (e.g., AWS, Heroku, etc.).

### Front-End
1. Build the React application:
   ```bash
   npm run build
   ```
2. Deploy the `build` folder to a hosting service (e.g., Vercel, Netlify, or S3 bucket).

---

## Troubleshooting

1. **CORS Issues**:
   If the front-end cannot connect to the back-end, ensure that CORS is properly configured in `WebConfig.java`:
   ```java
   @Configuration
   public class WebConfig implements WebMvcConfigurer {
       @Override
       public void addCorsMappings(CorsRegistry registry) {
           registry.addMapping("/**").allowedOrigins("http://localhost:3000");
       }
   }
   ```

2. **MongoDB Connection**:
   Verify that your MongoDB cluster is active and your credentials are correct.

3. **JWT Configuration**:
   Ensure that the `jwt.secret` in `application.properties` matches the secret used in your front-end requests.

---

## Contributing

Contributions are welcome! Feel free to submit a pull request or open an issue if you encounter any problems.

---

## License

This project is licensed under the MIT License.
