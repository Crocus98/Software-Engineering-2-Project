package controllers;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import classes.TemplateManager;
import entities.Forecast;
import entities.User;
import enums.Usertype;
import exceptions.ForecastRetrievalException;
import services.UserService;


@WebServlet("/HomeFarmer")
public class GoToHomeFarmer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TemplateManager templateManager;
	@EJB(name = "services/UserService")
	private UserService userService;

	public GoToHomeFarmer() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		templateManager = new TemplateManager(getServletContext(), request, response);
		String path = getServletContext().getContextPath() + "/GoToLoginPage";

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null || !templateManager.checkUsertype(user, Usertype.Farmer)) {
			templateManager.redirect(path);
			return;
		}
		
		String message = null;
		boolean isBadRequest = false;
		
		Forecast forecast = null;
		
		try {
			forecast = userService.getForecast();
		}catch(ForecastRetrievalException e) {
			isBadRequest = true;
			message = e.getMessage();
		}
		
		path = "/WEB-INF/HomeFarmer.html";
		templateManager = new TemplateManager(getServletContext(), request, response);
		if(isBadRequest) {
			templateManager.setVariable("errorMsg", message);
		}else {
			templateManager.setVariable("forecast", forecast);
		}
		templateManager.redirect(path);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}
}
