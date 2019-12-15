package cn.dante.p02proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkDbQueryHanlder implements InvocationHandler {
    IDBQuery real = null;

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (real==null){
            real = new DBQuery();
        }
        return real.request();
    }

    public static IDBQuery createJdkProxy(){
        IDBQuery jdkProxy = (IDBQuery)Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                new Class[]{IDBQuery.class},new JdkDbQueryHanlder());
        return jdkProxy;
    }
}
