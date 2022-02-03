package controllers;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringEscapeUtils;

import classes.TemplateManager;
import entities.User;
import enums.Usertype;
import exceptions.CredentialsException;
import services.UserService;

import javax.persistence.NonUniqueResultException;

@WebServlet("/CheckLogin")
public class CheckLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TemplateManager templateManager;
	@EJB(name = "services/UserService")
	private UserService userService;

	public CheckLogin() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String mail = null;
		String pwd = null;
		String message = null;
		boolean isBadRequest = false;
		User user = null;
		
		//Retrieve parameters and check them
		mail = StringEscapeUtils.escapeJava(request.getParameter("mail"));
		pwd = StringEscapeUtils.escapeJava(request.getParameter("pwd"));
		if (mail == null || pwd == null || mail.isEmpty() || pwd.isEmpty()) {
			message = "Missing or empty credentials";
			isBadRequest = true;
		}
		
		//Interacting with application server (EJB)
		if(!isBadRequest) {
			try {
				user = userService.checkCredentials(mail, pwd);
				if(user == null) {
					throw new CredentialsException("ERROR: Wrong mail or password.");
				}
			} 
			catch (CredentialsException | NonUniqueResultException e) {
				isBadRequest = true;
				message = e.getMessage();
			}
		}

		//Preparing response
		String path;
		if (isBadRequest) {
			templateManager = new TemplateManager(getServletContext(), request, response);
			templateManager.setVariable("errorMsg", message);
			path = "/index.html";
			templateManager.redirect(path);
		} 
		else {
			request.getSession().setAttribute("user", user);
			path = getServletContext().getContextPath();
			if (user.getUsertype() == Usertype.PolicyMaker) {
				path += "/HomePolicyMaker";
			}
			else if (user.getUsertype() == Usertype.Agronomist) {
				path += "/HomeAgronomist";
			}
			else if (user.getUsertype() == Usertype.Farmer) {
				path += "/HomeFarmer";
			}
			response.sendRedirect(path);
		}

	}


}