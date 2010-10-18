package org.mob.templ8;
import java.io.*;
import java.util.*;

public class LiteralStringAtom extends Atom{

    private final String val;

    public LiteralStringAtom(String val){
		this.val = val;
	}

	@Override
	public String getValue(ExecutionContext ec){
		return this.val;
	}

}
