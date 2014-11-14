package ma.meditel.reporting.util;

public class JSONColor {
	public static final JSONColor BLUE_0 = new JSONColor(0, 0, 102);
	public static final JSONColor BLUE_1 = new JSONColor(0, 0, 153);
	public static final JSONColor BLUE_2 = new JSONColor(0, 0, 255);
	public static final JSONColor BLUE_3 = new JSONColor(51, 51, 255);
	public static final JSONColor BLUE_4 = new JSONColor(0, 128, 255);
	public static final JSONColor BLUE_5 = new JSONColor(102, 178, 255);
	public static final JSONColor BLUE_6 = new JSONColor(153, 204, 255);
	public static final JSONColor BLUE_7 = new JSONColor(204, 229, 255);
	
	
	
	
	public static final JSONColor RED_0 = new JSONColor(153, 0, 76);
	public static final JSONColor RED_1 = new JSONColor(204, 0, 102);
	public static final JSONColor RED_2 = new JSONColor(255, 0, 0);
	public static final JSONColor RED_3 = new JSONColor(255, 0, 127);
	public static final JSONColor RED_4 = new JSONColor(255, 51, 53);
	public static final JSONColor RED_5 = new JSONColor(255, 102, 178);
	public static final JSONColor RED_6 = new JSONColor(255, 153, 204);
	public static final JSONColor RED_7 = new JSONColor(255, 204, 229);

	public static final JSONColor GREEN_0 = new JSONColor(0, 153, 0);
	public static final JSONColor GREEN_1 = new JSONColor(0, 255, 0);
	public static final JSONColor GREEN_2 = new JSONColor(51, 255, 51);
	public static final JSONColor GREEN_3 = new JSONColor(102, 255, 102);
	public static final JSONColor GREEN_4 = new JSONColor(153, 255, 153);
	public static final JSONColor GREEN_5 = new JSONColor(204, 255, 153);
	public static final JSONColor GREEN_6 = new JSONColor(204, 255, 204);
	public static final JSONColor GREEN_7 = new JSONColor(255, 255, 204);

	public static final JSONColor YELLOW_0 = new JSONColor(255, 255, 0);
	public static final JSONColor ORANGE_0 = new JSONColor(255, 140, 0);
	public static final JSONColor PINK_0 = new JSONColor(199, 21, 133);
	
	public static final JSONColor YELLOW_1 = new JSONColor(240, 230, 140);
	public static final JSONColor ORANGE_1 = new JSONColor(255, 215, 0);
	public static final JSONColor PINK_1 = new JSONColor(255, 105, 180);
	
	public static final JSONColor[] JSONColorList_0 = {BLUE_0, RED_0, GREEN_0, YELLOW_0, ORANGE_0, PINK_0};
	public static final JSONColor[] JSONColorList_1 = {BLUE_1, RED_1, GREEN_1, YELLOW_1, ORANGE_1, PINK_1};
	public static final JSONColor[] JSONColorList_2 = {BLUE_2, RED_2, GREEN_2, YELLOW_1, ORANGE_1, PINK_1};
	public static final JSONColor[] JSONColorList_3 = {BLUE_3, RED_3, GREEN_3, YELLOW_1, ORANGE_1, PINK_1};
	public static final JSONColor[] JSONColorList_4 = {BLUE_4, RED_4, GREEN_4, YELLOW_1, ORANGE_1, PINK_1};
	public static final JSONColor[] JSONColorList_5 = {BLUE_5, RED_5, GREEN_5, YELLOW_1, ORANGE_1, PINK_1};
	public static final JSONColor[] JSONColorList_6 = {BLUE_6, RED_6, GREEN_6, YELLOW_1, ORANGE_1, PINK_1};
	public static final JSONColor[] JSONColorList_7 = {BLUE_7, RED_7, GREEN_7, YELLOW_1, ORANGE_1, PINK_1};
	
	public int R, G, B;

	public JSONColor(int r, int g, int b) {
		R = r;
		G = g;
		B = b;
	}

	public int getR() {
		return R;
	}

	public void setR(int r) {
		R = r;
	}

	public int getG() {
		return G;
	}

	public void setG(int g) {
		G = g;
	}

	public int getB() {
		return B;
	}

	public void setB(int b) {
		B = b;
	}
	
	public static JSONColor[] getLisColors(int i){
		switch(i){
		case 0:
			return JSONColorList_0;
		case 1:
			return JSONColorList_1;
		case 2:
			return JSONColorList_2;
		case 3:
			return JSONColorList_3;
		case 4:
			return JSONColorList_4;
		case 5:
			return JSONColorList_5;
		case 6:
			return JSONColorList_6;
		case 7:
			return JSONColorList_7;
		}
		
		return JSONColorList_7;
	}
	
}
