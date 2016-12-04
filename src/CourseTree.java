import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author Danny, Kai, Spencer
 * Gonna build some trees
 *
 */

public class CourseTree {
	
	Map<CourseID, Course> courseMap;
	StudentInfo stuInfo;
	Node root;
	
	//This will hold the Nodes that we still need to generate children for
	Queue<Node> nodeQueue;
	
	
	public CourseTree( Map<CourseID, Course> courseMap, StudentInfo stuInfo){
		this.courseMap = courseMap;
		this.stuInfo = stuInfo;
		
		Node root = new Node();
		root.setStuInfo(stuInfo);
		this.root=root;
	}
	
	
		
	//Generates all children of an inputted node
	public CourseID[] generateChildren(Node n){
		
		//This method will add the children instance variable for each node
		HashMap<CourseID, Integer> validCourses = new HashMap<CourseID, Integer>();
		
	    for(Entry<CourseID, Course> entry: courseMap.entrySet()) {
	        Course c = (entry.getValue());
	        if (isValidChildCourse(n, c) == true){
	        	//System.out.print(c.toString());
	        	validCourses.put(entry.getKey(), (Integer) generateScore(n, c));
	        }
	    }
	    
	    System.out.println("Now printing out all the valid courses and their scores: ");
	    for(Entry<CourseID, Integer> entry: validCourses.entrySet()){
	    	System.out.println(entry.getKey().toString() + ", Score = " + entry.getValue());
	    }
	    
	    
	    ArrayList<Entry<CourseID, Integer>> sortedCourses = sortMap(validCourses);
	    
	    CourseID[] top4 = new CourseID[4];
	    
	    if (sortedCourses.size() < 4){
	    	for(int i = 0; i < sortedCourses.size(); i++){
	    		top4[i] = sortedCourses.get(i).getKey();
	    	}
	    	for(int j = 0 + sortedCourses.size(); j < 4; j++){
	    		top4[j] = new CourseID("Free", 420);
	    	}
	    }
	    
	    else{
	    	for(int i = 0; i < 4; i++){
	    		if (sortedCourses.get(i).getKey() == null){
	    			top4[i] = new CourseID("Free", 420);
	    		}
	    		top4[i] = sortedCourses.get(i).getKey();
	    	}
	    }
	    
	    return top4;
	}
	
	//A method used to convert a map into an arrayList that is sorted according to the maps values; descending order.
	private ArrayList<Entry<CourseID, Integer>> sortMap(HashMap<CourseID, Integer> map){
		Set<Entry<CourseID, Integer>> set = map.entrySet();
		ArrayList<Entry<CourseID, Integer>> list = new ArrayList<Entry<CourseID, Integer>>(set);
		Collections.sort(list, new Comparator<Map.Entry<CourseID, Integer>>(){
			public int compare( Map.Entry<CourseID, Integer> o1, Map.Entry<CourseID, Integer> o2)
			{
				return (o2.getValue()).compareTo( o1.getValue() );
			}
		});
		return list;
	}
	
	
	/**
	 * Generates a score that is indicative of how to prioritize the course. 
	 * @param node
	 * @param course
	 * @return
	 */
	public int generateScore(Node node, Course course){
		int score = 0;
		if (node.getStuInfo().getMajors().contains(course.getDept())){
			score += 1;
		}
		ArrayList<String> crossListsquared =  new ArrayList<String>(Arrays.asList(course.getCrossListed().split(" ")));
		if (node.getStuInfo().getMajors().contains(crossListsquared.get(0))){
			score += 3;
		}
		if (course.getRequired() == 1){
			score += 2;
		}
		if (node.getStuInfo().getSemester().equals("SPRING")){
			if(course.offered.equals("SPRING")){
				score += 1;
			}
			if(course.offered.equals("EVEN SPRING")){
				score += 2;
			}
			if(course.offered.equals("ODD SPRING")){
				score += 2;
			}
		}
		if (node.getStuInfo().getSemester().equals("FALL")){
			 if(course.offered.equals("FALL")){
				 score += 1;
			 }
			if(course.offered.equals("EVEN FALL")){
				score += 2;
			}
			if(course.offered.equals("ODD FALL")){
				score += 2;
			}
		}
		if (node.getStuInfo().getYearInSchool() == 3 || node.getStuInfo().getYearInSchool() == 4){
			if (node.getStuInfo().getCapstone().equals(false) && course.capstone == 1){
				score += 4;
			}
		}
		if (course.courseNum > 300){
			score += 1;
		}
		score += preReqScore(node, course);
		return score;
	}
	
	
	private int preReqScore(Node node, Course course){
		int score = 0;
		ArrayList<String> preReqList =  new ArrayList<String>(Arrays.asList(course.getPrereqFor().split(",")));
		score += preReqList.size(); //add one to the score per course that this is a preReq for
		if(node.getStuInfo().getYear() == 2 || node.getStuInfo().getYear() == 3){ //special case if we are in a soph or junior year
			for (String s: preReqList){
				if(s.equals("N/A")){
					continue;
				}
			ArrayList<String> singlePreReq = new ArrayList<String>(Arrays.asList(s.split(" ")));
			CourseID tempID = new CourseID(singlePreReq.get(0), new Integer(singlePreReq.get(1)));
			if (courseMap.get(tempID).getCapstone() == 1){
				score ++; //add one to the score per capstone that this is a preReq for
				}
			}
		}
		return score;
	}
	
