package syy.filter_learning;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyLearning {
    public static void main(String[] args) {
        Subject realSubject = new RealSubject();
        InvocationHandler handler = new InvocationHandlerDemo(realSubject);

        Subject subject = (Subject) Proxy.newProxyInstance(handler.getClass().getClassLoader(),realSubject.getClass().getInterfaces(),handler);

        System.out.println(subject.getClass().getName());
        subject.hello("World");
        String result = subject.bye();
        System.out.println("Result is: " + result);
    }

}

class InvocationHandlerDemo implements InvocationHandler{
    private Object subject;
    public InvocationHandlerDemo(Object subject){
        this.subject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 在代理真实对象前我们可以添加一些自己的操作
        System.out.println("Before method");
        System.out.println("Call Method: " + method);
        // 当代理对象调用真实对象的方法时，其会自动的跳转到代理对象关联的handler对象的invoke方法来进行调用
        Object obj = method.invoke(subject, args);
        // 在代理真实对象后我们也可以添加一些自己的操作
        System.out.println("After method");
        System.out.println();
        return obj;
    }
}

interface Subject {
    void hello(String str);
    String bye();
}

class RealSubject implements Subject {
    @Override
    public void hello(String str) {
        System.out.println("Hello  " + str);
    }
    @Override
    public String bye() {
        System.out.println("Goodbye");
        return "Over";
    }
}
