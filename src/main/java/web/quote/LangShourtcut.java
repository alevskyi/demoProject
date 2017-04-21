package web.quote;

import quoteutils.Lang;

public enum LangShourtcut{ ru("ru"), en("en");

	private final Lang lang; 
	
	LangShourtcut(String s){
		lang = Lang.fromValue(s);
	}
	
	public Lang value(){
		return lang;
	}
	
}
