package org.mob.templ8;
import java.util.regex.*;
import java.util.*;
import java.io.*;

public class EndForBlock extends BlockNode{

	public EndForBlock(){ }

	public void appendNode(Node n){} //does nothing in this case.

	@Override
	public Node execute(ExecutionContext ec, Template tmpl) throws IOException{
		return this.getNextNode();
	}

	public CompilerCommand preProcessCompileStack(Node node, Node appendTo, Stack<Node> nodeStack) throws CompileError{
		if( nodeStack.isEmpty() ){
			throw new CompileError("endfor without matching for block", this);
		}

		Node newAppendTo = nodeStack.pop();
		if( !(newAppendTo instanceof AbstractForBlock) ){
			throw new CompileError("endfor without matching for block", this);
		}
		return new CompilerCommand(node, newAppendTo, true);
	}

     public CompilerCommand processCompileNodes(Node node, Node appendTo, Stack<Node> nodeStack, Map<String, StartNamedBlock> blocks){
		 //SHOUL DNOT HAPPEN
		 return null;
	 }

// 	@Override
// 	public String debug(){
// 		return " EndForBlock, nnextNode = " + (this.getNextNode() != null ? this.getNextNode().debug() : "(null)"); 
// 	}
}

