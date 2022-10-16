### springboot: redbook

**Aspect Oriented Programming(AOP)** is a programming paradigm that aims to increase modularity by allowing separation of cross-cutting concerns.

#### Usage: 
- Logging, Tracing, Exception Handling, Security Management.

#### Concepts:
- **Aspect** - class to define all AOP self methods.
- **PointCut** - expression to find all main application methods to insert AOP.
- **Advice** - when to execute AOP methods when the application pointcut is found. Annotations are @Before, @After, @Around, @AfterReturning, @AfterThrowing, @Aspect, @Component, BeforeAll.

#### Implementing Spring AOP:
1. Add AOP dependency into the **pom.xml**;
2. Create a new package **AOP** and add classes/aspects `WebLog`, `WebLogAspect`, and `LoggingAspect`.
3. Develop Pointcuts and Advices within those classes/Aspects.

#### Extra:
- disable acutator dependency and add property in **application.propertites** to fix springfox issue.
