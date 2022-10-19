### springboot: redbook

#### Unit Testing:
- involves the tesing of each unit or an individual component of the software application;
- the purpose is to validate that each unit of the software code performs as expected;
- a unit can be an individual method, function, procedure, module, or object;
- unit testing is done during the development of an application by the developers.



#### Code Coverage:
1. Run xxxTest.java with Coverage;

2. Add the blew plug-in into the **pom.xml**, so that a report will be produced in **test phase** since we set `<phase>test</phase>`.
- The report will be produced in the **target/site/index.html**; Command line: **mvn clean test**.
```
<build>
    <plugins>
	<plugin>
            <groupId>org.jacoco</groupId>
            <artifactId>jacoco-maven-plugin</artifactId>
            <version>0.8.7</version>
            <executions>
                <execution>
                    <goals>
                        <goal>prepare-agent</goal>
                    </goals>
                </execution>
                <execution>
                    <id>report</id>
                    <phase>test</phase>
                    <goals>
                        <goal>report</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```


