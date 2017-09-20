
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
 * @author bhosale_s
 *
 */
public class MyTestCases {

	Map<String, Object> argsMapEmp;
	Map<String, Object> argsMapStu;
	Client client;
	private static String genericErrorMessage = "Should throw an Exception";

	/**
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



	@Test
	public void getEmpBeanBySetterInjection() throws Exception {
		Employee emp = client.getBean("emp", argsMapEmp);
		assertEquals(emp.getEmpId(), new Long(1));
		assertEquals(emp.getEmpName(), "Swapnil");
		assertEquals(emp.getSalary(), new BigDecimal(20000));
	}

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

	@Test
	public void getStuBeanByConstructorInjection() throws Exception {
		Student student = client.getBean("student", argsMapStu);
		assertEquals(student.getRollNo(), new Long(1));
		assertEquals(student.getName(), "Swapnil");
	}

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

	@Test(expected = Exception.class)
	public void stuShouldThrowMissingParamxception() throws Exception{
		try{
			argsMapStu.remove("name");
			Student stu = client.getBean("student", argsMapStu);
		}catch(Exception e){
			assertEquals(e.getMessage(), Constants.MISSING_PARAMETER);
			throw e;
		}
		fail(genericErrorMessage);
	}

}
