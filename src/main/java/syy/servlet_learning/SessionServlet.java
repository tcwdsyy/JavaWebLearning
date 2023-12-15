package syy.servlet_learning;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SessionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        System.out.println("SessionID为：" + session.getId());
        /**
         * 使用Session来绑定对象
         */
        Test test = new Test();
        test.name = "Jason";
        test.age = 18;
        session.setAttribute("name", test);

        /**
         * 获取Session对象的空闲时间（单位：秒）
         * 默认Session失效时间为1800（30分钟）
         * 1800
         */
        System.out.println("此Session对象" + session.getMaxInactiveInterval() + "秒后失效！");

        /**
         * 获取Session对象的创建时间（单位：毫秒）
         */
        System.out.println("Session的创建时间：" + session.getCreationTime());

        /**
         * 获取Session对象的最后一次访问时间（单位：毫秒）
         */
        System.out.println("Session的最后一次访问时间：" + session.getLastAccessedTime());

        /**
         * 删除Session对象
         */
        //session.invalidate();
    }
}

class Test {
    String name;
    int age;

    @Override
    public String toString() {
        return "Test{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
