package PhonePe;

import java.util.*;

public class Ship {
    private String id;
    private int size;
    private List<int[]> coordinates;
    // private Set<String> hits;
    private boolean isHit;

    public Ship(String id, int size, int centerX, int centerY) {
        this.id = id;
        this.size = size;
        this.coordinates = new ArrayList<>();
        // this.hits = new HashSet<>();
        this.isHit = false;
        // Calculate the starting corner of the ship based on the center coordinates
        int halfSize = size / 2;
        int startX = centerX - halfSize;
        int startY = centerY - halfSize;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                coordinates.add(new int[] { startX + i, startY + j });
                System.out.println("Ship " + id + " coordinates: " + (startX + i) + ", " + (startY + j));
            }
        }
    }

    public String getId() {
        return id;
    }

    public List<int[]> getCoordinates() {
        return coordinates;
    }

    // This method marks the entire ship as hit if any part of it is hit
    public boolean isHit(int x, int y) {
        for (int[] coord : coordinates) {
            if (coord[0] == x && coord[1] == y) {
                isHit = true;
                return true;
            }
        }
        return false;
    }

    // This method checks if the ship is destroyed (i.e., hit)
    public boolean isDestroyed() {
        return isHit;
    }
}
