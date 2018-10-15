package com.Football2Services.Model;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conection {
	
	public Connection dbConnect(){
	   Connection con=null;
	      try {
	         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	         con = DriverManager.getConnection("jdbc:sqlserver://SQL5006.site4now.net",
	        		 "DB_9D8B6E_prueba_admin", "prueba2018");
	         System.out.println("connected");
	         
	      } catch (Exception e) {
	         //e.printStackTrace();
	    	  	System.out.println(e);
	      }
      return con;
   }
	
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		Conection connServer = new Conection();
	      connServer.dbConnect();
	}*/
	
}



