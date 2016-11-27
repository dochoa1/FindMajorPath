import java.util.ArrayList;


//IM THINKING THAT WE SHOULD HAVE A GEN_ED_CHECKER CLASS THAT CHECKS GEN_ED REQUIREMENTS. MAYBE IN THE BEGINNING WE CAN
//JUST PROMP THE USER WITH A BUNCH OF QUESTIONS LIKE: HOW MANY CREDITS DO YOU HAVE? or HOW MANY WRITING CLASSES HAVE YOU TAKEN?


/*
 * The object created by this class should check the status of the student's CS major checklist.
 * 
 * The constructor for this class takes an arrayList of Courses, representing
 * the courses that the student has already completed.
 */


public class CSChecker {
	
	ArrayList<Course> courses;
	ArrayList<Course> coursesLeft;
	
	
	
	public CSChecker (ArrayList<Course> courses){
		this.courses = courses;
	}

	
	/*
	 * Method that updates the coursesLeft arrayList so that it contains the courses 
	 * that the student is still required to complete.
	 */

	public void findCoursesLeft(){
		//The below methods should update the coursesLeft arrayList as needed:
		
		//HERE, maybe we should create an instance of the GEN_ED_CHECKER class, and perhaps add required gen-ed 
		//classes to the coursesLeft arrayList
		
		
		//checkRequired();
		//checkElectives();
		//checkMathReqs();
		
		//etc... there are a lot of things that we have to do now.
		
		
		
	}
	
	
	
//	public boolean checkRequired(){
//		if (ArrayUtils.contains(courses,) 
//	            return true;
//		
//	}
//	
	
	
	
}
