import java.util.Scanner;
import java.io.FileReader;

public class Day3 {
	public static void main(String[] args) {
		int sum = 0;
		try {
			Scanner sc = new Scanner(new FileReader("day3-actual-data.txt"));
			while (sc.hasNextLine()) {
				String bag1 = sc.nextLine();
				String bag2 = sc.nextLine();
				String bag3 = sc.nextLine();

				sum += score(commonInThreeStrings(bag1,bag2,bag3));
			}
		} catch (Exception e) {
			System.out.println("File not found!");
		}
		System.out.println(sum);
	}

	public static char commonInTwoStrings(String s1, String s2) {
		for (char itemIn1 : s1.toCharArray()) {
			for (char itemIn2 : s2.toCharArray()) {
				if (itemIn1==itemIn2) {
					return itemIn1;
				}
			}
		}
		return ' ';
	}

	public static char commonInThreeStrings(String s1, String s2, String s3) {
		for (char itemIn1 : s1.toCharArray()) {
			for (char itemIn2: s2.toCharArray()) {
				for (char itemIn3: s3.toCharArray()) {
					if (itemIn1==itemIn2 && itemIn2==itemIn3) return itemIn1;
				}
			}
		}
		return ' ';
	}

	public static int score(char c) {
		int sum = 0;
		if (Character.isUpperCase(c)) {
			sum += 26;
		}
		sum += (int)(Character.toUpperCase(c)) - 64;
		return sum;
	}
}
