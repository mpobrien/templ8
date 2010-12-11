package org.mob.templ8;
import java.util.regex.*;
import java.util.*;
import java.io.*;

public class EndIfBlock extends BlockNode{

	public EndIfBlock(){ }

	public void appendNode(Node n){} //does nothing in this case.

	@Override
	public Node execute(ExecutionContext ec, Template tmpl) throws IOException{
		return this.getNextNode();
	}

	public CompilerCommand preProcessCompileStack(Node node, Node appendTo, Stack<Node> nodeStack) throws CompileError{
		if( nodeStack.isEmpty() ){
			throw new CompileError("endif without matching if", this);
		}

		Node newAppendTo = nodeStack.pop();
		if( !(newAppendTo instanceof IfBlock) ){
			throw new CompileError("endif without matching if", this);
		}
		return new CompilerCommand(node, newAppendTo, true);
	}

	public CompilerCommand processCompileNodes(Node node, Node appendTo, Stack<Node> nodeStack, Map<String, StartNamedBlock> blocks){
		//should never happen!
		return null;
// 		this.setNextNode(node);
// 		return new CompilerCommand(null, node, true);
	}


// 	@Override
// 	public String debug(){
// 		return " EndIfBLock, nnextNode = " + (this.getNextNode() != null ? this.getNextNode().debug() : "(null)"); 
// 	}
}

