package org.mob.templ8;
import java.util.regex.*;
import java.io.*;

public class EndIfBlock extends BlockNode{

	public EndIfBlock(){ }

	public void appendNode(Node n){} //does nothing in this case.

	@Override
	public Node execute(ExecutionContext ec) throws IOException{
		return this.getNextNode();
	}

	public CompilerCommand preProcessCompileStack(Node node, Node appendTo, Stack<Node> nodeStack){
		//TODO check node type!
		//TODO check stack non-empty!
		Node newAppendTo = nodeStack.pop();
		return new CompilerCommand(node, newAppendTo, true);
	}

	public CompilerCommand processCompileNodes(Node node, Node appendTo, Stack<Node> nodeStack){
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

