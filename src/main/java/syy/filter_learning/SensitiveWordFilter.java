package syy.filter_learning;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;


public class SensitiveWordFilter implements Filter {
    List<String> sensitiveWords = new ArrayList<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Enumeration<String> initParam = filterConfig.getInitParameterNames();
        while (initParam.hasMoreElements()) {
            String word = filterConfig.getInitParameter(initParam.nextElement());
            sensitiveWords.add(word);
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        //增强getParameter方法（增加过滤功能）
        HttpServletRequest requestProxy = (HttpServletRequest) Proxy.newProxyInstance(
                req.getClass().getClassLoader(),
                req.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Object returnValues = null;
                        String methodName = method.getName();
                        if ("getParameter".equals(methodName)) {
                            String returnValues1 = (String) method.invoke(req, args);
                            //开始处理脏词
                            for (String sensitiveWord : sensitiveWords) {
                                if (returnValues1.contains(sensitiveWord)) {
                                    /**
                                     * getParameter方法返回值中有脏词
                                     * 可以根据脏词长度返回*号个数
                                     */
                                    StringBuffer buffer = new StringBuffer();
                                    for (int i = 0; i < sensitiveWord.length(); i++) {
                                        buffer.append("*");
                                    }
                                    returnValues1 = returnValues1.replace(sensitiveWord, buffer);
                                }
                            }
                            return returnValues1;
                        } else {
                            returnValues = method.invoke(req, args);
                        }
                        return returnValues;
                    }
                });
        /**
         * 放行增强的request对象——requestProxy
         */
        chain.doFilter(requestProxy, response);
    }

    @Override
    public void destroy() {

    }
}
