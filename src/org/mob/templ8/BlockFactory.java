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

	private static final Pattern FOR_PATTERN = Pattern.compile("\\s*for\\s+(\\w+)\\s+in\\s+(\\w+)\\s*");
	private static final Pattern FOR_KV_PATTERN = Pattern.compile("\\s*for\\s+(\\w+)\\s*,\\s*(\\w+)\\s+in\\s+(\\w+)\\s*");
	private static final Pattern IF_PATTERN = Pattern.compile("\\s*(if)\\s*(.*)\\s*");
 //Pattern.compile("\\s*for\\s+(\\w+)in\\s+(.+)\\s*");

    public static BlockNode create(String blockText){//{{{
		String elements[] = blockText.trim().split("\\s+");
		String blockType = elements[0].toLowerCase();
		if( blockType.equals(BLOCK_IF) ){
			return new IfBlock(blockText.trim().substring(2).trim());
		}else if( blockType.equals(BLOCK_ELSE) ){
			return new ElseBlock();
		}else if( blockType.equals(BLOCK_ELSEIF) ){
			return new ElseBlock(blockText.trim().substring(6).trim());
		}else if( blockType.equals(BLOCK_ENDIF) ){
			return new EndIfBlock();
		} else if( blockType.equals(BLOCK_FOR) ){
			Matcher m = FOR_PATTERN.matcher(blockText);
			if( m.matches() ){
				System.out.println("UEAR");
				return new ForBlock(m.group(1), m.group(2) );
			}else{
				Matcher m2 = FOR_KV_PATTERN.matcher(blockText);
				if( m2.matches() ){
					return new ForKeyValBlock(m2.group(1), m2.group(2), m2.group(3) );
				}else{
					System.out.println("badly formed for block");
					return null;
				}
			}
		}else if( blockType.equals(BLOCK_ENDFOR) ){
			return new EndForBlock();
		}
		return null; //TODO
	}//}}}

}
