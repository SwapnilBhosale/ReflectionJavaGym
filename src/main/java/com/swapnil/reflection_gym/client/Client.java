package com.swapnil.reflection_gym.client;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.swapnil.reflection_gym.annotation.Bean;
import com.swapnil.reflection_gym.bean.Employee;
import com.swapnil.reflection_gym.bean.Student;
import com.swapnil.reflection_gym.constant.Constants;
import com.swapnil.reflection_gym.constant.DIType;
import com.swapnil.reflection_gym.util.Utility;


/**
 * @author Swapnil Bhosale
 * This is Main class for execution
 */
public class Client {

	Map<String,Class<?>> annotattedMap = new HashMap<String, Class<?>>();
	Utility util;


	 /**
	   * This method is used to get the bean runtime using reflection
	   * @param objName This is the name of the bean
	   * @param constParams  this is a Map which holds field name and values
	   * 					 which needs to be initialized
	   * @return Generic This returns the object of the Bean.
	  */
	@SuppressWarnings("unchecked")
	public <T> T getBean(String objName,Map<String,Object> constParams) throws Exception{
		T output = null;
		if(annotattedMap.get(objName) != null) {
			Class<?> cls = annotattedMap.get(objName);
			Annotation annotation = cls.getAnnotation(Bean.class);
			DIType diType;
			if(annotation instanceof Bean){
				Bean bean = (Bean) annotation;
				diType = bean.type();
				Constructor[] constructors = cls.getConstructors();
				switch(diType){
				case DIByConstructore :
					for(Constructor constructor : constructors){
						int paramsCount = constructor.getParameterCount();
						if(paramsCount != constParams.size())
							throw new Exception(Constants.MISSING_PARAMETER);
						Field[] fields = cls.getDeclaredFields();
						//System.out.println("checking fields "+fields.length+" count : "+paramsCount);
						if(util.checkParams(constParams, fields)){
							output = (T) constructor.newInstance(util.populateObjects(constParams, fields));
						}
						break;

					}
					break;
				case DIBySetter :
					output = (T) cls.newInstance();
					for(Field field : cls.getDeclaredFields()){
						for(Method method : cls.getMethods()){
							if((method.getName().startsWith("set")) && (method.getName().length() == (field.getName().length() + 3))){
								if (method.getName().toLowerCase().endsWith(field.getName().toLowerCase())){
									try{
										method.setAccessible(true);
										if(util.checkParamType(field,constParams.get(field.getName()))){
											method.invoke(output,constParams.get(field.getName()));
										}else{
											throw new Exception(Constants.PARAM_TYPE_MISMATCH+""+field.getName());
										}
									}catch(Exception e){
										throw e;
									}
								}
							}
						}
					}
					break;
				default:
					break;
				}
			}
		}else{
			throw new Exception(Constants.BEAN_NOT_FOUND+""+objName);
		}
		return  output;
	}

	/**
	   * This is getter function for Utility class
	   * @return Utility reference to Utility class object.
	  */
	public Utility getUtil(){
		return util;
	}

	/**
	   * This is setter method for storing classes with annotated map
	   * @param map Map of the annotated class and name of the bean
	  */
	public void setAnnotattedMap( Map<String,Class<?>> map ){
		this.annotattedMap = map;
	}

	/**
	   * This is a main method where execution starts
	   * @param args This is command line parameters
	  */
	public static void main(String[] args) {
		System.out.println("Inside main");
		Client client = new Client();
		client.setAnnotattedMap(client.getUtil().allFoundClassesAnnotatedWithEntityToBeScanned());
		try {
			Map<String, Object> argsMap = new HashMap<String,Object>();
			argsMap.put("empId", 1L);
			argsMap.put("empName", "Swapnil");
			argsMap.put("salary", new BigDecimal(20000));
			Employee e = client.getBean("emp",argsMap);
			System.out.println("Got Employee Bean : "+e.toString());

			Map<String, Object> argsMapStu = new HashMap<String,Object>();
			argsMapStu.put("rollNo", 2L);
			argsMapStu.put("name", "Swapnil Bhosale");
			Student student = client.getBean("student",argsMapStu);
			System.out.println("Got Student Bean : "+student.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Client() {
		util = new Utility();
	}
}
