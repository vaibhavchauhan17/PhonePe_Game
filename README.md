# PhonePe Game

## Overview
The PhonePe Game is a simple battleship game implemented in Java. It allows two players to place ships on their respective battlefields and take turns attacking each other until one player wins by destroying all the opponent's ships.

## Features
- Initialize a game with a customizable battlefield size.
- Add ships of varying sizes to each player's battlefield.
- Display the current state of each player's battlefield.
- Take turns attacking the opponent's battlefield.
- Determine the winner when all ships of a player are destroyed.

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 8 or higher installed on your machine.
- A Java IDE or text editor of your choice (e.g., IntelliJ IDEA, Eclipse, VS Code).

### Installation
1. Clone the repository:
    ```sh
    git clone https://github.com/vaibhavchauhan17/PhonePe_Game.git
    ```
2. Navigate to the project directory:
    ```sh
    cd PhonePe_Game
    ```

### Running the Game
1. Compile the Java files:
    ```sh
    javac PhonePe/*.java
    ```
2. Run the `GameApp` class:
    ```sh
    java PhonePe.GameApp
    ```

## Project Structure
- `GameApp.java`: The main entry point of the game. It initializes the game, adds ships, displays battlefields, and starts the game.
- `GameService.java`: Manages the overall game logic, including initializing the game, adding ships, starting the game, and displaying battlefields.
- `Player.java`: Represents a player in the game. Manages the player's battlefield, ship placement, and attack logic.
- `Ship.java`: Represents a ship in the game. Manages the ship's coordinates, hit detection, and destruction status.

## How to Play
1. Upon running the game, the battlefield is initialized with a specified size (e.g., 8x8).
2. Ships are added to both players' battlefields at specified coordinates.
3. The game starts, and players take turns attacking random coordinates on the opponent's battlefield.
4. The game continues until one player has destroyed all the opponent's ships.
5. The winner is announced at the end of the game.

## Example Output
```plaintext
Game initialized with battlefield size 8x8
Ship SH1 added successfully for both players.
Ship SH2 added successfully for both players.
PlayerA's Battlefield:
A-SH1 . . . . . . .
A-SH1 . . . . . . .
A-SH1 . . . . . . .
. . . . . . . .
. . . . . . . .
. . . . . . . .
. . . . . . . .
. . . . . . . .

PlayerB's Battlefield:
B-SH1 . . . . . . .
B-SH1 . . . . . . .
B-SH1 . . . . . . .
. . . . . . . .
. . . . . . . .
. . . . . . . .
. . . . . . . .
. . . . . . . .

PlayerA fires at (5, 3) - Miss
PlayerB fires at (2, 7) - Miss
...
PlayerA wins the game!
