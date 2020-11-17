# Corona-API-Client by Jan

# Requirements

- JDK 11 or higher
- [OkHttp](https://square.github.io/okhttp/) (tested with 4.10.0-RC1)
- [JSON](https://mvnrepository.com/artifact/org.json/json) (test with 20201115)

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

System.out.println(client.getWorldWideStats().getTotalConfirmed());

CoronaCountry germany = client.getCountry("DE");
System.out.println(germany.getTotalConfirmed());
System.out.println(germany.getNewDeathsForToday());

List<CoronaCountry> countries = client.findCountry("d");
```
