/**
 * 
 */
package coloring.algorithms;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import marcupic.opjj.statespace.coloring.Picture;

/**
 * @author Luka
 *
 */
public class Coloring implements Consumer<Pixel>, Function<Pixel, List<Pixel>>, Predicate<Pixel>, Supplier<Pixel> {

	/**
	 * Reference to one pixel that stores all relevant informations (coordinates) 
	 */
	private Pixel reference;
	/**
	 * Picture
	 */
	private Picture picture;
	/**
	 * Color which will be used to fill
	 */
	private int fillColor;
	/**
	 * Color of referent dot
	 */
	private int refColor;
	
	/**
	 * Constructor without referent color
	 * @param reference
	 * @param picture
	 * @param fillColor
	 */
	public Coloring(Pixel reference, Picture picture, int fillColor) {
		super();
		Objects.requireNonNull(reference, "Reference can not be null");
		Objects.requireNonNull(picture, "Picture can not be null");
		Objects.requireNonNull(fillColor, "Color can not be null");
		this.reference = reference;
		this.picture = picture;
		this.fillColor = fillColor;
		this.refColor = picture.getPixelColor(reference.x, reference.y);
	}

	@Override
	public Pixel get() {
		
		return reference;
	}

	@Override
	public boolean test(Pixel t) {
		
		return picture.getPixelColor(t.x, t.y) == refColor;
	}

	@Override
	public List<Pixel> apply(Pixel t) {
		
		List<Pixel> neighbours = new LinkedList<>();
		int xCoord = t.x;
		int yCoord = t.y;
		
		if(xCoord > 0) {
			neighbours.add(new Pixel(xCoord - 1, yCoord));
		}	
		if(yCoord > 0) {
			neighbours.add(new Pixel(xCoord, yCoord - 1));
		}
		if(xCoord < picture.getWidth() - 1) {
			neighbours.add(new Pixel(xCoord + 1, yCoord));
		}
		if(yCoord < picture.getHeight() - 1) {
			neighbours.add(new Pixel(xCoord, yCoord + 1));
		}
	
		return neighbours;
	}

	@Override
	public void accept(Pixel t) {
		
		picture.setPixelColor(t.x, t.y, fillColor);
	}
}
