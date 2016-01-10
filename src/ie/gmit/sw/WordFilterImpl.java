package ie.gmit.sw;

import java.io.*;
import java.util.*;

import javax.imageio.IIOException;

public class WordFilterImpl implements Filterable {
	private HashSet<String> stopwords = new HashSet<String>();
	private String sourcefile = "/home/john/stopwords.txt";
	
	public WordFilterImpl(){
		parseFile();
	}
	
	public WordFilterImpl(String filename){
		sourcefile = filename;
		parseFile();
	}
	
	private void parseFile(){
		
		String line;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(sourcefile)));
			while((line = br.readLine()) != null){
				line = line.trim();
				line.toLowerCase();
				line.replaceAll("[^a-z]", "");
				if(!stopwords.contains(line)){
					stopwords.add(line);
				}
			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			
			sourcefile = ("stopwords.txt".equals(sourcefile)) ? null : sourcefile;
			if(sourcefile != null)
				parseFile();
		}
		
	}
	
	public boolean checkWord(String word){
		
		// sick of all the short numbers that turn up and add no values
		// if its 4 then it's probably a year and may be interesting, as may things that are longer.
		if(word.length() < 4){
			try{
				Integer.parseInt(word);
				return true;
			}catch(Exception e)
			{
				return stopwords.contains(word);
			}
		}
		return stopwords.contains(word);
	}
	
	
}