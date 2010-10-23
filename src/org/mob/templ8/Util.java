package org.mob.templ8;
import java.io.*;
import java.util.*;

public class Util{

    public static int any(Object operand, Object... possible){//{{{
		if(possible == null || possible.length == 0 || operand == null){ 
			return -1;
		} else {
			for( int i = 0; i < possible.length; i++ ){
				if( operand.equals(possible[i]) ) return i;
			}
			return -1;
		}
	}//}}}

}
