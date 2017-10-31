package springaop.staticaop;

import com.alibaba.fastjson.JSONObject;

public class Test {
	public static void main(String[] args) {
		IHello hello = new ProxyHello(new Hello());// 如果我们需要日志功能，则使用代理类
		// IHello hello = new Hello();//如果我们不需要日志功能则使用目标类
		hello.sayHello("明天");

		JSONObject json = new JSONObject();
	}
}