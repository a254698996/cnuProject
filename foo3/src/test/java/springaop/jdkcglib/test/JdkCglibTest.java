package springaop.jdkcglib.test;

import springaop.jdkcglib.UserManager;
import springaop.jdkcglib.UserManagerImpl;
import springaop.jdkcglib.cglib.CGLibProxy;
import springaop.jdkcglib.jdk.JDKProxy;

public class JdkCglibTest {

	public static void main(String[] args) {

		UserManager userManager = (UserManager) new CGLibProxy().createProxyObject(new UserManagerImpl());
		System.out.println("-----------CGLibProxy-------------");
		userManager.addUser("Macower", "root");
		System.out.println("-----------JDKProxy-------------");
		JDKProxy jdkPrpxy = new JDKProxy();
		UserManager userManagerJDK = (UserManager) jdkPrpxy.newProxy(new UserManagerImpl());
		userManagerJDK.addUser("Macower", "root");
	}

}