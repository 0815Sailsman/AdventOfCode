import java.util.Scanner;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;


public class Day5 {

	public static void main(String[] args) {
		ArrayList<Character>[] stacks = getStacks();
		ArrayList<int[]> instructions = getInstructions();
		executeInstructionsOnStacks(stacks, instructions);

		printTop(stacks);
	}

	public static void printTop(ArrayList<Character>[] stacks) {
		for (ArrayList<Character> a : stacks) {
			System.out.print(a.get(a.size()-1));
		}
	}

	public static void executeInstructionsOnStacks(ArrayList<Character>[] stacks, ArrayList<int[]> instructions) {
		for (int[] instruction : instructions) {
			for (int i=instruction[0];i>0;i--) {
				ArrayList<Character> target = stacks[instruction[2]-1];
				ArrayList<Character> source = stacks[instruction[1]-1];
				System.out.println("Moved " + source.get(source.size()-1) + " to " + target);
				target.add(source.remove(source.size()-i));
			}
		}
	}

	public static ArrayList<int[]> getInstructions() {
		try {
			Scanner sc = new Scanner(new FileReader("day5-actual-actions.txt"));
			ArrayList<int[]> instructions = new ArrayList<>();
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				int a = Integer.valueOf(line.split(" ")[1]);
				int b = Integer.valueOf(line.split(" ")[3]);
				int c = Integer.valueOf(line.split(" ")[5]);
				instructions.add(new int[] {a, b, c});
			}
			/*
			for (int[] i : instructions) {
				for (int j : i) {
					System.out.print(j + ", ");
				}
				System.out.println();
			}
			*/
			return instructions;
		} catch (Exception e) {
			System.out.println("Exception, most likely file not found!");
		}

		return null;
	}


	public static ArrayList<Character>[] getStacks() {
		try {
			Scanner scstacks = new Scanner(new FileReader("day5-actual-stacks.txt"));
			String line = scstacks.nextLine();
			ArrayList<Character>[] stacks = new ArrayList[(line.length()+1) / 4];
			for (int i=0;i<line.length();i=i+4) {
				stacks[i/4] = new ArrayList<>();
				String partstring = line.substring(i, i+3);
				if (partstring.charAt(1) != ' ') {
					stacks[i/4].add(partstring.charAt(1));
				}
			}
			while (scstacks.hasNextLine()) {
				line = scstacks.nextLine();
				if (!scstacks.hasNextLine()) {
					break;
				}
				for (int i=0;i<line.length();i=i+4) {
					String partstring = line.substring(i, i+3);
					if (partstring.charAt(1) != ' ') {
						stacks[i/4].add(partstring.charAt(1));
					}
				}
			}
				
			for (ArrayList a : stacks) {
				//System.out.println(a);
				Collections.reverse(a);
			}
			
			return stacks;
		} catch (Exception e) {
			//System.out.println("File not found");
			e.printStackTrace();
		}
		return null;
	}
}
