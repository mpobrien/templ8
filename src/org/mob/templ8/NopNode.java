package org.mob.templ8;
import java.util.regex.*;
import java.io.*;

public class NopNode extends Node{

	public NopNode(){ }

	public String toString(){
		return "nopNode";
	}

	@Override
	public Node execute(ExecutionContext ec) throws IOException{
		return this.getNextNode();
	}
	
	public String debug(){
		return "nopnode";
	}

}
