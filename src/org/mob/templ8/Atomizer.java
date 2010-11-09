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
				DynamicAtom dynamicAtom;
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

	public Atom getDirective(String str, int fromIndex){
		if( str.charAt(fromIndex) == '\'' ){
			String atomText = walkUntil(str.substring(fromIndex+1), "'");
			return new LiteralStringAtom(atomText);
		}else{
			if( str.trim().matches(numericAtom) ){
				return new LiteralIntAtom(new Integer(str.trim()) );
			}else{
				int firstDot = str.indexOf(".", fromIndex);
				int firstBkt = str.indexOf("[", fromIndex);
				if( firstDot < 0 && firstBkt < 0 ){
					return new VarAtom(str.trim());
				}
				DynamicAtom dynAtom;
				String varName;
				int startPos;
				if( firstDot >= 0 && (firstBkt < 0 || firstBkt > firstDot ) ){
					varName = str.substring(fromIndex, firstDot);
					startPos = firstDot;
				}else if( firstBkt >= 0 && (firstDot < 0 || firstDot > firstBkt )){
					varName = str.substring(fromIndex, firstBkt);
					startPos = firstBkt;
				}else{
					startPos = 0;
					return new VarAtom(str.trim());
				}
				dynAtom = new DynamicAtom(varName);

				while(startPos <= str.length()-1 && startPos >=0){
					if( str.charAt(startPos) == '[' ){
						int matchingPos = matchBracket(str, startPos + 1 );
						String insideOfBracket = str.substring(startPos+1, matchingPos);
						startPos = matchingPos +1;
						dynAtom.addLookup( new IndexLookup(getDirective(insideOfBracket, 0)));
						continue;
					}else if(str.charAt(startPos) == '.' ){
						int nextStart = firstIndexOfChars(str, startPos+1, '.','[');
						String methodCall;
						if( nextStart == -1 ){
							methodCall = str.substring(startPos+1);
							startPos = str.length();
						}else{
							methodCall = str.substring(startPos+1, nextStart);
						}
						startPos = nextStart;

						dynAtom.addLookup( new PropertyLookup(methodCall));
					}else{
						System.out.println("nothing");
						break;
					}
				}
				return dynAtom;
			}
		}
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

	public int firstIndexOfChars(String inputString, int fromIndex, Character... chars){//{{{
		int minIndex = -1;
		for( Character c : chars ){
			int test = inputString.indexOf(c, fromIndex);
			if( test >=0 ){
				if( minIndex < 0 || test < minIndex ){
					minIndex = test;
				}
			}
		}
		return minIndex;
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
