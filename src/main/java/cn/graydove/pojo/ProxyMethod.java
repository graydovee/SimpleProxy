package cn.graydove.pojo;

import java.util.ArrayList;
import java.util.List;

public class ProxyMethod {
    private String className;
    private String methodName;
    private List<Param> params;

    @Override
    public String toString() {
        return "ProxyMethod{" +
                "className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", args=" + params +
                '}';
    }

    public List<Param> getParams() {
        return params;
    }

    public void addArgs(Param arg){
        if(params==null){
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
