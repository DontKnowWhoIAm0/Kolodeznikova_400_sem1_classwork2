package ru.kpfu.itis.Kolodeznikova.server;

import ru.kpfu.itis.Kolodeznikova.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "LogIn", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.getRequestDispatcher("login.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        UserServiceImpl userService = new UserServiceImpl();
        try {
            if (userService.checkPasswordAndLogin(login, password)) {

                HttpSession httpSession = req.getSession();
                httpSession.setAttribute("user", login);
                httpSession.setMaxInactiveInterval(60 * 60);

                Cookie cookie = new Cookie("user", login);
                cookie.setMaxAge(24 * 60 * 60);
                resp.addCookie(cookie);

                req.setAttribute("user", login);
                req.setAttribute("sessionId", httpSession.getId());
                req.setAttribute("cookieUser", login);

                resp.sendRedirect("main");
            } else {
                req.setAttribute("error", "1");
                req.getRequestDispatcher("login.ftl").forward(req, resp);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
