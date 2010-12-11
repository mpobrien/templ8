package org.mob.templ8;
import java.io.*;
import java.util.*;

public class Template{

	private final File file;
	private Node headNode;
	private long lastLoadTime = 0;
	private Map<String,StartNamedBlock> blocks = new HashMap<String,StartNamedBlock>();
	private String parentName;
	private TemplateLoader loader;
	private Template parent;


	public Template(File file){
		this.file = file;
	}

	public Template(Node headNode){
		this.file = null;
		this.headNode = headNode;
	}
	
	public Map<String,StartNamedBlock> getBlocks(){
		return this.blocks;
	}

	public void setBlocks(Map<String,StartNamedBlock> blocks){
		this.blocks = blocks;
	}

	public String getParentName(){ return this.parentName; }
	public void loadParent(String parentName) throws CompileError,IOException{
		this.parentName = parentName;
		if( this.parentName != null ){
			this.parent = this.loader.getTemplate(this.parentName);
		}
	}

	public Template getParent() throws IOException, CompileError{ 
		if( this.parentName != null ){
			this.parent = this.loader.getTemplate(this.parentName);
			return this.parent;
		}else{
			return null;
		}
		//return this.parent; 
	}

 	public void reload() throws IOException, CompileError{//{{{
 		if( this.file != null ){
 			BufferedReader br = new BufferedReader(new FileReader(this.file));
 			String line = null, src = "";
 			while( (line = br.readLine()) != null ){
 				src += line + "\n";
 			}
 			//TODO close and clean up
 			String lines[] = src.split("\n");
 			LineTokenizer lt = new LineTokenizer(lines);
 			Compiler compiler = new Compiler();
			Template newTemplate = compiler.compile(lt.tokens(), this.loader);
			setBlocks(newTemplate.getBlocks());
			newTemplate.setTemplateLoader(this.loader);
			loadParent(newTemplate.getParentName());
 			//this.headNode = compiler.compile(lt.tokens());
			this.headNode = newTemplate.getHeadNode();
 			this.lastLoadTime = System.currentTimeMillis();
 		}
 	}//}}}

    public TemplateLoader getTemplateLoader(){ return this.loader; }
 
    public void setTemplateLoader(TemplateLoader loader){
		this.loader = loader;
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

	public void setHeadNode(Node headNode){
		this.headNode = headNode;
	}

	public void execute(ExecutionContext ec) throws IOException, CompileError{
		//Interpreter interp = new Interpreter(this.headNode);
		Interpreter interp = new Interpreter(this);
		interp.execute(ec, this);
	}

}
