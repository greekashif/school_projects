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
 * Servlet implementation class LoginServlet.
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public LoginServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 *      Logs a user in and sends them to the appropriate JSP page.
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		LoginHelper helper = new LoginHelper();
		HttpSession session = request.getSession();
		ServletConfig config = this.getServletConfig();
		ServletContext context = config.getServletContext();
		RequestDispatcher dispatcher;
		// get parameters from the request
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if (helper.isAuthorizedUser(username, password) == true)
		{
			String role;
			role = helper.getRole(username);
			session.setAttribute("role", role);
			session.setAttribute("username", username);
			if (role.equals("Reg"))
			{
				dispatcher = context.getRequestDispatcher("/user.jsp");
				dispatcher.forward(request, response);
			} 
			else if (role.equals("Mod")) 
			{
				dispatcher = context.getRequestDispatcher("/mod.jsp");
				dispatcher.forward(request, response);
			}
			else if (role.equals("Guest"))
			{
				dispatcher = context.getRequestDispatcher("/mod.jsp");
				dispatcher.forward(request, response);
			}

		}

		else if (helper.isAuthorizedUser(username, password) == false) 
		{
			String error = helper.isAuthorizedUserError(username, password);
			request.setAttribute("error", error);
			dispatcher = context.getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
		}
	}
}
