package controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import exceptions.FilterParametersException;
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

		String path = getServletContext().getContextPath() + "/GoToLoginPage";

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null || !TemplateManager.checkUsertype(user, Usertype.PolicyMaker)) {
			response.sendRedirect(path);
			return;
		}

		String message = null;
		int limitNumber = 0;
		int idArea = -1;
		boolean desc = true;
		Date fromDate = null;
		
		try {
			limitNumber = Integer.parseInt(request.getParameter("limit_number"));
			desc = Boolean.parseBoolean(request.getParameter("order_mode"));
			idArea = Integer.parseInt(request.getParameter("idArea"));
			String fromDateString = request.getParameter("start_date");
			if(!fromDateString.isEmpty()){
	 			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	 			fromDate = dateFormat.parse(fromDateString);
 			}
 			
 			if(limitNumber < 0 || idArea < 0 || (!fromDateString.isEmpty() && fromDate.after(new Date()))) {
 				message = "[RankingPageController] ERROR: Incorrect filter parameters.";
 				throw new FilterParametersException("Invalid parameters");
 			}
		} catch (Exception e) {
			limitNumber = 0;
			idArea = -1;
			desc = true;
			fromDate = null;
		}

		List<RankingAggregateData> ranking = null;
		List<Area> areas = null;

		try {
			ranking = dataminerService.getRankingAggregateData(limitNumber, desc, idArea, fromDate);
			areas = dataminerService.getAllAreas();
		} catch (RankingAggregateDataException | AreaRetrievalException  e) {
			message = e.getMessage();
		}
		
		templateManager = new TemplateManager(getServletContext(), request, response);
		path = "/WEB-INF/RankingPage.html";
		templateManager.setVariable("areas", areas);
		templateManager.setVariable("errorMsg", message);
		templateManager.setVariable("areas", areas);
		templateManager.setVariable("ranking", ranking);
		templateManager.redirect(path);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
