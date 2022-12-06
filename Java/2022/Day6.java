import java.util.Scanner;
import java.io.FileReader;

public class Day6 {

	public static void main(String[] args) {
		String line = readData();
		System.out.println(line);
		char[] chain = new char[]{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
		for (int i=0;i<line.length();i++) {
			visu(chain, line.charAt(i));
			int pos = 0;
			if ((pos = existsIn(line.charAt(i), chain) )!= -1) {
				System.out.println("reset at " + pos);
				chain = resetChain(chain, pos);
				for (char c : chain) {
					System.out.print(c != ' ' ? c : "_");
				}
				System.out.println();
			}


			chain = append(line.charAt(i), chain);
			if (has4(chain)) {
				System.out.println(i+1);
				System.exit(0);
			}
		}
	}

	public static void visu(char[] chain, char pc) {
		for (char c : chain) {
			System.out.print(c + ",");
		}
		System.out.println(" <- " + pc);
	}

	public static char[] append(char c, char[] chain) {
		for (int i=0;i<chain.length;i++) {
			if (chain[i] == ' ') {
				chain[i] = c;
				return chain;
			}
		}
		return chain;
	}

	public static boolean has4(char[] c) {
		for (char i : c) {
			if (i == ' ') return false;
		}
		return true;
	}

	public static char[] resetChain(char[] chain, int pos) {
		for (int i=0;i<pos;i++) {
			char[] temp = new char[]{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
			for (int k=0;k<chain.length-2;k++) {
				temp[k] = chain[k+1];
			}
			/*
			for (int j=0;j<=i;j++) {
				temp[chain.length-1-j] = ' ';
			}
			*/
			chain = temp;
		}
		return chain;
	}

	public static int existsIn(char c, char[] chain) {
		for (int j=0;j<chain.length;j++) {
			if (chain[j]==c) {
				return j+1;
			}
		}
		return -1;
	}

	public static String readData() {
		try {
			Scanner sc = new Scanner(new FileReader("day6-actual-data.txt"));
			return sc.nextLine();
		} catch (Exception e) {
			System.out.println("File not found!");
		}
		return "";
	}

}
