package org.mob.templ8;
import java.util.*;
import java.io.*;

public class TemplateLoader{

    private String rootDirectory = null;
	private Map<String, Template> templates = new HashMap<String, Template>();

	public TemplateLoader(){}

    public TemplateLoader(String rootDirectory){ // change the name.
        this.rootDirectory = rootDirectory;
    }

	public void setRootDirectory(String rootDirectory){
        this.rootDirectory = rootDirectory;
	}

	public Template getTemplate(String path) throws IOException, CompileError{
		if( rootDirectory == null ) throw new IOException("template loader root path is not set.");
		Template returnVal = null;
		if( this.templates.containsKey(path) ){
			returnVal = this.templates.get(path);
			if( returnVal != null ){
				returnVal.setTemplateLoader(this);
				if( returnVal.isStale() ){
					returnVal.reload();
				}
			}
		}

		if( returnVal == null ){
			String templatePath = this.rootDirectory + "/" + path;
			Template template = new Template(new File(templatePath));
			template.setTemplateLoader(this);
			template.reload();
			this.templates.put(path, template);
			returnVal = template;
		}
		return returnVal;
	}



}
