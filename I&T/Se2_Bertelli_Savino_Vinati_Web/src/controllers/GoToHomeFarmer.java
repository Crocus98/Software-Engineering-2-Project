package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.TemplateManager;


@WebServlet("/HomeFarmer")
public class GoToHomeFarmer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TemplateManager templateManager;

	public GoToHomeFarmer() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String path = "/WEB-INF/HomeFarmer.html";
		templateManager = new TemplateManager(getServletContext(), request, response);
		templateManager.redirect(path);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}
}
