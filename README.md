# COVID Screening Simulation Game

Java desktop game that simulates primary COVID-19 screening using temperature-based decision making.

## Project Overview
The player takes the role of a screening officer and must decide whether each character should be quarantined or released based on their body temperature.

The game is designed as a short decision-making simulation using Java, object-oriented programming, and a Swing-based graphical user interface.

## How to Play
- Enter the player's name before starting the game.
- The game runs for 30 seconds.
- A character with a randomly generated temperature appears on the screen.
- If the temperature is **37.5°C or higher**, select **Quarantine**.
- If the temperature is **below 37.5°C**, select **Release**.
- A correct decision gives **+1 point**.
- An incorrect decision reduces the remaining time by **3 seconds**.
- When the timer reaches zero, the final score is shown on the Game Over page.

## Main Components

### MainMenu
Provides access to:
- Play
- High Score

### Direction
Collects the player's name and displays the game instructions before starting.

### GamePage
Handles the main gameplay including:
- Player name
- Current score
- Remaining time
- Quarantine and Release decisions
- Pause functionality

### Character
Generates a random temperature between 36.0°C and 39.0°C and determines whether the character should be quarantined.

### GameOverPage
Displays the player's final score and provides options to:
- Try Again
- Return to Home

### HighScorePage
Displays player scores sorted from highest to lowest.

### ScoreManager
Handles score storage and retrieval from the score file.

## Score Management
The game stores player scores and displays them in descending order.

If the same player name appears multiple times, only the highest score is retained.

The High Score page retrieves and displays the sorted scores.

## Tools & Concepts
- Java
- Java Swing
- Object-Oriented Programming
- Event-Driven Programming
- GUI Development
- File Handling
- Class Design

## Key Learning
This project strengthened my understanding of:
- object-oriented programming
- class relationships
- event handling in Java
- GUI development with Swing
- timer-based game logic
- file-based score management
- designing an application across multiple classes
