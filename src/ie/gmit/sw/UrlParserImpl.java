package ie.gmit.sw;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.*;

public class UrlParserImpl implements Parseable {
	
	private Filterable filter;
	private Map<String, Integer> wordFrequencyMap =  new HashMap<>();
	
	public UrlParserImpl(){
		filter = new WordFilterImpl();
	}
	
	public UrlParserImpl(Filterable meansOfFiltration){
		filter = meansOfFiltration;
	}

	public WordFrequencyKeyValue[] parse(String source){
		Document doc = null;
		try {
			doc = Jsoup.connect(source)
					  .userAgent("Mozilla")
					  .timeout(10000)
					  .get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Invalid URL or No Internet Connection");
		}
		
		if(doc == null)
			return null;
		
		String [] words = doc.text().split("\\W|\\s+");
		for(String word : words){
			word = word.toLowerCase();
			if(!filter.checkWord(word) && word.length() > 1){
				// taking advantage of autoboxing
				int count = (wordFrequencyMap.containsKey(word)) ? wordFrequencyMap.get(word) : 0;
				wordFrequencyMap.put(word, ++count);
			}
		}
		
		return getFrequentWords();
	}
	
	private WordFrequencyKeyValue [] getFrequentWords(){
		WordFrequencyKeyValue [] arrayOfKeyValues = new WordFrequencyKeyValue [wordFrequencyMap.size()];
		int i = 0;
		for(String key : wordFrequencyMap.keySet()){
			arrayOfKeyValues[i++] = new WordFrequencyKeyValue(key, wordFrequencyMap.get(key));
		}
		Arrays.sort(arrayOfKeyValues, new WordFrequencyComparator());
		
		WordFrequencyKeyValue [] top50 = new WordFrequencyKeyValue[150];
		top50 = Arrays.copyOf(arrayOfKeyValues, 150);
		
		return top50;
	}
	
	public static void main(String[] args) throws IOException {
		Parseable urlParser = new UrlParserImpl();
		WordFrequencyKeyValue [] array = urlParser.parse("https://www.donaldjtrump.com/");
		
		for(WordFrequencyKeyValue keypair : array){
			System.out.println(keypair);
		}
	}

}
