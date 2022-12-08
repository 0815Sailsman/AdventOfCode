import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileReader;

public class Day8 {

	public static void main(String[] args) {
		int[][] data = readData();
		int[][] scores = score(data);
		//System.out.println(sumLarger0(scores));
		System.out.println(findLargest(scores));
	}

	public static int findLargest(int[][] scores) {
		int max = 0;
		for (int[] i : scores) {
			for (int j : i) {
				if (j > max) max = j;
			}
		}
		return max;
	}

	public static int sumLarger0(int[][] scores) {
		int count = 0;
		for (int i=0;i<scores.length;i++) {
			for (int j=0;j<scores[i].length;j++) {
				System.out.print(scores[i][j] + " ");
				if (scores[i][j] > 0) count++; 
			}
			System.out.println();
		}
		return count;
	}

	public static int[][] score(int[][] data) {
		int[][] scores= new int[data.length][data[0].length];
		for (int i=0;i<data.length;i++) {
			for (int j=0;j<data[i].length;j++) {
				scores[i][j] = scoreSingle(data, i, j);
			}
		}
		return scores;
	}

	public static int scoreSingle(int[][] data, int y, int x) {
		int total = 1;
		int score = 0;
		boolean flag = true;
		// top
		/*
		if (y == 0) {
			score++;
		} else {
			for (int i=0;i<y;i++) {
				if (data[i][x] >= data[y][x]) {
					flag = false;
					break;
				}
			}
			if (flag) {
				score++;
			}
		}
		flag = true;*/
		int i=y+1;
		while (i <= (data.length-1) && data[i][x]<=data[y][x]) {
			
			if (data[i][x] == data[y][x]) break;
			score++;
			if (i == data.length-1) score--;
			i++;
		}
		score++;
		if (y == data.length - 1) {
			total *= 0;
		} else {
			total *= score;
		}
		score = 0;
		// Bottom
		/*
		if (y == (data.length -1)) {
			score++;
		} else {
			for (int i=data.length-1;i>y;i--) {
				if (data[i][x] >= data[y][x]) {
					flag = false;
					break;
				}
			}
			if (flag) {
				score++;
			}
		}
		flag = true;*/
		i=y-1;
		while (i >= 0 && data[i][x] <=data[y][x]) {
			
			if (data[i][x] == data[y][x]) break;
			score++;
			
			if (i == 0) score--;
			i--;
		}
		score++;
		//System.out.println("topscore"+score);
		if (y == 0) {
			total *= 0;
		} else {
			total *= score;
		}
		score = 0;
		// Left
		/*
		if (x == 0) {
			score++;
		} else {
			for (int i=0;i<x;i++) {
				if (data[y][i] >= data[y][x]) {
					flag = false;
					break;
				}
			}
			if (flag) {
				score++;
			}
		}
		flag = true;*/
		i = x - 1;
		while (i>= 0 && data[y][i] <= data[y][x]) {
			
			if (data[y][i] == data[y][x]) break;
			score++;
			
			if (i == 0) score--;
			i--;
		}
		score++;
		if (x == 0) {
			total *= 0;
		} else {
			total *= score;
		}
		score = 0;
		// Right
		/*
		if (x == (data[y].length - 1)) {
			score++;
		} else {
			for (int i=data[y].length-1;i>x;i--) {
				if (data[y][i] >= data[y][x]) {
					flag = false;
					break;
				}
			}
			if (flag) {
				score++;
			}
		}
		*/
		i = x + 1;
		while (i <= data.length-1 && data[y][i] <= data[y][x]) {
			
			if (data[y][i] == data[y][x]) break;
			score++;
			
			if (i == data.length -1) score--;
			i++;
		}
		score++;
		if (x == data.length -1) {
			total *= 0;
		} else {
			total *= score;
		}
		return total;
	}

	public static int[][] readData() {
		//int [][] data = new int[5][5];
		int [][] data = new int[99][99];

		try {
			//Scanner sc = new Scanner(new FileReader("day8-example-data.txt"));
			Scanner sc = new Scanner(new FileReader("day8-actual-data.txt"));
			int lc = 0;
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				for (int i=0;i<line.length();i++) {
					data[lc][i] = (int) line.charAt(i);
				}
				lc++;
			}
		} catch(Exception e) {
			System.out.println("file not foun");
		}
		return data;
	}

}
