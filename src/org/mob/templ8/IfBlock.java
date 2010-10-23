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

	public void addSkipNode(ElseBlock node){
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
	}

	public Node getExecuteNode(){
		return this.executeNode;
	}
	public void setExecuteNode(Node node){
		//System.out.println(this + " Setting execute node to: " + node);
		this.executeNode = node;
	}

	public void setSkipNode(ElseBlock node){
		//System.out.println(this + " Setting skip node to: " + node);
		this.skipNode = node;
	}

	public Node getSkipNode(){
		return this.skipNode;
	}

	@Override
	public Node execute(ExecutionContext ec) throws IOException{
		String tokens[] =this.expression.trim().split("\\s+") ;
		BooleanExpressionEvaluator bee = new BooleanExpressionEvaluator(tokens); // TODO fix tokenization here
		boolean evaluated;
		try{
			evaluated = bee.evaluate(ec);
			System.out.println("evaluating " + evaluated);
			for( String t : tokens ){
				System.out.println("token:" +  t);
			}
		}catch(Exception e){// TODO evaluate throws EXCEPTION here but execute() method throws IO exception. organize this
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

// 	@Override
// 	public String debug(){
// 		return " IfBlock, execNode = " + (this.getExecuteNode() != null ? this.getExecuteNode().debug() : "(null)") + "\n" + 
// 				"         nextNode = " + (this.getNextNode() != null ? this.getNextNode().debug() : "(null)"); 
// 	}
// 


}
