import java.util.Scanner;
import java.io.FileReader;


public class Day4 {
	public static void main(String[] args) {
		int sum = 0;
		try {
			Scanner sc = new Scanner(new FileReader("day4-actual-data.txt"));
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				String e1 = line.split(",")[0];
				String e2 = line.split(",")[1];
				//if (containedIn(e1, e2) || containedIn(e2, e1)) {
				if (overlap(e1, e2) || overlap(e2, e1)) {
					//System.out.println(line);
					sum++;
				}
			}
		} catch (Exception e) {
			System.out.println("File not found");
		}
		System.out.println(sum);
	}

	public static boolean containedIn(String e1, String e2) {
		int start1 = Integer.valueOf(e1.split("-")[0]);
		int end1 = Integer.valueOf(e1.split("-")[1]);
		int start2 = Integer.valueOf(e2.split("-")[0]);
		int end2 = Integer.valueOf(e2.split("-")[1]);
		//System.out.println("" + start1 + " " + end1);

		if (start1 <= start2) {
			if (end1 >= end2) {
				return true;
			}
		}
		return false;
	}

	public static boolean overlap(String e1, String e2) {
		int start1 = Integer.valueOf(e1.split("-")[0]);
		int end1 = Integer.valueOf(e1.split("-")[1]);
		int start2 = Integer.valueOf(e2.split("-")[0]);
		int end2 = Integer.valueOf(e2.split("-")[1]);

		if (start1 <= start2 && end1>= start2) {
			return true;
		}
		return false;
	}
}
