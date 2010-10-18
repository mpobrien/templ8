package org.mob.templ8;
import java.util.regex.*;
import java.io.*;

public class ElseBlock extends BlockNode{

	private Node executeNode = null;
	private ElseBlock skipNode = null;

	private final String expression;

	public ElseBlock(String expression){//{{{
		this.expression = expression;
		//super(expression);
	}//}}}

	public ElseBlock(){//{{{
		this.expression = null;
	}//}}}

	public Node getExecuteNode(){
		return this.executeNode;
	}

	public void setExecuteNode(Node node){
		System.out.println(this + " Setting execute node to: " + node);
		this.executeNode = node;
	}

	public void setSkipNode(ElseBlock node){
		System.out.println(this + " Setting skip node to: " + node);
		this.skipNode = node;
	}

	public ElseBlock getSkipNode(){
		return this.skipNode;
	}

	@Override
	public Node execute(ExecutionContext ec) throws IOException{
		if( this.expression == null ){
			return this.getExecuteNode();
		}else{
			if( this.expression.endsWith("false") ){
				return null;
			}else{
				return this.getExecuteNode();
			}
		}
	}


	@Override
	public String debug(){ return "ElseBlock: " ; }


}
