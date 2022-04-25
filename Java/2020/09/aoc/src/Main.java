import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Scanner;
import java.util.ArrayList;


public class Main {

    public static void main(String[] args) {
        ArrayList<Long> data = readFileData("data.txt");
        // part1(data);
        part2(data);
    }

    public static void part1(ArrayList<Long> data) {
        System.out.println(findWrongNumber(data, 25));
    }

    public static boolean checkIfNrIsSumOfLastInterval(ArrayList<Long> sublist, long sum) {
        for (int i=0;i<sublist.size()-1;i++) {
            for (int j=i+1;j<sublist.size();j++) {
                if ((sublist.get(i)+sublist.get(j)) == sum) {
                    return true;
                }
            }
        }
        return false;
    }

    public static long findWrongNumber(ArrayList<Long> data, int interval) {
        for (int i=interval;i<data.size();i++) {
            if (!checkIfNrIsSumOfLastInterval(new ArrayList<Long>(data.subList(i-interval, i)), data.get(i))) {
                return(data.get(i));
            }
        }
        return(-1);
    }

    public static void part2(ArrayList<Long> data) {
        Long wrong_nr = findWrongNumber(data, 25);
        // System.out.println(wrong_nr);
        ArrayList<Long> sum_parts = findNrAsPartsIn(wrong_nr, data);
        // System.out.println(sum_parts);
        System.out.println(Collections.min(sum_parts) + Collections.max(sum_parts));
    }

    public static ArrayList<Long> findNrAsPartsIn(long wrongNr, ArrayList<Long> data) {
        for (int i=0;i<data.size()-1;i++) {
            int parts = 2;
            while (combine(data, i, parts) < wrongNr) {
                parts++;
            }
            if (combine(data, i, parts) == wrongNr) {
                return new ArrayList<>(data.subList(i, i + parts));
            }
        }
        return null;
    }

    public static long combine(ArrayList<Long> data, int start, int parts) {
        long sum = 0;
        for (int i=start;i<start+parts;i++) {
            sum = sum + data.get(i);
        }
        // System.out.println(new ArrayList<>(data.subList(start, start+parts)));
        // System.out.println(start);
        // System.out.println(parts);
        return sum;
    }

    public static ArrayList<Long> readFileData(String filename) {
        ArrayList<Long> data = new ArrayList<>();

        try {
            File myObj = new File("resources/" + filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String temp = myReader.nextLine();
                data.add(Long.parseLong(temp));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return data;
    }
}
