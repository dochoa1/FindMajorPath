import java.util.ArrayList;
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
	
	
	//Haven't tested this, but should remove all courses that student has taken from courseMap
	public void removeTakenCourses(){
		for (Course c: stuInfo.getStudentCourses()){
			String dept = c.getDept();
			int num = c.getCourseNum();
			CourseID tempID = new CourseID(dept,num);
			courseMap.remove(tempID);
		}
	}
		
	
		
	public void generateChildren(Node n){
		//This method will add the children instance variable for each node
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
