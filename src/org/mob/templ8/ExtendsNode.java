package org.mob.templ8;
import java.util.regex.*;
import java.io.*;
import java.util.*;

public class ExtendsNode extends BlockNode{

	private final String parentName;

	public ExtendsNode(String parentName){
		this.parentName = parentName;
	}

	public String getParentName(){
		return this.parentName;
	}

	@Override
	public Node execute(ExecutionContext ec, Template tmpl) throws IOException{//{{{
		return this.nextNode;
	}//}}}


     public CompilerCommand preProcessCompileStack(Node node, Node appendTo, Stack<Node> nodeStack){
		return CompilerCommand.identity(node, appendTo);
	 }

     public CompilerCommand processCompileNodes(Node node, Node appendTo, Stack<Node> nodeStack, Map<String, StartNamedBlock> blocks){
		this.setNextNode(node);
		return new CompilerCommand(null, node, true);
	 }

}
