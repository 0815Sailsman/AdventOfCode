import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.ArrayList; // import the ArrayList class


public class Day3 {
    
    public static void main(String args[]) {
        ArrayList<String> data = readFileData("data.txt");
        /*
        for (String string : data) {
            System.out.println(string);
        }
        */
        // System.out.println(count_trees_1(data));
        long[] nrs = new long[5];
        nrs[0] = count_trees_2(data, 1, 1);
        nrs[1] = count_trees_2(data, 3, 1);
        nrs[2] = count_trees_2(data, 5, 1);
        nrs[3] = count_trees_2(data, 7, 1);
        nrs[4] = count_trees_2(data, 1, 2);
        System.out.println(nrs[0] * nrs[1] * nrs[2] * nrs[3] * nrs[4]);
    }

    public static ArrayList<String> readFileData(String filename) {
        ArrayList<String> data = new ArrayList<String>(); // Create an ArrayList object
        
        try {
          File myObj = new File("Java/2020/03/" + filename);
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

    public static int count_trees_1(ArrayList<String> data) {
      int x = 0;
      int y = 0;
      int trees = 0;
      while (y < data.size()) {
        if (data.get(y).charAt(x) == '#') {
          trees++;
        }
        x = x + 3;
        y++;
        if (x >= data.get(0).length()) {
          x = x - data.get(0).length();
        }
      }
      return trees;
    }

    public static long count_trees_2(ArrayList<String> data, int stepsx, int stepsy) {
      int x = 0;
      int y = 0;
      long trees = 0;
      while (y < data.size()) {
        if (data.get(y).charAt(x) == '#') {
          trees++;
        }
        x = x + stepsx;
        y = y + stepsy;
        if (x >= data.get(0).length()) {
          x = x - data.get(0).length();
        }
      }
      return trees;
    }
}
