package org.mob.templ8;

public class CompileError extends Exception{

	private final Node errorNode;

	public CompileError(String message, Node errorNode){
		super(message);
		this.errorNode = errorNode;
	}

	public CompileError(String message, Node errorNode, Throwable t){
		super(message, t);
		this.errorNode = errorNode;
	}

	public Node getNode() { return errorNode; }
}
