import java.util.ArrayList;

/*
 * These Node objects represent a semesters' worth of courses for a certain student
 */

public class Node {
	

	
	//General Requirements
	StudentInfo stuInfo;
	
	
	//Courses in one semester
	Course c1;
	Course c2;
	Course c3;
	Course c4;
	
	ArrayList<Node> children;
	
	Node parent;
	
	
	public Node (Course c1, Course c2, Course c3, Course c4){
		this.c1=c1;
		this.c2=c2;
		this.c3=c3;
		this.c4=c4;
	}

	public Node(){
	}

	public StudentInfo getStuInfo() {
		return stuInfo;
	}

	public void setStuInfo(StudentInfo stuInfo) {
		this.stuInfo = stuInfo;
	}

	public Course getC1() {
		return c1;
	}

	public void setC1(Course c1) {
		this.c1 = c1;
	}

	public Course getC2() {
		return c2;
	}

	public void setC2(Course c2) {
		this.c2 = c2;
	}

	public Course getC3() {
		return c3;
	}

	public void setC3(Course c3) {
		this.c3 = c3;
	}

	public Course getC4() {
		return c4;
	}

	public void setC4(Course c4) {
		this.c4 = c4;
	}

	public ArrayList<Node> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<Node> children) {
		this.children = children;
	}
	
	
	
}