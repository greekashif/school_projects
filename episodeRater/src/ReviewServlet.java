package rater;

import java.io.IOException;
import java.util.ArrayList;

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
 * Servlet implementation class ReviewServlet
 */
public class ReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReviewServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ReviewHelper helper = new ReviewHelper();
		TVShowHelper th = new TVShowHelper();
		HttpSession session = request.getSession();
		ServletConfig config = this.getServletConfig();
		ServletContext context = config.getServletContext();
		RequestDispatcher dispatcher;
		String username = (String) session.getAttribute("username");
		String role = (String) session.getAttribute("role");

		String title = request.getParameter("title");
		int season = Integer.parseInt(request.getParameter("season"));
		int episodeNumber = Integer.parseInt(request.getParameter("episodeNumber"));
		Episode episode = th.getEpisode(title, season, episodeNumber);		 
		int id = episode.getId();
		ArrayList<Review> review = new ArrayList<Review>();
		//int tvShowID = Integer.parseInt(request.getParameter("tvShowID"));
		//review = helper.getReviews(tvShowID);
		review = helper.getReviews(id);
		int size = review.size();
		String error = request.getParameter("error");
		request.setAttribute("error", error);
		request.setAttribute("episode", episode);
		request.setAttribute("size", size);
		request.setAttribute("review", review);
		request.setAttribute("username", username);
		request.setAttribute("role", role);
		dispatcher = context.getRequestDispatcher("/episode.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ReviewHelper helper = new ReviewHelper();
		HttpSession session = request.getSession();
		ServletConfig config = this.getServletConfig();
		ServletContext context = config.getServletContext();
		RequestDispatcher dispatcher;
		String username = (String) session.getAttribute("username");
		String role = (String) session.getAttribute("role");

		if (request.getParameter("addReview") != null)
		{
			String error;
			String review = request.getParameter("review");
			int tvShowID = Integer.parseInt(request.getParameter("tvShowID"));
			int rating = Integer.parseInt(request.getParameter("rating"));
			if (role.equals("Guest"))
			{
				username = "Anonymous";
			}
			error = helper.addReview(review, tvShowID, rating, username);
			request.setAttribute("error", error);
			String title = request.getParameter("title");
			int season = Integer.parseInt(request.getParameter("season"));
			int episodeNumber = Integer.parseInt(request.getParameter("episodeNumber"));
			request.setAttribute("title", title);
			request.setAttribute("season", season);
			request.setAttribute("episodeNumber", episodeNumber);
		}
		if (request.getParameter("delete") != null)
		{
			String error;
			int reviewID = Integer.parseInt(request.getParameter("reviewID"));
			error = helper.deleteReview(reviewID);
			request.setAttribute("error", error);
			String title = request.getParameter("title");
			int season = Integer.parseInt(request.getParameter("season"));
			int episodeNumber = Integer.parseInt(request.getParameter("episodeNumber"));
			request.setAttribute("title", title);
			request.setAttribute("season", season);
			request.setAttribute("episodeNumber", episodeNumber);
		}
		doGet(request, response);
	}

}
