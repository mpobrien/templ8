package org.mob.templ8;
import java.util.regex.*;
import java.io.*;
import java.util.*;

public class NopNode extends Node{

    public Integer x = 2;

	public NopNode(){ }

	public String toString(){
		return "nopNode";
	}

	public Node execute(ExecutionContext ec, Template tmpl) throws IOException{
		return this.getNextNode();
	}
	
	public String debug(){
		return "nopnode";
	}

     public CompilerCommand preProcessCompileStack(Node node, Node appendTo, Stack<Node> nodeStack){
		return CompilerCommand.identity(node, appendTo);
	 }

     public CompilerCommand processCompileNodes(Node node, Node appendTo, Stack<Node> nodeStack, Map<String, StartNamedBlock> blocks){
		this.setNextNode(node);
		return new CompilerCommand(null, node, true);
	 }

}
