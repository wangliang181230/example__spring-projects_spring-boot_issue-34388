package cn.wangliang181230.spring_projects__spring_boot__issue_34388;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled("For debugging")
public class ExampleApplicationTest {

	/**
	 * Please run this test after execute `mvn clean install -Pnative -e` .
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void runWithSpringAotModeAfterProcessAot() throws Exception {
		// Enable spring-aot-mode
		System.setProperty("spring.aot.enabled", "true");

		// Start the application
		ExampleApplication.main(new String[0]);
	}

}
