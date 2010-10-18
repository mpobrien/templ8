package org.mob.templ8;
import java.util.*;
import java.io.*;

public class TemplateLoader{

    private final String rootDirectory;
	private Map<String, Template> templates = new HashMap<String, Template>();

    public TemplateLoader(String rootDirectory){ // change the name.
        this.rootDirectory = rootDirectory;
    }

	public Template getTemplate(String path) throws IOException, CompileError{
		Template returnVal = null;
		if( this.templates.containsKey(path) ){
			returnVal = this.templates.get(path);
			if( returnVal != null ){
				if( returnVal.isStale() ){
					returnVal.reload();
				}
			}
		}

		if( returnVal == null ){
			String templatePath = this.rootDirectory + "/" + path;
			Template template = new Template(new File(templatePath));
			template.reload();
			this.templates.put(path, template);
			returnVal = template;
		}
		return returnVal;
	}



}
