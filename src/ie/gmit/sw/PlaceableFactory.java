package ie.gmit.sw;

public class PlaceableFactory {
	private static PlaceableFactory factory = new PlaceableFactory();
	
	private PlaceableFactory(){
		
	}
	
	public static PlaceableFactory getInstance(){
		return factory;
	}
	
	public static Placeable getPlaceable(DrawingPattern pattern){
		Placeable ret = null;
		switch(pattern){
		case GAUSSIAN:
			ret = new GaussianRotatingPlacer();
			break;
		case LOGARITHMIC_SPIRAL:
			ret = new LogarithmicSpiralPlacerImpl();
			break;
		}
		
		return ret;
	}
}
