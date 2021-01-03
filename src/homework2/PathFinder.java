package homework2;

import java.util.*;

/**
 * <b>Abstract Function-</b> path finder for a specific graph. holds the graph,
 * possible start points, possible end points. <b>Representation Invariant-</b>
 * this.graph != null && this.start_points != null && this.end_points != null
 */

public class PathFinder<N, P extends Path<N, P>> {
    protected Graph<N> graph;
    protected ArrayList<P> start_points;
    protected ArrayList<N> end_points;  // need to save this as Node for algorithm
    private void checkRep () {
        assert
                this.graph != null && this.start_points != null && this.end_points != null:
                "Rep. Inv. of class homework2.PathFinder is violated.";
    }
    /**
     * @requires graph != null && start_points != null && end_points != null
     * @effects builds a instance of homework2.PathFinder with specific graph, start_points
     *          and end_points
     */
    public PathFinder(Graph<N> graph, ArrayList<P> start_points, ArrayList<P> end_points) {
        this.graph = graph;
        this.start_points = start_points;
        this.end_points = new ArrayList<>();
        for (Path<N, P> path : end_points) { // need to save this as Node for algorithm
            this.end_points.add(path.getEnd());
        }
        this.checkRep();
    }
    /**
     * @requires nothing
     * @effects returns the shortest path possible with the curretn graph, end
     *          points, and start points
     */
    public P FindShortestPath()  throws NodeNotInGraphException {
        this.checkRep();
        // creating the data structures needed for the algorithm
        HashMap<N, P> paths = new HashMap<>();
        for (P path : this.start_points) {
            paths.put(path.getEnd(), path);
        }
        PriorityQueue<P> active = new PriorityQueue<>(this.start_points);
        ArrayList<N> finished = new ArrayList<>();
        while (!active.isEmpty()) {
            N queueMin = active.poll().getEnd();
            P queueMinPath = paths.get(queueMin);
            if (this.end_points.contains(queueMin)) {
                this.checkRep();
                return queueMinPath;
            }
            for (N child : this.graph.getChildren(queueMin)) {
                P cpath = queueMinPath.extend(child);
                if (!finished.contains(child) && !active.contains(paths.get(child)) && (queueMin != child)) { //added due to error
                    paths.put(child, cpath);
                    active.add(cpath);
                }
            }
            finished.add(queueMin);
        }
        // there is no path available
        this.checkRep();
        return null;
    }

}
