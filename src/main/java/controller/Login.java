package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import dto.MyUser;

@WebServlet("/login")
public class Login extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");

		UserDao userDao = new UserDao();

		MyUser myUser = userDao.fetchByEmail(email);

		if (myUser == null) {
			resp.getWriter().print("<h1>Incorrect Email</h1>");
			req.getRequestDispatcher("Login.html").include(req, resp);
		} else {
			if (myUser.getPassword().equals(password)) {
				req.getSession().setAttribute("user", myUser);
				//req.getSession().setMaxInactiveInterval(10);
				resp.getWriter().print("<h1>Login successful</h1>");
				
				req.setAttribute("list", myUser.getTasks());

				req.getRequestDispatcher("Home.jsp").include(req, resp);

			} else {
				resp.getWriter().print("<h1>incorrect password</h1>");
				req.getRequestDispatcher("Login.html").include(req, resp);
			}
		}
	}
}
