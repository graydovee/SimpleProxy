package cn.graydove.test;

import cn.graydove.ProxyBeanFactory;
import cn.graydove.SimpleProxy;

public class Test {

	public static void main(String[] args) {
		
//		TriAngleHandler h = new TriAngleHandler(g);
		ProxyBeanFactory factory = ProxyBeanFactory.getInstance();

		TestInterface proxy = (TestInterface)factory.getProxyBean("testProxy");

		proxy.print(1);

	}

}
