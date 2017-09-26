# ReflectionJavaGym

##### WIP

### (Experimental) Spring Dependency Injection using Java Reflection API

## How to Run this project

	1) Maven build the project
	2) mvn exec:java -Dexec.mainClass="com.swapnil.reflection_gym.client.Client"
	
#### How to use custom 'Bean' Annotation: 

	@Bean(name="emp",type=DIType.DIBySetter)
		1) 'name' is manadatory parameter. You can get object of class which is annotated, by calling getBean() methodof Client class.
		2) 'type' parameter is used to specify which kind of dependency injection you want. By default it will use constructor otherwise you can specify using type parameter		
		
		
#### Output : 
	Got Employee Bean : Employee [empId=1, empName=Swapnil, salary=20000]
	
	Got Student Bean : Student [rollNo=2, name=Swapnil Bhosale]
			 
