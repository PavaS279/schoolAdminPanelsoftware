package studentServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/register")
public class RegisterSer extends HttpServlet {
	
	
	private final static String query = "insert into student (  std_name, std_mobile, std_email, std_dob, std_address, std_gender,std_grade ) values (?,?,?,?,?,?,?)";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        //get PrintWriter
        PrintWriter pw = res.getWriter();
        //set content type
        res.setContentType("text/html");
        //link the bootstrap
        pw.println("<link rel='stylesheet' href='css/bootstrap.css'></link>");
        //get the values
        String name = req.getParameter("userName");
        String mobile = req.getParameter("mobile");
        String email = req.getParameter("email");
        String dob = req.getParameter("dob");
        String address = req.getParameter("address");
        String gender = req.getParameter("gender");
        String grade = req.getParameter("grade");

        //load the JDBC driver
       
        //generate the connection
        try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/schooladmin","root","tiger");
                PreparedStatement ps = con.prepareStatement(query);){
            //set the values
            ps.setString(1, name);
            ps.setString(2, mobile);
            ps.setString(3, email);
            ps.setString(4, dob);
            ps.setString(5, address);
            ps.setString(6, gender);
            ps.setString(7, grade);
            //execute the query
            int count = ps.executeUpdate();
            pw.println("<div class='card' style='margin:auto;width:300px;margin-top:100px'>");
            if(count==1) {
                pw.println("<h2 class='bg-danger text-light text-center'>Record Registered Successfully</h2>");
            }else {
                pw.println("<h2 class='bg-danger text-light text-center'>Record Not Registered</h2>");
            }
        }catch(SQLException se) {
            pw.println("<h2 class='bg-danger text-light text-center'>"+se.getMessage()+"</h2>");
            se.printStackTrace();
        }catch(Exception e) {
            e.printStackTrace();
        }
        pw.println("<a href='home.html'><button class='btn btn-outline-success'>Home</button></a>");
        pw.println("</div>");
        //close the stram
        pw.close();
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req,res);
    }

		
		
}


