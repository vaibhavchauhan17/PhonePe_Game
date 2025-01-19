package PhonePe;

import java.util.*;

public class Player {
    private String name;
    private String[][] battlefield;
    private Map<String, Ship> ships;
    private boolean isLeftHalf; // To identify if the player is PlayerA (left half) or PlayerB (right half)

    public Player(String name, int size, boolean isLeftHalf) {
        this.name = name;
        this.battlefield = new String[size][size];
        this.ships = new HashMap<>();
        this.isLeftHalf = isLeftHalf;
        for (int i = 0; i < size; i++) {
            Arrays.fill(battlefield[i], ".");
        }
    }

    @Override
    public String toString() {
        return "Player [name=" + name + ", battlefield=" + Arrays.toString(battlefield) + ", ships=" + ships
                + ", isLeftHalf=" + isLeftHalf + "]";
    }

    public String[][] getBattlefield() {
        return battlefield;
    }

    public String getName() {
        return name;
    }

    public void placeShip(String shipId, int size, int centerX, int centerY) throws IllegalArgumentException {
        System.out.println("Placing ship " + shipId + " for " + name + " at (" + centerX + ", " + centerY + ")");
        Ship ship = new Ship(shipId, size, centerX, centerY);
        for (int[] coord : ship.getCoordinates()) {
            if (coord[0] < 0 || coord[1] < 0 || coord[0] >= battlefield.length || coord[1] >= battlefield[0].length) {
                throw new IllegalArgumentException("Ship " + shipId + " placement is out of bounds.");
            }
            if (!battlefield[coord[0]][coord[1]].equals(".")) {
                throw new IllegalArgumentException("Ship " + shipId + " placement overlaps with another ship.");
            }
            // Validation to ensure the ship is placed in the correct half of the
            // battlefield
            if (isLeftHalf && coord[1] >= battlefield[0].length / 2) {
                throw new IllegalArgumentException("Ship " + shipId
                        + " placement is in the opponent's territory. isLeftHalf: " + isLeftHalf + " coord[1]: "
                        + coord[1] + " battlefield[0].length / 2: " + battlefield[0].length / 2);
            }
            if (!isLeftHalf && coord[1] < battlefield[0].length / 2) {
                throw new IllegalArgumentException("Ship " + shipId
                        + " placement is in the opponent's territory. isLeftHalf: " + isLeftHalf + " coord[1]: "
                        + coord[1] + " battlefield[0].length / 2: " + battlefield[0].length / 2);
            }
        }
        for (int[] coord : ship.getCoordinates()) {
            battlefield[coord[0]][coord[1]] = shipId;
        }
        ships.put(shipId, ship);
    }

    public String[] attack(int x, int y) {
        String[] result = new String[2];
        result[0] = "Miss";
        if (x < 0 || x >= battlefield.length || y < 0 || y >= battlefield[0].length) {
            result[0] = "Miss, Outside arena";
        }
        String cell = battlefield[x][y];
        if (!cell.equals(".") && ships.containsKey(cell)) {
            Ship ship = ships.get(cell);
            if (ship.isHit(x, y)) {
                for (int[] coord : ship.getCoordinates()) {
                    battlefield[coord[0]][coord[1]] = ".";
                }
                ships.remove(cell);
                result[0] = "Hit";
                result[1] = cell;
            }
        }
        return result;
    }

    public boolean hasShipsRemaining() {
        for (Ship ship : ships.values()) {
            if (!ship.isDestroyed()) {
                return true;
            }
        }
        return false;
    }

    public int getShipsRemaining() {
        int count = 0;
        for (Ship ship : ships.values()) {
            if (!ship.isDestroyed()) {
                count++;
            }
        }
        return count;
    }

    public void displayBattlefield() {
        for (String[] row : battlefield) {
            System.out.println(String.join(" ", row));
        }
    }
}
