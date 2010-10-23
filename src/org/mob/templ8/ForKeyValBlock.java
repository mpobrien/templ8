package org.mob.templ8;
import java.util.regex.*;
import java.util.*;
import java.io.*;

public class ForKeyValBlock extends AbstractForBlock{

	private final String keyVarName, valVarName;

	public ForKeyValBlock(String keyVarName, String valVarName, String collectionName){
		super(collectionName);
		this.keyVarName = keyVarName;
		this.valVarName = valVarName;
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
			if( !(o instanceof Map) ){
				throw new IOException(); // TOOD better exception here.
			}else{
				it = ((Map)o).entrySet().iterator();
				ec.setIteratorForBlock(this, it);
			}
		}
		if( it.hasNext() ){
			Map.Entry keyValPair = (Map.Entry)it.next();
			ec.setObject(keyVarName, keyValPair.getKey());
			ec.setObject(valVarName, keyValPair.getValue());
			return this.executeNode;
		}else{
			ec.terminateForBlockIterator(this);
			return new EndForBlock();
		}

	}//}}}

}
