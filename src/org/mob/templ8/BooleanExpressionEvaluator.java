package org.mob.templ8;
import java.io.*;
import java.util.*;

public class BooleanExpressionEvaluator extends ExpressionEvaluator{
	private final String leftSide;
	private final String op;
	private final String rightSide;
	private final String EQ  = "==";
	private final String LT  = "<";
	private final String LTE = "<=";
	private final String GT  = ">";
	private final String GTE = ">=";
	private final String NEQ = "!=";
	private final String CONTAINS = "in";


	public BooleanExpressionEvaluator(String tokens[]){
		if( tokens.length == 1 ){
			leftSide = tokens[0];
			op = null;
			rightSide = null;
		}else if( tokens.length == 2 ){
			leftSide = tokens[0];
			op = tokens[1];
			rightSide = null;
		}else if( tokens.length == 3 ){
			leftSide = tokens[0];
			op = tokens[1];
			rightSide = tokens[2];
		}else{
			leftSide = null;
			op = null;
			rightSide = null;
		}

	}

	public boolean evaluate( ExecutionContext ec ){

		// no operand - 
		if( rightSide == null && op == null && leftSide != null ){
			Object o = ec.getObject( leftSide );
			if( o == null ) return false;
			if( o instanceof String ){
				return ((String)o).length() > 0;
			}else if( o instanceof Integer ){
				return !((Integer)o).equals(0);
			}else{
				return true;
			}
		}else if( rightSide != null && op != null && leftSide != null ){
			Object o = ec.getObject( leftSide );
			if( o == null ) return false;
			if( o instanceof String ){
				return ((String)o).length() > 0;
			}else if( o instanceof Integer ){
				return !((Integer)o).equals(0);
			}else{
				return true;
			}
		} else {
			Atom rightAtom = AtomFactory.getAtom(rightSide);
			Atom leftAtom = AtomFactory.getAtom(leftSide);
			return true;
		}
	}

}
