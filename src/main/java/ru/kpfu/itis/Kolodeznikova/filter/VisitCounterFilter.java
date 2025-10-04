package ru.kpfu.itis.Kolodeznikova.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = "/main")
public class VisitCounterFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) servletRequest).getSession(false);
        if (session != null) {
            String user = (String) session.getAttribute("user");

            int counter = 0;
            Cookie[] cookies = ((HttpServletRequest) servletRequest).getCookies();
            if (cookies != null) {
                for (Cookie c : cookies) {
                    if ((user + "counter").equals(c.getName())) {
                        try {
                            counter = Integer.parseInt(c.getValue());
                        } catch (NumberFormatException ignored) {}
                    }
                }
            }
            counter++;
            Cookie cookie = new Cookie(user + "counter", String.valueOf(counter));
            cookie.setMaxAge(24 * 60 * 60);
            cookie.setPath("/");
            ((HttpServletResponse) servletResponse).addCookie(cookie);
            ((HttpServletRequest) servletRequest).setAttribute("counter", counter);
        }
        filterChain.doFilter(servletRequest, servletResponse);    }
}
