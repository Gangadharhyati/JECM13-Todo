package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import dto.MyUser;
import dto.Task;

@WebServlet("/deletetask")
public class DeleteTask extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{	
		MyUser user=(MyUser)req.getSession().getAttribute("user");
		if (req.getSession().getAttribute("user")==null) 
		{
			resp.getWriter().print("<h1>Session expired, login again</h1>");
			req.getRequestDispatcher("Login.html").include(req, resp);
		}else {
				int id=Integer.parseInt(req.getParameter("id"));
				UserDao dao=new UserDao();
				Task task=dao.fetchTask(id);
				if (task==null) 
				{
					resp.getWriter().print("<h1>Session expired, login again</h1>");
					req.getRequestDispatcher("Login.html").include(req, resp);
				}
				else {
				//remove mapping
				user.getTasks().remove(task);

				dao.update(user);

				dao.remove(task);

				MyUser user2 = dao.fetchByEmail(user.getEmail());

				req.getSession().setAttribute("user", user2);

				resp.getWriter().print("<h1 style='color:green'>Task Deleted Success</h1>");
				req.setAttribute("list", user2.getTasks());
				req.getRequestDispatcher("Home.jsp").include(req, resp);
				}
				
		}
	}
}
