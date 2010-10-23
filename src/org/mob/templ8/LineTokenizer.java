
package org.mob.templ8;
import java.util.regex.*;
import java.io.*;
import com.google.common.collect.*;
import java.util.*;

public class LineTokenizer{

	private static final Pattern TMPL8_PATTERN = Pattern.compile("(.*?)?\\{(\\{|%)(.+?)(\\}|%).*", Pattern.DOTALL);
	private static final String PERCENT = "%";
	private static final String OPENSTACHE = "{";
	private static final String CLOSESTACHE = "}";

	private final String[] sourceLines;

	public LineTokenizer(String[] sourceLines){
		this.sourceLines = sourceLines;
	}

	private Iterable<Iterable<Node>> tokenizeLines(){//{{{
		return new Iterable<Iterable<Node>>(){
			public Iterator<Iterable<Node>> iterator(){
				return new AbstractIterator<Iterable<Node>>(){
					private int lineNumber = 0;
					public Iterable<Node> computeNext(){
						if(lineNumber >= sourceLines.length){
							return endOfData();
						}else{
							Tokenizer t = new Tokenizer(sourceLines[lineNumber] + "\n", lineNumber+1);
							lineNumber++;
							return t.tokens();
						}
					}
				};
			}
		};
	}//}}}

	public Iterable<Node> tokens(){
		return Iterables.concat(tokenizeLines());
	}

	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader(new File(args[0])));
		String line = null, src = "";
		while( (line = br.readLine()) != null ){
			src += line + "\n";
		}

		String[] srcLines = src.split("\n");
		LineTokenizer it = new LineTokenizer(srcLines);
		for( Node node : it.tokens() ){
			System.out.println(node);
		}
	}

}
