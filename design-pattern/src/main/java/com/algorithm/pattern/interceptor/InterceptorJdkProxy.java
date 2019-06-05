package com.algorithm.pattern.interceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @program: com.algorithm.pattern.interceptor
 * @author: mashifei
 * @create: 2019-06-04-14
 */
public class InterceptorJdkProxy implements InvocationHandler {

    private Object target ;//真实对象
    private String interceptorClass = null;//拦截器全限定名

    public InterceptorJdkProxy(Object target,String interceptorClass){
        this.target = target;
        this.interceptorClass = interceptorClass;
    }

    public static Object getProxy(Object target,String interceptorClass){
        //取得代理对象
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InterceptorJdkProxy(target,interceptorClass));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(null==interceptorClass){
            //没有设置拦截器直接反射原有方法
            return method.invoke(target,args);
        }

        Object result = null;
        Interceptor interceptor = (Interceptor) Class.forName(interceptorClass).newInstance();
        if(interceptor.before(proxy,target,method,args)){
            result = method.invoke(target,args);
        }else{//before返回false
            interceptor.around(proxy,target,method,args);
        }

        //调用后置方法
        interceptor.after(proxy,target,method,args);
        return result;
    }
}
