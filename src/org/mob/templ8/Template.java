package org.mob.templ8;
import java.io.*;

public class Template{

	private final File file;
	private Node headNode;
	private long lastLoadTime = 0;

	public Template(File file){
		this.file = file;
	}

	public void reload() throws IOException, CompileError{
		BufferedReader br = new BufferedReader(new FileReader(this.file));
		String line = null, src = "";
		while( (line = br.readLine()) != null ){
			src += line + "\n";
		}
		//TODO close and clean up
		String lines[] = src.split("\n");
		LineTokenizer lt = new LineTokenizer(lines);
		Compiler compiler= new Compiler();
		this.headNode = compiler.compile(lt.tokens());
		this.lastLoadTime = System.currentTimeMillis();
	}

	public boolean isStale(){
		if( this.file.lastModified() > lastLoadTime){
			return true;
		}else{
			return false;
		}
	}

	public Node getHeadNode(){
		return this.headNode;
	}

	public void execute(ExecutionContext ec) throws IOException{
		Interpreter interp = new Interpreter(this.headNode);
		interp.execute(ec);
	}

}
