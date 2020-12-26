package homework2;

import java.util.*;

public class PathFinder<N, P extends Path<N,P>> {

    private ArrayList<P> start_paths;
    private ArrayList<N> end_paths;
    private Graph<N> graph;


    /**
     * @requiers graph != Null && start_paths != Null && end_paths != Null
     * @effects TODO
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
            P queueMinPath = active.poll();
            N queueMin = queueMinPath.getEnd();
            if (this.end_paths.contains(queueMin)) {
                return queueMinPath;
            }
            // iterate over edges (queueMin, c) in queueMin.edges
            for (N child : this.graph.ListChildren(queueMin)) {
                P c_path = queueMinPath.extend(child);
                if ((!finished.contains(child)) && (!active.contains(c_path)) && (queueMin != child)) {
                    active.remove(paths.get(child));
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
