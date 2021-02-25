package in.co.sunrays.proj3.dto;
/**
 * Contains Course data
 * 
 * @author SUNRAYS Technologies
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 * 
 */
public class CourseDTO extends BaseDTO {
	/**
	 * Course Name
	 */
	private String name;
	/**
	 * Description of course
	 */
	private String description;
	/**
	 * duration of course
	 */
	private String duration;

	
	/**
	 * 
	 * accessors
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String descreption) {
		this.description = descreption;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getKey() {
		return id + "";
	}

	public String getValue() {
		return name;
	}

	

}
