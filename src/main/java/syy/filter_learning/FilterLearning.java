package syy.filter_learning;


import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.Enumeration;

//@WebFilter(filterName = "AnnotationFilter", value = "/*",initParams = {
//        @WebInitParam(name = "username", value = "join"),
//        @WebInitParam(name = "password", value = "123456")})

public class FilterLearning implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("---Filter初始化---");
        //取得初始化的参数名称
        String name = filterConfig.getInitParameter("name");
        System.out.println(name);

        Enumeration<String> initParameterNames = filterConfig.getInitParameterNames();
        while (initParameterNames.hasMoreElements()) {
            //获取初始化参数名称
            String parameterNames = initParameterNames.nextElement();
            //获取初始化参数值
            String parameterValues = filterConfig.getInitParameter(parameterNames);
            System.out.println("name : " + parameterNames + "\t" + "value : " + parameterValues);
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        //过滤器处理请求
        System.out.println("---Filter处理请求---");
        //使用过滤器链放行
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("---Filter销毁---");
    }
}
