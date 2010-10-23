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

	public Node getExecuteNode(){//{{{
		return this.executeNode;
	}//}}}

	public void setExecuteNode(Node node){//{{{
		this.executeNode = node;
	}//}}}

	public void setSkipNode(ElseBlock node){//{{{
		this.skipNode = node;
	}//}}}

	public ElseBlock getSkipNode(){//{{{
		return this.skipNode;
	}//}}}

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

	@Override
	public Node execute(ExecutionContext ec) throws IOException{
		if( this.expression == null ){
			return this.getExecuteNode();
		}else{
			String tokens[] =this.expression.trim().split("\\s+") ;
			BooleanExpressionEvaluator bee = new BooleanExpressionEvaluator(tokens); // TODO fix tokenization here
			boolean evaluated;
			try{
				evaluated = bee.evaluate(ec);
			}catch(Exception e){
				// TODO evaluate throws EXCEPTION here but
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
		}
	}

	public CompilerCommand preProcessCompileStack(Node node, Node appendTo, Stack<Node> nodeStack){
		Node newAppendTo = nodeStack.peek();
		return new CompilerCommand(node, newAppendTo, false);
	}

	public CompilerCommand processCompileNodes(Node node, Node appendTo, Stack<Node> nodeStack){
		if( node instanceof ElseBlock){
			addSkipNode((ElseBlock)node);
			return new CompilerCommand(node, node, true);
		}else if( node instanceof EndIfBlock ){
			Node newAppendTo = nodeStack.pop();
			return new CompilerCommand(node, newAppendTo, true);
		}

		if( getExecuteNode() == null ){
			setExecuteNode(node);
			return new CompilerCommand(node, node, true);
		}else{
			setNextNode(node);
			return new CompilerCommand(node, node, true);
		}
	}

// 	@Override
// 	public String debug(){ return "ElseBlock: " ; }


}
