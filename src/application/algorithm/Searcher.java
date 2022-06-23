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
}
