package com.tcs.ilp.servlet;

import com.tcs.ilp.util.*;
import com.tcs.ilp.bean.*;
import com.tcs.ilp.dao.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AccountServlet
 */
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = null;
		String action = request.getParameter("action");
		AccountBean acc = null;
		
		HttpSession session = request.getSession();
		session.setAttribute("BankName", "Bank of America");
		
		if(action == null) {
			ArrayList<AccountBean> accountList = AccountDAO.viewAll();
			if(accountList != null) {
				request.setAttribute("accounts", accountList);
				url = "/jsp/ViewAllAccounts.jsp";
			}
			else 
				url = "/jsp/home.jsp";
		}
		
		else {
			
		if(action.equals(Constants.ADD)) {
			String error = null;
			String id = request.getParameter("id");
			String name = request.getParameter("acctName");
			String type = request.getParameter("type");
			
			acc = new AccountBean();
			acc.setId(Integer.parseInt(id));
			acc.setName(name);
			acc.setType(type);
			
			if(AccountDAO.addAccount(acc)==0)
				error = "ACCOUND ID MUST BE UNIQUE";
								
			request.setAttribute("error", error);
			url = "/jsp/success.jsp";
				
		}
		
		if(action.equals(Constants.VIEW)) {
			int id = Integer.parseInt(request.getParameter("accId"));
			if((acc = AccountDAO.viewAccount(id))==null)
				request.setAttribute("notFound", "ACCOUNT NOT FOUND");
			
			url = "/jsp/ViewAccount.jsp";
		}
		
		if(action.equals(Constants.UPDATE)) {
			boolean hasUpdated = false;
			int id = Integer.parseInt(request.getParameter("accId"));
					
			if(AccountDAO.viewAccount(id) != null) {
				String name = request.getParameter("name");
				String type = request.getParameter("type");
				acc = new AccountBean();
				acc.setId(id);
				acc.setName(name);
				acc.setType(type);
				hasUpdated = true;
				
				request.setAttribute("hasResult", hasUpdated);
				request.setAttribute("disable", true);
				request.setAttribute("id", id);
			}
			else {
				acc = null;
				request.setAttribute("notFound", "UNABLE TO FIND ACCOUNT");
			}
				
			url = "/jsp/UpdateAccount.jsp";

		}
		
		if(action.equals(Constants.DELETE)) {
			boolean hasResult;
			int id = Integer.parseInt(request.getParameter("accId"));
			if(AccountDAO.deleteAccount(id) == 0) {
				request.setAttribute("notFound", "UNABLE TO DELETE ACCOUNT\n" +
						"WITH ACCOUNT ID " + id);
				hasResult = false;
			}
			else
				hasResult = true;
						
			url = "/jsp/DeleteAccount.jsp";
			request.setAttribute("accId", id);
			request.setAttribute("hasResult", hasResult);
		}
		
		if(action.equals(Constants.DEPOSIT)) {
			
		}
		
		//cast return type -> Object getAttribute(key);
		
		}//else
		
		request.setAttribute("account", acc);

		RequestDispatcher rd = request.getRequestDispatcher(url); 
		rd.forward(request, response);
		
	}

}
