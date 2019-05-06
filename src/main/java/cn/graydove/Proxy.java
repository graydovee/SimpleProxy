package cn.graydove;


import cn.graydove.dom.Analysis;
import cn.graydove.pojo.ProxyClass;
import cn.graydove.pojo.ProxyMethod;
import cn.graydove.pojo.ProxyMethods;
import org.dom4j.DocumentException;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class Proxy implements InvocationHandler {
    private static List<ProxyClass> proxyClasses;
    private static Analysis analysis;

    private Object obj;
    private ProxyClass proxyClass;
    static{

        analysis = new Analysis();
        try {
            proxyClasses = analysis.analyse();
        } catch (DocumentException e) {
            System.out.println("XML解析错误");
            e.printStackTrace();
        }
    }

    public Object getProxy(String proxyName) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        proxyClass = searchClass(proxyName);
        if(proxyClass==null)
            return null;
        Class clazz = Class.forName(proxyClass.getClassName());
        obj = clazz.newInstance();

        return java.lang.reflect.Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(),this);
    }

    private ProxyClass searchClass(String name){
        if(name==null){
            return null;
        }
        for(ProxyClass proxyClass:proxyClasses){
            if(name.equals(proxyClass.getClassName())){
                return proxyClass;
            }
        }
        return null;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        List<ProxyMethods> list = proxyClass.getMethods();
        String methodName = method.getName();

        ProxyMethod prefix = null;
        ProxyMethod suffix = null;
        for(ProxyMethods methods:list){
            if(methodName.equals(methods.getMethodName())){
                prefix = methods.getPrefix();
                suffix = methods.getSuffix();
            }
        }

        methodInvoke(prefix);
        Object invoke = method.invoke(obj, args);
        methodInvoke(suffix);
        return invoke;
    }

    private void methodInvoke(ProxyMethod method) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {

        if(method!=null){
            Class clazz = Class.forName(method.getClassName());
            Method[] methods = clazz.getMethods();
            for(Method m:methods){
                if(m.getName().equals(method.getMethodName())){
                    m.invoke(clazz.newInstance());
                }
            }
        }
    }
}
