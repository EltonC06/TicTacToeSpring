# Tic-Tac-Toe Spring

(https://github.com/EltonC06/TicTacToeSpring/blob/main/LICENSE)

## About the Project

Tic-Tac-Toe Spring is a Java Spring Boot web application developed for educational purposes.

The main function of the application is to allow users to play a Tic-Tac-Toe game locally on the same computer using HTTP requests. Currently, the app does not include a front-end interface, having only the back-end functionality.

## Technologies Used

### Back End
- Java (Spring Boot)
- H2 Database

## How to Run the Project

### Prerequisites
- IDE for Java (Spring Tool Suite recommended)
- Java 17

### Step-by-Step Guide

1. **Clone the Repository**
   ```bash
   git clone git@github.com:EltonC06/TicTacToeSpring.git
   ```

2. **Open the Project**
   - Open the project in a Java IDE with Spring Boot support (Spring Tool Suite is recomemended).

3. **Set Up the Database**
   - H2 database configuration is included by default. No additional setup is required for local development.

4. **Run the Application**
   - In your IDE, click to run the application.

5. **Testing the Application**
   - Since the application does not have a front-end interface, use a HTTP client like **Postman** to interact with the API endpoints.

## API Endpoints

Here are the main endpoints available for interacting with the application:

### Game

- **Get all running games**
  - **Method:** `GET`
  - **URL:** `localhost:8080/games
  - **Description:** Returns, in entity format, all running games at moment.

- **Get specific game**
  - **Method:** `GET`
  - **URL:** `localhost:8080/games/{id}
  - **Description:** Returns, in DTO format, the game of specified id.

- **Start New Game**
  - **Method:** `POST`
  - **URL:** `localhost:8080/games/play`
  - **Description:** Starts a new Tic-Tac-Toe game.

- **Make a move**
  - **Method:** `PUT`
  - **URL:** localhost:8080/games/play/{position}
  - **Description:** Makes a move and places the piece on the given position. The turn alternate automatically between 'X' and 'O'.

- **Restart game**
  - **Method:** `PUT`
  - **URL:** localhost:8080/games/restart
  - **Description:** As the project currently supports only one Tic-Tac-Toe game at a time, this endpoint will restart the only running game.

### Observations

- The application is designed for local play and does not yet feature a front-end interface.
- The game state and moves are managed entirely through the API (HTTP Requests).

## How You Can Contribute

- Implement a front-end interface for user interaction.
- Enhance the game logic and add additional features, such as a counter of wins, draws and loses.

## Author

Elton da Costa Oliveira

[LinkedIn](https://www.linkedin.com/in/elton-da-costa/)
