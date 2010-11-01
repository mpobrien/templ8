package org.mob.templ8;
import java.io.*;
import java.util.*;

public class EchoNode extends Node{

	private final String expression;

	public EchoNode(String expression){
		this.expression = expression;
	}

	public String toString(){
		return "EchoNode: " + this.expression;
	}

	@Override
	public Node execute(ExecutionContext ec, Template tmpl) throws IOException{
		Atom atom = AtomFactory.getAtom(this.expression);
		Object v = atom.getValue(ec);
		if( v != null ){
			ec.write( v.toString() );
		}
		return this.getNextNode();
	}

     public CompilerCommand preProcessCompileStack(Node node, Node appendTo, Stack<Node> nodeStack){
		return CompilerCommand.identity(node, appendTo);
	 }

     public CompilerCommand processCompileNodes(Node node, Node appendTo, Stack<Node> nodeStack, Map<String,StartNamedBlock> blocks){
		this.setNextNode(node);
		return new CompilerCommand(null, node, true);
	 }


}
