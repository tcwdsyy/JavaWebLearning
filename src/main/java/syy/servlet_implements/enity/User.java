package syy.servlet_implements.enity;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class User implements HttpSessionBindingListener {
    private Integer id;
    private String username;
    private String password;

    public User() {
    }

    public User(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        System.out.println("User对象绑定");
        ServletContext servletContext = event.getSession().getServletContext();
        Integer count = (Integer) servletContext.getAttribute("count");
        if (count ==null){
            count=1;
        } else {
            count++;
        }
        servletContext.setAttribute("count",count);
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        System.out.println("User对象解绑");
        ServletContext servletContext = event.getSession().getServletContext();
        Integer count = (Integer) servletContext.getAttribute("count");
        count--;
        servletContext.setAttribute("count",count);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
