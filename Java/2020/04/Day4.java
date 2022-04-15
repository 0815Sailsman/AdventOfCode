import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.ArrayList; // import the ArrayList class


public class Day4 {
    
    public static void main(String args[]) {
        ArrayList<String> data = readFileData("data.txt");
        /*
        for (String string : data) {
            System.out.println(string);
        }
        */
        // System.out.println(valid_passports_1(data));
        System.out.println(valid_passports_2(data));

    }

    public static ArrayList<String> readFileData(String filename) {
        ArrayList<String> data = new ArrayList<String>(); // Create an ArrayList object
        
        try {
          File myObj = new File("Java/2020/04/" + filename);
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

    public static int valid_passports_1(ArrayList<String> data) {
        String current_msg = "";
        int current_colons = 0;
        int valids = 0;
        for (String string : data) {
            if (!string.equals("")) {
                current_msg += string;
            } else {
                for (int i=0;i<current_msg.length();i++) {
                    if (current_msg.charAt(i) == ':') {
                        current_colons++;
                    }
                }
                if (current_colons == 8) {
                    valids++;
                } else if (current_colons == 7 && !current_msg.contains("cid:")) {
                    valids++;
                }
                current_msg = "";
                current_colons = 0;
            }
        }
        return valids;
    }

    public static int valid_passports_2(ArrayList<String> data) {
        String current_msg = "";
        int valids = 0;
        for (String string : data) {
            if (!string.equals("")) {
                if (current_msg.length() == 0) {
                    current_msg = current_msg + string;
                } else {
                    current_msg = current_msg + " " + string;
                }
                
                System.out.println(current_msg);
            } else {
                boolean byr = false;
                boolean iyr = false;
                boolean eyr = false;
                boolean hgt = false;
                boolean hcl = false;
                boolean ecl = false;
                boolean pid = false;
                for (int i=0;i<current_msg.split(" ").length;i++) {
                    switch (current_msg.split(" ")[i].substring(0, 3)) {
                        case "byr":
                            int y1 = Integer.parseInt(current_msg.split(" ")[i].substring(4));
                            if (y1 >= 1920 && y1 <= 2002) {
                                System.out.println("Byr true");
                                byr = true;
                            }
                            break;
                        case "iyr":
                            int y2 = Integer.parseInt(current_msg.split(" ")[i].substring(4));
                            if (y2 >= 2010 && y2 <= 2020) {
                                System.out.println("Iyr true");
                                iyr = true;
                            }
                            break;
                        case "eyr":
                            int y3 = Integer.parseInt(current_msg.split(" ")[i].substring(4));
                            if (y3 >= 2020 && y3 <= 2030) {
                                System.out.println("Eyr true");
                                eyr = true;
                            }
                            break;
                        case "hgt":
                            int h = 0;
                            if (current_msg.split(" ")[i].contains("cm")) {
                                h = Integer.parseInt(current_msg.split(" ")[i].substring(4, current_msg.split(" ")[i].length()-2));
                                if (h >= 150 && h<=193) {
                                    System.out.println("Hgt true");
                                    hgt = true;
                                }
                            } else if (current_msg.split(" ")[i].contains("in")){
                                h = Integer.parseInt(current_msg.split(" ")[i].substring(4, current_msg.split(" ")[i].length()-2));
                                if (h >= 59 && h<=76) {
                                    System.out.println("Hgt true");
                                    hgt = true;
                                }
                            }
                            break;
                        case "hcl":
                            if (current_msg.split(" ")[i].length() == 11) {
                                if (current_msg.split(" ")[i].charAt(4) == '#') {
                                    boolean any_missmatch = false;
                                    for (int ascii : current_msg.split(" ")[i].substring(5).toCharArray()) {
                                        if (!(ascii >=48 && ascii <=57) && !(ascii>=97 && ascii <=102)) {
                                            any_missmatch = true;
                                        }
                                    }
                                    if (!any_missmatch) {
                                        System.out.println("Hcl true");
                                        hcl = true;
                                    }
                                }
                            }
                            break;
                        case "ecl":
                            if (current_msg.split(" ")[i].length() == 7) {
                                String c = current_msg.split(" ")[i].substring(4);
                                if (c.equals("amb") || c.equals("blu") || c.equals("brn") || c.equals("gry") || c.equals("grn") || c.equals("hzl") || c.equals("oth")) {
                                    ecl = true;
                                    System.out.println("Ecl true");
                                }
                            }
                            break;
                        case "pid":
                            if (current_msg.split(" ")[i].length() == 13) {
                                String p = current_msg.split(" ")[i].substring(4);
                                if (p.matches("[0-9]+")) {
                                    System.out.println("Pid true");
                                    pid  = true;
                                }
                            }
                            break;
                        default:
                            break;
                    }
                }
                if (byr && iyr && eyr && hgt && hcl && ecl && pid) {
                    valids++;
                }
                current_msg = "";
            }
        }
        return valids;
    }
}
