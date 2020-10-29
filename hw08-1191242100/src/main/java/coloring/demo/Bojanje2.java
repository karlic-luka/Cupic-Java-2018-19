/**
 * 
 */
package coloring.demo;

import java.util.Arrays;

import coloring.algorithms.Coloring;
import coloring.algorithms.Pixel;
import coloring.algorithms.SubspaceExploreUtil;
import marcupic.opjj.statespace.coloring.FillAlgorithm;
import marcupic.opjj.statespace.coloring.FillApp;
import marcupic.opjj.statespace.coloring.Picture;

/**
 * @author Luka
 *
 */
public class Bojanje2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
//		FillApp.run(FillApp.OWL, Arrays.asList(bfs, dfs, bfsv));
		FillApp.run("C:\\Users\\Luka\\Desktop\\8dz-slike\\spuzvabob.gif", Arrays.asList(bfs, dfs, bfsv));
		}
	
		private static final FillAlgorithm bfs = new FillAlgorithm() {
			
			@Override
			public String getAlgorithmTitle() {
				return "Moj bfs!";
			}
			
			@Override
			public void fill(int x, int y, int color, Picture picture) {
				Coloring col = new Coloring(new Pixel(x,y), picture, color);
				SubspaceExploreUtil.bfs(col, col, col, col);
			}
		};
		
		private static final FillAlgorithm dfs = new FillAlgorithm() {
			
			@Override
			public String getAlgorithmTitle() {
				return "Moj dfs!";
			}
			
			@Override
			public void fill(int x, int y, int color, Picture picture) {
				Coloring col = new Coloring(new Pixel(x,y), picture, color);
				SubspaceExploreUtil.dfs(col, col, col, col);
			}
		};
		
		private static final FillAlgorithm bfsv = new FillAlgorithm() {
			
			@Override
			public String getAlgorithmTitle() {
				return "Moj bfsv!";
			}
			
			@Override
			public void fill(int x, int y, int color, Picture picture) {
				Coloring col = new Coloring(new Pixel(x,y), picture, color);
				SubspaceExploreUtil.bfsv(col, col, col, col);
			}
		};
}
