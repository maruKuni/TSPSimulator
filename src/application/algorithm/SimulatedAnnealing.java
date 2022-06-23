package application.algorithm;

import java.util.*;

import application.Point;

public class SimulatedAnnealing extends Searcher {
    private final double multiplier;
    private final int iterate;
    private int t;
    private double currentTemper;
    private Random rnd;

    public SimulatedAnnealing(ArrayList<Point> points, double[][] distMatrix, double start, double multi,
            int iter) {
        super(points, distMatrix);
        currentTemper = start;
        multiplier = multi;
        iterate = iter;
        t = 1;
        rnd = new Random(System.currentTimeMillis());

        initializeRoute();
    }

    @Override
    public int[] search(int step) {

        for (int i = 0; i < step; i++) {
            //searching
            int[] tmpRoute = generateNeighbor();
            if (accept(tmpRoute)) {
                route = tmpRoute;
            }
            t++;
            if (t % iterate == 0) {
                currentTemper *= multiplier;
            }
        }
        return route;
    }

    private void initializeRoute() {
        boolean isVisit[] = new boolean[points.size()];
        for (int i = 0; i < isVisit.length; i++)
            isVisit[i] = false;

        isVisit[0] = true;
        route[0] = 0;

        for (int i = 0; i < points.size() - 1; i++) {
            double tmpMin = Double.MAX_VALUE;
            int tmp = -1;
            for (int j = 0; j < points.size(); j++) {
                if (i == j || isVisit[j]) {
                    continue;
                }
                if (distMatrix[i][j] < tmpMin) {
                    tmpMin = distMatrix[i][j];
                    tmp = j;
                }
            }
            assert tmp >= 0;
            route[i + 1] = tmp;
        }
        for (int i = 0; i < points.size(); i++) {
            if (!isVisit[i]) {
                route[route.length - 1] = i;
                break;
            }
        }
    }

    private double calcCost(int[] route) {
        double tmp = 0;
        for (int i = 0; i < points.size() - 1; i++) {
            tmp += distMatrix[route[i]][route[i + 1]];
        }
        tmp += distMatrix[route[0]][route[route.length - 1]];
        return tmp;
    }

    private double calcAcceptanceProb(int[] route) {
        double cost = calcCost(route);
        double currentCost = calcCost(this.route);
        if (cost < currentCost)
            return 1.0;
        return Math.exp(-(cost - currentCost) / currentTemper);
    }

    private boolean accept(int[] route) {
        return (rnd.nextDouble() < calcAcceptanceProb(route)) ? true : false;
    }

    private int[] generateNeighbor() {
        int start = rnd.nextInt(route.length - 2);
        int end = rnd.nextInt(route.length - start - 2) + start + 2;
        return generateLambdaneighbor(start, end);
    }

    private int[] generateLambdaneighbor(int start, int end) {
        int tmp[] = new int[this.route.length];
        for (int i = 0; i < tmp.length; i++) {
            if (i <= start || i > end) {
                tmp[i] = this.route[i];
            } else {
                tmp[i] = this.route[end - (i - start - 1)];
            }
        }
        return tmp;
    }
}