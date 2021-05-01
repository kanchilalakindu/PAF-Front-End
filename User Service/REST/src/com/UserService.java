package com;
import model.User;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/User")
public class UserService
{
 User user = new User();
 RequestValidator requestValidator =  new RequestValidator();
@GET
@Path("/")
@Produces(MediaType.TEXT_HTML)
// read cart
public String ReadUsers() {

	return user.readUsers();
}

@POST
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public String insertUser(String data) {
	JsonObject result = new JsonObject();
	//result.addProperty("status", "error");
	int user_level = 0;
	String email ;
	String fname;
	String lname;
	String dob;
	String address;
	int tp_number = 0;

	try {
		JsonObject userObject = new JsonParser().parse(data).getAsJsonObject();
		
		
			user_level = userObject.get("user_level").getAsInt();
			email = userObject.get("email").getAsString();
			fname = userObject.get("fname").getAsString();
			lname = userObject.get("lname").getAsString();
			dob = userObject.get("dob").getAsString();
			address = userObject.get("address").getAsString();
			tp_number = userObject.get("tp_number").getAsInt();
			String output = user.insertUser(user_level, email, fname, lname, dob, address, tp_number);
			return output;
			

	} catch (Exception e) {
		e.printStackTrace();
		result.addProperty("status", "error");
	}

	return result.toString();
}

@PUT
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public String updateUser(String userData)
{
//Convert the input string to a JSON object
 JsonObject userObject = new JsonParser().parse(userData).getAsJsonObject();
 
//Read the values from the JSON object
 int user_id = userObject.get("user_id").getAsInt();
 int user_level = userObject.get("user_level").getAsInt();
 String email = userObject.get("email").getAsString();
 String fname = userObject.get("fname").getAsString();
 String lname = userObject.get("lname").getAsString();
 String dob = userObject.get("dob").getAsString();
 String address = userObject.get("address").getAsString();
 int tp_number = userObject.get("tp_number").getAsInt();
 String output = user.updateUser(user_id,user_level, email, fname, lname, dob, address, tp_number);
 return output ;
}

@DELETE
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public String deleteUser(String data) {
	String returnValue = "failed";
	JsonObject userObject = new JsonParser().parse(data).getAsJsonObject();
	
	int userId = userObject.get("user_id").getAsInt();
		returnValue = user.deleteUser(userId);
		return "{status:" + returnValue + "}";
}

}


