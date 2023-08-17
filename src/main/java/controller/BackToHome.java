package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.MyUser;

@WebServlet("/backtohome")
public class BackToHome extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		MyUser myUser=(MyUser) req.getSession().getAttribute("user");
		if (myUser==null) 
		{
			resp.getWriter().print("<h1>Session expired, login again</h1>");
			req.getRequestDispatcher("Login.html").include(req, resp);
		}else {
				req.setAttribute("list", myUser.getTasks());
				req.getRequestDispatcher("Home.jsp").include(req, resp);
		}
	}
}
