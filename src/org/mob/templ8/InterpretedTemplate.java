package org.mob.templ8;
import java.util.regex.*;
import java.io.*;

public class InterpretedTemplate{

	private static final Pattern TMPL8_PATTERN = Pattern.compile("(.*?)?\\{(\\{|%)(.+?)(\\}|%).*", Pattern.DOTALL);
	private static final String PERCENT = "%";
	private static final String OPENSTACHE = "{";
	private static final String CLOSESTACHE = "}";

	public InterpretedTemplate(String source){
		String str = source;
		boolean hasMatch;
		do{
			Matcher m = TMPL8_PATTERN.matcher(str);
			hasMatch = m.matches();
			if( hasMatch ){
				String prefixText = m.group(1);
				String leftSide = m.group(2) + "";
				String innerBlock = m.group(3) + "";
				String rightSide = m.group(4) + "";
     			if( prefixText != null ){
					TextNode tn = new TextNode(prefixText);
					System.out.println("bunch of text: " + prefixText);
				}
				if( leftSide.equals(PERCENT) && rightSide.equals(PERCENT) ){
					System.out.println("block command: " + innerBlock);
				}else if( leftSide.equals(OPENSTACHE) && rightSide.equals(CLOSESTACHE) ){
					System.out.println("echo command: " + innerBlock);
				}else{
					System.out.println("bunch of text::: " + str);
				}
				System.out.println("starting at: " +str.substring(m.start(4)+2));
				str = str.substring(m.start(4)+2);
			}
		}while(hasMatch);

	}

	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader(new File(args[0])));
		String line = null, src = "";
		while( (line = br.readLine()) != null ){
			src += line;
		}
		InterpretedTemplate it = new InterpretedTemplate(src);
	}

}
