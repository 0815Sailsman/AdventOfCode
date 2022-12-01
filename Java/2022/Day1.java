//package 2022;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileReader;
import java.util.Collections;
import java.util.Comparator;

public class Day1 {
	public static void main (String[] args) {
		ArrayList<Integer> sums = new ArrayList<>();
		Scanner sc;
		try {
			//sc = new Scanner(new FileReader("day1-example-data.txt"));
			sc = new Scanner(new FileReader("day1-actual-data.txt"));
			String line = "";
			int current = 0;
			while (sc.hasNextLine()) {
				line = sc.nextLine();
				if (line.equals("")) {
					sums.add(current);
					current = 0;
				} else {
					current += Integer.valueOf(line);
				}
			}
		} catch (Exception e) {
			System.out.println("File not found");
		}
		/*
		int largest = 0;
		for (Integer i : sums) {
			if (i > largest) {
				largest = i;
			}
		}
		System.out.println(largest);
		*/
		//Collections.sort(sums);
//		for (Integer i : sums) {
//			System.out.println(i);
//		}
		sums.sort(Comparator.reverseOrder());
//		for (Integer i : sums) {
//			System.out.println(i);
//		}
		int sum = 0;
		sum += sums.get(0);
		sum += sums.get(1);
		sum += sums.get(2);
		System.out.println(sum);
		//System.out.println(sums.get(0));
		//System.out.println(sums.get(1));
		//System.out.println(sums.get(2));
	}
}
