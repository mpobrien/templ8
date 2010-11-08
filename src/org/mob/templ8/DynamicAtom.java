package org.mob.templ8;
import java.io.*;
import java.util.*;

public class VarAtom extends Atom{

    private final String varName;

	// name --> callmethod(methodname) --> lookup(key) --> 
	private final LinkedList directives;

    public VarAtom(String varName){
		this.varName = varName;
	}

	@Override
	public Object getValue(ExecutionContext ec){
		return ec.getObject(this.varName);
	}



	

}
