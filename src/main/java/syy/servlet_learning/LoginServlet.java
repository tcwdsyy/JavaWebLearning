package syy.servlet_learning;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * 解决获取浏览器Post请求后在控制台中解码后的乱码问题
         */
        request.setCharacterEncoding("utf-8");
        /**
         * 获取浏览器发送的请求
         */
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("username : " + username + "\t" + "password : " + password);
        /**
         * 解决服务器响应浏览器后浏览器显示内容乱码问题
         */
        response.setContentType("text/html;charset=utf-8");
        /**
         * 根据传来的用户名和密码响应浏览器登录成功或登录失败
         */
        if (username.equals("ziph") && password.equals("123456")) {
            response.getWriter().println("登录成功！");
        } else {
            response.getWriter().println("登录失败！");
        }
    }
}
