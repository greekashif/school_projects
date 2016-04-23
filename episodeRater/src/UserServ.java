package rater;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Clint
 * Servlet implementation class UserServ.
 */
public class UserServ extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserServ() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserHelper helper = new UserHelper();
		HttpSession session = request.getSession();
		ServletConfig config = this.getServletConfig();
		ServletContext context = config.getServletContext();
		RequestDispatcher dispatcher;

		if (request.getParameter("create") != null)
		{
			String user = request.getParameter("user");
			String pass = request.getParameter("pass");
			String pass2 = request.getParameter("pass2");
			String error = helper.addUser(user, pass, pass2);
			String username = user;
			String role = "Reg";
			session.setAttribute("role", role);
			session.setAttribute("username", username);
			request.setAttribute("error", error);
			dispatcher = context.getRequestDispatcher("/user.jsp");
			dispatcher.forward(request, response);
		}

		if (request.getParameter("change") != null)
		{
			String error;
			if (session.getAttribute("role").equals("Mod"))
			{
				String user = (String) session.getAttribute("username");
				String pass = request.getParameter("pass");
				String newPass = request.getParameter("newPass");
				String newPass2 = request.getParameter("newPass2");
				error = helper.changePassword(user, pass, newPass, newPass2);
				request.setAttribute("error2", error);
				dispatcher = context.getRequestDispatcher("/mod.jsp");
				dispatcher.forward(request, response);
			}
			else
			{
				String user = (String) session.getAttribute("username");
				String pass = request.getParameter("pass");
				String newPass = request.getParameter("newPass");
				String newPass2 = request.getParameter("newPass2");
				error = helper.changePassword(user, pass, newPass, newPass2);
				request.setAttribute("error", error);
				dispatcher = context.getRequestDispatcher("/user.jsp");
				dispatcher.forward(request, response);

			}
		}

		if (request.getParameter("promote") != null)
		{
			String error;
			String user = request.getParameter("user");
			error = helper.changeRole(user);
			request.setAttribute("error", error);
			dispatcher = context.getRequestDispatcher("/mod.jsp");
			dispatcher.forward(request, response);
		}
	}

}
