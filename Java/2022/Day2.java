package aoc;

import java.io.FileReader;
import java.util.Scanner;

public class Day1 {
public static void main(String[] args) {
	try {
		Scanner sc = new Scanner(new FileReader("data.txt"));
		int points = 0;
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			String they = line.substring(0, 1);
			String you = line.substring(2);
			System.out.println(they + you);
			switch (they) {
			case "A"://Rock 1
				switch (you) {
				case "X"://Lose
					points += 3;
					break;
				case "Y"://Paper
					points += 4;
					break;
				case "Z"://Scissors
					points += 8;
					break;
				default:
					break;
				}
				break;
			case "B"://Paper 2
				switch (you) {
				case "X":
					points += 1;
					break;
				case "Y":
					points += 5;
					break;
				case "Z":
					points += 9;
					break;
				default:
					break;
				}
				break;
			case "C"://Scissors 3
				switch (you) {
				case "X":
					points += 2;
					break;
				case "Y":
					points += 6;
					break;
				case "Z":
					points += 7;
					break;
				default:
					break;
				}
				break;
			default:
				break;
			}
		}
		System.out.println(points);
	} catch (Exception e) {
		System.out.println("File not found");
	}
}
}
