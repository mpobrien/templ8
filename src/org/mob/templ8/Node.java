package org.mob.templ8;
import java.util.*;
import java.util.regex.*;
import java.io.*;

public abstract class Node{

	protected Node nextNode = null;
	protected int lineNumber;

	//public abstract NodeTree.StackOperation push(Node newNode);
	//
	//public abstract void appendNode(Node n);

	public void setNextNode(Node n){
		this.nextNode = n; 
	}
	public Node getNextNode(){return this.nextNode;}

	public abstract Node execute(ExecutionContext ec, Template templ) throws IOException;

// 	public abstract String debug();

	public int getLineNumber(){
		return this.lineNumber;
	}

	public void setLineNumber(int lineNumber){
		this.lineNumber = lineNumber;
	}

     public CompilerCommand preProcessCompileStack(Node node, Node appendTo, Stack<Node> nodeStack){
		 return new CompilerCommand(node, appendTo, false);
	 }

     public abstract CompilerCommand processCompileNodes(Node node, Node appendTo, Stack<Node> nodeStack, Map<String, StartNamedBlock> blocks);

}
