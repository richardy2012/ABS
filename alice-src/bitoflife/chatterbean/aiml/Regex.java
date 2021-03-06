package bitoflife.chatterbean.aiml;

import hha.aiml.Jcseg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.xml.sax.Attributes;

public class Regex extends Pattern implements AIMLElement {
	
	public Regex() {
		// TODO Auto-generated constructor stub
	}

	public Regex(String pattern) {
		 String p = pattern.trim();
		 if(isToUseChineseTranslate) {    
			 p = Jcseg.chineseTranslate(p);
			 p.toUpperCase();
			 java.lang.System.out.println(p);
		 }
		 this.pattern = p.split(" ");
	    
		 hashCode = Arrays.hashCode(this.pattern);
	}
	
	private String[] pattern;

	private int hashCode;
	  
	@Override
	public void appendChild(AIMLElement child) {
		// TODO Auto-generated method stub
		String text = child.toString();
		   // System.out.println(text);
		    if(isToUseChineseTranslate) {    
				 text = Jcseg.chineseTranslate(text);
				 text = text.toUpperCase();
			//	 System.out.println(text);
			 }
		    if (pattern == null)
		      pattern = new String[] {text};
		    else
		    {
		      int length = pattern.length;
		      String[] larger = new String[length + 1];
		      java.lang.System.arraycopy(pattern, 0, larger, 0, length);
		      larger[length] = text;
		      pattern = larger;
		    }
	}
	
	private boolean isSharp(String name) {
//		System.out.println(name);
		return ("#".equals(name.substring(0, 1)));
	}
	
	private boolean isToUseChineseTranslate = true;
	List<String> patternList;
	  
	public void appendChild(String str)
	{
	    String text = str;
	   // System.out.println(text);
	    if (!isSharp(text))
	    {
	    	if(isToUseChineseTranslate) {    
				 text = Jcseg.chineseTranslate(text);
				 text = text.toUpperCase();
			//	 System.out.println(text);
			 }
	    }

	    if (patternList == null)
	    {
	      patternList = new ArrayList<String>();
	    }
	    String[] pp = text.split(" ");
	    for (int i = 0; i < pp.length; i++) {
	    	patternList.add(pp[i]);
	    }
	}

	@Override
	public void appendChildren(List<AIMLElement> children) {
		// TODO Auto-generaed method stub
		StringBuilder builder = new StringBuilder();
	    for (AIMLElement child : children)
	      builder.append(child);
	    
	    String text = builder.toString().trim();

	    String[] p = text.split(" ");
	    patternList = null;
	    for (String string : p) {
	    	appendChild(string);
		}
	    pattern = new String[patternList.size()];
	    patternList.toArray(pattern);

	    hashCode = Arrays.hashCode(pattern);
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj == null || !(obj instanceof Regex)) return false;
		Regex compared = (Regex) obj;
	    return Arrays.equals(pattern, compared.pattern);
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return hashCode;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder buffer = new StringBuilder();
	    for (int i = 0, n = pattern.length;;)
	    {
	      buffer.append(pattern[i]);
	      if (++i >= n) break;
	      buffer.append(" ");
	    }
	    
	    return buffer.toString();
	}

	@Override
	public String[] getElements() {
		// TODO Auto-generated method stub
		 return pattern;
	}

	@Override
	public void setElements(String[] pattern) {
		// TODO Auto-generated method stub
		this.pattern = pattern;
	    hashCode = Arrays.hashCode(pattern);
	}

	public Regex(Attributes attributes) {
	
		// TODO Auto-generated constructor stub
	}

}
