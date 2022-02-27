package Segregation;

import java.util.ArrayList;
import java.util.List;

public class Agent {

    public int x, y;
    public int bound;
    private int[][] neighborMatrix = {{-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}};

    public Agent(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Agent(int x, int y, int bound) {
        this.x = x;
        this.y = y;

        this.bound = bound;


        periodicBoundaryCheck();
    }

    private void periodicBoundaryCheck() {
        if (x < 0) {
            x += bound;
        } else if (x >= bound) {
            x -= bound;
        }

        if (y < 0) {
            y += bound;
        } else if (y >= bound) {
            y -= bound;
        }

    }

    public Agent offset(int offsetX, int offsetY) {
        return new Agent(x + offsetX, y + offsetY, bound);
    }

    public List<Agent> getNeighbors() {
        List<Agent> neighbors = new ArrayList<>();

        for (int iter = 0; iter < 8; iter++) {
            neighbors.add(this.offset(neighborMatrix[iter][0], neighborMatrix[iter][1]));
        }

        return neighbors;
    }

    public String toString() {
        return "(" + x + "," + y + ")";
    }
}