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

// 	@Override
// 	public String debug(){
// 		return " EndIfBLock, nnextNode = " + (this.getNextNode() != null ? this.getNextNode().debug() : "(null)"); 
// 	}
}

