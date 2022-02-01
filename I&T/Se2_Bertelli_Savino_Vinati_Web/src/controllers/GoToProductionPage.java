package controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringEscapeUtils;

import classes.TemplateManager;
import entities.Production;
import entities.User;
import enums.TypeOfProduct;
import enums.Usertype;
import exceptions.InsertProductionException;
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

		List<TypeOfProduct> types = TypeOfProduct.getAllTypesOfProduct();
		List<Production> productions = user.getFarm().getProductions();

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

		String message = null;
		boolean isBadRequest = false;
		TypeOfProduct typeOfProduct = null;
		Date date = null;
		int amount = 0;
		String problemsFaced = null;

		try {
			typeOfProduct = TypeOfProduct.getTypeOfProductFromInt(Integer.parseInt(request.getParameter("type")));
			problemsFaced = StringEscapeUtils.escapeJava(request.getParameter("problem"));
			amount = Integer.parseInt(request.getParameter("amount"));
			String fromDateString = request.getParameter("date");
			if(!fromDateString.isEmpty()){
	 			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	 			date = dateFormat.parse(fromDateString);
 			}else {
 				date = new Date();
 			}
			
			if(typeOfProduct == null || problemsFaced == null || date == null || problemsFaced.isEmpty() 
					|| amount < 1 || date.after(new Date())) {
				throw new InsertProductionException("[InsertProductionException] ERROR: Incorrect parameters for creating production.");
			}
		} catch(InsertProductionException e) {
			isBadRequest = true;
			message = e.getMessage();
		}catch (Exception e) {
			isBadRequest = true;
			message = "ERROR: Incorrect parameters for creating production.";
		}

		if (!isBadRequest) {
			try {
				user = userService.insertProduction(user, typeOfProduct, problemsFaced, amount, date);
				message = "Production inserted successfully.";
				session.setAttribute("user", user);
			} catch (InsertProductionException e) {
				isBadRequest = true;
				message = e.getMessage();
			}
		}

		List<TypeOfProduct> types = TypeOfProduct.getAllTypesOfProduct();
		List<Production> productions = user.getFarm().getProductions();

		path = "/WEB-INF/ProductionPage.html";
		templateManager = new TemplateManager(getServletContext(), request, response);
		templateManager.setVariable("errorMsg", message);
		templateManager.setVariable("types", types);
		templateManager.setVariable("productions", productions);
		templateManager.redirect(path);
	}
}
