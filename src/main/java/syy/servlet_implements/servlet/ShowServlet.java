package syy.servlet_implements.servlet;

import syy.servlet_implements.entity.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "ShowServlet", value = "/show")
public class ShowServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");

        HttpSession session = req.getSession();
        ServletContext sc = req.getServletContext();

        String sessionId = session.getId();
        User user = (User) session.getAttribute("user");
        Map<String, String> map = (Map<String, String>) sc.getAttribute("loginMap");


        if (user != null && map != null) {
            if (map.get(user.getUsername()).equals(sessionId)) {
                resp.getWriter().println("<h1>欢迎" + user.getUsername() + "回家！</h1>");
            } else {
                resp.getWriter().println("不好意思您已在其他地方登录还请您<a href='/JavaWebLearning/login.html'>登录</a>");
            }
        } else {
            resp.getWriter().println("不好意思您还没有登录！还请您<a href='/JavaWebLearning/login.html'>登录</a>");
        }
    }
}
