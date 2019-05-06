package cn.graydove.test;


public class Test2 {
    public Test2() {
    }

    public void test1(){
        System.out.println("prefix method invoked!");
    }
    public void test1(String a){
        System.out.println("prefix method invoked!arg:"+a);
    }
    public void test1(String a, String b){
        System.out.println("prefix method invoked!args:"+a+"-"+b);
    }

    public void test2(){
        System.out.println("suffix method invoked!");
    }
    public void test2(String a){
        System.out.println("prefix method invoked!arg:"+a);
    }
    public void test2(String a, String b){
        System.out.println("prefix method invoked!args:"+a+"-"+b);
    }
}
