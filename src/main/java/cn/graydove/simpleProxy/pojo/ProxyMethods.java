package cn.graydove.simpleProxy.pojo;

public class ProxyMethods {
    private String methodName;
    private ProxyMethod suffix;
    private ProxyMethod prefix;
    @Override
    public String toString() {
        return "ProxyMethods{" +
                "methodName='" + methodName + '\'' +
                ", suffix=" + suffix +
                ", prefix=" + prefix +
                '}';
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public ProxyMethod getSuffix() {
        return suffix;
    }

    public void setSuffix(ProxyMethod suffix) {
        this.suffix = suffix;
    }

    public ProxyMethod getPrefix() {
        return prefix;
    }

    public void setPrefix(ProxyMethod prefix) {
        this.prefix = prefix;
    }
}
