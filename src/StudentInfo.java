import java.util.ArrayList;

/**
 * 
 * @author Danny and Kai
 *
 */

public class StudentInfo {

	//We also have to add a list of majors to studentInfo
	
	ArrayList<Course> studentCourses;
	
	int year;
	
	//Here we can set flags to determine if certain requirements have been met
	//Example:
	//CS requirements
	Boolean comp123;
	Boolean comp124;
	Boolean comp221;
	Boolean comp225;
	Boolean comp240;
	Boolean comp261;
	Boolean math279;
	int mathCourse; //This must be greater then or equal to 2, math 279 does not count towards these two. 
	int electives; //This must be greater than or equal to 3. Must be satisfied by courses 300 - 500. 
	Boolean capstone; //Satisfied by comp 342, comp 346, comp 380, comp 440. comp 445, comp 479, comp 484. One of these courses must be taken
	//in the junior year or fall of the senior year. 

	//General Requirements
	Boolean writing;
	Boolean usID;
	Boolean internationalism;
	Boolean quantitative;
	Boolean language; //This could potentially take up to 4 classes. 
	int socialSci; //This must be greater than or equal to two.
	int naturalSci; //This also includes math classes, must be greater than or equal to two. 
	int humanFArts; //Humanities/Fine Arts, this must be greater than or equal to 3. 


	
	
	
	public StudentInfo(){
		
	}
	

	public ArrayList<Course> getStudentCourses() {
		return studentCourses;
	}
	public void setStudentCourses(ArrayList<Course> studentCourses) {
		this.studentCourses = studentCourses;
	}
	public Boolean getComp123() {
		return comp123;
	}
	public void setComp123(Boolean comp123) {
		this.comp123 = comp123;
	}
	public Boolean getComp124() {
		return comp124;
	}
	public void setComp124(Boolean comp124) {
		this.comp124 = comp124;
	}
	public Boolean getComp221() {
		return comp221;
	}
	public void setComp221(Boolean comp221) {
		this.comp221 = comp221;
	}
	public Boolean getComp225() {
		return comp225;
	}
	public void setComp225(Boolean comp225) {
		this.comp225 = comp225;
	}
	public Boolean getComp240() {
		return comp240;
	}
	public void setComp240(Boolean comp240) {
		this.comp240 = comp240;
	}
	public Boolean getComp261() {
		return comp261;
	}
	public void setComp261(Boolean comp261) {
		this.comp261 = comp261;
	}
	public Boolean getMath279() {
		return math279;
	}
	public void setMath279(Boolean math279) {
		this.math279 = math279;
	}
	public int getMathCourse() {
		return mathCourse;
	}
	public void setMathCourse(int mathCourse) {
		this.mathCourse = mathCourse;
	}
	public int getElectives() {
		return electives;
	}
	public void setElectives(int electives) {
		this.electives = electives;
	}
	public Boolean getCapstone() {
		return capstone;
	}
	public void setCapstone(Boolean capstone) {
		this.capstone = capstone;
	}
	public Boolean getWriting() {
		return writing;
	}
	public void setWriting(Boolean writing) {
		this.writing = writing;
	}
	public Boolean getUsID() {
		return usID;
	}
	public void setUsID(Boolean usID) {
		this.usID = usID;
	}
	public Boolean getInternationalism() {
		return internationalism;
	}
	public void setInternationalism(Boolean internationalism) {
		this.internationalism = internationalism;
	}
	public Boolean getQuantitative() {
		return quantitative;
	}
	public void setQuantitative(Boolean quantitative) {
		this.quantitative = quantitative;
	}
	public Boolean getLanguage() {
		return language;
	}
	public void setLanguage(Boolean language) {
		this.language = language;
	}
	public int getSocialSci() {
		return socialSci;
	}
	public void setSocialSci(int socialSci) {
		this.socialSci = socialSci;
	}
	public int getNaturalSci() {
		return naturalSci;
	}
	public void setNaturalSci(int naturalSci) {
		this.naturalSci = naturalSci;
	}
	public int getHumanFArts() {
		return humanFArts;
	}
	public void setHumanFArts(int humanFArts) {
		this.humanFArts = humanFArts;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
		
}
