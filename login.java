package loginregisterform;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mysql.cj.protocol.Resultset;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class login extends HttpServlet{
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// TODO Auto-generated method stub
	//super.doPost(req, resp);
	String myemail=req.getParameter("myemail");
	String mypass=req.getParameter("mypass");

	try {
		Connection con=Dbconnection.getConnection();
		String select_sql_query="SELECT * FROM register WHERE email=? AND password=?";
		PreparedStatement ps=con.prepareStatement(select_sql_query);
		ps.setString(1, myemail);
		ps.setString(2, mypass);
		
		ResultSet rs=ps.executeQuery();
		if(rs.next())
		{
			user user=new user();
			user.setName(rs.getString("name"));
			user.setEmail(rs.getString("email"));
			user.setCity(rs.getString("city"));
			
			HttpSession session=req.getSession();
			session.setAttribute("session_user",user);
			
			RequestDispatcher rd=req.getRequestDispatcher("/profile.jsp");
			rd.forward(req, resp);
		}
		else {
			System.out.println("emailid and password did not match");
		    RequestDispatcher rd=req.getRequestDispatcher("/login.html");
		   rd.include(req, resp);
		}
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
}
}
