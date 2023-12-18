package syy.servlet_implements.filter;

import syy.servlet_implements.enity.User;
import syy.servlet_implements.utils.CookieUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebFilter(filterName = "LoginFilter", value = "/*")
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String requestURL = req.getRequestURI();
        if (requestURL.contains("login")) {
            chain.doFilter(req, resp);
        } else {
            User user = (User)req.getSession().getAttribute("user");
            if(user==null){
                Cookie cookie = CookieUtils.getCookie(req.getCookies(),"autoLogin");
                if(cookie==null){
                    req.getRequestDispatcher("/login.html").forward(req,resp);
                } else {
                    String cookieValue = cookie.getValue();
                    String[] split = cookieValue.split("-");
                    String name = split[0];
                    String password = split[1];
                    if(name.equals("jason") && password.equals("123456")){
                        user = new User();
                        user.setUsername(name);
                        user.setPassword(password);
                        req.getSession().setAttribute("user", user);
                        chain.doFilter(request, resp);
                    } else {
                        request.getRequestDispatcher("/login.html").forward(req,resp);
                    }
                }
            } else {
                chain.doFilter(req,resp);
            }
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
