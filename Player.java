package PhonePe;

import java.util.*;

public class Player {
    private String name;
    private String[][] battlefield;
    private Map<String, Ship> ships;

    public Player(String name, int size) {
        this.name = name;
        this.battlefield = new String[size][size];
        this.ships = new HashMap<>();
        for (int i = 0; i < size; i++) {
            Arrays.fill(battlefield[i], ".");
        }
    }

    public String getName() {
        return name;
    }

    public void placeShip(String shipId, int size, int x, int y) throws IllegalArgumentException {
        Ship ship = new Ship(shipId, size, x, y);
        for (int[] coord : ship.getCoordinates()) {
            if (coord[0] >= battlefield.length || coord[1] >= battlefield[0].length) {
                throw new IllegalArgumentException("Ship " + shipId + " placement is out of bounds.");
            }
            if (!battlefield[coord[0]][coord[1]].equals(".")) {
                throw new IllegalArgumentException("Ship " + shipId + " placement overlaps with another ship.");
            }
        }
        for (int[] coord : ship.getCoordinates()) {
            battlefield[coord[0]][coord[1]] = shipId;
        }
        ships.put(shipId, ship);
    }

    public String attack(int x, int y) {
        if (x < 0 || x >= battlefield.length || y < 0 || y >= battlefield[0].length) {
            return "Miss";
        }
        String cell = battlefield[x][y];
        if (!cell.equals(".") && ships.containsKey(cell)) {
            Ship ship = ships.get(cell);
            if (ship.isHit(x, y)) {
                if (ship.isDestroyed()) {
                    return "Hit and destroyed " + cell;
                }
                return "Hit";
            }
        }
        return "Miss";
    }

    public boolean hasShipsRemaining() {
        for (Ship ship : ships.values()) {
            if (!ship.isDestroyed()) {
                return true;
            }
        }
        return false;
    }

    public void displayBattlefield() {
        for (String[] row : battlefield) {
            System.out.println(String.join(" ", row));
        }
    }
}
