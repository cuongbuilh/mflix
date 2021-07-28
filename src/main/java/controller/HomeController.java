package controller;

import com.mongodb.client.DistinctIterable;
import model.Movie;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;
import service.MovieService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class HomeController implements IController {

    public void process(final HttpServletRequest request, final HttpServletResponse response, final ServletContext servletContext, final ITemplateEngine templateEngine) throws Exception {
        WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
        List<Movie> list = new MovieService().getMoviesforHomePage();
        ctx.setVariable("list", list);

        String url = "/?";
        if (request.getParameter("by") != null) {
            String by = request.getParameter("by").trim();
            url = url + "&by=" + by;
        }
        if (request.getParameter("value") != null) {
            String value = request.getParameter("value").trim();
            url = url + "&value=" + value;
        }
        ctx.setVariable("url", url);

        templateEngine.process("index", ctx, response.getWriter());
    }
}