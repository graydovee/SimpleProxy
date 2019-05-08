package cn.graydove.simpleProxy.pojo;

public class Param {
    private String className;
    private String value;

    @Override
    public String toString() {
        return "Param{" +
                "className='" + className + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    public Param() {
    }

    public Param(String className, String value) {
        this.className = className;
        this.value = value;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
