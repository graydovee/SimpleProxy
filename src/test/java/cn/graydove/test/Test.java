package cn.graydove.test;

import cn.graydove.simpleProxy.ProxyBeanFactory;

public class Test {

	public static void main(String[] args) {

		ProxyBeanFactory factory = ProxyBeanFactory.getInstance();

		TestInterface proxy = (TestInterface)factory.getProxyBean("testProxy");

		proxy.print(1);
		proxy.print2();

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

		TestInterface proxy2 = (TestInterface)factory.getProxyBean("testProxy2");

		proxy2.print(1);
	}

}
