package cn.graydove.pojo;

public class ProxyMethod {
    private String className;
    private String methodName;

    @Override
    public String toString() {
        return "ProxyMethod{" +
                "className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                '}';
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
