package org.mob.templ8;
import java.io.*;
import java.util.*;
import static org.mob.templ8.Util.any;

public class BooleanExpressionEvaluator extends ExpressionEvaluator{
	private final String leftSide;
	private final String op;
	private final String rightSide;
	private final String CONTAINS = "in";
	private static final String[] inequalityOperators = new String[]{"<", "<=", ">", ">="};

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

	public boolean evaluate( ExecutionContext ec ) throws Exception{
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
			//TODO needs WORK!!!!!
			Atom rightAtom = AtomFactory.getAtom(rightSide);
			Atom leftAtom = AtomFactory.getAtom(leftSide);
			int inequalityOperatorTest = any(op, (Object[])inequalityOperators);
			if( inequalityOperatorTest >= 0){
				Number leftNum, rightNum;
				try{
					leftNum = (Number)(leftAtom.getValue(ec));
					rightNum = (Number)(rightAtom.getValue(ec));
				}catch(ClassCastException cce ){
					throw new Exception("Variable in inequality is not numeric");
				}
				int equalityCheck = EqualityTests.compare(leftNum, rightNum);
				switch(inequalityOperatorTest){
					// Left Side < Right Side : -1, // Left Side > RightSide : 1 // Left == Right : 0
					case 0: //LT
						return equalityCheck < 0;
					case 1: // LTE
						return equalityCheck <= 0;
					case 2: // GT
						return equalityCheck > 0;
					case 3: // GTE
						return equalityCheck >= 0;
				}
				return false;
			}else if( op.equals("==") ){
				Object leftVal = leftAtom.getValue(ec);
				Object rightVal = rightAtom.getValue(ec);
				if( leftVal == null && rightVal == null ){
					return true; //both are null
				}
				if( leftVal == null || rightVal == null ){
					return false; //one is null, the other is not.
				}
				return leftVal.equals(rightVal); //both are non-null.
			}else if( op.equals("!=") ){
				Object leftVal = leftAtom.getValue(ec);
				Object rightVal = rightAtom.getValue(ec);
				if( leftVal == null && rightVal == null ) return false; //both are null
				if( leftVal == null || rightVal == null ) return true; //one is null, the other is not.
				return !leftVal.equals(rightVal); //both are non-null.
 			}else if ( op.equals("in") ){
				Object leftVal = leftAtom.getValue(ec);
				Object rightVal = rightAtom.getValue(ec);
				if( leftVal == null || rightVal == null ) return false;
				if( rightVal instanceof Collection ){
					return ((Collection)rightVal).contains(leftVal);
				}else if( rightVal instanceof Map ){
					return ((Map)rightVal).containsKey(leftVal);
				}else{
					throw new Exception("value on right side of 'in' operator must be a collection or a map.");
				}
			}else{
				throw new Exception("unknown operator");
			}
		}else{
			System.out.println("bad tokens");
			return false;
		}

	}

}
