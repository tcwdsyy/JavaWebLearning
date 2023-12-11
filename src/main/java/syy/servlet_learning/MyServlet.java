package syy.servlet_learning;
import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
/**
 * Servlet
 * 实现Servlet接口中的所有方法
 */
public class MyServlet implements Servlet{
    public MyServlet(){

    }

    /**
     * 初始化方法
     *
     * @param servletConfig 包含 servlet 的配置和初始化参数的 ServletConfig 对象
     * @throws ServletException 如果发生妨碍 servlet 正常操作的异常
     */
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        //Servlet初始化工作
    }

    /**
     * 获取Servlet配置信息
     *
     * @return 返回 ServletConfig 对象，该对象包含此 servlet 的初始化和启动参数。返回的 ServletConfig 对象是传递给 init 方法的对象。
     */
    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    /**
     * 提供服务
     * <p>
     * 由 servlet 容器调用，以允许 servlet 响应某个请求。
     * 此方法仅在 servlet 的 init() 方法成功完成之后调用。
     * 应该为抛出或发送错误的 servlet 设置响应的状态代码。
     *
     * @param servletRequest  包含客户端请求的 ServletRequest 对象
     * @param servletResponse 包含 servlet 的响应的 ServletResponse 对象
     * @throws ServletException 如果发生妨碍 servlet 正常操作的异常
     * @throws IOException      果发生输入或输出异常
     */
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        //请求相关内容    ServletRequest  ;   相应相关内容  ServletResponse
        /**
         * 在控制台内打印输出
         */
        System.out.println("这是我的第一个Servlet！");

        /**
         * 利用输出流输出系统时间，在浏览器中显示
         */
        PrintWriter printWriter = servletResponse.getWriter();
        Date date = new Date();
        printWriter.println(date);
        printWriter.close();

        /**
         * 利用流输出信息在浏览器内显示
         * 解决浏览器显示乱码问题
         */
        servletResponse.setContentType("text/html;charset=utf-8");
        servletResponse.getWriter().println("这是我的第一个Servlet");
    }

    /**
     * 返回有关 servlet 的信息，比如作者、版本和版权。
     * <p>
     * 此方法返回的字符串应该是纯文本，不应该是任何种类的标记（比如 HTML、XML，等等）。
     *
     * @return 包含 servlet 信息的 String
     */
    @Override
    public String getServletInfo() {
        return null;
    }

    /**
     * 销毁（清除所有资源）
     * <p>
     * 由 servlet 容器调用，指示将从服务中取出该 servlet。
     * 此方法仅在 servlet 的 service 方法已退出或者在过了超时期之后调用一次。
     * 在调用此方法之后，servlet 容器不会再对此 servlet 调用 service 方法。
     * 此方法为 servlet 提供了一个清除持有的所有资源（比如内存、文件句柄和线程）的机会，并确保任何持久状态都与内存中该 servlet 的当前状态保持同步。
     */
    @Override
    public void destroy() {

    }
}
