package org.mob.templ8;
import java.util.regex.*;
import java.util.*;
import java.io.*;

public class StartNamedBlock extends BlockNode{

	private final String blockName;
	protected Node executeNode;

	public StartNamedBlock(String blockName){
		this.blockName = blockName;
	}

	@Override
	public Node execute(ExecutionContext ec, Template tmpl) throws IOException{//{{{
		Map<String,StartNamedBlock> blocks = tmpl.getBlocks();
		Node n = blocks.get(this.blockName);
		if( blocks.containsKey(this.blockName) ){
			return blocks.get(this.blockName).getExecuteNode();
		}else{
			return this.executeNode;
		}
		//return this.getNextNode();
	}//}}}

	public Node getExecuteNode(){//{{{
		return this.executeNode;
	}//}}}

	public void setExecuteNode(Node node){//{{{
		this.executeNode = node;
	}//}}}


     public CompilerCommand preProcessCompileStack(Node node, Node appendTo, Stack<Node> nodeStack){
 		return CompilerCommand.identity(node, appendTo);
 	 }

     public CompilerCommand processCompileNodes(Node node, Node appendTo, Stack<Node> nodeStack, Map<String, StartNamedBlock> blocks){
		 //TODO check for duplicate key?
		blocks.put(this.blockName, this);
		if( getExecuteNode() == null ){
			nodeStack.push(this);
			setExecuteNode(node);
			return new CompilerCommand(node, node, false);
		}else{
			setNextNode(node);
			return new CompilerCommand(node, node, false);
		}
	 }

	 public String getBlockName(){
		 return this.blockName;
	 }
}
