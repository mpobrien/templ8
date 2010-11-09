package org.mob.templ8;
import java.io.*;
import java.util.*;

public class DynamicAtom extends Atom{

    private final String varName;

	//// name --> callmethod(methodname) --> lookup(key) --> 
	private final LinkedList<DynamicLookup> lookups = new LinkedList<DynamicLookup>();

	public DynamicAtom(String varName){
		this.varName = varName;
	}

	@Override
	public Object getValue(ExecutionContext ec){
		Object o = ec.getObject(this.varName);
		for( DynamicLookup lookup : lookups){
			if( o == null ) return null;
			o = lookup.getValue(o, ec);
		}
		return o;
	}

	public void addLookup( DynamicLookup lookup){
		this.lookups.add( lookup );
	}

}
