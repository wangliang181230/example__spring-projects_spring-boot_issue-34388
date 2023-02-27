java ^
	-Dspring.aot.enabled=true ^
	-agentlib:native-image-agent=config-merge-dir=./src/main/resources/META-INF/native-image/cn.wangliang181230/tracing-agent/ ^
	-jar ./target/spring-projects_spring-boot_issue-34388.jar
