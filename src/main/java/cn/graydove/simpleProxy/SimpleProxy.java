package cn.graydove.simpleProxy;

import cn.graydove.simpleProxy.pojo.ProxyClass;
import cn.graydove.simpleProxy.pojo.ProxyMethod;
import cn.graydove.simpleProxy.pojo.ProxyMethods;
import cn.graydove.simpleProxy.util.ClassUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

public class SimpleProxy implements InvocationHandler {
    private ProxyClass proxyClass;
    private Object obj;

    public Object getProxy(ProxyClass proxyClass) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        this.proxyClass = proxyClass;
        if (proxyClass == null)
            return null;
        Class clazz = Class.forName(proxyClass.getClassName());
        obj = clazz.newInstance();

        return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
    }

    public Object getProxy(ProxyClass proxyClass, Object obj) {
        this.proxyClass = proxyClass;
        if (proxyClass == null)
            return null;
        Class clazz = obj.getClass();
        this.obj = obj;

        return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        List<ProxyMethods> list = proxyClass.getMethods();
        String methodName = method.getName();

        ProxyMethod prefix = null;
        ProxyMethod suffix = null;
        for (ProxyMethods methods : list) {
            if (methodName.equals(methods.getMethodName())) {
                prefix = methods.getPrefix();
                suffix = methods.getSuffix();
            }
        }

        if (prefix != null) {
            methodInvoke(prefix);
        }
        Object invoke = method.invoke(obj, args);
        if (suffix != null)
            methodInvoke(suffix);
        return invoke;
    }

    private void methodInvoke(ProxyMethod method) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {

        if (method != null) {
            Object o;
            Class clazz;
            if (method.getBean() != null && method.getClassName() == null) {
                o = ProxyBeanFactory.getInstance().getProxyBean(method.getBean());
                clazz = o.getClass();
            } else if (method.getBean() == null && method.getClassName() != null) {
                clazz = Class.forName(method.getClassName());
                o = clazz.newInstance();
            } else {
                throw new RuntimeException("参数错误");
            }

            Method[] methods = clazz.getMethods();

            Object[] args = ClassUtil.analyseParams(method.getParams());

            for (Method m : methods) {
                boolean flag = true;
                if (m.getName().equals(method.getMethodName())) {
                    if (args != null && m.getParameterTypes().length == args.length) {
                        int i = 0;
                        for (Class c : m.getParameterTypes()) {
                            Class argc = args[i].getClass();
                            if ((c != argc) && ClassUtil.getBasicClass(argc) != null && c != ClassUtil.getBasicClass(argc)) {
                                flag = false;
                            }
                            ++i;
                        }
                    } else if (!(args == null && m.getParameterTypes().length == 0)) {
                        flag = false;
                    }
                } else {
                    flag = false;
                }

                if (flag) {
                    m.invoke(o, args);
                    break;
                }
            }

        }
    }


}
