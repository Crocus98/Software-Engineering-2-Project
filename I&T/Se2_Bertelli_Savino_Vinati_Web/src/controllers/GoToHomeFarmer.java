package controllers;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.persistence.NonUniqueResultException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.time.DateUtils;

import classes.ProductionAggregateData;
import classes.TemplateManager;
import classes.Utility;
import entities.Forecast;
import entities.Humidityofsoil;
import entities.User;
import enums.Usertype;
import exceptions.ForecastRetrievalException;
import exceptions.HumidityRetrievalException;
import services.DataminerService;
import services.UserService;

@WebServlet("/HomeFarmer")
public class GoToHomeFarmer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TemplateManager templateManager;
	@EJB(name = "services/UserService")
	private UserService userService;
	@EJB(name = "services/DataminerService")
	private DataminerService dataminerService;

	public GoToHomeFarmer() {
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

		Forecast forecast = null;
		Double waterConsumption = null;
		Humidityofsoil humidity = null;
		ProductionAggregateData summary = null;
		List<String> months = null;

		try {
			forecast = userService.getForecast(user, new Date());
			Date lastYear = DateUtils.addYears(new Date(), -1);
			waterConsumption = user.getFarm().getWaterconsumptionM2(lastYear, false);
			humidity = userService.getHumidity(user);
			months = Utility.getMonths(lastYear);
			summary = dataminerService.getFarmerLastYearProductionSummary(user);
		} catch (ForecastRetrievalException | HumidityRetrievalException | NonUniqueResultException e) {
			isBadRequest = true;
			message = e.getMessage();
		} catch (Exception e) {
			isBadRequest = true;
			message = "ERROR: Server error retrieving summary data.";
		}

		path = "/WEB-INF/HomeFarmer.html";
		templateManager = new TemplateManager(getServletContext(), request, response);
		if (isBadRequest) {
			templateManager.setVariable("errorMsg", message);
		} else {
			templateManager.setVariable("waterConsumption", waterConsumption);
			templateManager.setVariable("forecast", forecast);
			templateManager.setVariable("humidity", humidity);
			templateManager.setVariable("months", months);
			templateManager.setVariable("summary", summary);
		}
		templateManager.redirect(path);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
