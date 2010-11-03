package org.mob.templ8;
import java.util.regex.*;
import java.io.*;
import com.google.common.collect.*;
import java.util.*;

public class Interpreter{

	//private final Node head;
	private final Template template;
	Stack<Node> nodeStack = new Stack<Node>();

	public Interpreter(Template template){
		this.template = template;
	}

	public void execute(ExecutionContext ec, Template tmpl) throws IOException{
		Node node;
		if( this.template.getParent() != null ){
			node = this.template.getParent().getHeadNode();
		}else{
			node = this.template.getHeadNode();
		}
		System.out.println("1:" +this.template.getHeadNode());
		//System.out.println("2: "+this.template.getParent().getHeadNode());
		StringWriter sw = new StringWriter();
		while( true ){
			System.out.println("Executing: " + node);
			if( node == null ){
				if( nodeStack.getSize() == 0 ){
					System.out.println("done");
					break;
				}else{
					Node topNode = nodeStack.peek();
					if( topNode instanceof IfBlock ){
						node = nodeStack.pop().getNextNode();
					}else if( topNode instanceof AbstractForBlock){
						node = topNode.execute(ec, template);
						if( node instanceof EndForBlock ){
							node = nodeStack.pop().getNextNode();
							continue;
						}
					} else if ( topNode instanceof StartNamedBlock ){
						node = nodeStack.pop().getNextNode();
						System.out.println("ok now: " +node);
						continue;
					} 
				}
			}

			if( node instanceof IfBlock || node instanceof AbstractForBlock ){
				nodeStack.push(node);
			}
			if( node instanceof	StartNamedBlock ){
				String blockName = ((StartNamedBlock)node).getBlockName();
				if( this.template.getParent() != null ){
 					nodeStack.push(this.template.getParent().getBlocks().get(blockName));
				}else{
					nodeStack.push(node);
				}
			}

			if( node == null ) break;
			node = node.execute(ec, template);
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
		String rootDir = args[0];
		String templateName = args[1];
		TemplateLoader tl = new TemplateLoader(rootDir);
		Template t = tl.getTemplate(templateName);
		StringWriter sw = new StringWriter();
		ExecutionContext ec = new ExecutionContext(sw);
		LinkedHashMap xs = new LinkedHashMap();
		xs.put("mike", 1);
		xs.put("bob", 2);
		xs.put("joe", 3);
		ec.setObject("xs", xs);
		t.execute(ec);

		System.out.println(sw.toString());
// 		BufferedReader br = new BufferedReader(new FileReader(new File(args[0])));
// 		String line = null, src = "";
// 		while( (line = br.readLine()) != null ){
// 			src += line;
// 		}
// 		Tokenizer it = new Tokenizer(src);
// 		Compiler comp = new Compiler();
// 		Template t = comp.compile( it.tokens(), null ); 
// 		//Node root = comp.compile( it.tokens() );
// 		//Interpreter interp = new Interpreter(root);
// 		Interpreter interp = new Interpreter(t);
// 		interp.execute(new ExecutionContext(), t);
	}//}}}

}
