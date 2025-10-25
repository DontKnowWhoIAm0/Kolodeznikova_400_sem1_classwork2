    package ru.kpfu.itis.Kolodeznikova.server;

    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;

    import ru.kpfu.itis.Kolodeznikova.service.UserService;
    import ru.kpfu.itis.Kolodeznikova.service.impl.UserServiceImpl;

    import javax.servlet.ServletException;
    import javax.servlet.annotation.MultipartConfig;
    import javax.servlet.annotation.WebServlet;
    import javax.servlet.http.HttpServlet;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
    import javax.servlet.http.Part;
    import java.io.IOException;
    import java.sql.SQLException;

    @WebServlet(name = "SignUp", urlPatterns = "/signup")
    @MultipartConfig
    public class SignUpServlet extends HttpServlet {

        private static final Logger log = LoggerFactory.getLogger(SignUpServlet.class);
        public static final int DIRECTORIES_COUNT = 100;

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
            req.getRequestDispatcher("signup.ftl").forward(req, resp);
        }

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String name = req.getParameter("name");
            String lastname = req.getParameter("lastname");
            String login = req.getParameter("login");
            String password = req.getParameter("password");

            if (login == null || password == null || name == null || lastname == null
                    || login.isEmpty() || password.isEmpty() || name.isEmpty() || lastname.isEmpty()
                    || req.getPart("profile_image").getSubmittedFileName().isEmpty()) {
                req.setAttribute("error", "1");
                req.getRequestDispatcher("signup.ftl").forward(req, resp);
                return;
            }

            Part part = req.getPart("profile_image");

            UserService userService = new UserServiceImpl();
            try {
                userService.registerUser(name, lastname, login, password, part.getInputStream().readAllBytes());
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
