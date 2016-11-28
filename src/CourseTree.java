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
		boolean preReqsSatisfied = preReqsSatisfied(node, course);
		
		
		return preReqsSatisfied;
	}
	
	
	
	
	/*
	 * Returns true if inputted course is a possible child of inputted node
	 */
	
	//Needs testing and to deal with 'ors' in terms of pre-reqs
	private boolean preReqsSatisfied(Node node, Course course){
		
		String preReqs = course.getPreReqs();
		
		if (preReqs.equals("N/A")){
			return true;}
		
		
		//This has list of PreReqs as Strings (ex. "COMP 123") note, some are two courses separated by "/"
		ArrayList<String> stringList = (ArrayList<String>) Arrays.asList(preReqs.split(","));
		
		
		//This has List of preReqs as Course objects
		ArrayList<Course> preReqCourses = new ArrayList<Course>();
		
		//Need to test this somehow, this should turn strings into Course Objects
		for (String s: stringList){
			if (s.contains("/")){
				//Uhhhhhhh lets do this later
				}
			else{
			 ArrayList<String> stringList2 = (ArrayList<String>) Arrays.asList(preReqs.split(" "));
			 CourseID tempID = new CourseID(stringList2.get(0), new Integer(stringList2.get(1)));
			 preReqCourses.add(courseMap.get(tempID));
			}
		}
		
		//Going to need a good equals method and hash method (in Course class)
		for (Course c: preReqCourses){
			if (stuInfo.studentCourses.get(c) != 1){
				return false;
			}
		}
		return true;
	}
	
	
	
	
	
	
	
	
	
}
