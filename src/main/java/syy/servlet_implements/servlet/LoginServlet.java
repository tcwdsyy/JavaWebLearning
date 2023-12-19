package syy.servlet_implements.servlet;

import syy.servlet_implements.dao.UserDao;
import syy.servlet_implements.dao.impl.UserDaoImpl;
import syy.servlet_implements.entity.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    private UserDao userDao = new UserDaoImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        User loginUser = null;
        try {
            loginUser = userDao.login(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(loginUser);
        if (loginUser == null) {
            req.getRequestDispatcher("/login.html").forward(req, resp);
        } else {
//            if(req.getParameter("autoLogin").equals("autoLogin")){
//                Cookie cookie = new Cookie("autoLogin", username+"-"+password);
//                cookie.setMaxAge(60*60*24);
//                resp.addCookie(cookie);
//            }
            ServletContext sc = req.getServletContext();
            HttpSession session = req.getSession();
            String sessionId = session.getId();
            Map<String, String> map = (Map<String, String>) sc.getAttribute("loginMap");
            if (map == null) {
                map = new HashMap<>();
            }
            map.put(loginUser.getUsername(), sessionId);

            sc.setAttribute("loginMap", map);
            session.setAttribute("user", loginUser);

            resp.sendRedirect("/JavaWebLearning/show");


            //session.setAttribute("user",user);
            //req.getRequestDispatcher("/show").forward(req,resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
