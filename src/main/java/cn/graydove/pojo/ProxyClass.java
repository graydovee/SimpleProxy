package cn.graydove.pojo;

import java.util.ArrayList;
import java.util.List;

public class ProxyClass {
    private String id;
    private String className;
    private List<ProxyMethods> methods = new ArrayList<>();

    public String getClassName() {
        return className;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<ProxyMethods> getMethods() {
        return methods;
    }

    public void setMethods(List<ProxyMethods> methods) {
        this.methods = methods;
    }

    public void addMethods(ProxyMethods method){
        this.methods.add(method);
    }

    @Override
    public String toString() {
        return "ProxyClass{" +
                "id='" + id + '\'' +
                ", className='" + className + '\'' +
                ", methods=" + methods +
                '}';
    }
}
