package com.automation.xero.base;



	import java.sql.*;  
	public class Database{  
	public static void main(String args[]){  
	try{  
	//Class.forName("com.mysql.jdbc.Driver");  


    Connection con=DriverManager.getConnection(  
	"jdbc:mysql://localhost:3306/testDB","root","root");  
	//here sonoo is database name, root is username and password  
	Statement stmt=con.createStatement();  
	ResultSet rs=stmt.executeQuery("select * from customer");  
	while(rs.next())  
	System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
	con.close();  
	}
	catch(Exception e){ System.out.println(e);
	}  
	}  
	}