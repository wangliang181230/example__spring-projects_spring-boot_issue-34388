package cn.wangliang181230.spring_projects__spring_boot__issue_34388.controller;

import java.lang.reflect.Proxy;

import cn.wangliang181230.spring_projects__spring_boot__issue_34388.openfeign.HttpbinClient;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@Autowired
	private HttpbinClient httpbinClient;

	@GetMapping("/confirmbug")
	public String confirmbug() {
		try {
			TestInterface proxy = (TestInterface) Proxy.newProxyInstance(
					this.getClass().getClassLoader(),
					new Class[]{TestInterface.class},
					(proxy1, method, args) -> {
						System.out.println(method.getName());
						if (method.getName().equals("testMethod")) {
							return null;
						}
						return method.invoke(proxy1, args);
					});
	
			return proxy.getClass().getMethod("testMethod").getName();
		} catch (Throwable t) {
			return t.getClass().getName() + ": " + t.getMessage();
		}
	}

	@SentinelResource("aaaa")
	@GetMapping("/rt")
	public String delay() {
		return httpbinClient.delay();
	}

	@SentinelResource("bbbb")
	@GetMapping("/exp")
	public String exp() {
		return httpbinClient.status500();
	}

	@SentinelResource("cccc")
	@GetMapping("/get")
	public String get() {
		return httpbinClient.get();
	}

}
