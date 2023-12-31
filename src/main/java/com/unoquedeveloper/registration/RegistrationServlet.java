package com.unoquedeveloper.registration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	   String uname=request.getParameter("name");
	   String email=request.getParameter("email");
	   String upwd=request.getParameter("pass");
	   String umobile=request.getParameter("contact");
	   RequestDispatcher dispatcher=null;
	   
	   Connection con=null;
	   
	   try {
		   
		   Class.forName("com.mysql.cj.jdbc.Driver");
		   con = DriverManager.getConnection("jdbc:mysql://localhost:3306/common?useSSL=false","root","DaHaMPusH@1120");
		   
		   PreparedStatement ps= con.prepareStatement("INSERT INTO auth(uname, upwd, uemail, umobile) VALUES (?,?,?,?)");
		   
		   ps.setString(1, uname);
		   ps.setString(2, upwd);
		   ps.setString(3, email);
		   ps.setString(4, umobile);
		   
		   int rowCount = ps.executeUpdate();
		   dispatcher = request.getRequestDispatcher("registration.jsp");
		   if(rowCount>0) {
			   request.setAttribute("status", "Success");
		   }else {
			   request.setAttribute("status", "Failed");
		   }
		   dispatcher.forward(request, response);
	   }catch (Exception e) {
		e.printStackTrace();
	}finally {
		   try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	   
	}

}
