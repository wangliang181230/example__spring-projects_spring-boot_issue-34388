package cn.wangliang181230.spring_projects__spring_boot__issue_34388.controller;

import java.lang.reflect.Method;

import cn.wangliang181230.spring_projects__spring_boot__issue_34388.openfeign.HttpbinClient;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@Autowired
	private HttpbinClient httpbinClient;

	@GetMapping("/confirmbug")
	public String testAopUtils_getMostSpecificMethod_getDeclaringClass() throws NoSuchMethodException {
		Class<?> targetClass = httpbinClient.getClass();
		Method method = targetClass.getMethod("delay");
		return AopUtils.getMostSpecificMethod(method, targetClass).getDeclaringClass().getName();
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
