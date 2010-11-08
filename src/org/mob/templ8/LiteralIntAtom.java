package org.mob.templ8;
import java.io.*;
import java.util.*;

public class LiteralIntAtom extends Atom{

    private final Integer val;

    public LiteralIntAtom(Integer val){
		this.val = val;
	}

	@Override
	public Integer getValue(ExecutionContext ec){
		return this.val;
	}

	public String toString(){
		return "Int Literal: \"" + this.val.toString() + "\"";
	}
}
