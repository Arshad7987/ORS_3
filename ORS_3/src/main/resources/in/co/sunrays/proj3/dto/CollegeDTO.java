package in.co.sunrays.proj3.dto;

/**
 * College JavaBean encapsulates College attributes
 * 
 * 
 * @version 1.0
 * @Copyright (c) SunilOS
 * 
 */
public class CollegeDTO extends BaseDTO {

	/*protected long id;
	protected String createdBy;
	protected String modifiedBy;
	protected Timestamp createdDateTime;
	protected Timestamp modifiedDateTime;*/
	
	
	private String name;
    private String address;
    private String state;
    private String city;
    private String phoneNo;
    
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	public String getKey() {
		return id+"";
	}
	public String getValue() {
		return name;
	}
	
    
    
}
