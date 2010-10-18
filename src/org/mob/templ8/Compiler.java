package org.mob.templ8;
import java.util.regex.*;
import java.io.*;
import com.google.common.collect.*;
import java.util.*;

public class Compiler{

	Stack<Node> nodeStack = new Stack<Node>();

	public Compiler(){}

 	public Node compile(Iterable<Node> nodes) throws CompileError{
 		Node root = null;
 		Node appendTo = null;
 		for( Node node : nodes ){
 			if( root == null ){
 				root = node;
 				appendTo = node;
 				continue;
 			}
			
			//this is a mess. refactor it into something sexy.

			if( node instanceof ElseBlock ){
				appendTo = nodeStack.peek();
			}else if ( node instanceof EndIfBlock ){
				appendTo = nodeStack.pop();
				continue;
			}
			
			if( appendTo instanceof ElseBlock ){
				ElseBlock elseblock = (ElseBlock)appendTo;
				if( node instanceof ElseBlock ){
					elseblock.setSkipNode((ElseBlock)node);
					appendTo = node;
					continue;
				}else if( node instanceof EndIfBlock ){
					appendTo = nodeStack.pop();
					System.out.println("popped, " + appendTo);
					continue;
				}

				if( elseblock.getExecuteNode() == null ){
					elseblock.setExecuteNode(node);
					appendTo = node;
					continue;
				}else{
					elseblock.setNextNode(node);
					appendTo = node;
				}
			}

			if( appendTo instanceof IfBlock ){
				IfBlock ifblock = (IfBlock)appendTo;
				if( node instanceof ElseBlock ){
					ifblock.setSkipNode((ElseBlock)node);
					appendTo = node;
					continue;
				}else if( node instanceof EndIfBlock ){
					appendTo = nodeStack.pop();
					continue;
				}

				if( ifblock.getExecuteNode() == null ){
					nodeStack.push(ifblock);
					ifblock.setExecuteNode(node);
					appendTo = node;
				}else{
					ifblock.setNextNode(node);
					appendTo = node;
				}
				continue;
			} else{
				System.out.println("mob5");
				appendTo.setNextNode(node);
				appendTo = node;
				continue;
			}
// 
// 			System.out.println("mob4 " + node + " appendto " + appendTo);
// 
// 			if( node instanceof EndIfBlock || node instanceof ElseBlock ){
// 				if( nodeStack.getSize() == 0 ){
// 					throw new CompileError("endif without matching if");
// 				}
// 				appendTo = nodeStack.pop();
// 				if( !(appendTo instanceof IfBlock ) ){
// 					throw new CompileError("wrong closing block");
// 				}
// 				continue;
// 			}

		}
		System.out.println(root);
		return root;
	}

// 	public Node compile(Iterable<Node> nodes){
// 		Node root = null;
// 		Node currentNode = null;
// 		Node head = null;
// 		for( Node node : nodes ){
// 			System.out.println("got node: " + node);
// 			if( currentNode == null ){
// 				root = node;
// 				head = node;
// 				currentNode = node;
// 				continue;
// 			}
// 			currentNode = node;
// 			/******/
// 			//System.out.println("head: " + head + ", currentNode: " + currentNode);
// 
// 			/* stack operations */
//             if( currentNode instanceof IfBlock ){
// 				System.out.println("pushing: ");
// 				nodeStack.push( currentNode );
// 				head.setNextNode( currentNode );
// 				head = currentNode;
// 				continue;
// 			}
//             if( currentNode instanceof EndIfBlock ){
// 				System.out.println("popping from " + nodeStack.getSize());
// 				head = nodeStack.pop();
// 				System.out.println("head is: " + head);
// 			}
// 
// 			if( !(head instanceof IfBlock) ){
// 				System.out.println("appending node: " + currentNode + " to " + head);
// 				head.setNextNode( currentNode );
// 				head = currentNode;
// 				continue;
// 			}else {
// 				IfBlock ifblock = (IfBlock)head;
// 				if( ifblock.getExecuteNode() == null ){
// 					System.out.println("setting execute node to: " + currentNode);
// 					ifblock.setExecuteNode(currentNode);
// 				}else{
// 					System.out.println("setting NEXT node to: " + currentNode);
// 					ifblock.setNextNode( currentNode );
// 				}
// 				head = currentNode;
// 			}
// 		}
// 		return root;
// 	}

	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader(new File(args[0])));
		String line = null, src = "";
		while( (line = br.readLine()) != null ){
			src += line;
		}
		Tokenizer it = new Tokenizer(src);
		Compiler comp = new Compiler();
		Node head = comp.compile( it.tokens() );

		System.out.println( "\n\n");
		System.out.println(head);
		System.out.println(head.debug());
	}


}