	/*
	 * Determines if ONE course is a valid choice for next semester (in one Node)
	 * 
	 */
	
	private boolean isValidChildCourse(Node node, Course course){
		
		//Checking preReqs
		if (preReqsSatisfied(node, course) == false){
			return false;
		}
		
		//Check if we have already not taken the course
		if (courseNotTaken(node, course) == false){
			return false;
		}
		
		//Check if the course is offered next semester
		if (courseOffered(node, course) == false){
			return false;
		}

		
		return true;
	}
	
	
	
	/*
	 * Returns true if inputted course is a possible child of inputted node
	 */
	
	//Needs testing and to deal with 'ors' in terms of pre-reqs
	public boolean preReqsSatisfied(Node node, Course course){
		
		String preReqs = course.getPreReqs();
		
		if (preReqs.equals("N/A")){
			return true;}
		
		
		//This has list of PreReqs as Strings (ex. "COMP 123") note, some are two courses separated by "/"
		ArrayList<String> stringList =  new ArrayList<String>(Arrays.asList(preReqs.split(",")));
		
		//This has List of preReqs as Course objects
		ArrayList<Course> preReqCourses = new ArrayList<Course>();
		
		//Need to test this somehow, this should turn strings into Course Objects
		for (String s: stringList){
			boolean orClassesSatisfied = true; 
			
			if (s.contains("/")){
				ArrayList<String> orList = new ArrayList<String>( Arrays.asList(s.split("/")));
				for (String element1 : orList){
					ArrayList<String> orList2 = new ArrayList<String>( Arrays.asList(element1.split(" ")));
					CourseID tempID1 = new CourseID(orList2.get(0), new Integer(orList2.get(1)));
					if ((node.getStuInfo().studentCourses.get(tempID1) == null)){
						orClassesSatisfied = false;
					}
					else{
						orClassesSatisfied = true;
					}
				}
				if (orClassesSatisfied == false){
					return false;
				}
			}
			else{
			 ArrayList<String> stringList2 = new ArrayList<String> (Arrays.asList(s.split(" ")));
			 
			 
			 CourseID tempID = new CourseID(stringList2.get(0), new Integer(stringList2.get(1)));
			 preReqCourses.add(courseMap.get(tempID));
			}
		}
		for (Course c: preReqCourses){
			if (node.getStuInfo().studentCourses.get(c) == null){
				return false;
			}
		}
		return true;
	}
	
	
	/**
	 * Checks if the given course has already been taken by the student
	 * @param node
	 * @param course
	 * @return
	 */
	public boolean courseNotTaken(Node node, Course course){
		if (node.getStuInfo().getStudentCourses().containsKey(course)){
			return false;
		}
		return true;
	}


	
	/*
	 * Checks to see if the inputted course is offered the next semester
	 */
	
