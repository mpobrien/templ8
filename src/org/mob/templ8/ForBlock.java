package org.mob.templ8;
import java.util.regex.*;
import java.util.*;
import java.io.*;

public class ForBlock extends AbstractForBlock{

	private final String varName;

	public ForBlock(String varName, String collectionName){
		super(collectionName);
		this.varName = varName;
	}

	@Override
	public Node execute(ExecutionContext ec) throws IOException{//{{{
		Iterator it = ec.getIteratorForBlock(this);
		if( it == null ){
			Atom collectionAtom = AtomFactory.getAtom(collectionName);
			Object o = collectionAtom.getValue(ec);
			if( o == null ){
				return this.nextNode;
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
			System.out.println("returning: " + this.executeNode);
			return this.executeNode;
		}else{
			ec.terminateForBlockIterator(this);
			return new EndForBlock();
		}

	}//}}}

}
