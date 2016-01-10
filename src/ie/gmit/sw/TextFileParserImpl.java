package ie.gmit.sw;

import java.util.*;
import java.io.*;

public class TextFileParserImpl implements Parseable {
	private Map<String, Integer> wordFrequencyMap =  new HashMap<>();
	private Filterable filter;
	
	public TextFileParserImpl(){
		filter = new WordFilterImpl();
	}
	
	public TextFileParserImpl(Filterable meansOfFiltration){
		filter = meansOfFiltration;
	}
	
	public WordFrequencyKeyValue [] parse(String source){
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(source)));
			String line;
		
			while((line = br.readLine()) != null){
				String [] words = line.split("\\W|\\s+");
				for(String word : words){
					word = word.toLowerCase();
					if(!filter.checkWord(word) && word.length() > 1){
						// taking advantage of autoboxing
						int count = (wordFrequencyMap.containsKey(word)) ? wordFrequencyMap.get(word) : 0;
						wordFrequencyMap.put(word, ++count);
					}
				}
			}
			br.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
			return null;
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
		Parseable tester = new TextFileParserImpl();
		tester.parse("warandpeace.txt");
	}
}
