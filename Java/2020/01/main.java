import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.ArrayList; // import the ArrayList class


public class main {
  
  public static void main(String[] args) {
    ArrayList<Integer> data = readFileData("data.txt");
    /*
    for (int i = 0; i<data.size(); i++) {
      System.out.println(data.get(i));
    }
    */
    //System.out.println(find_2020_from_2(data));
    System.out.println(find_2020_from_3(data));
  }
  
  public static ArrayList<Integer> readFileData(String filename) {
    ArrayList<Integer> data = new ArrayList<Integer>(); // Create an ArrayList object
    
    try {
      File myObj = new File(filename);
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        String temp = myReader.nextLine();
        data.add(Integer.parseInt(temp.replaceAll("[\\n\\t ]", "")));
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    return data;
  }

  public static int find_2020_from_2(ArrayList<Integer> data) {
    for (int i = 0; i<data.size(); i++) {
      int val1 = data.get(i);
      for (int j = i; j<data.size(); j++) {
        int val2 = data.get(j);
        if ((val1 + val2) == 2020) {
          return val1 * val2;
        }
      }
    }
    return -1;
  }
  

  public static int find_2020_from_3(ArrayList<Integer> data) {
    for (int i = 0; i<data.size(); i++) {
      int val1 = data.get(i);
      for (int j = i; j<data.size(); j++) {
        int val2 = data.get(j);
        for (int k = j; k<data.size(); k++) {
          int val3 = data.get(k);
          if ((val1 + val2 + val3) == 2020) {
            return val1 * val2 * val3;
          } 
        }
      }
    }
    return -1;
  }
}
