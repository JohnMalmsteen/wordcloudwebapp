package ie.gmit.sw;

import java.util.Comparator;

public class WordFrequencyComparator implements Comparator<WordFrequencyKeyValue> {

	@Override
	public int compare(WordFrequencyKeyValue o1, WordFrequencyKeyValue o2) {
		return o2.getFrequency() - o1.getFrequency();
	}
	

}
