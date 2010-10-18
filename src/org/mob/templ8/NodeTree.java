package org.mob.templ8;
import java.util.Stack;

public class NodeTree{

	public enum StackOperation{ NONE, PUSH, POP };

	private Node head = null;
	private Stack<Node> stack = new Stack<Node>();
	private Node stackPointer = null;

	public NodeTree(){}

	public void addNode(Node n){
		if( head == null ){
			head = n;
			stack.push(n);
		}else{
			return;
			//Node stackPointer = stack.peek();
			//StackOperation stackOp = stackPointer.push(n);
			//switch( stackOp ){
				//case NONE: 
					//break;
				//case PUSH:
					//stack.push(n);
					//break;
				//case POP:
					//stack.pop();
					//break;
			//}
		}
	}

	

}
