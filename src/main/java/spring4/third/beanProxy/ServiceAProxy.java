package spring4.third.beanProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ServiceAProxy {
    private ServiceA serviecA;

    private Object proxy;

    private ServiceAProxy(ServiceA serviceA) {
        this.serviecA = serviceA;
        this.proxy = Proxy.newProxyInstance(
                ServiceA.class.getClassLoader(),
            new Class[]{ ServiceA.class },
            new methodHandler());
    }

    /**
     * 外部からはこのメソッドを通じてProxyを取得します.
     */
    public static ServiceA createProxy(ServiceA serviceA) {
        ServiceAProxy obj = new ServiceAProxy(serviceA);
        return ServiceA.class.cast(obj.proxy);
    }

    /**
     * Proxyのメソッド呼び出しハンドラ.
     */
    private class methodHandler implements InvocationHandler {

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            try {
                    System.out.println("メソッド開始");

                    Object obj = method.invoke(serviecA, args);

                    System.out.println("メソッド終了");

                    return obj;
            } catch (InvocationTargetException ite) {
                throw ite.getCause();
            }
        }
    }
}
