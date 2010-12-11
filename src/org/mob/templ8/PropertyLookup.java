package org.mob.templ8;
import java.io.*;
import java.util.*;
import java.lang.reflect.Method;
import org.apache.commons.beanutils.PropertyUtils;

public class PropertyLookup implements DynamicLookup{

	private final String name;
	public PropertyLookup(String name){ this.name = name; }

	@Override
	public Object getValue(Object from, ExecutionContext ec){//{{{
		try{
			return PropertyUtils.getSimpleProperty(from, name);
		}catch(Exception e){ //TODO FIX THIS
			try{
				Class[] paramTypes = null;
				Object[] paramVals = null;
				Method method = from.getClass().getMethod(name, paramTypes);
				return method.invoke(from, paramVals);
			}catch(Exception f){
				f.printStackTrace();
				throw new RuntimeException("couldn't get property value"); // TODO use a better exception here.
			}
		}
	}//}}}

}

