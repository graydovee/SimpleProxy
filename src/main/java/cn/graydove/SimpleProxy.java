package cn.graydove;


import cn.graydove.pojo.Param;
import cn.graydove.pojo.ProxyClass;
import cn.graydove.pojo.ProxyMethod;
import cn.graydove.pojo.ProxyMethods;

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
        if(proxyClass==null)
            return null;
        Class clazz = Class.forName(proxyClass.getClassName());
        obj = clazz.newInstance();

        return Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(),this);
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

            Object[] args = analyseParams(method.getParams());

            for(Method m:methods){
                boolean flag = true;
                if(m.getName().equals(method.getMethodName())){
                    if(m.getParameterTypes().length==args.length){
                        int i=0;
                        for(Class c:m.getParameterTypes()){
                            Class argc = args[i].getClass();
                            if((c!=argc) && getBasicClass(argc)!=null && c!=getBasicClass(argc)){
                                flag = false;
                            }
                            ++i;
                        }
                    }else{
                        flag = false;
                    }
                }else{
                    flag = false;
                }

                if(flag){
                    m.invoke(clazz.newInstance(),args);
                    break;
                }
            }

        }
    }

    private Object[] analyseParams(List<Param> params){
        Object[] args = new Object[params.size()];
        int i=0;
        for(Param param:params){
            if("String".equals(param.getClassName())){
                args[i] = param.getValue();
            }else if("Integer".equals(param.getClassName()) || "int".equals(param.getClassName())){
                args[i]= Integer.parseInt(param.getValue());
            }else if("Long".equals(param.getClassName()) || "long".equals(param.getClassName())){
                args[i]= Long.parseLong(param.getValue());
            }else if("Double".equals(param.getClassName()) || "double".equals(param.getClassName())){
                args[i]= Double.parseDouble(param.getValue());
            }else if("Float".equals(param.getClassName()) || "float".equals(param.getClassName())){
                args[i]= Float.parseFloat(param.getValue());
            }else if("Byte".equals(param.getClassName()) || "byte".equals(param.getClassName())){
                args[i]= Byte.parseByte(param.getValue());
            }else if("Boolean".equals(param.getClassName()) || "boolean".equals(param.getClassName())){
                args[i]= Boolean.parseBoolean(param.getValue());
            }else if("Short".equals(param.getClassName()) || "short".equals(param.getClassName())){
                args[i]= Short.parseShort(param.getValue());
            }
            ++i;
        }
        return  args;
    }

    private Class getBasicClass(Class c){
        if(c==null){
            return null;
        }
        if(Integer.class==c){
            return int.class;
        }else if(Double.class==c){
            return double.class;
        }else if(Long.class==c){
            return long.class;
        }else if(Float.class==c){
            return float.class;
        }else if(Short.class==c){
            return short.class;
        }else if(Boolean.class==c){
            return boolean.class;
        }else if(Byte.class==c){
            return byte.class;
        }
        return null;
    }
}
