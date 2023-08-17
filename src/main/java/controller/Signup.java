package controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import dto.MyUser;

@WebServlet("/signup")
public class Signup extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String name=req.getParameter("name");
		String email=req.getParameter("email");
		String mobile=req.getParameter("mobile");
		String gender=req.getParameter("gen");
		String adress=req.getParameter("adr");
		String dob=req.getParameter("dob");
		String[] lang=req.getParameterValues("lang");
		String password=req.getParameter("password");
		
		MyUser user=new MyUser();
		user.setAdress(adress);
		user.setEmail(email);
		user.setPassword(password);
		user.setGender(gender);
		user.setLang(lang);
		user.setName(name);
		user.setMobile(Long.parseLong(mobile));
		user.setDob(LocalDate.parse(dob));
		
		UserDao dao=new UserDao();
		MyUser myUser=dao.fetchByEmail(email);
		if (myUser==null) 
		{
			dao.save(user );
			res.setContentType("text/html");
			res.getWriter().print("Account created");
			req.getRequestDispatcher("Login.html").include(req,res);
		}
		else {
				res.getWriter().print("email should not be repeated");
				req.getRequestDispatcher("Signup.html").include(req,res);
			 }
		
//		System.out.println(name);
//		System.out.println(email);
//		System.out.println(mobile);
//		System.out.println(gender);
//		System.out.println(adress);
//		System.out.println(dob);
//		System.out.println(lang);
//		System.out.println(password);
	}
}
