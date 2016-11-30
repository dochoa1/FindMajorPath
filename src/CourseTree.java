import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Queue;

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
	public void generateChildren(Node n){
		//This method will add the children instance variable for each node
		
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
				//Uhhhhhhh lets do this later
				ArrayList<String> orList = new ArrayList<String>( Arrays.asList(preReqs.split("/")));
				for (String element1 : orList){
					ArrayList<String> orList2 = new ArrayList<String>( Arrays.asList(element1.split(" ")));
					CourseID tempID1 = new CourseID(orList2.get(0), new Integer(orList2.get(1)));
					if ((node.getStuInfo().studentCourses.get(tempID1) != 1)){
						orClassesSatisfied = false;
					}
					if ((node.getStuInfo().studentCourses.get(tempID1) == 1)){
						orClassesSatisfied = true;
					}
				}
				if (orClassesSatisfied == false){
					return false;
				}
			}
			else{
			 ArrayList<String> stringList2 = new ArrayList<String> (Arrays.asList(preReqs.split(" ")));
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
	 * Checks if the give course has already been taken by the student
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
	 * Increments the current semester in a given node object
	 * BE SURE TO CALL THIS ON THE CHILD!!
	 * 
	 * 
	 * Needs to be tested
	 */
	
	
	private void incrementSemester(Node node){
		int year = node.getStuInfo().getYear();
		String semester = node.getStuInfo().getSemester();
		
		if (semester.equals("FALL")){
			node.stuInfo.setYear(year+1);
			node.stuInfo.setSemester("SPRING");}
		
		else{
			node.stuInfo.setSemester("FALL");}
	}

	
	
	
	
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
