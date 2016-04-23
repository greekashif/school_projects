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
 * Servlet implementation class TVShowServlet
 */
public class TVShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TVShowServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TVShowHelper helper = new TVShowHelper();
		HttpSession session = request.getSession();
		ServletConfig config = this.getServletConfig();
		ServletContext context = config.getServletContext();
		RequestDispatcher dispatcher;

		if (request.getParameter("genre") != null)
		{
			ArrayList<Show> browse = new ArrayList<Show>();
			String genre = request.getParameter("genre");
			browse = helper.browse(genre);
			request.setAttribute("browse", browse);
			if (browse.size() != 0)
			{
				int size = browse.size();
				request.setAttribute("size", size);
			}
			else if (browse.size() == 0)
			{
				browse = null;
			}
			dispatcher = context.getRequestDispatcher("/browse.jsp");
			dispatcher.forward(request, response);
		}
		
		if (request.getParameter("episodeNumber") != null)
		{
			String title = request.getParameter("title");
			int season = Integer.parseInt(request.getParameter("season"));
			int episodeNumber = Integer.parseInt(request.getParameter("episodeNumber"));
			request.setAttribute("title", title);
			request.setAttribute("season", season);
			request.setAttribute("episodeNumber", episodeNumber);
			dispatcher = context.getRequestDispatcher("/review");
			dispatcher.forward(request, response);				
		}
		
		else if (request.getParameter("season") != null)
		{
			String title = request.getParameter("title");
			int season = Integer.parseInt(request.getParameter("season"));

			Season seasons = helper.getSeasons(title, season);
			request.setAttribute("season", seasons);

			ArrayList<Episode> episodes = new ArrayList<Episode>();
			episodes = helper.listEpisodes(title, season);
			if (episodes.size() != 0)
			{
				int size = episodes.size();
				request.setAttribute("size", size);
			}
			else if (episodes.size() == 0)
			{
				episodes = null;
			}
			request.setAttribute("episodes", episodes);
			dispatcher = context.getRequestDispatcher("/season.jsp");
			dispatcher.forward(request, response);
		}
		
		else if (request.getParameter("title") != null)
		{
			String title = request.getParameter("title");
			Show show = helper.getShow(title);
			request.setAttribute("show", show);

			ArrayList<Season> seasons = new ArrayList<Season>();
			seasons = helper.listSeasons(title);
			if (seasons.size() != 0)
			{
				int size = seasons.size();
				request.setAttribute("size", size);
			}
			else if (seasons.size() == 0)
			{
				seasons = null;
			}
			request.setAttribute("seasons", seasons);

			dispatcher = context.getRequestDispatcher("/show.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		TVShowHelper helper = new TVShowHelper();
		HttpSession session = request.getSession();
		ServletConfig config = this.getServletConfig();
		ServletContext context = config.getServletContext();
		RequestDispatcher dispatcher;

		if (request.getParameter("search") != null)
		{
			String searchQuery = request.getParameter("search");
			ArrayList<Show> search = new ArrayList<Show>();
			search = helper.search(searchQuery);
			if (search.size() != 0)
			{
				int size = search.size();
				request.setAttribute("size", size);
			}
			else if (search.size() == 0)
			{
				search = null;
			}
			request.setAttribute("search", search);
			dispatcher = context.getRequestDispatcher("/search.jsp");
			dispatcher.forward(request, response);
		}
		else if (request.getParameter("addShow") != null)
		{
			String error;
			String title = request.getParameter("title");
			int rating = Integer.parseInt(request.getParameter("rating"));
			String genre = request.getParameter("genre");
			String synopsis = request.getParameter("synopsis");
			error = helper.addShow(title, rating, genre, synopsis);
			request.setAttribute("error3", error);
			dispatcher = context.getRequestDispatcher("/mod.jsp");
			dispatcher.forward(request, response);
		}

		else if (request.getParameter("addSeason") != null)
		{
			String error;
			String title = request.getParameter("title");
			int season = Integer.parseInt(request.getParameter("season"));
			int rating = Integer.parseInt(request.getParameter("rating"));
			String synopsis = request.getParameter("synopsis");
			error = helper.addSeason(title, season, rating, synopsis);
			System.out.println(error);
			request.setAttribute("error", error);
			Show show = helper.getShow(title);
			request.setAttribute("show", show);

			ArrayList<Season> seasons = new ArrayList<Season>();
			seasons = helper.listSeasons(title);
			if (seasons.size() != 0)
			{
				int size = seasons.size();
				request.setAttribute("size", size);
			}
			else if (seasons.size() == 0)
			{
				seasons = null;
			}
			request.setAttribute("seasons", seasons);
			dispatcher = context.getRequestDispatcher("/show.jsp");
			dispatcher.forward(request, response);
		}

		else if (request.getParameter("addEpisode") != null)
		{
			String error;
			String title = request.getParameter("title");
			int season = Integer.parseInt(request.getParameter("season"));
			int episodeNumber = Integer.parseInt(request.getParameter("episodeNumber"));
			String episodeName = request.getParameter("episodeName");
			String synopsis = request.getParameter("synopsis");
			error = helper.addEpisode(title, season, episodeNumber, episodeName, synopsis);
			request.setAttribute("error", error);
			Season seasons = helper.getSeasons(title, season);
			request.setAttribute("season", seasons);
			ArrayList<Episode> episodes = new ArrayList<Episode>();
			episodes = helper.listEpisodes(title, season);
			if (episodes.size() != 0)
			{
				int size = episodes.size();
				request.setAttribute("size", size);
			}
			else if (episodes.size() == 0)
			{
				episodes = null;
			}
			request.setAttribute("episodes", episodes);
			dispatcher = context.getRequestDispatcher("/season.jsp");
			dispatcher.forward(request, response);
		}
		else
			doGet(request, response);
	}

}
