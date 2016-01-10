package ie.gmit.sw;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;

import org.w3c.dom.ls.LSInput;

public class LogarithmicSpiralPlacerImpl implements Placeable {
	
	private final double GROWTH_RATE = .1;
	
	private int horizontalCentre = 0;
	private int verticalCentre = 0;
	
	private int turn = 29;
	
	private List<Rectangle> listOfPlacedWords = new ArrayList<Rectangle>();

	private BufferedImage image = null;
	private Graphics context = null;
	private Color textColor = Color.WHITE;
	private int grayscale = 254;
	
	public Detectable detector = new CollisionDetectorImpl();
	
	public LogarithmicSpiralPlacerImpl(){
		image = new BufferedImage(1600, 1000, BufferedImage.TYPE_4BYTE_ABGR);
		context = image.getGraphics();
		context.setColor(Color.BLACK);
		context.fillRect(0, 0, 1600, 1000);
		horizontalCentre = 700;
		verticalCentre = 500;
	}
	
	public void placeString(WordFrequencyKeyValue word){
		int h = horizontalCentre;
        int v = verticalCentre;
		int k = 1;
		Font font = new Font(Font.SANS_SERIF, Font.PLAIN, word.getFontSize());
		textColor = new Color(grayscale, grayscale, grayscale--);
		context.setColor(textColor);
		Rectangle2D rect = context.getFontMetrics(font).getStringBounds(word.getWord(), context);
		Rectangle simpleRect = new Rectangle(h, v-(int)(rect.getHeight()*.8), (int)(rect.getWidth()), (int)(rect.getHeight()));
		
		while (detector.detectCollision(simpleRect, listOfPlacedWords))
        { 
            int theta= k*turn %360;
            double L= k*GROWTH_RATE;

            int h_next= (int) Math.round(h+L*Math.cos(theta*Math.PI/180));
            int v_next= (int) Math.round(v+L*Math.sin(theta*Math.PI/180));

	        h= h_next; v= v_next; 
	   
	        simpleRect = new Rectangle(h, v-(int)(rect.getHeight()*.8), (int)(rect.getWidth()), (int)(rect.getHeight()));
	        k= k+1;
        }
	    
		context.setFont(font);
		context.drawString(word.getWord(), h, v);
		//context.drawRect(h, v-(int)(rect.getHeight()*.8), (int)rect.getWidth(), (int)rect.getHeight());
		listOfPlacedWords.add(simpleRect);
	}
	
	public BufferedImage complete(String outputName){
		context.dispose();
		try {
			ImageIO.write(image, "png", new File(outputName + ".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return image;
	}

}
