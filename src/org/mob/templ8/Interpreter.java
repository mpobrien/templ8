package org.mob.templ8;
import java.util.regex.*;
import java.io.*;
import com.google.common.collect.*;
import java.util.*;

public class Interpreter{

	private final Node head;
	Stack<Node> nodeStack = new Stack<Node>();

	public Interpreter(Node head){
		this.head = head;
	}

	public void execute(ExecutionContext ec) throws IOException{
		Node node = head;
		StringWriter sw = new StringWriter();
		while( true ){
			//System.out.println("node is: " + node);
			if( node == null ){
				if( nodeStack.getSize() == 0 ){
					break;
				}else{
					node = nodeStack.pop().getNextNode();
				}
			}
			if( node instanceof IfBlock ){
				nodeStack.push(node);
			}

			if( node == null ) break;
			node = node.execute(ec);
		}
		ec.flush();
	}

// 	public void execute(Node head) throws IOException{//{{{
// 		Node currentNode = head;
// 		Node nextNode = null;
// 		StringWriter sw = new StringWriter();
// 		while( true ){
// 			if( currentNode instanceof IfBlock ){
// 				nodeStack.push(currentNode);
// 			}
// 			System.out.print("Executing: " + currentNode + ", got: " );
// 			nextNode = currentNode.execute(sw);
// 			System.out.println(nextNode);
// 			if( currentNode instanceof EndIfBlock ){
// 
// 				System.out.println("should be at " + nodeStack.peek().getNextNode());
// 			}
// 			currentNode = nextNode;
// 			nextNode = null;
// 			if( currentNode == null ) break;
// 		}
// 		System.out.println(sw.toString());
// 	}//}}}

	public static void main(String args[]) throws Exception{//{{{
		BufferedReader br = new BufferedReader(new FileReader(new File(args[0])));
		String line = null, src = "";
		while( (line = br.readLine()) != null ){
			src += line;
		}
		System.out.println(src);
		Tokenizer it = new Tokenizer(src);
		Compiler comp = new Compiler();
		Node root = comp.compile( it.tokens() );
		System.out.println("\n\n\n");
		Interpreter interp = new Interpreter(root);
		interp.execute(new ExecutionContext());
	}//}}}

}