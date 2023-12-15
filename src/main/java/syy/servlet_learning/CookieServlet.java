package syy.servlet_learning;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "CookieServlet", value = "/cookie")
public class CookieServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Cookie cookie = new Cookie("username","syy");
//        cookie.setMaxAge(60); // 更新Cookie的生命周期：负数->浏览器内存里  0->失效   正数->过期时间
//        cookie.setPath("/"); // 设置Cookie的共享范围： 默认同一项目下   /->当前服务器下
//        resp.addCookie(cookie);
//        resp.setHeader("set-cookie", "password=123456"); // 不推荐

        /**
         * Cookie的编码
         * URLEncoder.encode("username", "utf-8")
         */
//        Cookie cookie1 = new Cookie(URLEncoder.encode("username", "utf-8"), URLEncoder.encode("ziph", "utf-8"));


        Cookie[] cookies = req.getCookies();
        Cookie cookie_val = null;
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("lastTime")){
                    cookie_val = cookie;
                }
            }
        }

        /**
         * 1.创建时间格式化对象并指定格式
         * 2.判断临时cookie_avl对象是否为空
         * 3.cookie_avl对象为空，证明该站点是被第一次访问
         * 4.获取当前时间对象
         * 5.打印第一次访问时间（第一次访问肯定没有上一次访问时间啊）
         * 6.获取当前时间毫秒值（因为Cookie是需要传入字符串，我们这里用空字符串做拼接，转换为字符串传入参数）存入cookie_avl对象
         * 7.操作cookie_avl对象并发出响应
         */
        SimpleDateFormat format = new SimpleDateFormat("yyyy MM dd hh:mm:ss");
        if(cookie_val==null){
            Date currDate = new Date();
            System.out.println("第一次访问："+format.format(currDate));
            cookie_val = new Cookie("lastTime",currDate.getTime()+"");
        }else{
            /**
             * 1.获取上一次cookie_avl对象中的时间字符串，并将字符串转换为毫秒值
             * 2.把毫秒值转换为该时间格式化的时间对象
             * 3.打印上一次访问的时间对象
             * 4.获取当前时间对象的毫秒值，并传入cookie_avl对象中保存
             * 5.操作cookie_avl对象并发出响应
             */
            long currTimeMills = Long.parseLong(cookie_val.getValue());
            Date lastDate = new Date(currTimeMills);
            System.out.println("上一次访问时间为：" + format.format(lastDate));
            Date currentDate = new Date();
            cookie_val.setValue(currentDate.getTime()+"");
        }
        resp.addCookie(cookie_val);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }


}
