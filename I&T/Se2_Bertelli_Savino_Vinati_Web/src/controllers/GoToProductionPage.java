package controllers;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import classes.TemplateManager;
import entities.Production;
import entities.User;
import enums.TypeOfProduct;
import enums.Usertype;
import services.UserService;

@WebServlet("/Production")
public class GoToProductionPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TemplateManager templateManager;
	@EJB(name = "services/UserService")
	private UserService userService;

	public GoToProductionPage() {
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

		List<TypeOfProduct> types = null;
		List<Production> productions = null;

		types = TypeOfProduct.getAllTypesOfProduct();
		productions = user.getFarm().getProductions();

		path = "/WEB-INF/ProductionPage.html";
		templateManager = new TemplateManager(getServletContext(), request, response);
		templateManager.setVariable("types", types);
		templateManager.setVariable("productions", productions);
		templateManager.redirect(path);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		templateManager = new TemplateManager(getServletContext(), request, response);
		String path = getServletContext().getContextPath() + "/GoToLoginPage";

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null || !templateManager.checkUsertype(user, Usertype.Farmer)) {
			templateManager.redirect(path);
			return;
		}
		
		List<TypeOfProduct> types = null;
		List<Production> productions = null;

		types = TypeOfProduct.getAllTypesOfProduct();
		productions = user.getFarm().getProductions();

		path = "/WEB-INF/ProductionPage.html";
		templateManager = new TemplateManager(getServletContext(), request, response);
		templateManager.setVariable("types", types);
		templateManager.setVariable("productions", productions);
		templateManager.redirect(path);
	}
}
