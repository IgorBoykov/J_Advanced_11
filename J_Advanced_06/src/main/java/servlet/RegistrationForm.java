package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.User;
import domain.UserRole;
import service.UserService;
import serviceImpl.UserServiceImpl;

@WebServlet("/")
public class RegistrationForm extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private UserService userService = UserServiceImpl.getUserService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("email");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String password = request.getParameter("password");

		if (!email.isEmpty() && !firstname.isEmpty() && !lastname.isEmpty() && !password.isEmpty()) {
			userService.create(new User(email, firstname, lastname, UserRole.USER.toString(), password));
		}

		request.getRequestDispatcher("homepage.jsp").forward(request, response);
	}

}
