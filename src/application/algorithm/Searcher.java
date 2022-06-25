package application.algorithm;

import application.*;
import java.util.*;

public abstract class Searcher {
    protected final ArrayList<Point> points;
    protected double distMatrix[][];
    protected int route[];

    protected Searcher(ArrayList<Point> points, double distMatrix[][]) {
        this.points = points;
        this.distMatrix = distMatrix;
        route = new int[points.size()];
    }

    public abstract int[] search(int step);

    protected double calcCost(int[] route) {
        double tmp = 0;
        for (int i = 0; i < points.size() - 1; i++) {
            tmp += distMatrix[route[i]][route[i + 1]];
        }
        tmp += distMatrix[route[0]][route[route.length - 1]];
        return tmp;
    }
}
