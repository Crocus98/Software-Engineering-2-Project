package controllers;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringEscapeUtils;

import classes.TemplateManager;
import entities.Discussion;
import entities.User;
import enums.Usertype;
import exceptions.InsertPostException;
import exceptions.PostParametersException;
import exceptions.PostsRetrievalException;
import services.ForumService;

@WebServlet("/Post")
public class GoToPostPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TemplateManager templateManager;
	@EJB(name = "services/ForumService")
	private ForumService forumService;

	public GoToPostPage() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = getServletContext().getContextPath() + "/GoToLoginPage";

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null || !TemplateManager.checkUsertype(user, Usertype.Farmer)) {
			response.sendRedirect(path);
			return;
		}

		String message = null;
		boolean isBadRequest = false;
		Discussion discussion = null;
		int idDiscussion = 0;
		try {
			idDiscussion = Integer.parseInt(request.getParameter("discussion"));
		} catch (Exception e) {
			isBadRequest = true;
			message = "ERROR: Incorrect discussion id selected.";
		}

		if (!isBadRequest) {
			try {
				discussion = forumService.getAllPostsOfDiscussion(idDiscussion);
				if (discussion == null) {
					throw new PostsRetrievalException("ERROR: Could not retrieve discussion with posts.");
				}
			} catch (PostsRetrievalException e) {
				isBadRequest = true;
				message = e.getMessage();
			}
		}

		path = "/WEB-INF/ForumPage.html";
		templateManager = new TemplateManager(getServletContext(), request, response);
		if (isBadRequest) {
			templateManager.setVariable("errorMsg", message);
		} else {
			templateManager.setVariable("discussion", discussion);
		}
		templateManager.setVariable("forum", "false");
		templateManager.redirect(path);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = getServletContext().getContextPath() + "/GoToLoginPage";

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null || !TemplateManager.checkUsertype(user, Usertype.Farmer)) {
			response.sendRedirect(path);
			return;
		}

		String message = null;
		boolean isBadRequest = false;
		String comment = null;
		int idDiscussion = 0;
		Discussion discussion = null;

		try {
			idDiscussion = Integer.parseInt(request.getParameter("discussion"));
			comment = StringEscapeUtils.escapeJava(request.getParameter("comment"));
			if (comment == null || comment.isEmpty()) {
				throw new PostParametersException("[PostParametersException]");
			}
		} catch (Exception e) {
			isBadRequest = true;
			message = "ERROR: Incorrect parameters for creating discussion.";
		}

		if (!isBadRequest) {
			try {
				user = forumService.insertPost(user, comment, idDiscussion);
				session.setAttribute("user", user);
				message = "Discussion created successfully.";
				discussion = forumService.getAllPostsOfDiscussion(idDiscussion);
			} catch (PostsRetrievalException | InsertPostException e) {
				isBadRequest = true;
				message = e.getMessage();
			}
		}

		path = "/WEB-INF/ForumPage.html";
		templateManager = new TemplateManager(getServletContext(), request, response);
		templateManager.setVariable("errorMsg", message);
		if (!isBadRequest) {
			templateManager.setVariable("discussion", discussion);
		}
		templateManager.setVariable("forum", "false");
		templateManager.redirect(path);
	}
}
