package cn.graydove.dom;

import cn.graydove.pojo.ProxyClass;
import cn.graydove.pojo.ProxyMethod;
import cn.graydove.pojo.ProxyMethods;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 解析XML文件
 */
public class Analysis {
    private String XMLPath;
    public Analysis(String fileName) {
        this.XMLPath = "src/main/resources/"+fileName;
    }

    public Analysis() {
        this("proxy.xml");
    }

    public List<ProxyClass> analyse() throws DocumentException {
        List<ProxyClass> classes = new ArrayList<>();

        File XMLFile = new File(XMLPath);
        SAXReader reader = new SAXReader();
        Document document = reader.read(XMLFile);

        Element proxys = document.getRootElement();     //proxys节点为根节点

        ProxyClass proxyClass;
        ProxyMethods proxyMethods;
        ProxyMethod proxyMethod;

        //遍历proxy节点
        for(Iterator i = proxys.elementIterator("proxy");i.hasNext();){
            proxyClass = new ProxyClass();
            Element proxy = (Element)i.next();
            //读取proxy的属性
            for ( Iterator j = proxy.attributeIterator(); j.hasNext(); ) {
                Attribute attribute = (Attribute) j.next();
                String name = attribute.getName();
                String value = attribute.getValue();
                if(name.equals("id")){
                    proxyClass.setId(value);
                }else if(name.equals("class")){
                    proxyClass.setClassName(value);
                }
            }
            //遍历method节点
            for ( Iterator j = proxy.elementIterator("method"); j.hasNext();) {
                proxyMethods = new ProxyMethods();
                Element methods = (Element) j.next();

                //读取method属性
                for ( Iterator k = methods.attributeIterator(); k.hasNext(); ) {
                    Attribute attribute = (Attribute) k.next();
                    String name = attribute.getName();
                    String value = attribute.getValue();
                    if(name.equals("name")){
                        proxyMethods.setMethodName(value);
                    }
                }
                //读取method下的节点
                for(Iterator k = methods.elementIterator(); k.hasNext();){
                    Element ele = (Element) k.next();

                    proxyMethod = new ProxyMethod();
                    //读取suffix和suffix属性
                    for(Iterator iter = ele.attributeIterator();iter.hasNext();){
                        Attribute attribute = (Attribute)iter.next();
                        String name = attribute.getName();
                        String value = attribute.getValue();
                        if(name.equals("bean")){
                            proxyMethod.setClassName(value);
                        }else if(name.equals("mtd")){
                            proxyMethod.setMethodName(value);
                        }
                    }

                    if(ele.getName().equals("suffix")){
                        proxyMethods.setSuffix(proxyMethod);
                    }else if(ele.getName().equals("prefix")){
                        proxyMethods.setPrefix(proxyMethod);
                    }
                }
                proxyClass.addMethods(proxyMethods);
            }
            classes.add(proxyClass);
        }
        return classes;
    }
}
