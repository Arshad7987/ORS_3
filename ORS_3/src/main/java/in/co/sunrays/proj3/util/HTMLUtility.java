package in.co.sunrays.proj3.util;
import in.co.sunrays.proj3.dto.DropdownList;
import in.co.sunrays.proj3.dto.RoleDTO;
//import in.co.sunrays.proj4.model.BaseModel;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.Collection;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
/**
 * HTML Utility class to produce HTML contents like Dropdown List.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 * 
 */

public class HTMLUtility {

	/**
	 * Create HTML SELECT list from MAP paramters values
	 * 
	 * @param name
	 * @param selectedVal
	 * @param map
	 * @return
	 */

	public static String getList(String name, String selectedVal, HashMap<String, String> map) {

		StringBuffer sb = new StringBuffer("<select  class='form-control' name='" + name + "'>");
    
    
		Set<String> keys = map.keySet();
          
		String val = null;
		
		sb.append("<option selected value=''>--------------Select-------------</option>");
		for (String key : keys) {
			val = map.get(key);
			if (key.trim().equals(selectedVal)) {
				
				sb.append("<option  selected value='" + key + "'>" + val + "</option>");
			} else {
				
				sb.append("<option  value='" + key + "'>" + val + "</option>");
				
			}
		}
		sb.append("</select>");
		return sb.toString();
	}
	/**
	 * Create HTML SELECT Semester Sorted list from MAP paramters values
	 * 
	 * @param name
	 * @param selectedVal
	 * @param map
	 * @return
	 */
	public static String getSemList(String name, String selectedVal, HashMap<String, String> map) {

		StringBuffer sb = new StringBuffer("<select class='form-control' name='" + name + "'>");
    
    TreeMap<String, String> tmap=new TreeMap<>(map);
    
		
		//Set<String> keys = map.keySet();
    Set<String> keys=tmap.keySet();
		String val = null;
		
		sb.append("<option selected value=''>--------------Select-------------</option>");
		for (String key : keys) {
			
			val = map.get(key);
			//System.out.println("keys="+key+"="+val);
			if (key.trim().equals(selectedVal)) {
				
				sb.append("<option selected value='" + key + "'>" + val + "</option>");
			
			} else {
				
				sb.append("<option value='" + key + "'>" + val + "</option>");
				
			//	sb.append("<option></option><input type='checkbox' value='"+key +" 'name=''>"+ val);
			}
		}
		sb.append("</select>");
		return sb.toString();
	}

	
	
	/**
	 * Create HTML SELECT List from List parameter
	 * 
	 * @param name
	 * @param selectedVal
	 * @param list
	 * @return
	 */
	public static String getList(String name, String selectedVal, List list) {

		 Collections.sort(list);

	 List<DropdownList> dd = (List<DropdownList>) list;

		StringBuffer sb = new StringBuffer("<select  class='form-control' name='" + name + "'>");
		sb.append("<option  selected value='0'>-------------Select-------------</option>");
if(list.size()>0){
		String key = null;
		String val = null;

	

			 for (DropdownList obj : dd) {
			key = obj.getKey();
			val = obj.getValue();

			if (key.trim().equals(selectedVal)) {
				sb.append("<option selected value='" + key + "'>" + val + "</option>");
			} else {
				sb.append("<option value='" + key + "'>" + val + "</option>");
			}
		}
			 }
		sb.append("</select>");
		return sb.toString();
	}

	public static String getList(String name, String selectedVal, HashMap<String, String> map, boolean select) {

		StringBuffer sb = new StringBuffer("<select  class='form-control' name='" + name + "'>");

		Set<String> keys = map.keySet();
		String val = null;

		if (select) {

			sb.append("<option selected value=''>------------Select------------</option>");
		}

		for (String key : keys) {
			val = map.get(key);
			if (key.trim().equals(selectedVal)) {
				sb.append("<option selected value='" + key + "'>" + val + "</option>");
			} else {
				sb.append("<option value='" + key + "'>" + val + "</option>");
			}
		}
		sb.append("</select>");
		return sb.toString();
	}

	public static String getInputErrorMessages(HttpServletRequest request) {

		Enumeration<String> e = request.getAttributeNames();

		StringBuffer sb = new StringBuffer("<UL>");
		String name = null;

		while (e.hasMoreElements()) {
			name = e.nextElement();
			if (name.startsWith("error.")) {
				sb.append("<LI class='error'>" + request.getAttribute(name) + "</LI>");
			}
		}
		sb.append("</UL>");
		return sb.toString();
	}

	/**
	 * Returns Error Message with HTML tag and CSS
	 * 
	 * @param request
	 * @return
	 */
	/*public static String getErrorMessage(HttpServletRequest request) {
		String msg = ServletUtility.getErrorMessage(request);
		if (!DataValidator.isNull(msg)) {
			msg = "<p class='st-error-header'>" + msg + "</p>";
		}
		return msg;
	}
*/
	/**
	 * Returns Success Message with HTML tag and CSS
	 * 
	 * @param request
	 * @return
	 */

	/*public static String getSuccessMessage(HttpServletRequest request) {
		String msg = ServletUtility.getSuccessMessage(request);
		if (!DataValidator.isNull(msg)) {
			msg = "<p class='st-success-header'>" + msg + "</p>";
		}
		return msg;
	}*/

	/**
	 * Creates submit button if user has access permission.
	 * 
	 * @param label
	 * @param access
	 * @param request
	 * @return
	 */
	public static String getSubmitButton(String label, boolean access, HttpServletRequest request) {

		String button = "";

		if (access) {
			button = "<input type='submit' name='operation'    value='" + label + "' >";
		}
		return button;
	}

	/*public static String getCommonFields(HttpServletRequest request) {

		BaseModel model = ServletUtility.getModel(request);

		StringBuffer sb = new StringBuffer();

		sb.append("<input type='hidden' name='id' value=" + model.getId() + ">");
		
		 * sb.append("<input type='hidden' name='createdBy' value=" +
		 * DataUtility.getString(model.getCreatedBy()) + ">");
		 * sb.append("<input type='hidden' name='modifiedBy' value=" +
		 * DataUtility.getString(model.getModifiedBy()) + ">");
		 * sb.append("<input type='hidden' name='createdDatetime' value=" +
		 * DataUtility.getTimestamp(model.getCreatedDatetime()) + ">");
		 * sb.append("<input type='hidden' name='modifiedDatetime' value=" +
		 * DataUtility.getTimestamp(model.getModifiedDatetime()) + ">");
		 
		return sb.toString();
	}
*/
	
	
	
	
}
