package ie.gmit.sw;

// this concrete strategy will take in the List of WordFrequencyKeyValues and use the total occurrences and word frequencies to weight the size of the font
// words with the same frequency will have the same font size, in smaller sample sets this will likely lead to many of the fonts having the same size

public class WeightedFontStrategy implements FontSizeSelectionStrategy {

	@Override
	public WordFrequencyKeyValue[] getFontSizes(WordFrequencyKeyValue[] arrayOfWordKeyValues) {
		
		int totalOccurrences = 0;
		
		for(WordFrequencyKeyValue keyValue : arrayOfWordKeyValues){
			if(keyValue != null){
				totalOccurrences += keyValue.getFrequency();
			}
		}
		
		for(WordFrequencyKeyValue keyValue : arrayOfWordKeyValues){
			if(keyValue != null){
				keyValue.setFontSize((int)(((float)keyValue.getFrequency()/(float)totalOccurrences)*1500.00)+15);
			}
		}
		
		return arrayOfWordKeyValues;
	}



}
