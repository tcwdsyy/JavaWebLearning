package syy.servlet_learning;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Default extends HttpServlet{
    private String text;

    @Override
    public void init() throws ServletException {
        text = "Can not found sources";
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {
        // 设置响应内容类型
        response.setContentType("text/html");

        // 实际的逻辑是在这里
        PrintWriter out = response.getWriter();
        out.println("<h1>" + text + "</h1>");
    }
}
