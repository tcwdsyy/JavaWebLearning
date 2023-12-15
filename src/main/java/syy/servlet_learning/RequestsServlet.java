package syy.servlet_learning;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

@WebServlet(name = "RequestsServlet", value = "/requests")
public class RequestsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
    protected void doPost (HttpServletRequest request, HttpServletResponse response){
        /**
         * 操作响应头
         * setHeader():覆盖原有的响应头的值
         * addHeader():在原有的响应头的值后面追加 (Cookie)
         * 服务器告诉浏览器，给的响应正文的类型是text/html，告诉你应该以utf-8进行解码
         */
//        response.setHeader("Content-Type","text/html;charset=utf-8");

        /**
         * 定时跳转
         * 添加学生成功后，显示空白页5秒后跳转到inserok.html页面提示添加学生成功！
         */
//        response.setHeader("refresh","5;url=/JavaLearning/test.html");

        StringBuffer requestURL = request.getRequestURL();
        System.out.println("请求路径为：" + requestURL);

        String requestMethod = request.getMethod();
        System.out.println("请求方式为："+requestMethod);

        String requestRemoteAddr = request.getRemoteAddr();
        System.out.println("请求IP地址：" + requestRemoteAddr);

        int requestLocalPort = request.getLocalPort();
        System.out.println("请求端口号为：" + requestLocalPort);

        String requestQueryString = request.getQueryString();
        System.out.println("请求网址后的拼接内容为：" + requestQueryString);

        String requestHeader = request.getHeader("User-Agent");
        System.out.println("User-Agent为：" + requestHeader);

//  操作请求参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("username: "+ username + "\t" + "password: "+ password);

//  获取所有请求参数名称
        Enumeration<String> requestParameterNames = request.getParameterNames();
        while(requestParameterNames.hasMoreElements()){
            String parameterName = requestParameterNames.nextElement();
            String parameterValue = request.getParameter(parameterName);
            System.out.println("name : " + parameterName + "\t" + "value : " + parameterValue);
        }

//  获取指定参数的所有值
        String[] usernames = request.getParameterValues("username");
        System.out.println(usernames[0]);
        String[] usernames1 = request.getParameterValues("username");
        for (String name : usernames1) {
            System.out.println(name);
        }

//  获取请求参数对应的Map集合
        Map<String,String[]> requestParameterMap = request.getParameterMap();
        Set<Map.Entry<String, String[]>> entrySet = requestParameterMap.entrySet();
        for (Map.Entry<String, String[]> entry : entrySet) {
            //键 - 请求参数名称
            String parameterName = entry.getKey();
            //值 - 一组请求参数值
            String[] values = entry.getValue();
            StringBuffer buffer = new StringBuffer();
            for (String value : values) {
                buffer.append(value + " ");
            }
            System.out.println("参数名称 : " + parameterName + "参数值 : " + buffer);
        }
    }

}
