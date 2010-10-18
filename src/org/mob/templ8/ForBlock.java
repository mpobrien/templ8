package org.mob.templ8;
import java.util.regex.*;
import java.util.*;
import java.io.*;

public class ForBlock extends BlockNode{
	// for k, v in map
	//
	// for i in list
	//
	// for k in set

	public String render(){ return ""; }

	private Node executeNode;
	private final String collectionName;
	private final String varName;

	public ForBlock(String varName, String collectionName){
		this.varName = varName;
		this.collectionName = collectionName;
	}

	public Node getExecuteNode(){//{{{
		return this.executeNode;
	}//}}}

	public void setExecuteNode(Node node){//{{{
		this.executeNode = node;
	}//}}}

	@Override
	public Node execute(ExecutionContext ec) throws IOException{//{{{
		Iterator it = ec.getIteratorForBlock(this);
		if( it == null ){
			Atom collectionAtom = AtomFactory.getAtom(collectionName);
			Object o = collectionAtom.getValue(ec);
			if( o == null ){
				return this.executeNode;
			}
			if( !(o instanceof Collection) ){
				throw new IOException(); // TOOD better exception here.
			}else{
				it = ((Collection)o).iterator();
				ec.setIteratorForBlock(this, it);
			}
		}
		if( it.hasNext() ){
			Object o = it.next();
			ec.setObject(varName, o);
			return this.executeNode;
		}else{
			ec.terminateForBlockIterator(this);
			return this.nextNode;
		}

	}//}}}

	@Override
	public String debug(){//{{{
		return "forblock";
	}//}}}

}
