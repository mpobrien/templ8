package org.mob.templ8;
import java.util.regex.*;
import java.io.*;

public class TextNode extends Node{

	private final String text;

	public TextNode(String text){
		this.text = text;
	}

	public String toString(){
		return "TextNode: " + this.text;
	}

	@Override
	public Node execute(ExecutionContext ec) throws IOException{
		ec.write(this.text);
		return this.getNextNode();
	}
	
// 	public String debug(){
// 		return " (TextNode) -> " + (this.getNextNode() != null ? this.getNextNode().debug() : "(null)") + "\n";
// 	}
// 
}
