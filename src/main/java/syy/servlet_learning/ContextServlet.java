package syy.servlet_learning;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ContextServlet", value = "/context")
public class ContextServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = req.getServletContext();
        System.out.println(servletContext.getRealPath("/"));

        /**
         * 获取容器的附加信息
         * 1.获取Servlet信息 : Apache Tomcat/8.5.45
         * 2.获取上下文路径 : /reqweb
         * 3.获取请求路径 : /reqweb
         */
        System.out.println(servletContext.getServerInfo());
        System.out.println(servletContext.getContextPath());
        System.out.println(req.getContextPath());

        /**
         * 1.将数据存储到ServletContext域中
         * 2.从域中获取数据并查看
         * 3.从域中移除该数据并查看
         */
        servletContext.setAttribute("username", "ziph");
        /**
         * 移除ServletContext域中的数据
         */
        servletContext.removeAttribute("username");


    }
}
