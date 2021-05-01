package model;
import java.sql.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
public class User
{ //A common method to connect to the DB
private Connection connect()
 {
 Connection con = null;
 try
 {
 Class.forName("com.mysql.jdbc.Driver");

 //Provide the correct details: DBServer/DBName, username, password
 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf_user_management", "paf_user", "^paf_user_pw_000");
 }
 catch (Exception e)
 {e.printStackTrace();}
 return con;
 }
public String insertUser(int user_level, String email, String fname, String lname, String dob, String address, int tp_number)
 {
	String output = ""; 
 try
 {
 Connection con = connect();
 if (con == null)
 {return "Error while connecting to the database for inserting."; }
 // create a prepared statement
 String query = " insert into users (`user_id`, `user_level`, `email`, `fname`, `lname`, `dob`, `address`, `tp_number`)"
 + " values (?, ?, ?, ?, ?, ?, ?, ?)";
 PreparedStatement preparedStmt = con.prepareStatement(query);
 // binding values
 preparedStmt.setInt(1, 0);
 preparedStmt.setInt(2, user_level);
 preparedStmt.setString(3, email);
 preparedStmt.setString(4, fname);
 preparedStmt.setString(5, lname);
 preparedStmt.setString(6, dob);
 preparedStmt.setString(7, address);
 preparedStmt.setInt(8, tp_number);
// execute the statement
 preparedStmt.execute();
 con.close();
 output = "Inserted successfully";
 }
 catch (Exception e)
 {
 output = "Error while inserting the user.";
 System.err.println(e.getMessage());
 }
 return output; 
 }

public String readUsers() {
	String output = ""; 

	try {

		Connection con = connect();
		if (con == null) {
			return "Error while connecting to the database for reading.";
		}
		output = "<table border='1'><tr><th>User ID</th>"
				 + "<th>User Level</th><th>Email</th>"
				 + "<th>First Name</th><th>Last Name</th>"
				 + "<th>Date Of Birth</th><th>Address</th><th>Telephone Number</th>"
				 + "<th>Update</th><th>Remove</th></tr>";
		
		// create a prepared statement
		String query = " select * from users ";
		Statement stmt = con.createStatement();
		 ResultSet rs = stmt.executeQuery(query); 
		// binding values

		
		while (rs.next()) {
			String user_id = Integer.toString(rs.getInt("user_id"));
			String user_level = Integer.toString(rs.getInt("user_level"));
			String email = rs.getString("email");
			String fname = rs.getString("fname");
			String lname = rs.getString("lname");
			String dob = rs.getString("dob");
			String address = rs.getString("address");
			String tp_number = Integer.toString(rs.getInt("tp_number"));
			
			output += "<td>" + user_level + "</td>";
			output += "<td>" + email + "</td>";
			output += "<td>" + fname + "</td>";
			output += "<td>" + lname + "</td>";
			output += "<td>" + dob + "</td>";
			output += "<td>" + address + "</td>";
			output += "<td>" + tp_number + "</td>";
					 
			output += "<td><input name='btnUpdate' type='button' value='Update' "
					+ "class='btnUpdate btn btn-secondary' data-itemid='" + user_id + "'></td>"
					+ "<td><input name='btnRemove' type='button' value='Remove' "
					+ "class='btnRemove btn btn-danger' data-itemid='" + user_id + "'></td></tr>";
			

		}
		con.close();
		 // Complete the html table
		 output += "</table>";

	} catch (Exception e) {

		output = "Error while reading the items.";
		 System.err.println(e.getMessage()); 

	}

	return output; 
}

public String updateUser(int user_id,int user_level, String email, String fname, String lname, String dob, String address, int tp_number)
{
	String output = "error";
	try {
		Connection con = connect();
		if (con == null) {
			return "Error while connecting to the database";
		}

		if (output.equals("invalid")) {
			return output;
		}

		// create a prepared statement for update
		String query = "UPDATE users SET user_level=?,email=?,fname=?,lname=?,dob=?,address=?,tp_number=? WHERE user_id=?";
		PreparedStatement preparedStmt = con.prepareStatement(query);
		// binding values

		 preparedStmt.setInt(1, user_level);
		 preparedStmt.setString(2, email);
		 preparedStmt.setString(3, fname);
		 preparedStmt.setString(4, lname);
		 preparedStmt.setString(5, dob);
		 preparedStmt.setString(6, address);
		 preparedStmt.setInt(7, tp_number);
		 preparedStmt.setInt(8, user_id);
		// execute the statement

		int result = preparedStmt.executeUpdate();

		if (result >= 1) {
			output = "done";

		}
		con.close();

	} catch (Exception e) {
		output = "Error while prosessing the request.";
		System.err.println(e.getMessage());
	}
	return output;
}

public String deleteUser(int user_id) {
	String output = "error";
	try {
		Connection con = connect();
		if (con == null) {
			return "Error while connecting to the database";
		}
		// create a prepared statement
		String query = " delete from users where user_id = ? ";
		PreparedStatement preparedStmt = con.prepareStatement(query);
		// binding values

		preparedStmt.setInt(1, user_id);

		// execute the statement

		int result = preparedStmt.executeUpdate();

		if (result >= 1) {
			output = "done";

		}
		con.close();

	} catch (Exception e) {
		output = "Exception";
		System.err.println(e.getMessage());
	}
	return output;
}
	
}
