package org.mob.templ8;
import java.util.regex.*;
import java.io.*;

public class CompilerCommand{

	private final Node newNode;
	private final Node newAppendTo;
	private final boolean restartLoop;

	public static CompilerCommand identity(Node newNode, Node newAppendTo){
		return new CompilerCommand(newNode, newAppendTo, false);
	}

    public CompilerCommand(Node newNode, Node newAppendTo, boolean restartLoop){
		this.newNode = newNode;
		this.newAppendTo = newAppendTo;
		this.restartLoop = restartLoop;
	}

	public Node getNewNode(){ return newNode; }
	public Node getNewAppendTo(){ return newAppendTo; }
	public boolean restartLoop(){ return this.restartLoop; }

}
