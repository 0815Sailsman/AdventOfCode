import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.ArrayList; // import the ArrayList class


public class Day2 {
    
    public static void main(String args[]) {
        ArrayList<String> data = readFileData("data.txt");
        /*
        for (String string : data) {
            System.out.println(string);
        }
        */
        //System.out.println(count_valids_1(data));
        System.out.println(count_valids_2(data));
    }

    public static ArrayList<String> readFileData(String filename) {
        ArrayList<String> data = new ArrayList<String>(); // Create an ArrayList object
        
        try {
          File myObj = new File("Java/2020/02/" + filename);
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

    public static int count_valids_1(ArrayList<String> data) {
      int valids = 0;
      for (String string : data) {
        int min_occurences = Integer.parseInt(string.split("-")[0]);
        int max_occurences = Integer.parseInt(string.split("-")[1].split(" ")[0]);
        char cond_char = string.split(":")[0].split(" ")[1].charAt(0);
        String message = string.split(" ")[2];
        
        int tOccurences = 0;
        for (int i=0;i<message.length();i++) {
          if (message.charAt(i) == cond_char) {
            tOccurences++;
          }
        }
        if (tOccurences >= min_occurences && tOccurences <= max_occurences) {
          valids++;
        }
      }
      return valids;
    }

    public static int count_valids_2(ArrayList<String> data) {
      int valids = 0;
      for (String string : data) {
        int ind1 = Integer.parseInt(string.split("-")[0]) - 1;
        int ind2 = Integer.parseInt(string.split("-")[1].split(" ")[0]) - 1;
        char cond_char = string.split(":")[0].split(" ")[1].charAt(0);
        String message = string.split(" ")[2];
        
        int tOccurences = 0;
        if (message.charAt(ind1) == cond_char) {
          tOccurences++;
        }
        if (message.charAt(ind2) == cond_char) {
          tOccurences++;
        }
        if (tOccurences == 1) {
          valids++;
        }
      }
      return valids;
    }
}
