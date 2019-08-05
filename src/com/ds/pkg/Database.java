package com.ds.pkg;

import java.sql.*;  


//JDK 1.7 and above
public class Database {   // Save as "JdbcSelectTest.java"
public static void main(String[] args) {
	System.out.println(GetEntry());
	//SetEntry(5, "dakota", "json2");
}

public static String GetEntry(){
	
	String json = null;
	   try (
	      Connection conn = DriverManager.getConnection(    //creating a connection object and connection to waypoints databse.
	            "jdbc:mysql://localhost:3306/pubsub_db?useSSL=false", "prasanth", "plslogme");
	      Statement stmt = conn.createStatement();         // creating a statement instance
	   ) {
		   
	      String strSelect = "select TOPIC from pubsub_table order by id DESC LIMIT 1" ;
	      System.out.println("The SQL query is: " + strSelect); // Echo For debugging
	      
	      ResultSet rset = stmt.executeQuery(strSelect);   // executing the command on db

	      while(rset.next()) { 
	         json = rset.getString("TOPIC");
	      }
	      

	   } catch(SQLException ex) {
	      ex.printStackTrace();
	   }

	   return json;
	   
}

public static void SetEntry( int id, String topic, String message){
	

	   try (
	      Connection conn = DriverManager.getConnection(   //creating a connection object and connection to waypoints databse.
	            "jdbc:mysql://localhost:3306/pubsub_db?useSSL=false", "prasanth", "plslogme");
	      Statement stmt = conn.createStatement();    // creating a statement instance
	   ) {
		   String sqlInsert = "insert into pubsub_table "  //
	               + "values ( '" + id + "' , '" + topic +"', '" + message + "' )";
	         System.out.println("The SQL query is: " + sqlInsert);  // Echo for debugging
	         
	      int countInserted = stmt.executeUpdate(sqlInsert);   // executing the command on db

	         System.out.println(countInserted + " records inserted.\n");	 // Echo for debugging
	      
	      

	   } catch(SQLException ex) {
	      ex.printStackTrace();
	   }

	
}

}