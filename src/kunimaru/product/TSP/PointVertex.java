package kunimaru.product.TSP;

import java.util.*;

public class PointVertex {
	int num;
	double px;
	double py;
	boolean isHighLight;
	ArrayList<Double> dist;

	public PointVertex(int n, double x, double y) {
		// 座標と頂点番号を受け取って初期化.
		num = n;
		px = x;
		py = y;
		isHighLight=false;
		dist=new ArrayList<Double>();
	}
	void calcDist(ArrayList<PointVertex> nodes) {
		for(int i=0;i<nodes.size();i++) {
			PointVertex tmp=nodes.get(i);
			if(tmp.num==num) {
				if(i==dist.size()-1) {
					dist.add(-1.0);
				}else {
					dist.add(i, -1.0);
				}
			}else {
				dist.add(i,Math.sqrt((px-tmp.px)*(px-tmp.px)+(py-tmp.py)*(py-tmp.py))/10.0);
			}
		}
	}
	
}
