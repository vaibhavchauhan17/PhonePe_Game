package PhonePe;

import java.util.*;

public class Ship {
    private String id;
    private int size;
    private List<int[]> coordinates;
    private Set<String> hits;

    public Ship(String id, int size, int x, int y) {
        this.id = id;
        this.size = size;
        this.coordinates = new ArrayList<>();
        this.hits = new HashSet<>();
        for (int i = 0; i < size; i++) {
            coordinates.add(new int[] { x + i, y }); // Horizontal placement
        }
    }

    public String getId() {
        return id;
    }

    public List<int[]> getCoordinates() {
        return coordinates;
    }

    public boolean isHit(int x, int y) {
        for (int[] coord : coordinates) {
            if (coord[0] == x && coord[1] == y) {
                hits.add(x + "," + y);
                return true;
            }
        }
        return false;
    }

    public boolean isDestroyed() {
        for (int[] coord : coordinates) {
            if (!hits.contains(coord[0] + "," + coord[1])) {
                return false;
            }
        }
        return true;
    }
}
