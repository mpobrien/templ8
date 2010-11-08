package org.mob.templ8;
import java.io.*;
import java.util.*;
import java.util.regex.*;

public class Atomizer{

    //VAR_INDEXED
	//VAR
	//STRING_LITERAL
	//INT_LITERAL
// 	public Atom parseatom(String source){
// 		source = source.trim();
// 		if( source.startsWith("\'") ){
// 		}
// 	}
//


	//
	//
	//  foo[bar.baz].x.z[g[f]]
	//  var + get index ( var + getproperty )
	//


	private static final String numericAtom = "\\d+";
	private static final String varAtom = "\\w+";
	
	public void atomize(String s, int fromIndex){
		if( s.charAt(fromIndex) == '\'' ){
			String atomText = walkUntil(s.substring(fromIndex+1), "'");
			System.out.println(new LiteralStringAtom(atomText));
			//return new LiteralStringAtom(atom);
		}else{
			String nextAtom = walkUntilChars(s, "[", ".");
			if( nextAtom.matches(numericAtom) ){
				System.out.println(new LiteralIntAtom(new Integer(nextAtom) ));
			}else if( nextAtom.matches(varAtom) ){
				VarAtom va = new VarAtom(nextAtom);
				String rest = s.substring(nextAtom.length());
				if( rest.startsWith("[") ){
					String inner = walkUntil(rest.substring(1), "]");
					System.out.println("inner: " + inner);
				}
				System.out.println(rest);
			}
		}
	}

	public String walkUntil(String inputString, String terminator){
		int stopPoint = inputString.indexOf(terminator);
		if( stopPoint >= 0){
			return inputString.substring(0, stopPoint);
		}else{
			return null;
		}

	}

	public String walkUntilChars(String inputString, String... terminators){
		int min = -1;
		for( String t : terminators ){
			int testMin = inputString.indexOf(t);
			if( testMin < 0 ) continue;
			if( min < 0 || (min >=0 && testMin < min )){
				min = testMin;
			}
		}

		if( min >= 0){
			return inputString.substring(0, min);
		}else{
			return inputString;
		}

	}



}
