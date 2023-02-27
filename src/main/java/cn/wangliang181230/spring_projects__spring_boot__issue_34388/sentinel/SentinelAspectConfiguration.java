package cn.wangliang181230.spring_projects__spring_boot__issue_34388.sentinel;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class SentinelAspectConfiguration {

	@Bean
	@ConditionalOnMissingBean
	public SentinelResourceAspect sentinelResourceAspect() {
		return new SentinelResourceAspect();
	}

}
