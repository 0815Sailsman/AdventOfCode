import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;


public class Main {

    public static void main(String[] args) {
        ArrayList<String> data = readFileData("data.txt");
        // part1(data);
        part2(data);
    }

    public static void part1(ArrayList<String> data) {
        int acc = calc_acc_before_loop(data);
        System.out.println(acc);
    }

    // Also returns the size of the acc at the end of the program when there is no loop
    public static int calc_acc_before_loop(ArrayList<String> data) {
        int acc = 0;
        int line = 0;
        ArrayList<Integer> linesRun = new ArrayList<>();
        while (!linesRun.contains(line)) {
            if (line == data.size()) {
                return acc;
            }
            linesRun.add(line);
            String lineTxt = data.get(line);
            String cmd = lineTxt.substring(0, 3);
            char op = lineTxt.charAt(4);
            int value = Integer.parseInt(lineTxt.substring(5));
            if (op == '-') {
                value = value * -1;
            }

            if (cmd.equals("acc")) {
                acc = acc + value;
                line++;
            } else if (cmd.equals("nop")) {
                line++;
            } else if (cmd.equals("jmp")) {
                line = line + value;
            }
        }
        return acc;
    }

    public static boolean has_infinite_loop(ArrayList<String> data) {
        int line = 0;
        ArrayList<Integer> linesRun = new ArrayList<>();
        while (!linesRun.contains(line)) {
            if (line == data.size()) {
                return false;
            }
            linesRun.add(line);
            String lineTxt = data.get(line);
            String cmd = lineTxt.substring(0, 3);
            char op = lineTxt.charAt(4);
            int value = Integer.parseInt(lineTxt.substring(5));
            if (op == '-') {
                value = value * -1;
            }

            if (cmd.equals("acc") || cmd.equals("nop")) {
                line++;
            } else if (cmd.equals("jmp")) {
                line = line + value;
            }
        }
        return true;
    }

    public static ArrayList<ArrayList<String>> build_all_possible_codes(ArrayList<String> data) {
        ArrayList<ArrayList<String>> collection = new ArrayList<>();
        for (int i=0;i<data.size();i++) {
            if (data.get(i).contains("jmp") || data.get(i).contains("nop")) {
                ArrayList<String> cloned = new ArrayList<>();
                cloned.addAll(data);
                cloned.set(i, invertLine(data.get(i)));
                collection.add(cloned);
            }
        }
        return collection;
    }

    public static String invertLine(String old) {
        if (old.contains("jmp")) {
            return "nop" + old.substring(3);
        } else if (old.contains("nop")) {
            return "jmp" + old.substring(3);
        }
        return "";
    }

    public static void part2(ArrayList<String> data) {
        for (ArrayList<String> code:build_all_possible_codes(data)) {
            if (!has_infinite_loop(code)) {
                System.out.println(calc_acc_before_loop(code));
                break;
            }
        }
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
