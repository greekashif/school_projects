package com.tcs.ilp.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String pwd = request.getParameter("pwd");
		String url = null;
		String error = null;
		HttpSession session = request.getSession();
		
		if(username!=null && pwd != null) {
			if(username.equals("Kuldip") && pwd.equals("burgerkonig")) {
				session = request.getSession(); 
				session.setMaxInactiveInterval(30);
				session.setAttribute("name", username);
				url = "/jsp/home.jsp";
				error = null;
			}
			else {
				url = "/jsp/login.jsp";
				error = "Invalid login, try again!";
			}
		}
				
		if(request.getParameter("action")!=null) {
			if(request.getParameter("action").equals("logout")) {
				session = request.getSession();
				session.invalidate();
				error = null;
				url = "/jsp/login.jsp";
			}
		}
		
		request.setAttribute("error", error);
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
			
	}

}
