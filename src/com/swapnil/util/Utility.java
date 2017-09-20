package com.swapnil.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;

import com.swapnil.annotation.Bean;
import com.swapnil.constant.Constants;

public class Utility {

	public Map<String,Class<?>> allFoundClassesAnnotatedWithEntityToBeScanned(){
        Reflections reflections = new Reflections(".*");
        Set<Class<?>> annotattedClasses = reflections.getTypesAnnotatedWith(Bean.class);
        Map<String,Class<?>> annotattedMap = createMapOfAnnotatedClasses(annotattedClasses);
        return annotattedMap;
    }

	private  Map<String,Class<?>> createMapOfAnnotatedClasses(Set<Class<?>> set){
		Map<String,Class<?>> annotattedMap = new HashMap<String, Class<?>>();
		set.forEach((s) -> {
			annotattedMap.put(s.getAnnotation(Bean.class).name(),s);
		});
		return annotattedMap;
	}
	
	
	public boolean checkParamType(Field field, Object obj){
		if(obj != null)
			return field.getType().equals(obj.getClass());
		return false;
	}
	
	public Object[] populateObjects(Map<String,Object> map, Field[] fields){
		
		Object[] obj = new Object[fields.length];
		
		for(int i=0;i<fields.length;i++){
			obj[i] = map.get(fields[i].getName());
		}
		return obj;
		
	}
	public boolean checkParams(Map<String,Object> map, Field[] fields) throws Exception{
		for(Field field : fields){
			if(!map.containsKey(field.getName()) || !(map.get(field.getName()).getClass().equals(field.getType()))){
				throw new Exception(Constants.PARAM_MISSING_OR_TYPE_MISMATCH+""+field.getName());
			}
		}
		return true;
	}
}
