/**
 * modified by Group8
 * */
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public class TramFinder { 

	//Assignment: Implement this!
	/**Method that implements the fast route finding system.
	 */
	public static TramArrival[] fastFindRoute(TramNetwork nw, int startTime, TramNetwork.Station from, TramNetwork.Station to) {
		TramArrival[] bestPath = new TramArrival[nw.stations.length]; 
		Heap<TramArrival> queue = new Heap<>(); 
		for (TramNetwork.TramConnection tc : from.tramsFrom) {
			queue.add(new TramArrival(tc.tram, from, null, 0, startTime));
		}

		TramArrival currentStation;
		while (!queue.isEmpty()) {
			currentStation = queue.removeMin(); 
			if (currentStation.station.equals(to)) { // got to arrival stop
				bestPath[currentStation.station.id] = new TramArrival(currentStation.tram, to, currentStation.from, currentStation.timeTaken, currentStation.time);
				break;
			}
			if (bestPath[currentStation.station.id] != null) { continue; } // already passed through here
			for (TramNetwork.TramConnection edge : currentStation.station.tramsFrom) {
				int waitTime = edge.tram.waitingTime(currentStation.time, currentStation.station);
				TramArrival dep = new TramArrival(edge.tram, edge.to, edge.from, edge.timeTaken, currentStation.time + edge.timeTaken + waitTime);
				queue.add(dep);
			}
			bestPath[currentStation.station.id] = currentStation;
		}

		bestPath = getComplete(bestPath, bestPath[to.id]);

		return bestPath;
	}

	//remember path
	public static TramArrival[] getComplete(TramArrival[] last, TramArrival target) {
		ArrayList<TramArrival> res = new ArrayList<>();
		while (target.from != null){
			res.add(target);
			target = last[target.from.id];
		}
		res.add(target); // Add the origin last
		Collections.reverse(res);
		return res.toArray(new TramArrival[0]);
	}

	// This works but is not fast enough. 
	public static TramArrival[] findRoute(TramNetwork nw, int starttime, TramNetwork.Station from, TramNetwork.Station to){
		Finder f = new Finder(nw); 
		f.findRoute(starttime, from, to);
		return f.bestPath;
	}

	private static class Finder {
		private boolean[] visited;
		private LinkedList<TramArrival> currentPath = new LinkedList<TramArrival>();

		private int bestTime = -1;
		TramArrival[] bestPath = null;

		public Finder(TramNetwork nw) {
			visited = new boolean[nw.stations.length];
		}


		public void findRoute(int currentTime, TramNetwork.Station from, TramNetwork.Station to){

			if (visited[from.id])
				return;
			if (from.equals(to)){
				if (bestTime == -1 || currentTime < bestTime){
					bestTime = currentTime;
					currentPath.addLast(new TramArrival(currentPath.getLast().tram, to, from, currentTime, currentTime));
					bestPath = currentPath.toArray(new TramArrival[0]);
					currentPath.removeLast();
				}
				return;
			}


			visited[from.id]=true;

			List<TramNetwork.TramConnection> fromHere = from.tramsFrom;
			for(TramNetwork.TramConnection conn : fromHere ){

				int waitTime = conn.tram.waitingTime(currentTime,from);

				TramArrival dep = new TramArrival(conn.tram, from, conn.to, currentTime, currentTime+waitTime);

				currentPath.addLast(dep);
				findRoute(dep.time+conn.timeTaken, conn.to, to);
				currentPath.removeLast();
			}

			visited[from.id]=false;

		}		

	}

	/** Represents a single arrival/departure of a tram at/from a station*/
	public static class TramArrival implements Comparable<TramArrival>{
		public final TramNetwork.Tram tram;
		public final TramNetwork.Station station;
		public final TramNetwork.Station from;
		public final int timeTaken;

		/** Absolute time, in minutes from 00:00, possibly exceeds 24*60 */
		public final int time;

		public TramArrival(TramNetwork.Tram tram, TramNetwork.Station station, TramNetwork.Station from, int timeTaken, int time) {
			this.tram = tram;
			this.station = station;
			this.from = from;
			this.timeTaken = timeTaken;
			this.time = time;
		}

		public String toString(){
			int timeofday = time % (60*24),
					hour = timeofday/60,
					minute = timeofday%60;

			return (hour < 10 ? "0"+hour: hour)+":"+(minute < 10 ? "0"+minute: minute)+" time taken: "+timeTaken+", Tram "+tram+" at " + station;

		}

		public int compareTo(TramArrival t) {
			return time - t.time;
			//return (time + timeTaken) - (t.timeTaken + t.time);
		}
	}


}
