import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;


public class Main {

    public static void main(String[] args) {
        ArrayList<String> data = readFileData("data.txt");
        // part1(data);
        part2(data);
    }

    public static ArrayList<String> findOuterBags(String bagName, ArrayList<String> rules) {
        ArrayList<String> outerBags = new ArrayList<>();
        for (String rule:rules) {
            if (rule.split("contain")[1].contains(bagName)) {
                outerBags.add(rule.split(" bag")[0]);
            }
        }
        return outerBags;
    }

    public static ArrayList<String> findInnerBagsDuplicates(String bagName, ArrayList<String> rules) {
        ArrayList<String> innerBags = new ArrayList<>();
        for (String rule:rules) {
            if (rule.split(" contain")[0].contains(bagName)) {
                for (String innerBagRule : rule.split("contain ")[1].split(", ")) {
                    String countString = innerBagRule.split(" ")[0];
                    int countInt = 0;
                    if (!countString.contains("no")) {
                        countInt = Integer.parseInt(countString);
                    }
                    String bagColor = innerBagRule.split(" ", 2)[1].split(" bag")[0];
                    if (bagColor.charAt(bagColor.length()-1) == '.') {
                        bagColor = bagColor.substring(0, bagColor.length()-1);
                    }
                    for (int i=0;i<countInt;i++) {
                        innerBags.add(bagColor);
                    }
                }
            }
        }
        return innerBags;
    }

    public static void part1(ArrayList<String> data) {
        ArrayList<String> currentBags = new ArrayList<>();
        int finalCount = -1;
        currentBags.add("shiny gold");
        ArrayList<String> usedBags = new ArrayList<>();
        while (!currentBags.isEmpty()) {
            for (String bag: findOuterBags(currentBags.get(0), data)) {
                if (!currentBags.contains(bag) && !usedBags.contains(bag)) {
                    currentBags.add(bag);
                }
            }
            usedBags.add(currentBags.get(0));
            currentBags.remove(0);
            finalCount++;
        }
        System.out.println(finalCount);
    }

    public static void part2(ArrayList<String> data) {
        ArrayList<String> currentBags = new ArrayList<>();
        int finalCount = -1;
        currentBags.add("shiny gold");
        ArrayList<String> usedBags = new ArrayList<>();
        while (!currentBags.isEmpty()) {
            currentBags.addAll(findInnerBagsDuplicates(currentBags.get(0), data));
            usedBags.add(currentBags.get(0));
            currentBags.remove(0);
        }
        System.out.println(usedBags.size()-1);
    }

    public static ArrayList<String> readFileData(String filename) {
        ArrayList<String> data = new ArrayList<>();

        try {
            File myObj = new File("resources/" + filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String temp = myReader.nextLine();
                data.add(temp);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return data;
    }
}
