package ua.training.quotes.model;

public enum LangShourtcut{ ru("ru"), en("en");

	private final Lang lang; 
	
	LangShourtcut(String s){
		lang = Lang.fromValue(s);
	}
	
	public Lang value(){
		return lang;
	}
	
}
