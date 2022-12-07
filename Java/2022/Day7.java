import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileReader;
import java.util.Comparator;
import java.util.Collections;

public class Day7 {

	public static void main(String[] args) {
		ArrayList<String> lines = getAllLines();
		Folder root = parseFS(lines);
		long total = root.getSize();
		long needed = 30000000l - (70000000l - total);
		System.out.println(needed);		
		ArrayList<Folder> allDirs = getAllDirs(root);
		ArrayList<Long> filtered = getAllWhereSizeMin(needed, allDirs);
		Collections.sort(filtered);
		System.out.println(filtered.get(0));
		/*
		long sum = 0;
		for (Folder f : filtered) {
			sum += f.getSize();
		}
		System.out.println(sum);
		*/
	}

	public static ArrayList<Long> getAllWhereSizeMin(long min, ArrayList<Folder> all) {
		ArrayList<Long> filtered = new ArrayList<Long>();
		for (Folder f : all) {
			if (f.getSize() >= min) filtered.add(f.getSize());
		}
		return filtered;
	}

	public static ArrayList<Folder> getAllWhereSizeMax(long max, ArrayList<Folder> all) {
		ArrayList<Folder> filtered = new ArrayList<Folder>();
		for (Folder f : all) {
			if (f.getSize() <= max) filtered.add(f); 
		}
		return filtered;
	}

	public static ArrayList<Folder> getAllDirs(Folder root) {
		ArrayList<Folder> all= new ArrayList<Folder>();
		all.add(root);
		for (int i=0;i<root.dirs.size();i++) {
			ArrayList<Folder> nested = getAllDirs(root.dirs.get(i));
			for (int j=0;j<nested.size();j++) {
				all.add(nested.get(j));
			}
		}
		return all;
	}

	public static Folder parseFS(ArrayList<String> lines) {
		Folder root = new Folder("root", null);
		Folder current = root;
		String line;
		for (int i=1;i<lines.size();i++) {
			line = lines.get(i);
			if (line.charAt(0) == '$') {
				if (line.split(" ")[1].substring(0, 2).equals("ls")) {
					continue;
				} else if (line.split(" ")[1].substring(0, 2).equals("cd")) {
					if (line.split(" ")[2].equals("..")) { // Set current to parent
						current = current.parent;
					} else { // Set current to parameter
						current = current.getInnerByName(line.split(" ")[2]);
					}
				}
			} else if (line.substring(0, 3).equals("dir")){
				current.addFolder(new Folder(line.substring(4), current));
			} else {
				current.addFile(new File(Integer.valueOf(line.split(" ")[0])));
			}
		}
		return root;
	}

	public static ArrayList<String> getAllLines() {
		ArrayList<String> lines = new ArrayList<>();
		try {
			Scanner sc = new Scanner(new FileReader("day7-actual-data.txt"));
			while (sc.hasNextLine()) {
				lines.add(sc.nextLine());
			}
		} catch (Exception e) {
			System.out.println("Error , file not found");
		}
		return lines;
	}

}

class Folder {

	ArrayList<Folder> dirs = new ArrayList<Folder>();
	ArrayList<File> files = new ArrayList<File>();
	public String name;
	public Folder parent;

	public Folder(String name, Folder parent) {
		this.name = name;	
		this.parent = parent;
	}

	public Folder(ArrayList<Folder> inner, ArrayList<File> files, String name) {
		this.dirs = inner;
		this.files = files;
		this.name = name;
	}

	public Folder getInnerByName(String name) {
		for (int i=0;i<this.dirs.size();i++) {
			if (this.dirs.get(i).name.equals(name)) return this.dirs.get(i);
		}
		return null;
	}

	public void addFolder(Folder ftoadd) {
		this.dirs.add(ftoadd);
	}

	public void addFile(File ftoadd) {
		this.files.add(ftoadd);
	}

	public long getSize() {
		long size = 0;
		for (int i=0;i<dirs.size();i++) {
			size += dirs.get(i).getSize();
		}
		for (int i=0;i<files.size();i++) {
			size += files.get(i).getSize();
		}
		return size;
	}

}

class File {

	long size;

	public File(long size) {
		this.size = size;
	}

	public long getSize() {
		return this.size;
	}

}
