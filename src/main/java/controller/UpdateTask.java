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
import dto.Task;

@WebServlet("/updatetask")
public class UpdateTask extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		MyUser user=(MyUser)req.getSession().getAttribute("user");
		if (req.getSession().getAttribute("user")==null) 
		{
			resp.getWriter().print("<h1>Session expired, login again</h1>");
			req.getRequestDispatcher("Login.html").include(req, resp);
		}else 
		{
			int id=Integer.parseInt(req.getParameter("id"));
			String name = req.getParameter("name");
			String description = req.getParameter("description");
			int days = Integer.parseInt(req.getParameter("days"));

			Task task = new Task();
			task.setId(id);
			task.setName(name);
			task.setCompletiondate(LocalDate.now().plusDays(days));
			task.setDescription(description);
			task.setTaskdate(LocalDate.now());

			UserDao dao = new UserDao();
			dao.update(task);
			
			MyUser myUser2=dao.fetchByEmail(user.getEmail());
			req.getSession().setAttribute("user",myUser2 );
			

			//resp.getWriter().print("<h1 style='color:green'>Task Updated Success</h1>");
			req.setAttribute("list", myUser2.getTasks());
			req.getRequestDispatcher("Home.jsp").include(req, resp);
		}
	}
}
