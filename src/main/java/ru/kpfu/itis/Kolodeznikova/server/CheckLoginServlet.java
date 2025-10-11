package ru.kpfu.itis.Kolodeznikova.server;

import ru.kpfu.itis.Kolodeznikova.service.UserService;
import ru.kpfu.itis.Kolodeznikova.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/ajax/checkLogin")
public class CheckLoginServlet extends HttpServlet {

    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        resp.setContentType("text/plain");

        boolean exists = false;
        try {
            exists = userService.loginExists(login);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        resp.getWriter().write(exists ? "exists" : "free");
    }
}
