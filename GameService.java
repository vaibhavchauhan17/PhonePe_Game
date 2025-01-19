package PhonePe;

import java.util.Random;

public class GameService {
    private Player playerA;
    private Player playerB;
    private int battlefieldSize;

    public void initGame(int size) {
        this.battlefieldSize = size;
        this.playerA = new Player("PlayerA", size);
        this.playerB = new Player("PlayerB", size);
        System.out.println("Game initialized with battlefield size " + size + "x" + size);
    }

    public void addShip(String shipId, int size, int xPlayerA, int yPlayerA, int xPlayerB, int yPlayerB) {
        try {
            playerA.placeShip("A-" + shipId, size, xPlayerA, yPlayerA);
            playerB.placeShip("B-" + shipId, size, xPlayerB, yPlayerB);
            System.out.println("Ship " + shipId + " added successfully for both players.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error adding ship: " + e.getMessage());
        }
    }

    public void startGame() {
        Random random = new Random();
        int turn = 0;
        while (playerA.hasShipsRemaining() && playerB.hasShipsRemaining()) {
            Player currentPlayer = (turn % 2 == 0) ? playerA : playerB;
            Player opponent = (currentPlayer == playerA) ? playerB : playerA;

            int x = random.nextInt(battlefieldSize);
            int y = random.nextInt(battlefieldSize);
            String result = opponent.attack(x, y);
            System.out.println(currentPlayer.getName() + " fires at (" + x + ", " + y + ") - " + result);
            turn++;
        }

        String winner = playerA.hasShipsRemaining() ? "PlayerA" : "PlayerB";
        System.out.println(winner + " wins the game!");
    }

    public void displayBattlefields() {
        System.out.println("PlayerA's Battlefield:");
        playerA.displayBattlefield();
        System.out.println("\nPlayerB's Battlefield:");
        playerB.displayBattlefield();
    }
}
