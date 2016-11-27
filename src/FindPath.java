import java.io.File;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//Make sure this package is included on your computer!!!
import com.opencsv.CSVReader;

/**
 * @author Danny, Kai, Spencer
 * Program that takes a student's inputed classes and determines if they are able to complete the Computer 
 * Science major at Macalester college
 * 
 */


public class FindPath {
	
	static File file; 
	static Map<CourseID, Course> courseMap;     //Map containing all courses in inputtedFile
	static StudentInfo stuInfo;
	static CourseTree courseTree;
	
	
	/*
	 * Initialize file and courseMap - make sure filename is correct here
	 */
	public static void init() throws NumberFormatException, IOException{
		file = new File("CoursesCSVs/ShortestPathMajorCS.csv");    //Change this if file is not found
		stuInfo = new StudentInfo();
		
		courseMap = createCourses();
		createStudentCourses();
	}
	
	
	/*
	 * Main method
	 */
	
	public static void main(String[] args) throws Exception {
		init();
		
		courseTree = new CourseTree(courseMap, stuInfo);
		courseTree.removeTakenCourses();
	
		//Print out all key/value pairs in the courseMap
//		for (Map.Entry<CourseID, Course> entry : courseMap.entrySet()){
//		    System.out.println(entry.getKey() + "/" + entry.getValue());}
		

		//Check to see how many Courses got added to studentCourses 
		//System.out.println(studentCourses.size());
		

	}
	
	
	/*
	 * Creates a Map of course objects - these are all courses contained in the given CSV
	 */
	private static Map<CourseID, Course> createCourses() throws NumberFormatException, IOException{
		Map<CourseID, Course> courseMap = new HashMap<CourseID, Course>();
		
		@SuppressWarnings("resource")
		CSVReader reader = new CSVReader(new FileReader(file));
		
		// nextLine is an array of Strings that represent all entries in a particular row of the given CSV
		String [] nextLine;
		
		//This is to get past the header row
		reader.readNext();
		
		while ((nextLine = reader.readNext()) != null) { //Read over every row and convert the row to a entry in the course map
	    		//Create CourseID object
	    		CourseID id = new CourseID(nextLine[1], Integer.parseInt(nextLine[2]));
	    		//Create Course object
	    		Course course = new Course(Integer.parseInt(nextLine[0]), nextLine[1], Integer.parseInt(nextLine[2]), 
	    									nextLine[3], nextLine[4], Integer.parseInt(nextLine[5]), Integer.parseInt(nextLine[6]),
	    									nextLine[7], nextLine[8], Integer.parseInt(nextLine[9]));
	    		//Add newly created entry to the map of courses
	    		courseMap.put(id, course);
	    	}
	return courseMap;	
	}
	
	
	/*
	 * Preliminary method that creates a list of Courses that the user has taken
	 */
	
	private static void createStudentCourses (){
		ArrayList<Course> studentCourseList = new ArrayList<Course>();
				
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner (System.in);
		System.out.print("What year in college are you? (1 - 4)");
		int stuYear = scanner.nextInt();
		if (stuYear >= 1 && stuYear <= 4){
			stuInfo.setYear(stuYear);
		}
		else{
			System.out.print("You entered something wrong sorry yo.");
			System.exit(0);
		}
		System.out.print("Have you satisfied your writing requirement? (Y or N): ");
		if (scanner.next().toUpperCase().equals("Y")){
			stuInfo.setWriting(true);
		}
		System.out.print("Have you satisfied your U.S. Identities requirement? (Y or N): ");
		if (scanner.next().toUpperCase().equals("Y")){
			stuInfo.setUsID(true);
		}
		System.out.print("Have you satisfied your Internationalism requirement? (Y or N): ");
		if (scanner.next().toUpperCase().equals("Y")){
			stuInfo.setInternationalism(true);
		}
		System.out.print("Have you satisfied your Quantitative requirement? (Y or N): ");
		if (scanner.next().toUpperCase().equals("Y")){
			stuInfo.setQuantitative(true);
		}
		System.out.print("Have you satisfied your language requirement? (Y or N): ");
		if (scanner.next().toUpperCase().equals("Y")){
			stuInfo.setLanguage(true);
		}
		System.out.print("How many Social Science classes have you taken? ");
		stuInfo.setSocialSci(scanner.nextInt());
		System.out.print("How many Natural Science classes have you taken? ");
		stuInfo.setNaturalSci(scanner.nextInt());
		System.out.print("How many HumanFArts classes have you taken? ");
		stuInfo.setHumanFArts(scanner.nextInt());
		while (true){
			System.out.print("Enter a course Department or QUIT if no more courses:  ");  
			String deptName = scanner.next().toUpperCase(); // Get what the user types.
			if (deptName.equals("QUIT")){
				break;
			}
			System.out.print("Enter a course Number: "); 
			int courseNum = scanner.nextInt();
			CourseID testID = new CourseID(deptName, courseNum);
			System.out.println(testID);
			if (courseMap.containsKey(testID)) {
				studentCourseList.add(courseMap.get(testID));
				System.out.println(deptName + " " + courseNum + " has been added.");
			}
			else {
				System.out.println("Sorry that course is not listed under our register.");
			}
		}
		scanner.close();
					
	stuInfo.setStudentCourses( studentCourseList);
	}

	public static File getFile() {
		return file;
	}

	public static void setFile(File file) {
		FindPath.file = file;
	}

	public static Map<CourseID, Course> getCourseMap() {
		return courseMap;
	}

	public static void setCourseMap(Map<CourseID, Course> courseMap) {
		FindPath.courseMap = courseMap;
	}

	public static StudentInfo getStuInfo() {
		return stuInfo;
	}

	public static void setStuInfo(StudentInfo stuInfo) {
		FindPath.stuInfo = stuInfo;
	}
	
}
	
