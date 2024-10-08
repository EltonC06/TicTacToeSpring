# Tic-Tac-Toe Spring

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://github.com/EltonC06/TicTacToeSpring/blob/main/LICENSE)

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
   - Open the project in a Java IDE with Spring Boot support (Spring Tool Suite is recommended).

3. **Set Up the Database**
   - H2 database configuration is included by default. No additional setup is required for local development.

4. **Run the Application**
   - In your IDE, click to run the application.

5. **Testing the Application**
   - Since the application does not have a front-end interface, use an HTTP client like **Postman** to interact with the API endpoints.

## Project Structure

### Entities

The project contains two main entities:

1. **TicTacToe**: This entity manages the gameplay and logic for each round of the TicTacToe game. It handles the state of the board and determines the winner or if a round ends in a draw. The attributes of the TicTacToe entity include:
   - `id`: Unique identifier of the game.
   - `firstLine`: Represents the current state of the first row of the board.
   - `secondLine`: Represents the current state of the second row of the board.
   - `thirdLine`: Represents the current state of the third row of the board.
   - `isRunning`: Indicates whether the game is currently active.
   - `roundWinner`: Displays the winner of the round or indicates if it ended in a draw.
   - `Match`: A OneToOne relationship with the `Match` entity.

2. **Match**: This entity serves as a counter for all the games (rounds) played. It tracks the total number of victories for each player, draws, and the number of rounds played. The attributes of the `Match` entity include:
   - `id`: Unique identifier of the match.
   - `xVictories`: Number of victories for player X.
   - `oVictories`: Number of victories for player O.
   - `draws`: Number of rounds that ended in a draw.
   - `roundsPlayed`: Total number of rounds played.
   - `ticTacToe`: A OneToOne relationship with the `TicTacToe` entity.

## API Endpoints

Here are the main endpoints available for interacting with the application:

### TicTacToe Endpoints

- **Get Game**
  - **Method:** `GET`
  - **URL:** `localhost:8080/games/1`
  - **Description:** Returns the current Tic-Tac-Toe game.

- **Make Move**
  - **Method:** `PUT`
  - **URL:** `localhost:8080/games/play/{position}`
  - **Description:** Makes a move and places the piece on the given position. The turn alternates automatically between 'X' and 'O'.

- **Restart Game**
  - **Method:** `PUT`
  - **URL:** `localhost:8080/games/restart`
  - **Description:** Resets the current game, allowing you to play again without creating a new match.

### Match Endpoints

- **Get Match**
  - **Method:** `GET`
  - **URL:** `localhost:8080/match/1`
  - **Description:** Retrieves the current match details, including the round statistics and linked TicTacToe game.

- **Create Match**
  - **Method:** `POST`
  - **URL:** `localhost:8080/match/create`
  - **Description:** Creates a new match and initializes a Tic-Tac-Toe game. This should be the first endpoint called to start a series of games.

- **Reset Match**
  - **Method:** `PUT`
  - **URL:** `localhost:8080/match/reset`
  - **Description:** Resets the match, clearing the counters for victories, draws, and rounds, and resets the linked TicTacToe game.

## Observations

- The application is designed for local play and does not yet feature a front-end interface.
- The game state and moves are managed entirely through the API (HTTP Requests).

## How You Can Contribute

- Implement a front-end interface for user interaction.
- Enhance the game logic and add additional features, such as improving the match statistics and tracking multiple games.

## Author

Elton da Costa Oliveira

[LinkedIn](https://www.linkedin.com/in/elton-da-costa/)
