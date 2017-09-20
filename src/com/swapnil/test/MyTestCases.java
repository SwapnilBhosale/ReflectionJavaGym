
package com.swapnil.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.swapnil.bean.Employee;
import com.swapnil.bean.Student;
import com.swapnil.client.Client;
import com.swapnil.constant.Constants;

/**
 * @author Swapnil Bhsale
 * This is Unit Test Case class 
 */
public class MyTestCases {

	Map<String, Object> argsMapEmp;
	Map<String, Object> argsMapStu;
	Client client;
	private static String genericErrorMessage = "Should throw an Exception";

	/**
	 * This set up the stubs for main method and creates Map for testing
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		client = new Client();
		client.setAnnotattedMap(client.getUtil().allFoundClassesAnnotatedWithEntityToBeScanned());

		argsMapEmp = new HashMap<String,Object>();
		argsMapEmp.put("empId", 1L);
		argsMapEmp.put("empName", "Swapnil");
		argsMapEmp.put("salary", new BigDecimal(20000));

		argsMapStu = new HashMap<String, Object>();
		argsMapStu.put("rollNo", 1L);
		argsMapStu.put("name", "Swapnil");
	}



	/**
	 * This creates a bean of employee class and test if values are populated correctly
	 * Employee object is created using DI using Setter
	 * @throws java.lang.Exception
	 */
	@Test
	public void getEmpBeanBySetterInjection() throws Exception {
		Employee emp = client.getBean("emp", argsMapEmp);
		assertEquals(emp.getEmpId(), new Long(1));
		assertEquals(emp.getEmpName(), "Swapnil");
		assertEquals(emp.getSalary(), new BigDecimal(20000));
	}

	/**
	 * This should throw an error with message of type mismatch as empId expects 
	 * Long and providing value as an Integer
	 * @throws java.lang.Exception
	 */
	@Test(expected = Exception.class)
	public void empShouldThrowparamMismatchException() throws Exception{
		try{
			argsMapEmp.put("empId", 1);
			Employee emp = client.getBean("emp", argsMapEmp);
		}catch(Exception e){
			assertEquals(e.getMessage(), Constants.PARAM_TYPE_MISMATCH+"empId");
			throw e;
		}
		fail(genericErrorMessage);
	}

	/**
	 * This creates a bean of Student class and test if values are populated correctly
	 * Student object is created using DI using Constructor
	 * @throws java.lang.Exception
	 */
	@Test
	public void getStuBeanByConstructorInjection() throws Exception {
		Student student = client.getBean("student", argsMapStu);
		assertEquals(student.getRollNo(), new Long(1));
		assertEquals(student.getName(), "Swapnil");
	}

	/**
	/**
	 * This should throw an error with message of type mismatch as rollNo expects 
	 * Long and providing value as an Integer
	 * @throws java.lang.Exception
	 */
	@Test(expected = Exception.class)
	public void stuShouldThrowparamMismatchException() throws Exception{
		try{
			argsMapStu.put("rollNo", 1);
			Student stu = client.getBean("student", argsMapStu);
		}catch(Exception e){
			assertEquals(e.getMessage(), Constants.PARAM_MISSING_OR_TYPE_MISMATCH+"rollNo");
			throw e;
		}
		fail(genericErrorMessage);
	}

	/**
	 * This should throw an error with message of missing parameter as name is not provided
	 * @throws java.lang.Exception
	 */
	@Test(expected = Exception.class)
	public void stuShouldThrowMissingParamException() throws Exception{
		try{
			argsMapStu.remove("name");
			Student stu = client.getBean("student", argsMapStu);
		}catch(Exception e){
			assertEquals(e.getMessage(), Constants.MISSING_PARAMETER);
			throw e;
		}
		fail(genericErrorMessage);
	}
	
	/**
	 * This should throw an error with message of bean not found
	 * As there is no class annotated wwith Bean and having name as 'studentBean'
	 * @throws java.lang.Exception
	 */
	@Test(expected = Exception.class)
	public void shouldThrowBeanNotFoundxception() throws Exception{
		try{
			Student stu = client.getBean("studentBean", new HashMap<String,Object>());
		}catch(Exception e){
			assertEquals(e.getMessage(), Constants.BEAN_NOT_FOUND+"studentBean");
			
			throw e;
		}
		fail(genericErrorMessage);
	}

}
