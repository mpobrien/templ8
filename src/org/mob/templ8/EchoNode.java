package org.mob.templ8;
import java.io.*;

public class EchoNode extends Node{

	private final String expression;

	public EchoNode(String expression){
		this.expression = expression;
	}

	public String toString(){
		return "EchoNode: " + this.expression;
	}

	@Override
	public Node execute(ExecutionContext ec) throws IOException{
		Atom atom = AtomFactory.getAtom(this.expression);
		System.out.println("atom: " + atom);
		Object v = atom.getValue(ec);
		if( v != null ){
			ec.write( v.toString());
		}
		return this.getNextNode();
	}

	@Override
	public String debug(){
		return " EchoNode, nextNode = " + (this.getNextNode() != null ? this.getNextNode().debug() : "(null)"); 
	}

}
