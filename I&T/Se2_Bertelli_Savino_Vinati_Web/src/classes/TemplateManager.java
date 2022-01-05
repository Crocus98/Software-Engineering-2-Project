package classes;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

public class TemplateManager {
	private TemplateEngine templateEngine;
	private HttpServletResponse response;
	private final WebContext ctx;
	
	public TemplateManager(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) {
		this.response = response;
		ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(servletContext);
		templateResolver.setTemplateMode(TemplateMode.HTML);
		this.templateEngine = new TemplateEngine();
		this.templateEngine.setTemplateResolver(templateResolver);
		templateResolver.setSuffix(".html");
		ctx = new WebContext(request, response, servletContext, request.getLocale());
	}
	
	public void setVariable(String message, Object item) {
		ctx.setVariable(message, item);
	}
	
	public void redirect(String path) throws IOException {
		templateEngine.process(path, ctx, response.getWriter());
	}
}
