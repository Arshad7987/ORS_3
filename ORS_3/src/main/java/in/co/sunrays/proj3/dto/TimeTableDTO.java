package in.co.sunrays.proj3.dto;

import java.util.Date;
/**
 * Contains Time Table data
 * 
 * @author SUNRAYS Technologies
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 * 
 */
public class TimeTableDTO extends BaseDTO{

	/**
	 * Course Id
	 */
	private long courseId;
	/**
	 * Course Name
	 */
	private String courseName;
	/**
	 * Subject Id
	 */
	private long subjectId;
	/**
	 * Subject Name
	 */
	private String subjectName;
	/**
	 * Semester
	 */
	private String semester;
	/**
	 * Exam Date
	 */
	private Date examDate;
	/**
	 * Exam Time
	 */
	private String time;

	/**
	 * Accessors
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

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public Date getExamDate() {
		return examDate;
	}

	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getKey() {
		// TODO Auto-generated method stub
		return id + "";
	}

	public String getValue() {
		// TODO Auto-generated method stub
		return examDate + "";
	}


}
