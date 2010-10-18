package org.mob.templ8;
import java.io.*;
import java.util.*;

public class CollectionAtom extends Atom{

	private String CollectionAtom;
	private final Atom keyAtom;
	private final String collectionName;

	public CollectionAtom(String collectionName, Atom keyAtom){
		this.collectionName = collectionName;
		this.keyAtom = keyAtom;
	}

	@Override
	public Object getValue(ExecutionContext ec){
		Object key = this.keyAtom.getValue(ec);
		Object collection = ec.getObject(this.collectionName);
		if( collection != null ){
			if( collection instanceof Map ){
				return ((Map)collection).get(key);
			}else if( collection instanceof List ){
				if( key instanceof Integer ){
					return ((List)collection).get((Integer)key);
				}else{
					return null; //TODO bad key?
				}
			}
		}
// 		else{
			return null; // return key not found? todo
// 		}
	}

}
