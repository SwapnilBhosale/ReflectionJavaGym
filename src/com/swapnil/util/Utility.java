package com.swapnil.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;

import com.swapnil.annotation.Bean;
import com.swapnil.constant.Constants;

/**
 * @author Swapnil Bhosale
 * This is Utility class having ultility functions used
 * throughout the program
 */
public class Utility {


	/**
	   * This method is used scan the java classpath for annotated class with 'Bean'
	   * This functions make a use of Reflection library by google to scan classpath
	   * for the classes with 'Bean' annotation
	   * @return Map a map of bean name and class reference
	*/
	public Map<String,Class<?>> allFoundClassesAnnotatedWithEntityToBeScanned(){
		Reflections reflections = new Reflections(".*");
		Set<Class<?>> annotattedClasses = reflections.getTypesAnnotatedWith(Bean.class);
		Map<String,Class<?>> annotattedMap = createMapOfAnnotatedClasses(annotattedClasses);
		return annotattedMap;
	}

	/**
	   * This method is used create a Map from the classes return by reflection library
	   * @param set A set of class references to annotated classes
	   * @return Map a map of bean name and class reference
	*/
	private  Map<String,Class<?>> createMapOfAnnotatedClasses(Set<Class<?>> set){
		Map<String,Class<?>> annotattedMap = new HashMap<String, Class<?>>();
		set.forEach((s) -> {
			annotattedMap.put(s.getAnnotation(Bean.class).name(),s);
		});
		return annotattedMap;
	}


	/**
	   * This method is used to compare the class instance variable type with provided
	   * value type
	   * @param field A filed of class
	   * @param obj Object which needs to be compare with
	   * @return boolean returns true if type of both is same
	*/
	public boolean checkParamType(Field field, Object obj){
		if(obj != null)
			return field.getType().equals(obj.getClass());
		return false;
	}

	/**
	   * This method is used to populate the Object array with user provided values
	   * @param map A map of user provided values
	   * @param fields A fields of the class 
	   * @return Object[] Array of objects with user provided values
	  */
	public Object[] populateObjects(Map<String,Object> map, Field[] fields){

		Object[] obj = new Object[fields.length];
		for(int i=0;i<fields.length;i++){
			obj[i] = map.get(fields[i].getName());
		}
		return obj;

	}
	
	/**
	   * This method is used to find if any value is missing in user provide map
	   * or if there is any type mismatch in case of DI by constructor
	   * @param map A map of user provided values
	   * @param fields A fields of the class 
	   * @throws Exception throws exception with appropriate message
	   * @return boolean true if all values are present and type matched with fields of class
	   * in user provided values
	  */
	public boolean checkParams(Map<String,Object> map, Field[] fields) throws Exception{
		for(Field field : fields){
			if(!map.containsKey(field.getName()) || !(map.get(field.getName()).getClass().equals(field.getType()))){
				throw new Exception(Constants.PARAM_MISSING_OR_TYPE_MISMATCH+""+field.getName());
			}
		}
		return true;
	}
}
