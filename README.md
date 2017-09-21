# ReflectionJavaGym

##### WIP

### Trying to simulate Spring Dependency Injection technique using Java Reflection API

## How to Run this project

	1) Add jars to the build path from lib folder
	2) Run the jUnit test cases from the test package
		i) If all test cases passed then everything is working correctly
	
#### How to use custom 'Bean' Annotation: 

	@Bean(name="emp",type=DIType.DIBySetter)
		1) 'name' is manadatory parameter. You can get object of class which is annotated, by calling getBean() methodof Client class.
		2) 'type' parameter is used to specify which kind of dependency injection you want. By default it will use constructor otherwise you can specify using type parameter		
		
		
#### Output : 
	Got Employee Bean : Employee [empId=1, empName=Swapnil, salary=20000]
	
	Got Student Bean : Student [rollNo=2, name=Swapnil Bhosale]
			 
