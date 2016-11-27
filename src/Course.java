
public class Course {

	int courseID;
	String dept;
	int courseNum;
	String courseName;
	String crossListed;
	int required;
	int capstone;
	String preReqs;
	String offered;
	int credits;
	
	
	
	public Course(int courseID, String dept, int courseNum, String courseName, String crossListed, int required, int capstone, String preReqs, String offered, int credits){
		this.courseID = courseID;
		this.dept=dept;
		this.courseNum=courseNum;
		this.courseName=courseName;
		this.crossListed=crossListed;
		this.required=required;
		this.capstone=capstone;
		this.preReqs=preReqs;
		this.offered=offered;
		this.credits=credits;
	}



	public int getCourseID() {
		return courseID;
	}



	public String getDept() {
		return dept;
	}



	public int getCourseNum() {
		return courseNum;
	}



	public String getCourseName() {
		return courseName;
	}



	public String getCrossListed() {
		return crossListed;
	}



	public int getRequired() {
		return required;
	}



	public int getCapstone() {
		return capstone;
	}



	public String getPreReqs() {
		return preReqs;
	}



	public String getOffered() {
		return offered;
	}



	public int getCredits() {
		return credits;
	}
	
	
	//Might need to change this if we ever want to print out full courses
	public String toString(){
		return "Course = " + dept + " " + new Integer(courseNum).toString();

	}
	
}
