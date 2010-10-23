package org.mob.templ8;
import java.io.*;
import java.util.*;
import java.math.*;

public class EqualityTests{

	private final String EQ  = "==";
	private final String LT  = "<";
	private final String LTE = "<=";
	private final String GT  = ">";
	private final String GTE = ">=";
	private final String NEQ = "!=";

	public static int compare(Number o1, Number o2) {
		if(o1 instanceof Short && o2 instanceof Short) {
			return ((Short) o1).compareTo((Short) o2);
		} else if(o1 instanceof Long && o2 instanceof Long) {
			return ((Long) o1).compareTo((Long) o2);
		} else if(o1 instanceof Integer && o2 instanceof Integer) {
			return ((Integer) o1).compareTo((Integer) o2);
		} else if(o1 instanceof Float && o2 instanceof Float) {
			return ((Float) o1).compareTo((Float) o2);
		} else if(o1 instanceof Double && o2 instanceof Double) {
			return ((Double) o1).compareTo((Double) o2);
		} else if(o1 instanceof Byte && o2 instanceof Byte) {
			return ((Byte) o1).compareTo((Byte) o2);
		} else if(o1 instanceof BigInteger && o2 instanceof BigInteger) {
			return ((BigInteger) o1).compareTo((BigInteger) o2);
		} else if(o1 instanceof BigDecimal && o2 instanceof BigDecimal) {
			return ((BigDecimal) o1).compareTo((BigDecimal) o2);
		} else {
			throw new RuntimeException("Ooopps!");
		}

	} 

}

