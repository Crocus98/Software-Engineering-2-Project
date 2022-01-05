package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.TemplateManager;


@WebServlet("/GoToLoginPage")
public class GoToLoginPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TemplateManager templateManager;

	public GoToLoginPage() {
		super();
	}

	public void init() throws ServletException {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String path = "/index.html";
		templateManager = new TemplateManager(getServletContext(), request, response);
		templateManager.redirect(path);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void destroy() {
	}
}
