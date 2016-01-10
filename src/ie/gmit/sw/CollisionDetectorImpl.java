package ie.gmit.sw;

import java.awt.Rectangle;
import java.util.List;

public class CollisionDetectorImpl implements Detectable {
	
	public boolean detectCollision(Rectangle proposed, List<Rectangle> listOfPlacedWords){
		for(Rectangle existing : listOfPlacedWords){
			if(proposed.intersects(existing) || proposed.contains(existing) || existing.contains(proposed))
				return true;
		}
		return false;
	}

}
