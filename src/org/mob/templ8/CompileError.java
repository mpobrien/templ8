package org.mob.templ8;

public class CompileError extends Exception{

	public CompileError(String message){
		super(message);
	}

	public CompileError(String message, Throwable t){
		super(message, t);
	}
}
