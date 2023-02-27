package cn.wangliang181230.spring_projects__spring_boot__issue_34388.openfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
		name = "openfeign-example",
		url = "https://httpbin.org",
		contextId = "openfeign-example"
)
public interface HttpbinClient {

	@GetMapping("/delay/3")
	String delay();

	@GetMapping("/status/500")
	String status500();


	@GetMapping("/get")
	String get();

}
