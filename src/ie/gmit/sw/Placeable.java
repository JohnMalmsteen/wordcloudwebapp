package ie.gmit.sw;

import java.awt.image.BufferedImage;

public interface Placeable {

	void placeString(WordFrequencyKeyValue word);
	BufferedImage complete(String outputName);

}