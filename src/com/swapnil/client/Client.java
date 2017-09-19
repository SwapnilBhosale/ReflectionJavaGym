package com.swapnil.client;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Parameter;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.swapnil.annotation.Bean;
import com.swapnil.bean.Employee;
import com.swapnil.constant.DIType;

public class Client {

	
	public static <T> T getBean(String objName,Map<String,Object> constParams) throws Exception{
		T output = null;
		Class cls = Class.forName(objName);
		System.out.println(cls.getName());
		Annotation annotation = cls.getAnnotation(Bean.class);
		DIType diType;
		if(annotation instanceof Bean){
			System.out.println("inside bean");
			Bean bean = (Bean) annotation;
			diType = bean.type();
			System.out.println("di type : "+diType.toString());
			switch(diType){
				case DIByConstructore :
					Constructor[] constructors = cls.getConstructors();
					for(Constructor constructor : constructors){
						int paramsCount = constructor.getParameterCount();
						if(paramsCount != constParams.size())
							throw new Exception("Missing parameters");
						Field[] fields = cls.getDeclaredFields();
						System.out.println("checking fields "+fields.length+" count : "+paramsCount);
						if(checkParams(constParams, fields)){
							Object[] obj = new Object[fields.length];
							output = (T) constructor.newInstance(populateObjects(constParams, fields));
						}
							
					}
					break;
			}
		}
		return  output;
	}
	
	
	private static Object[] populateObjects(Map<String,Object> map, Field[] fields){
		
		Object[] obj = new Object[fields.length];
		
		for(int i=0;i<fields.length;i++){
			obj[i] = map.get(fields[i].getName());
		}
		return obj;
		
	}
	private static boolean checkParams(Map<String,Object> map, Field[] fields) throws Exception{
		for(Field field : fields){
			System.out.println(!map.containsKey(field.getName()) && !(map.get(field.getName()).getClass().equals(field.getType())));
			//System.out.println(map.get(field.getName()).getClass().equals(field.getType()));
			if(!map.containsKey(field.getName()) || !(map.get(field.getName()).getClass().equals(field.getType()))){
//				/System.out.println("throwing exception for field "+field.getName());
				throw new Exception("Missing parameter or type mismatch: "+field.getName());
			}
		}
		return true;
	}
	public static void main(String[] args) {
		try {
			System.out.println("inside main");
			Map<String, Object> argsMap = new HashMap<String,Object>();
			argsMap.put("empId", 1L);
			argsMap.put("empName", "Swapnil");
			argsMap.put("salary", new BigDecimal(20000));
			Employee e = (Employee)getBean("com.swapnil.bean.Employee",argsMap);
			System.out.println("after getBean : "+e.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
