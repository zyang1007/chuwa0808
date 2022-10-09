#### Spring Benefits and Arhchitecture:
- Spring Benefits two key principles: dependency injection; Inversion of Control.
- Spring has the ability to autowire the dependency at run time, which allows the developer to write loosely coupled code.
- Dependency Injection -> Loose Coupling; No Dependency Injection -> Tight Coupling.

#### How to let Spring manage the dependencies?
- Class Level: use @Component annotation. Note that @Controller, @Service, and @Repository annotations are special versions of @Component.
- Method Level: use @Bean and along with @Configration for third party beans/tools. E.g. ModelMapper.
- Configrate XML file: 
```
<bean id="dataNucleusChuwaNoComponent"
 class="com.chuwa.springbasic.components.impl.DataNu
cleusChuwaNoComponent"></bean>
```
