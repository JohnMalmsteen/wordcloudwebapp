package ie.gmit.sw;

public class LinearFontSizeStrategy implements FontSizeSelectionStrategy{

	@Override
	public WordFrequencyKeyValue[] getFontSizes(WordFrequencyKeyValue[] arrayOfWordKeyValues) {
		int i = 100;
		
		for(WordFrequencyKeyValue keyValue : arrayOfWordKeyValues){
			if(keyValue != null)
				keyValue.setFontSize(Math.max(i, 5));
			
			i -= 2;
		}
		
		return arrayOfWordKeyValues;
	}

}
