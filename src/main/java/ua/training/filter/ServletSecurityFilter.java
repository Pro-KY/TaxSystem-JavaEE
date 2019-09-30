package ua.training.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.util.constans.Attributes;
import ua.training.util.handler.properties.ViewPropertiesHandler;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static ua.training.util.handler.properties.ViewPropertiesHandler.PATH_MAIN;

public class ServletSecurityFilter implements Filter {
    private static final Logger logger = LogManager.getLogger(ServletSecurityFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();

        logger.info("I'm here");

        if (session != null) {
            final Object isAuthorized = session.getAttribute(Attributes.IS_USER_AUTHORIZED);

            logger.info("isAuthorized - {}", isAuthorized);

            if(isAuthorized != null && (Boolean) isAuthorized) {
                final RequestDispatcher requestDispatcher = servletRequest.getServletContext().getRequestDispatcher(ViewPropertiesHandler.getViewPath(PATH_MAIN));
                requestDispatcher.forward(req, resp);
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) {}

    @Override
    public void destroy() { }
}