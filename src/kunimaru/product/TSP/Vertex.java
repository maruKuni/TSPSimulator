package kunimaru.product.TSP;

import java.util.*;

public class Vertex {
	int num;
	double px;
	double py;
	ArrayList<Double> dist;

	public Vertex(int n, double x, double y) {
		// 座標と頂点番号を受け取って初期化.
		num = n;
		px = x;
		py = y;
	}

	void calcCost(ArrayList<Vertex> v) {
		for (int i = 0; i < v.size(); i++) {
			if (dist.size() <= i)
				continue;
		}
	}
}