package cn.graydove.test;

public class TestClass implements TestInterface{

    @Override
    public void print(int arg) {
        System.out.println("invoke TestClass's method！arg:"+arg);
    }

    @Override
    public void print() {
        System.out.println("invoke TestClass's method！");
    }

    @Override
    public void print2() {
        System.out.println("invoke TestClass's method！---print2");
    }
}