	public boolean courseOffered(Node node, Course course){
		int year = node.stuInfo.getYear();
		String semester = node.stuInfo.getSemester();
		String offered = course.getOffered();
		
		if (semester.equals("SPRING")){
			
			if (offered.equals("Fall") || offered.equals("Even Fall") || offered.equals("Odd Fall")){
				return false;
				}
			
			if (year%2 == 0){
				if (offered.equals("Odd Spring")){
					return false;
				}
			}
			
			if (year%2 == 1){
				if (offered.equals("Even Spring")){
					return false;
				}	
			}
		}
		if (semester.equals("FALL")){
			
			if (offered.equals("Spring") || offered.equals("Even Spring") || offered.equals("Odd Spring")){
				return false;}
			
			if (year%2 == 0){
				if (offered.equals("Odd Fall")){
					return false;
				}
			}
			
			if (year%2 == 1){
				if (offered.equals("Even Fall")){
					return false;
				}	
			}		
		}
		return true;
	}
	
	
	
	/*
	 * If a course has been added as a suggestion for the user, this
	 * method updates the student info object 
	 * 
	 */
	//MAKE SURE THAT BEFORE THIS IS CALLED, WE DONT ADD COURSES THAT FULLFILL 
	//GEN-EDS THAT HAVE ALREADY BEEN MET
	public void setFlags(Node node, Course course){
		
		//Check for Math Dummy Course (This might be unnecessary)
		if (course.getCourseName().equals("MATH XXX")){
			node.stuInfo.setMathCourse(node.stuInfo.getMathCourse()+1);
		}
		
		if (course.getCourseName().equals("WRITING XXX")){
			node.stuInfo.setWriting(true);
		}
		
		if (course.getCourseName().equals("USID XXX")){
			node.stuInfo.setUsID(true);
		}
		
		if (course.getCourseName().equals("INTERNAT XXX")){
			node.stuInfo.setInternationalism(true);
		}
		
		if (course.getCourseName().equals("QUANT XXX")){
			node.stuInfo.setQuantitative(true);
		}
		
		if (course.getCourseName().equals("LANG XXX")){
			node.stuInfo.setLanguage(node.stuInfo.getLanguage()+1);
		}
		
		if (course.getCourseName().equals("SOCSCI XXX")){
			node.stuInfo.setSocialSci(node.stuInfo.getSocialSci()+1);
		}
		
		if (course.getCourseName().equals("NATSCI XXX")){
			node.stuInfo.setNaturalSci(node.stuInfo.getNaturalSci()+1);
		}
		
		if (course.getCourseName().equals("HUMANFARTS XXX")){
			node.stuInfo.setHumanFArts(node.stuInfo.getHumanFArts()+1);
		}
		
	}
	
	
	/*
	 * Increments the current semester in a given node object
	 * BE SURE TO CALL THIS ON THE CHILD!!
	 * 
	 * 
	 * Needs to be tested
	 */


	
	public Map<CourseID, Course> getCourseMap() {
		return courseMap;
	}



	public void setCourseMap(Map<CourseID, Course> courseMap) {
		this.courseMap = courseMap;
	}



	public StudentInfo getStuInfo() {
		return stuInfo;
	}



	public void setStuInfo(StudentInfo stuInfo) {
		this.stuInfo = stuInfo;
	}



	public Node getRoot() {
		return root;
	}



	public void setRoot(Node root) {
		this.root = root;
	}



	public Queue<Node> getNodeQueue() {
		return nodeQueue;
	}



	public void setNodeQueue(Queue<Node> nodeQueue) {
		this.nodeQueue = nodeQueue;
	}
	
}
