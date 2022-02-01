package controllers;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import classes.TemplateManager;
import entities.Discussion;
import entities.User;
import enums.Usertype;
import exceptions.DiscussionsRetrievalException;
import services.ForumService;

@WebServlet("/Forum")
public class GoToForumPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TemplateManager templateManager;
	@EJB(name = "services/ForumService")
	private ForumService forumService;

	public GoToForumPage() {
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
		List<Discussion> discussions = null;
		try {
			discussions = forumService.getAllDiscussions();
		} catch (DiscussionsRetrievalException e) {
			isBadRequest = true;
			message = e.getMessage();
		}

		path = "/WEB-INF/ForumPage.html";
		templateManager = new TemplateManager(getServletContext(), request, response);
		if (isBadRequest) {
			templateManager.setVariable("errorMsg", message);
		} else {
			templateManager.setVariable("discussions", discussions);
		}
		templateManager.redirect(path);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}
