package controllers;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.TemplateManager;
import services.DataminerService;
import entities.User;
import exceptions.FarmersOrderingException;


@WebServlet("/GoToRanking")
public class GoToRanking extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TemplateManager templateManager;
	@EJB(name = "services/DataminerService")
	private DataminerService dataminerService;

	public GoToRanking() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String message = null;
		boolean isBadRequest = false;
		List<User> rankedlist = null;
		try {
			rankedlist = dataminerService.getFarmersInLexicographicOrder(0, true, null, null);
		}
		catch(FarmersOrderingException e) {
			message = "Unable to retrieve Lexographical order list of Farmers";
			isBadRequest = true;
		}
		
		
		String path = "/WEB-INF/Ranking.html";
		templateManager = new TemplateManager(getServletContext(), request, response);
		templateManager.setVariable("farmers",rankedlist);
		templateManager.redirect(path);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}
}
