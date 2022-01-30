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
import entities.User;
import enums.Usertype;
import services.UserService;


@WebServlet("/Production")
public class GoToInsertProductionPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TemplateManager templateManager;
	@EJB(name = "services/UserService")
	private UserService userService;

	public GoToInsertProductionPage() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		templateManager = new TemplateManager(getServletContext(), request, response);
		String path = getServletContext().getContextPath() + "/GoToLoginPage";
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user == null || !templateManager.checkUsertype(user, Usertype.Farmer)) {
			templateManager.redirect(path);
			return;
		}
		
		// tutto il codice per prendere i dati di produzione
		
		path = "/WEB-INF/Production.html";
		templateManager = new TemplateManager(getServletContext(), request, response);
		// ctx da mandare
		templateManager.redirect(path);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}
}
