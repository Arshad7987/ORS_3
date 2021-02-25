
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;

public class JsonDemo {

public static void main(String[] args) {
	
List list=new ArrayList<>();
list.add("naqeeb");
list.add("khan");
list.add("kpp");
List list2=new ArrayList<>();
list2.add("Raj");
list2.add("Meena");
list2.add("kpp");
JSONObject json=new JSONObject();
JSONArray jarray=new JSONArray();
jarray.put(list);
jarray.put(list2);
System.out.println(list);

System.out.println(jarray);
Gson gson =new Gson();
gson.toJson(jarray);
System.out.println(gson);


	
	
	
}

}
