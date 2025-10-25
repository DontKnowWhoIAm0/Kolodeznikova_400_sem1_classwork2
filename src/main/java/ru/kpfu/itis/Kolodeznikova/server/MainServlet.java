package ru.kpfu.itis.Kolodeznikova.server;

import ru.kpfu.itis.Kolodeznikova.dao.UserDao;
import ru.kpfu.itis.Kolodeznikova.dao.impl.UserDaoImpl;
import ru.kpfu.itis.Kolodeznikova.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "Main Page", urlPatterns = "/main")
public class MainServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect("login.ftl");
            return;
        }

        String user = (String) session.getAttribute("user");

        String cookieUser = "";
        if (req.getCookies() != null) {
            for (Cookie cookie : req.getCookies()) {
                if ("user".equals(cookie.getName())) {
                    cookieUser = cookie.getValue();
                }
            }
        }

        UserDao userDao = new UserDaoImpl();
        User u;

        try {
            u = userDao.getByLogin(user);
            req.setAttribute("user", u.getName());
            req.setAttribute("profileImage", u.getProfileImage());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("cookieUser", cookieUser);
        req.setAttribute("sessionId", session.getId());
        req.setAttribute("counter", req.getAttribute("counter"));;

        req.getRequestDispatcher("index.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
