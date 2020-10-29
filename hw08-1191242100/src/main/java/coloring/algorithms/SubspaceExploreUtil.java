/**
 * 
 */
package coloring.algorithms;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author Luka
 *
 */
public class SubspaceExploreUtil {

	@SuppressWarnings("hiding")
	public static <Pixel> void bfs(Supplier<Pixel> s0, Consumer<Pixel> process, Function<Pixel, List<Pixel>> succ, Predicate<Pixel> acceptable) {
		
		List<Pixel> toCheck = new LinkedList<>();
		toCheck.add(s0.get());
		
		while(!toCheck.isEmpty()) {
			Pixel current = toCheck.remove(0);
			if(!acceptable.test(current)) {
				continue;
			}
			process.accept(current);
			toCheck.addAll(toCheck.size(), succ.apply(current));
		}
	}
	
	@SuppressWarnings("hiding")
	public static <Pixel> void dfs(Supplier<Pixel> s0, Consumer<Pixel> process, Function<Pixel, List<Pixel>> succ, Predicate<Pixel> acceptable) {
		
		List<Pixel> toCheck = new LinkedList<>();
		toCheck.add(s0.get());
		
		while(!toCheck.isEmpty()) {
			Pixel current = toCheck.remove(0);
			if(!acceptable.test(current)) {
				continue;
			}
			process.accept(current);
			toCheck.addAll(0, succ.apply(current));
		}
	}
	
	@SuppressWarnings("hiding")
	public static <Pixel> void bfsv(Supplier<Pixel> s0, Consumer<Pixel> process, Function<Pixel, List<Pixel>> succ, Predicate<Pixel> acceptable) {
		
		List<Pixel> toCheck = new LinkedList<>();
		toCheck.add(s0.get());
		Set<Pixel> alreadyChecked = new HashSet<>();
		alreadyChecked.add(s0.get());
		
		while(!toCheck.isEmpty()) {
			Pixel current = toCheck.remove(0);
			if(!acceptable.test(current)) {
				continue;
			}
			process.accept(current);
			List<Pixel> kids = succ.apply(current);
			kids.removeIf(pixel -> alreadyChecked.contains(pixel));
			toCheck.addAll(toCheck.size(), kids);
			alreadyChecked.addAll(kids);
		}
	}

}
