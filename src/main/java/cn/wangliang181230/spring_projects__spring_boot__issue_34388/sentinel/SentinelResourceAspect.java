/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.wangliang181230.spring_projects__spring_boot__issue_34388.sentinel;

import java.lang.reflect.Method;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.annotation.aspectj.AbstractSentinelAspectSupport;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Aspect for methods with {@link SentinelResource} annotation.
 *
 * @author Eric Zhao
 */
@Aspect
public class SentinelResourceAspect extends AbstractSentinelAspectSupport {

	private static final Logger LOGGER = LoggerFactory.getLogger(SentinelResourceAspect.class);


	@Pointcut("@annotation(com.alibaba.csp.sentinel.annotation.SentinelResource)")
	public void sentinelResourceAnnotationPointcut() {
	}

	@Around("sentinelResourceAnnotationPointcut()")
	public Object invokeResourceWithSentinel(ProceedingJoinPoint pjp) throws Throwable {
		LOGGER.info("Start aspect");

		Method method = resolveMethod(pjp);

		SentinelResource annotation = method.getAnnotation(SentinelResource.class);
		if (annotation == null) {
			throw new IllegalStateException("Wrong state for SentinelResource annotation");
		}

		try {
			return pjp.proceed();
		} finally {
			LOGGER.info("Finish aspect, method: {}, anno value: {}", method.getName(), annotation.value());
		}
	}

}
