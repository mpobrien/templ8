package org.mob.templ8;
import java.io.*;
import java.util.*;

public class VarAtom extends Atom{

    private final String varName;

    public VarAtom(String varName){
		this.varName = varName;
	}

	@Override
	public Object getValue(ExecutionContext ec){
		return ec.getObject(this.varName);
	}

}
