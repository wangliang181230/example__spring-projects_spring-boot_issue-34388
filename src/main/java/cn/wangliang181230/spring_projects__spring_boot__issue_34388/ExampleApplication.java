package cn.wangliang181230.spring_projects__spring_boot__issue_34388;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.core.NativeDetector;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class ExampleApplication {

	public static void main(String[] args) throws Exception {
		try {
			SpringApplication.run(ExampleApplication.class, args);
		} catch (SpringApplication.AbandonedRunException ignored) {
			throw ignored;
		} catch (Throwable t) {
			// In the `native-image`, if an exception occurs prematurely during the startup process, the exception log will not be recorded,
			// so here we sleep for 60 seconds to observe the exception information.
			if (NativeDetector.inNativeImage()) {
				t.printStackTrace();
				Thread.sleep(60000);
			}

			throw t;
		}
	}

}
