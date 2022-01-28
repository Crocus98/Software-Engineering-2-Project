package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import classes.TemplateManager;
import entities.User;
import enums.Usertype;


@WebServlet("/HomePolicyMaker")
public class GoToHomePolicyMaker extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TemplateManager templateManager;

	public GoToHomePolicyMaker() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		templateManager = new TemplateManager(getServletContext(), request, response);
		String path = getServletContext().getContextPath() + "/GoToLoginPage";
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(!templateManager.checkUsertype(user, Usertype.PolicyMaker)) {
			templateManager.redirect(path);
			return;
		}
		
		try {
			
		}
		catch(Exception e){
			
		}
		
		path = "/WEB-INF/HomePolicyMaker.html";
		templateManager = new TemplateManager(getServletContext(), request, response);
		templateManager.redirect(path);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}
}
