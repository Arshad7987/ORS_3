package in.co.sunrays.proj3.dto;
/**
 * Contains Subject data
 * 
 * @author SUNRAYS Technologies
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 * 
 */
public class SubjectDTO extends BaseDTO {
	/**
	 * Id foreign key course
	 */
	private long courseId;
	
	/**
	 * name of course
	 */
	private String courseName;

	/**
     *  Name of Subject
     */	
	private String name;
	/**
	 * semester in subjects 
	 */
	private String semester;
	/**
	 * Description of subjects
	 */
	private String description;
	
	
	
	/**
	 *accessor 
	 */

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getKey() {
		// TODO Auto-generated method stub
		return id+"";
	}

	public String getValue() {
		// TODO Auto-generated method stub
		return name;
	}

}
