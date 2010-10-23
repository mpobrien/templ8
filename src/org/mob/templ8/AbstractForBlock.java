package org.mob.templ8;
import java.util.regex.*;
import java.util.*;
import java.io.*;

public abstract class AbstractForBlock extends BlockNode{

	protected Node executeNode;
	protected final String collectionName;

	public AbstractForBlock(String collectionName){
		this.collectionName = collectionName;
	}

	public Node getExecuteNode(){//{{{
		return this.executeNode;
	}//}}}

	public void setExecuteNode(Node node){//{{{
		this.executeNode = node;
	}//}}}

	@Override
	public abstract Node execute(ExecutionContext ec) throws IOException;

	public CompilerCommand processCompileNodes(Node node, Node appendTo, Stack<Node> nodeStack){
		if( getExecuteNode() == null ){
			nodeStack.push(this);
			setExecuteNode(node);
			return new CompilerCommand(node, node, false);
		}else{
			setNextNode(node);
			return new CompilerCommand(node, node, false);
		}
	}

}

