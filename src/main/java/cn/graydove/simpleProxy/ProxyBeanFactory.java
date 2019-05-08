package cn.graydove.simpleProxy;

import cn.graydove.simpleProxy.dom.Analysis;
import cn.graydove.simpleProxy.pojo.ProxyClass;
import org.dom4j.DocumentException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProxyBeanFactory {
    private Map<String,Object> map;
    private List<ProxyClass> proxyClasses;

    private static ProxyBeanFactory instance;

    private ProxyBeanFactory(String path) {
        map = new HashMap<>();

        Analysis analysis;
        if(path==null)
            analysis = new Analysis();
        else
            analysis = new Analysis(path);
        try {
            proxyClasses = analysis.analyse();
        } catch (DocumentException e) {
            System.out.println("XML解析错误");
            e.printStackTrace();
        }
    }

    public static ProxyBeanFactory getInstance(){
        return getInstance(null);
    }

    public static ProxyBeanFactory getInstance(String path){
        if(instance==null){
            synchronized (ProxyBeanFactory.class){
                ProxyBeanFactory inst = instance;
                if(inst==null){
                    synchronized (ProxyBeanFactory.class){
                        inst = new ProxyBeanFactory(path);
                    }
                    instance = inst;
                }
            }
        }
        return instance;
    }

    public Object getProxyBean(String id){
        if(id == null)
            return null;
        Object obj = map.get(id);
        if(obj==null){
            for(ProxyClass proxyClass:proxyClasses){
                if(id.equals(proxyClass.getId())){
                    try {
                        if(proxyClass.getClassName()!=null && proxyClass.getBean()==null)
                            obj = new SimpleProxy().getProxy(proxyClass);
                        else if(proxyClass.getClassName()==null && proxyClass.getBean()!=null)
                            obj = new SimpleProxy().getProxy(proxyClass,getProxyBean(proxyClass.getBean()));
                    } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return obj;
    }
}
