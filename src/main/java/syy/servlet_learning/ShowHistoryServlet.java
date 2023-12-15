package syy.servlet_learning;

import syy.servlet_learning.utils.CookieUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ShowHistoryServlet",value = "/show1")
public class ShowHistoryServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie_val = CookieUtils.getCookie(req.getCookies(), "history");

        StringBuffer buffer = new StringBuffer();
        if(cookie_val == null){
            buffer.append("<font color='red'>您没有浏览记录</font><br>");
            buffer.append("<a href='bookstore.html'>书城主页</a><br>");
        } else {
            String[] books = {"《Java编程思想》", "《Java核心卷》", "《算法》", "《Syy的博客》"};
            String historyStr = cookie_val.getValue();
            String[] history = historyStr.split("-");
            buffer.append("您的浏览记录如下：<br>");
            for (String s : history) {
                String book = books[Integer.parseInt(s)];
                buffer.append(book + "<br>");
            }
        }
        /**
         * 解决响应给浏览器的中文乱码问题
         */
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().println(buffer.toString());
    }
}
