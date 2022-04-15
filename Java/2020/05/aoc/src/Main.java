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

    public static void part1(ArrayList<String> data) {
        int maxid = 0;
        for (String pass:data) {
            int tempid = calcBoardingPassID(pass);
            if (tempid > maxid) {
                maxid = tempid;
            }
        }
        System.out.println(maxid);
    }

    // Just print every value that is not used and manually search for the right one
    public static void part2(ArrayList<String> data) {
        ArrayList<Integer> allids = new ArrayList<Integer>();
        for (int i = 0; i <= 127; i++) {
            for (int j = 0; j <= 7; j++) {
                allids.add((i*8)+j);
            }
        }
        for (String pass:data) {
            allids.remove(allids.indexOf(calcBoardingPassID(pass)));
        }
        for (int leftovers:allids) {
            System.out.println(leftovers);
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

    public static int calcBoardingPassID(String instructions) {
        int row = calcRow(instructions.substring(0, 7));
        int seat = calcSeat(instructions.substring(7));
        return (row * 8) + seat;
    }

    public static int calcRow(String instructions) {
        int min = 0;
        int max = 127;
        for (char half:instructions.toCharArray()) {
            if (half == 'F') {
                max = (max + min) / 2;
            } else if (half == 'B') {
                min = -Math.floorDiv(-(max + min),2);
            }
        }
        return max;
    }

    public static int calcSeat(String instructions) {
        int min = 0;
        int max = 7;
        for (char half:instructions.toCharArray()) {
            if (half == 'L') {
                max = (max + min) / 2;
            } else if (half == 'R') {
                min = -Math.floorDiv(-(max + min),2);
            }
            System.out.println(min);
            System.out.println(max);
        }
        return max;
    }
}
