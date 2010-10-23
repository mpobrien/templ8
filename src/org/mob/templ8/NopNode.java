package org.mob.templ8;
import java.util.regex.*;
import java.io.*;

public class NopNode extends Node{

	public NopNode(){ }

	public String toString(){
		return "nopNode";
	}

	@Override
	public Node execute(ExecutionContext ec) throws IOException{
		return this.getNextNode();
	}
	
	public String debug(){
		return "nopnode";
	}

     public CompilerCommand preProcessCompileStack(Node node, Node appendTo, Stack<Node> nodeStack){
		return CompilerCommand.identity(node, appendTo);
	 }

     public CompilerCommand processCompileNodes(Node node, Node appendTo, Stack<Node> nodeStack){
		this.setNextNode(node);
		return new CompilerCommand(null, node, true);
	 }

}
