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

	//public String getDirective(){
		//int startIndex = 0;
		//int endIndex = 0;
		//s = "abcde.fghijkl.dd"
		//firstDot = s.indexOf(".", startIndex);
		//firstBkt = s.indexOf("[", startIndex);
		////TODO index at 0?
		//if( firstDot >= 0 && (firstBkt < 0 || firstBkt > firstDot ) ){
			//String methodName = s.substring(startIndex, firstDot;// got a . lookup
		//}else if( firstBkt >= 0 && (firstDot < 0 || firstDot > firstBkt )){
			//String insideOfBracket = 
		//}
		//if( firstDot
		//start capturing:
			//walk until hit a . or a [.
			//if it's a [:
				//find the matching ], capture the inside of the []s and blah blah etc. set pointer to ] + 1 char index.
			//if it's a .
				//add method to list, set pointer to . + 1 char index
	//}

	public String getDirective(String str, int fromIndex){
		if( str.charAt(fromIndex) == '\'' ){
			String atomText = walkUntil(str.substring(fromIndex+1), "'");
			return "literal : " + atomText;
			//OSystem.out.println(new LiteralStringAtom(atomText));
		}else{
			int firstDot = str.indexOf(".", fromIndex);
			int firstBkt = str.indexOf("[", fromIndex);
			String varName;
			if( firstDot >= 0 && (firstBkt < 0 || firstBkt > firstDot ) ){
				varName = str.substring(fromIndex, firstDot);
				String methodName = str.substring(fromIndex, firstDot);// got a . lookup
				System.out.println("method " + methodName + " on var: " + varName);
				return "";
			}else if( firstBkt >= 0 && (firstDot < 0 || firstDot > firstBkt )){
				int matchingPos = matchBracket(str, firstBkt + 1 );
				String insideOfBracket = str.substring(firstBkt+1, matchingPos);
				varName = str.substring(fromIndex, firstBkt);
				System.out.println("key loop " + insideOfBracket + " on var: " + varName);
			}
		}
		return "sfkasjbf";

		
	}

	{
		if( str.charAt(pos) == '[' ){
			//walk to ], appe
		}else if(str.charAt(pos) == '.' ){
			//walk to ., capture, append method call with str name
		}else
	}

	public static int matchBracket(String s, int fromPos){//{{{
		int pos = fromPos;
		int openBr = 1;
		//TODO handle escaped characters
		while( pos < s.length() ){
			char c = s.charAt(pos);
			switch(s.charAt(pos)){
				case '[':
					openBr++;
					break;
				case ']':
					openBr--;
					break;
				default:
			}
			if( openBr == 0 ) return pos;
			pos++;
		}
		return -1;
	}//}}}

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
