import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;
import java.util.ArrayList;


public class Main {

    public static void main(String[] args) {
        ArrayList<String> data = readFileData("data.txt");
        data.add("");
        // part1(data);
        part2(data);
    }

    public static void part1(ArrayList<String> data) {
        ArrayList<String> current_group = new ArrayList<>();
        int all_sums = 0;
        for (String line:data) {
            if (line.length() > 0) {
                current_group.add(line);
            } else {
                StringBuilder all_answers = new StringBuilder();
                for (String person : current_group) {
                    all_answers.append(person);
                }
                String total = all_answers.toString();
                int sum = 0;
                for (int i = 97; i <= 122; i++) {
                    if (total.contains(String.valueOf((char) i))) {
                        sum++;
                    }
                }
                all_sums = all_sums + sum;
                current_group.clear();
            }
        }
        System.out.println(all_sums);
    }

    public static void part2(ArrayList<String> data) {
        ArrayList<String> current_group = new ArrayList<>();
        int all_sums = 0;
        for (String line:data) {
            if (line.length() > 0) {
                current_group.add(line);
            } else {
                int sum = 0;
                boolean anynot = false;
                for (int i = 97; i <= 122; i++) {
                    for (String person:current_group) {
                        if (!person.contains(String.valueOf((char) i))) {
                            anynot = true;
                        }
                    }
                    if (!anynot) {
                        sum++;
                    }
                    anynot = false;
                }
                all_sums = all_sums + sum;
                current_group.clear();
            }
        }
        System.out.println(all_sums);
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
