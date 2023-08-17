package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import dto.Task;
import dto.MyUser;

@WebServlet("/changestatus")
public class ChangeStatus extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		MyUser user = (MyUser) req.getSession().getAttribute("user");
		if (user == null) {
			resp.getWriter().print("<h1 style='color:red'>Session Expired</h1>");
			req.getRequestDispatcher("Login.html").include(req, resp);
		} else {

			int id = Integer.parseInt(req.getParameter("id"));

			UserDao dao = new UserDao();
			Task task = dao.fetchTask(id);
			
			if(task.isStatus())
				task.setStatus(false);
			else
			task.setStatus(true);
			
			dao.update(task);
			
			//Logic to update Session
			MyUser myUser2=dao.fetchByEmail(user.getEmail());
			req.getSession().setAttribute("user",myUser2 );
			

			resp.getWriter().print("<h1 style='color:green'>status changed</h1>");
			req.setAttribute("list", myUser2.getTasks());
			req.getRequestDispatcher("Home.jsp").include(req, resp);
		}
	}
}
