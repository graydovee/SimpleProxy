package cn.graydove.pojo;

import java.util.ArrayList;
import java.util.List;

public class ProxyMethod {
    private String className;
    private String methodName;
    private List<String> args;

    @Override
    public String toString() {
        return "ProxyMethod{" +
                "className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", args=" + args +
                '}';
    }

    public List<String> getArgs() {
        return args;
    }

    public void addArgs(String arg){
        if(args==null){
            args = new ArrayList<>();
        }
        this.args.add(arg);
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
