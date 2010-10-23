package org.mob.templ8;
import java.util.regex.*;
import java.io.*;

public class IfBlock extends BlockNode{

	protected Node executeNode = null;
	protected ElseBlock skipNode = null;

	protected final String expression;

	public IfBlock(String expression){
		this.expression = expression;
	}

	public void addSkipNode(ElseBlock node){//{{{
		if( node == null ) return;
		if( this.skipNode == null ){
			this.skipNode = node;
		}else{
			ElseBlock pointer = this.skipNode;
			while( pointer.getSkipNode() != null ){
				pointer = pointer.getSkipNode();
			}
			pointer.setSkipNode(node);
		}
	}//}}}

	public Node getExecuteNode(){ return this.executeNode; }

	public void setExecuteNode(Node node){ this.executeNode = node; }

	public void setSkipNode(ElseBlock node){ this.skipNode = node; }

	public Node getSkipNode(){ return this.skipNode; }

	@Override
	public Node execute(ExecutionContext ec) throws IOException{//{{{
		String tokens[] =this.expression.trim().split("\\s+") ;
		BooleanExpressionEvaluator bee = new BooleanExpressionEvaluator(tokens); // TODO fix tokenization here
		boolean evaluated;
		try{
			evaluated = bee.evaluate(ec);
		}catch(Exception e){// TODO evaluate throws EXCEPTION here but
			                // execute() method throws IO exception. organize this
			throw new IOException("error evaluating expression", e);
		}

		if( evaluated ){
			return this.getExecuteNode();
		}else{
			if( this.skipNode != null ){
				return this.skipNode;
			}else{
				return null;
			}
		}
	}//}}}

	public CompilerCommand processCompileNodes(Node node, Node appendTo, Stack<Node> nodeStack){//{{{
		if( node instanceof ElseBlock){
			addSkipNode((ElseBlock)node);
			return new CompilerCommand(node, node, true);
		}else if( node instanceof EndIfBlock ){
			Node newAppendTo = nodeStack.pop();
			return new CompilerCommand(node, newAppendTo, true);
		}

		if( getExecuteNode() == null ){
			nodeStack.push(this);
			setExecuteNode(node);
			return new CompilerCommand(node, node, true);
		}else{
			setNextNode(node);
			return new CompilerCommand(node, node, true);
		}
	}//}}}

}
