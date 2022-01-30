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

import classes.RankingAggregateData;
import classes.TemplateManager;
import services.DataminerService;
import entities.Area;
import entities.User;
import enums.Usertype;
import exceptions.AreaRetrievalException;
import exceptions.RankingAggregateDataException;

@WebServlet("/RankingPage")
public class GoToRankingPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TemplateManager templateManager;
	@EJB(name = "services/DataminerService")
	private DataminerService dataminerService;

	public GoToRankingPage() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		templateManager = new TemplateManager(getServletContext(), request, response);
		String path = getServletContext().getContextPath() + "/GoToLoginPage";

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (!templateManager.checkUsertype(user, Usertype.PolicyMaker)) {
			templateManager.redirect(path);
			return;
		}

		String message = null;
		boolean isBadRequest = false;
		List<RankingAggregateData> ranking = null;
		List<Area> areas = null;

		try {
			ranking = dataminerService.getRankingAggregateData(0, true, null, null);
			areas = dataminerService.getAllAreas();
			
		} catch (RankingAggregateDataException | AreaRetrievalException e) {
			message = e.getMessage();
			isBadRequest = true;
		}

		if (isBadRequest) {
			templateManager.setVariable("errorMsg", message);
		} else {
			path = "/WEB-INF/RankingPage.html";
			templateManager.setVariable("areas", areas);
			templateManager.setVariable("ranking", ranking);
		}
		templateManager.redirect(path);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
