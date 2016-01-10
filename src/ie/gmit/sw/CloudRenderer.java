package ie.gmit.sw;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import java.util.ArrayList;
public class CloudRenderer {
	private ParseableFactory parserFactory = ParseableFactory.getInstance();
	private Parseable parser;
	private FontSizeSelectionStrategy strategy = null;
	private WordFrequencyKeyValue[] weightedArray = null;
	private String outputName;
	private PlaceableFactory patternFactory = PlaceableFactory.getInstance();
	private Placeable placer;
	
	public CloudRenderer(String source, SourceType sourceType, DrawingPattern pattern, String outputName){
		parser = parserFactory.getParseable(sourceType);
		
		WordFrequencyKeyValue[] unweightedArray = parser.parse(source);
		
		// here i am demonstrating the context element of the strategy pattern employed for font weighting, if there are less than 50 disrete words in the list then we will use
		// the linear font strategy, otherwise we will use the weighted strategy
		// this is done at run time.
		
		if(unweightedArray[unweightedArray.length-1] == null){
			strategy = new LinearFontSizeStrategy();
		}
		else{
			strategy = new WeightedFontStrategy();
		}
		
		weightedArray = strategy.getFontSizes(unweightedArray);
		
		placer = patternFactory.getPlaceable(pattern);
		this.outputName = outputName;
	}
	
	public void parse(String source){
		WordFrequencyKeyValue[] unweightedArray = parser.parse(source);
		weightedArray = strategy.getFontSizes(unweightedArray);
	}
	
	public void draw(){
		for(WordFrequencyKeyValue keyValue : weightedArray){
			if(keyValue!= null){
				placer.placeString(keyValue);
			}
			else{
				System.out.println("null");
			}
		}
		
		placer.complete(outputName);
	}

	public Parseable getParser() {
		return parser;
	}

	public void setParser(Parseable parser) {
		this.parser = parser;
	}

	public FontSizeSelectionStrategy getStrategy() {
		return strategy;
	}

	public void setStrategy(FontSizeSelectionStrategy strategy) {
		this.strategy = strategy;
	}

	public String getOutputName() {
		return outputName;
	}

	public void setOutputName(String outputName) {
		this.outputName = outputName;
	}

	public Placeable getPlacer() {
		return placer;
	}

	public void setPlacer(Placeable placer) {
		this.placer = placer;
	}

}
