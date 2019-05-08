package cn.graydove.simpleProxy.pojo;

import java.util.ArrayList;
import java.util.List;

public class ProxyMethod {
    private String className;
    private String bean;
    private String methodName;
    private List<Param> params;

    @Override
    public String toString() {
        return "ProxyMethod{" +
                "className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", params=" + params +
                ", bean='" + bean + '\'' +
                '}';
    }

    public String getBean() {
        return bean;
    }

    public void setBean(String bean) {
        this.bean = bean;
    }


    public List<Param> getParams() {
        return params;
    }

    public void addArgs(Param arg) {
        if (params == null) {
            params = new ArrayList<>();
        }
        this.params.add(arg);
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}
