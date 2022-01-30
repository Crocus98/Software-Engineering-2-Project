package controllers;

import java.io.IOException;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.time.DateUtils;
import classes.TemplateManager;
import entities.User;
import enums.Usertype;
import services.DataminerService;


@WebServlet("/HomePolicyMaker")
public class GoToHomePolicyMaker extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TemplateManager templateManager;
	@EJB(name = "services/DataminerService")
	private DataminerService dataminerService;

	public GoToHomePolicyMaker() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		templateManager = new TemplateManager(getServletContext(), request, response);
		String path = getServletContext().getContextPath() + "/GoToLoginPage";
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user == null || !templateManager.checkUsertype(user, Usertype.PolicyMaker)) {
			templateManager.redirect(path);
			return;
		}
		
		String message = null;
		boolean isBadRequest = true;
		
		String bestFarmer = null;
		String worstFarmer = null;
		String bestArea = null;
		String worstArea = null;
		
		try {
			Date last_year = DateUtils.addYears(new Date(), -1);
			User temp = dataminerService.getBestFarmer(true, null, last_year);
			bestFarmer = temp.getName() + " " + temp.getSurname() +"\n"+ temp.getMail() + "\n"+ temp.getFarm().getArea().getName();
			temp = dataminerService.getBestFarmer(false, null, last_year);
			worstFarmer = temp.getName() + " " + temp.getSurname() +"\n"+ temp.getMail() + "\n"+ temp.getFarm().getArea().getName();
		}
		catch(Exception e){
			isBadRequest = true;
			message = "Could not retrieve data";
		}
		
		path = "/WEB-INF/HomePolicyMaker.html";
		templateManager = new TemplateManager(getServletContext(), request, response);
		templateManager.setVariable("bestFarmer", bestFarmer);
		templateManager.setVariable("worstFarmer", worstFarmer);
		templateManager.redirect(path);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}
}
