package org.mob.templ8;
import java.io.*;
import java.util.*;

public class ExecutionContext{

    private Writer outputWriter;
	private Map<String, Object> objects = new HashMap<String, Object>();
	private Map<ForBlock, Iterator> loops = new HashMap<ForBlock, Iterator>();

	public ExecutionContext(Writer outputWriter){//{{{
		this.outputWriter = outputWriter;
	}//}}}

	public ExecutionContext(){//{{{
		this.outputWriter = new PrintWriter(System.out);
	}//}}}

    public void write(String data) throws IOException{//{{{
		outputWriter.write(data);
	}//}}}

	public void setObject(String key, Object value){//{{{
		this.objects.put(key, value);
	}//}}}

	public Object getObject(String key){//{{{
		return this.objects.get(key);
	}//}}}

	public Map getMap(String key){//{{{
		return (Map)this.objects.get(key);
	}//}}}

	public void flush() throws IOException{//{{{
		this.outputWriter.flush();
	}//}}}

	public Iterator getIteratorForBlock(ForBlock forblock){
		return this.loops.get(forblock);
	}

	public void setIteratorForBlock(ForBlock forblock, Iterator it){
		this.loops.put(forblock, it);
	}

	public void terminateForBlockIterator(ForBlock forblock){
		this.loops.remove(forblock);
	}
}
