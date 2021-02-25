package in.co.sunrays.proj3.dto;

import java.util.Date;
/**
 * Contains Faculty data
 * 
 * @author SUNRAYS Technologies
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 * 
 */
public class FacultyDTO extends BaseDTO {

	/**
	 * college id
	 */
	private long collegeId;
	/**
	 * college name
	 */
	private String collegeName;
	/**
	 * course id
	 */
	private long courseId;
	/**
	 * course name
	 */
	private String courseName;
	/**
	 * subject id
	 */
	private long subjectId;
	/**
	 * subject name
	 */
	private String subjectName;
	/**
	 * faculty first name
	 */
	private String firstName;
	/**
	 * faculty last name
	 */
	private String lastName;
	/**
	 * gender
	 */
	private String Gender;	
	/**
	 * date of birth
	 */
	private Date dob;
	
	/**
	 * mobile number
	 */
	private String mobNo;

	/**
	 * faulty email id
	 */
	private String emailId;

	/**
	 * qualification
	 */
	private String qualification;
	

	
	

	/**
	 * accessors
	 */

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

	public long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public long getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(long collegeId) {
		this.collegeId = collegeId;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getMobNo() {
		return mobNo;
	}

	public void setMobNo(String mobNo) {
		this.mobNo = mobNo;
	}
	
	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}
	public String getKey() {
		return id + "";
	}

	public String getValue() {
		return firstName + " " + lastName;
	}

}
