package org.mob.templ8;
import java.util.regex.*;
import java.io.*;
import com.google.common.collect.*;
import java.util.*;

public class Tokenizer{

	private static final Pattern TMPL8_PATTERN = Pattern.compile("(.*?)?\\{(\\{|%)(.+?)(\\}|%).*", Pattern.DOTALL);
	//private static final Pattern TMPL8_PATTERN = Pattern.compile("(.*?)?\\{(\\{|%)(.+?)(\\}|%).*", Pattern.DOTALL);
	private static final String PERCENT = "%";
	private static final String OPENSTACHE = "{";
	private static final String CLOSESTACHE = "}";

	private final String source;
	private final int lineNumber;

	public Tokenizer(String source){
		this.source = source;
		this.lineNumber = -1;
	}

	public Tokenizer(String source, int lineNumber){
		this.source = source;
		this.lineNumber = lineNumber;
	}

  	public Iterable<Node> tokens(){//{{{
		return new Iterable<Node>(){
			public Iterator<Node> iterator(){
				return new AbstractIterator<Node>(){
					private String src = source;
					boolean hasMatch;

					public Node computeNext(){
						Matcher m = TMPL8_PATTERN.matcher(src);
						hasMatch = m.matches();
						if( !hasMatch ){
							if( src != null && src.length() > 0 ){
								Node n = new TextNode(src);
								src = "";
								n.setLineNumber(lineNumber);
								return n;
							}else{
								return endOfData();
							}
						}
						String prefixText = m.group(1);
						String leftSide = m.group(2) + "";
						String innerBlock = (m.group(3) + "").trim();
						String rightSide = m.group(4) + "";
						if( prefixText != null && prefixText.length() > 0 ){
							src = src.substring(m.start(2)-1);
							Node n = new TextNode(m.group(1));
							n.setLineNumber(lineNumber);
							return n;
						}
						if( leftSide.equals(OPENSTACHE) && rightSide.equals(CLOSESTACHE) ){
							src = src.substring(m.start(4)+2);
							Node n = new EchoNode(innerBlock); 
							n.setLineNumber(lineNumber);
							return n;
						}else if( leftSide.equals(PERCENT) && rightSide.equals(PERCENT) ){
							src = src.substring(m.start(4)+2);
							Node n = BlockFactory.create(innerBlock);
							if( n != null ) n.setLineNumber(lineNumber);
							return n != null ? n : endOfData();
						}else{
							return endOfData();
						}
					}
				};
			}
		};
	}//}}}

	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader(new File(args[0])));
		String line = null, src = "";
		while( (line = br.readLine()) != null ){
			src += line + "\n";
		}
		Tokenizer it = new Tokenizer(src);
		for( Node node : it.tokens() ){
			System.out.println(node);
		}
	}

}
