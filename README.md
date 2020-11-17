# Corona-API-Client by Jan

# Requirements

- JDK 11 or higher
- [OkHttp](https://square.github.io/okhttp/) (tested with 4.10.0-RC1)
- [JSON](https://mvnrepository.com/artifact/org.json/json) (tested with 20201115)

# Installation

##### Maven:


```xml
<repositories>
	<repository>
		<id>jitpack.io</id>
		<url>https://jitpack.io</url>
	</repository>
</repositories>
```
```xml
<dependency>
	<groupId>com.github.jan-tennert</groupId>
	<artifactId>Corona-API-Client</artifactId>
	<version>1.0.0</version>
</dependency>
```
##### JAR Download: [Click Here](https://github.com/jan-tennert/Corona-API-Client/releases)

# Examples

```java
CoronaAPIClient client = new CoronaAPIClient();

System.out.println(client.getWorldWideStats().getTotalConfirmed().getCases());

CoronaCountry germany = client.getCountry("DE");
System.out.println(germany.getTotalConfirmed().getCases());
System.out.println(germany.getNewDeathsForToday().getCases());

List<CoronaCountry> countries = client.findCountry("d");
```

# More Information

If you found a bug or you have an idea, please create a new issue: [Click here](https://github.com/jan-tennert/Corona-API-Client/issues/new)
