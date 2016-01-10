package ie.gmit.sw;

import java.awt.Rectangle;
import java.util.List;

public interface Detectable {
	boolean detectCollision(Rectangle proposed, List<Rectangle> listOfPlacedWords);
}