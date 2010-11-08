package org.mob.templ8;
import java.io.*;
import java.util.*;
import java.util.regex.*;

public class AtomFactory{

	// atom = NUM_LITERAL | STRING LITERAL | VARNAME | VARNAME[ATOM] 

	//  2
	//  'asdc'
	//  foo
	//  bar[_]
	//  foo.x.y
	//  foo.
	//

	private static final String numericAtom = "\\d+";
	private static final Pattern stringAtom =  Pattern.compile("^\'(.*)\'$");
	private static final Pattern collectionAtom =  Pattern.compile("(\\w+)\\[(.+)\\]");
	private static final String varAtom = "\\w+";
	private static final String methodsAtom = "(\\w+\\.?(\\w+)?)+"; // matches aaa.bbb.ccc etc

	public static Atom getAtom(String atomText){
		Matcher m = stringAtom.matcher(atomText);
		Matcher collectionM = collectionAtom.matcher(atomText);
		if( atomText.matches(numericAtom) ){
			return new LiteralIntAtom( new Integer(atomText) );
		}else if( m.matches() ){
			return new LiteralStringAtom(m.group(1));
		}else if( collectionM.matches() ){
			String keyString = collectionM.group(2);
			Atom keyAtom = getAtom(keyString);
			return new CollectionAtom(collectionM.group(1), keyAtom);
		}else if(atomText.matches(varAtom)){
			return new VarAtom(atomText);
		}else{
			return null;
		}
	}

	//
	// while char:
	// if char = ':
	//    walk until ', capture contents, return stringliteral
	// if char = \w:
	//     walk until [ or ., capture contents, return property but:
	//	      if next part is [] make it a map/collection lookup, get contents of [] for key
	//	      if next part is . make it property lookup
	

}
