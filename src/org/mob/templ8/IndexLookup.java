package org.mob.templ8;
import java.io.*;
import java.util.*;

public class IndexLookup implements DynamicLookup{

	private final Atom key;
	public IndexLookup(Atom key){ this.key = key; }

	@Override
	public Object getValue(Object from, ExecutionContext ec){
		if( from instanceof Map){
			return ((Map)from).get(key.getValue(ec));
		}else if(from instanceof List){
			Integer value = (Integer)key.getValue(ec); //TODO classcastexception here?
			return ((List)from).get(value);
		}else{
			throw new RuntimeException("can't do key/index lookup on non-map or list types"); // TODO use a better exception here.
		}
	}

}

