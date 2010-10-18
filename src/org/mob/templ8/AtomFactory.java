package org.mob.templ8;
import java.io.*;
import java.util.*;
import java.util.regex.*;

public class AtomFactory{

	private static final String numericAtom = "\\d+";
	private static final Pattern stringAtom =  Pattern.compile("^\'(.*)\'$");
	private static final Pattern collectionAtom =  Pattern.compile("(\\w+)\\[(.+)\\]");

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
		}else{
			System.out.println("couldn't match atom");
			return null;
		}
	}


}
