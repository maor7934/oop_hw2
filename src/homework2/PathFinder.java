package homework2;

import java.util.*;

public class PathFinder<N, P extends Path<N,P>> {

    protected ArrayList<P> start_paths;
    protected ArrayList<N> end_paths;
    protected Graph<N> graph;


    /**
     * @requires graph != Null && start_paths != Null && end_paths != Null
     * @effects build instance of PathFinder with graph, start_paths, end_paths
     */
    public PathFinder(Graph<N> graph, ArrayList<P> start_paths, ArrayList<P> end_paths) {
        this.graph = graph;
        this.start_paths = start_paths;
        this.end_paths = new ArrayList<>();
        for (Path<N, P> path : end_paths) {
            this.end_paths.add(path.getEnd());
        }
    }

    public P GetShortestPath() throws NotContainsException {
        // maps nodes -> paths
        HashMap<N,P> paths = new HashMap<>();
        for (P start : this.start_paths) {
            paths.put(start.getEnd(), start);
        }
        // The priority queue contains nodes with priority equal to the cost
        // of the shortest path to reach that node. Initially it contains the
        // start nodes
        PriorityQueue<P> active = new PriorityQueue<>(this.start_paths);
        // The set of finished nodes are those for which we know the shortest
        // paths from starts and whose children we have already examined
        ArrayList<N> finished = new ArrayList<>();
        while (active.size() != 0) {
            //queueMin is the element of active with shortest path
            N queueMin = active.poll().getEnd();
            P queueMinPath = paths.get(queueMin);
            if (this.end_paths.contains(queueMin)) {
                return queueMinPath;
            }
            // iterate over edges (queueMin, c) in queueMin.edges
            for (N child : this.graph.ListChildren(queueMin)) {
                P c_path = queueMinPath.extend(child);
                if ((!finished.contains(child)) && (!active.contains(paths.get(child))) && (queueMin != child)) {
                    paths.put(child, c_path);
                    active.add(c_path);
                }
            }
            finished.add(queueMin);
        }
        // execution reaches this point only if active becomes empty
        return null;
    }
}
