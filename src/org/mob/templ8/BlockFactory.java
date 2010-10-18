package org.mob.templ8;
import java.util.regex.*;
import java.io.*;

public class BlockFactory{

	private static final String BLOCK_IF     = "if";
	private static final String BLOCK_ELSEIF = "elseif";
	private static final String BLOCK_ELSE   = "else";
	private static final String BLOCK_ENDIF  = "endif";        
	private static final String BLOCK_FOR    = "for";        
	private static final String BLOCK_ENDFOR = "endfor";        

	private static final Pattern FOR_PATTERN = Pattern.compile("\\s*for\\s+(\\w+)in\\s+(.+)\\s*");

    public static BlockNode create(String blockText){
		String elements[] = blockText.trim().split("\\s+");
		String blockType = elements[0].toLowerCase();
		if( blockType.equals(BLOCK_IF) ){
			return new IfBlock(blockText);
		}else if( blockType.equals(BLOCK_ELSE) ){
			return new ElseBlock();
		}else if( blockType.equals(BLOCK_ELSEIF) ){
			return new ElseBlock();
		}else if( blockType.equals(BLOCK_ENDIF) ){
			return new EndIfBlock();
		} else if( blockType.equals(BLOCK_FOR) ){
			Matcher m = FOR_PATTERN.matcher(blockText);
			if( !m.matches() ){
				return null; // throw compile exception, badly formed for block
			}else{
				return new ForBlock(m.group(1), m.group(2) );
			}
		}else if( blockType.equals(BLOCK_ENDFOR) ){
			return new EndForBlock();
		}
		return null; //TODO
	}

}
