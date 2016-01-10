package ie.gmit.sw;

public class ParseableFactory {
	private static ParseableFactory factory = new ParseableFactory();
	
	private ParseableFactory(){
		
	}
	
	public static ParseableFactory getInstance(){
		return factory;
	}
	
	public Parseable getParseable(SourceType source){
		Parseable ret = null;
		switch(source){
		case WEB:
			ret = new UrlParserImpl();
			break;
		case FILE:
			ret = new TextFileParserImpl();
			break;
		}
		return ret;
	}
}

