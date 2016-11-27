import java.util.Objects;

/*
 * Object that contains a department and a Course ID number, (i.e. COMP 123)
 */

public class CourseID {
	
	String dept;
	int courseNum;
	

	public CourseID(String dept,int courseNum){
		this.courseNum = courseNum;
		this.dept= dept;
	}

	
	public int getCourseNum() {
		return courseNum;
	}

	public String getDept() {
		return dept;
	}
	
	public String toString(){
		return dept + " " + new Integer(courseNum).toString();
	}
	
	
	/*
	 * This is shitty, has to be fixed
	 */
	@Override
	public boolean equals(Object object) {
		//self check
		if (this == object){
			return true;
		}
		//null check
		if (!(object instanceof CourseID)){
			return false;
		}
	    CourseID testID = (CourseID)object;
		if (testID.getDept().equals(dept)){
			if (testID.getCourseNum() == courseNum){			
				return true;
			}
		}
		return false;
	}
	
	
	//Had to override this for the get and containsKey methods to work. Probably has something to do 
	@Override
	public int hashCode() {
		return Objects.hash(dept, courseNum);
	}
	
}
