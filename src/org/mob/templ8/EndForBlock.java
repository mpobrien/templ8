package org.mob.templ8;
import java.util.regex.*;
import java.io.*;

public class EndForBlock extends BlockNode{

	public EndForBlock(){ }

	public void appendNode(Node n){} //does nothing in this case.

	@Override
	public Node execute(ExecutionContext ec) throws IOException{
		return this.getNextNode();
	}

// 	@Override
// 	public String debug(){
// 		return " EndForBlock, nnextNode = " + (this.getNextNode() != null ? this.getNextNode().debug() : "(null)"); 
// 	}
}

