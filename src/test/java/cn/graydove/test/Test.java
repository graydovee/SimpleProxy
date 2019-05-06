package cn.graydove.test;

import cn.graydove.Proxy;

public class Test {

	public static void main(String[] args) {
		
//		TriAngleHandler h = new TriAngleHandler(g);
		Proxy p = new Proxy();

		TestInterface proxy = null;
		try {
			proxy = (TestInterface)p.getProxy("cn.graydove.test.TestClass");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}

		proxy.print(1);

	}

}
