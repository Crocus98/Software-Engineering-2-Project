package classes;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import entities.User;
import enums.Usertype;

public class TemplateManager {
	private TemplateEngine templateEngine;
	private HttpServletResponse response;
	private final WebContext ctx;
	
	//Setup for thymeleaf
	public TemplateManager(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) {
		this.response = response;
		ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(servletContext);
		templateResolver.setTemplateMode(TemplateMode.HTML);
		this.templateEngine = new TemplateEngine();
		this.templateEngine.setTemplateResolver(templateResolver);
		templateResolver.setSuffix(".html");
		ctx = new WebContext(request, response, servletContext, request.getLocale());
	}
	
	public static boolean checkUsertype(User user, Usertype usertype) {
		if (user.getUsertype() == usertype) {
			return true;
		}
		return false;
	}
	
	//Set variable for thymeleaf
	public void setVariable(String message, Object item) {
		ctx.setVariable(message, item);
	}
	
	//Redirect by using thymeleaf
	public void redirect(String path) throws IOException {
		templateEngine.process(path, ctx, response.getWriter());
	}
}
