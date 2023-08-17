package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import dto.MyUser;
import dto.Task;

@WebServlet("/addtask")
public class AddTask extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String description = req.getParameter("description");
		int days = Integer.parseInt(req.getParameter("days"));

		Task task = new Task();
		task.setName(name);
		task.setCompletiondate(LocalDate.now().plusDays(days));
		task.setDescription(description);
		task.setTaskdate(LocalDate.now());

		UserDao userDao = new UserDao();
		userDao.save(task);

		MyUser myUser = (MyUser) req.getSession().getAttribute("user");
		List<Task> list = myUser.getTasks();
		if (list == null)
			list = new ArrayList<Task>();
		list.add(task);
		myUser.setTasks(list);

		userDao.update(myUser);
		
		resp.getWriter().print("<h1>Task added successfully</h1>");
		req.setAttribute("list",myUser.getTasks());
		req.getRequestDispatcher("Home.jsp").include(req, resp);
	}
}
