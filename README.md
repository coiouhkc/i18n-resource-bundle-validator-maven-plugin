i18n-resource-bundle-validator-maven-plugin
================================
Maven plugin to validate the completeness of `.properties` based i18n resource bundles.

Usage
---
Assuming you would like to check all `locale.*.properties` (e.g. `locale.properties` for english, `locale_de.properties` for german ) under `src/main/resources`
```xml
<plugin>
	<groupId>org.abratuhi.i18n</groupId>
	<artifactId>i18n-resource-bundle-validator-maven-plugin</artifactId>
	<version>1.0-SNAPSHOT</version>
	<configuration>
		<basenameRegex>src/main/resources/locale.*\.properties</basenameRegex>
	</configuration>
	<executions>
		<execution>
			<phase>package</phase>
			<goals>
				<goal>i18n-resource-validator</goal>
			</goals>
		</execution>
	</executions>
</plugin>
```