package cn.wangliang181230.spring_projects__spring_boot__issue_34388.controller;

import cn.wangliang181230.spring_projects__spring_boot__issue_34388.openfeign.HttpbinClient;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@Autowired
	private HttpbinClient httpbinClient;


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