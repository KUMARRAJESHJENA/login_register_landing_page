package loginregisterform;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.jasper.tagplugins.jstl.core.Out;

import com.mysql.cj.xdevapi.PreparableStatement;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/reg")
public class Register extends HttpServlet{
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// TODO Auto-generated method stub
	//super.doPost(req, resp);
	String myname=req.getParameter("name");
	String myemail=req.getParameter("email");
	String mypass=req.getParameter("pass");
	String mycity=req.getParameter("city");
	try {
		Connection con = Dbconnection.getConnection();
		String insr_sql_query="INSERT INTO register VALUES(?,?,?,?)";
	    PreparedStatement ps= con.prepareStatement(insr_sql_query);
	    ps.setString(1,myname );
	    ps.setString(2, myemail);
		ps.setString(3, mypass);
		ps.setString(4, mycity);
		int count=ps.executeUpdate();
		if(count > 0) {
			System.out.println("register succesfully");
			RequestDispatcher rd = req.getRequestDispatcher("/login.html");
			rd.include(req, resp);
		}
		else {
			System.out.println("register fail");
			RequestDispatcher rd = req.getRequestDispatcher("/reg.html");
			rd.include(req, resp);
		}
	}
	catch (Exception e) {
	e.printStackTrace();
		// TODO: handle exception
	}
}
}
