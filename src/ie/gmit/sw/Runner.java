package ie.gmit.sw;

public class Runner {

	public static void main(String args[]) throws Exception{		
		CloudRenderer warandpeace = new CloudRenderer("warandpeace.txt", SourceType.FILE, DrawingPattern.GAUSSIAN, "tolstoy");
		warandpeace.draw();
		
		CloudRenderer trump = new CloudRenderer("http://www.gutenberg.org/files/10/10-h/10-h.htm", SourceType.WEB, DrawingPattern.LOGARITHMIC_SPIRAL, "bible");
		trump.draw();
		
		CloudRenderer gmit = new CloudRenderer("http://www.gmit.ie/", SourceType.WEB, DrawingPattern.GAUSSIAN, "gmit");
		gmit.draw();
	}
}
