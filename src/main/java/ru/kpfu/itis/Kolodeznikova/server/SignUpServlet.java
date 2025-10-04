package ru.kpfu.itis.Kolodeznikova.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.kpfu.itis.Kolodeznikova.service.UserService;
import ru.kpfu.itis.Kolodeznikova.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "SignUp", urlPatterns = "/signup")
public class SignUpServlet extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(SignUpServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.sendRedirect("signup.ftl");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String lastname = req.getParameter("lastname");
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (login == null || password == null || name == null || lastname == null || login.isEmpty() || password.isEmpty() || name.isEmpty() || lastname.isEmpty()) {
            req.setAttribute("error", "1");
            req.getRequestDispatcher("signup.ftl").forward(req, resp);
            return;
        }

        UserService userService = new UserServiceImpl();
        try {
            userService.registerUser(name, lastname, login, password);
            resp.sendRedirect("login.ftl");
        } catch (SQLException e) {
            if (e.getSQLState().equals("23505")) {
                req.setAttribute("error", "2");
                req.getRequestDispatcher("signup.ftl").forward(req, resp);
            } else {
                throw new RuntimeException(e);
            }
        }
    }
}
