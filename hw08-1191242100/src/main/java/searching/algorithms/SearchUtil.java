/**
 * 
 */
package searching.algorithms;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author Luka
 *
 */
public class SearchUtil {

	public static <S> Node<S> bfs(Supplier<S> s0, Function<S, List<Transition<S>>> succ, Predicate<S> goal) {
		
		List<Node<S>> zaIstraziti = new LinkedList<>();
		zaIstraziti.add(new Node<S>(null, s0.get(), 0));
		
		while(!zaIstraziti.isEmpty()) {
			Node<S> trenutni = zaIstraziti.remove(0);
			if(goal.test(trenutni.getState())) {
				return trenutni;
			}
			List<Transition<S>> susjedi = succ.apply(trenutni.getState());
			for(Transition<S> susjed : susjedi) {
				zaIstraziti.add(zaIstraziti.size(), new Node<S>(trenutni, susjed.getState(), trenutni.getCost() + susjed.getCost()));
			}
		}
		return null;
	}
}	
