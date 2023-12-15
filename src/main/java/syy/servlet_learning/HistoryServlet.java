package syy.servlet_learning;

import syy.servlet_learning.utils.CookieUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "HistoryServlet", value = "/history")
public class HistoryServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie_val = CookieUtils.getCookie(req.getCookies(), "history");

        String id = req.getParameter("id");
        if (cookie_val == null) {
            cookie_val = new Cookie("history", id);
        } else {
            String historyStr = cookie_val.getValue();
            if(!historyStr.contains(id)){
                historyStr += "-"+id;
                cookie_val.setValue(historyStr);
            }
        }
        resp.addCookie(cookie_val);
        System.out.println("转发");
        req.getRequestDispatcher("/show1").forward(req,resp);
    }
}
