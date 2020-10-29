/**
 * 
 */
package coloring.algorithms;

import java.util.Objects;

/**
 * @author Luka
 *
 */
public class Pixel {
	
	/**
	 * X coordinate
	 */
	public int x;
	
	/**
	 * Y coordinate
	 */
	public int y;

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	public Pixel(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Pixel))
			return false;
		Pixel other = (Pixel) obj;
		return x == other.x && y == other.y;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}
	
}
