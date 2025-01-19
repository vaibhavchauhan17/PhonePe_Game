package PhonePe;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class GameService {
    private Player playerA;
    private Player playerB;
    private int battlefieldSize;
    private Set<String> firedCoordinates;

    public void initGame(int size) {
        this.battlefieldSize = size;
        this.playerA = new Player("PlayerA", size, true); // PlayerA is assigned the left half
        this.playerB = new Player("PlayerB", size, false); // PlayerB is assigned the right half
        this.firedCoordinates = new HashSet<>();
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

            int x, y;
            do {
                x = random.nextInt(battlefieldSize / 2) + (currentPlayer == playerA ? 0 : battlefieldSize / 2);
                y = random.nextInt(battlefieldSize);
            } while (firedCoordinates.contains(x + "," + y));

            firedCoordinates.add(x + "," + y);
            String[] attackResult = opponent.attack(x, y);
            String result = "";
            if (attackResult[0].equals("Hit")) {
                result = "Hit" + opponent.getName() + "'s ship with id " + attackResult[1] + "destroyed.";
            } else {
                result = "Miss";
            }
            String shipsRemaining = " Ships Remaining- PlayerA:" + playerA.getShipsRemaining() + ", PlayerB:"
                    + playerB.getShipsRemaining();

            System.out
                    .println(currentPlayer.getName() + "'s turn: Missile fired at (" + x + ", " + y + ") : " + result
                            + shipsRemaining);
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
