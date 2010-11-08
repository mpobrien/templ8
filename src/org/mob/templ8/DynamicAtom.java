package org.mob.templ8;
import java.io.*;
import java.util.*;

public class DynamicAtom extends Atom{

    //enum AtomDirective{
		//INDEX_LOOKUP, METHOD_CALL;
		//private final Atom atom;
		//AtomDirective(Atom atom){
			//this.atom = atom;
		//}
		//public Atom getAtom(){
			//return this.atom;
		//}
	//}

    private final String varName;

	//// name --> callmethod(methodname) --> lookup(key) --> 
	//private final LinkedList<AtomDirective> directives;

	public DynamicAtom(String varName){
		this.varName = varName;
	}

	@Override
	public Object getValue(ExecutionContext ec){
		return ec.getObject(this.varName);
	}

	//public void pushDirective( AtomDirective atomdirective){
		//this.directives.add( atomdirective );
	//}


	

}
