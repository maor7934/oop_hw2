package homework2;


import org.junit.Test;
import static org.junit.Assert.*;

import java.util.*;

/**
 * This class contains a set of test cases that can be used to test the graph
 * and shortest path finding algorithm implementations of homework assignment
 * #2.
 */
public class GraphTests extends ScriptFileTests {

	public GraphTests(java.nio.file.Path testFile) {
		super(testFile);
	}

	@Test
	public void check_create_graph() {
		Graph<Integer> graph = new Graph<>();
		assertEquals(new HashMap<Integer, HashSet<Integer>>(), graph.nodeCollection);
	}

	@Test
	public void check_add_node() {
		Graph<Integer> graph = new Graph<>();
		try {
			graph.addNode(1);
		} catch (AlreadyHasNodeException e) {
			e.printStackTrace();
		}
		HashMap<Integer, HashSet<Integer>> check_hash = new HashMap<>();
		check_hash.put(1, new HashSet<Integer>());
		assertEquals(check_hash, graph.nodeCollection);
	}

	@Test(expected = AlreadyHasNodeException.class)
	public void check_add_same_node() throws AlreadyHasNodeException {
		Graph<Integer> graph = new Graph<>();
		graph.addNode(123);
		graph.addNode(123);
	}

	@Test(expected = NodeNotInGraphException.class)
	public void check_missing_node() throws NodeNotInGraphException {
		Graph<Integer> graph = new Graph<>();
		graph.getChildren(1);
	}

	public void check_add_edge() {
		Graph<Integer> graph = new Graph<>();
		try {
			graph.addNode(1);
			graph.addNode(2);
			graph.addEdge(1, 2);
		} catch (AlreadyHasNodeException | NodeNotInGraphException | AlreadyHasEdgeException e) {
			e.printStackTrace();
		}
		HashMap<Integer, HashSet<Integer>> check_hash = new HashMap<>();
		check_hash.put(1, new HashSet<Integer>());
		check_hash.put(2, new HashSet<Integer>());
		check_hash.get(1).add(2);
		assertEquals(check_hash, graph.nodeCollection);
	}

	@Test(expected = AlreadyHasEdgeException.class)
	public void check_add_same_edge() throws AlreadyHasNodeException, NodeNotInGraphException, AlreadyHasEdgeException {
		Graph<Integer> graph = new Graph<>();
		graph.addNode(1);
		graph.addNode(2);
		graph.addEdge(1, 2);
		graph.addEdge(1, 2);
	}

	@Test(expected = NodeNotInGraphException.class)
	public void check_add_edge_missing_first_node()
			throws AlreadyHasNodeException, NodeNotInGraphException, AlreadyHasEdgeException {
		Graph<Integer> graph = new Graph<>();
		graph.addNode(1);
		graph.addEdge(1, 2);
	}

	@Test(expected = NodeNotInGraphException.class)
	public void check_add_edge_missing_second_node()
			throws AlreadyHasNodeException, NodeNotInGraphException, AlreadyHasEdgeException {
		Graph<Integer> graph = new Graph<>();
		graph.addNode(2);
		graph.addEdge(1, 2);
	}

	@Test(expected = NodeNotInGraphException.class)
	public void check_add_edge_missing_both_node()
			throws AlreadyHasNodeException, NodeNotInGraphException, AlreadyHasEdgeException {
		Graph<Integer> graph = new Graph<>();
		graph.addEdge(1, 2);
	}

	@Test
	public void check_graph_good_test() {
		Graph<Integer> graph = new Graph<>();
		try {
			graph.addNode(1);
			graph.addNode(2);
			graph.addNode(3);
			graph.addNode(4);
			graph.addNode(5);
			graph.addNode(6);
			graph.addNode(7);
			graph.addNode(8);
			graph.addNode(9);
			graph.addNode(10);
			graph.addEdge(1, 2);
			graph.addEdge(1, 3);
			graph.addEdge(1, 4);
			graph.addEdge(1, 5);
			graph.addEdge(1, 6);
			graph.addEdge(1, 7);
			graph.addEdge(1, 8);
			graph.addEdge(1, 9);
			graph.addEdge(1, 10);
			graph.addEdge(2, 1);
			graph.addEdge(3, 2);
			graph.addEdge(4, 7);
			graph.addEdge(7, 3);
			graph.addEdge(3, 4);
			graph.addEdge(4, 5);
			graph.addEdge(8, 4);
			graph.addEdge(8, 1);
			graph.addEdge(7, 2);
			graph.addEdge(7, 1);

		} catch (AlreadyHasNodeException | NodeNotInGraphException | AlreadyHasEdgeException e) {
			e.printStackTrace();
		}
		try {
			assertTrue(graph.nodeCollection.containsKey(1));
			assertTrue(graph.nodeCollection.containsKey(2));
			assertTrue(graph.nodeCollection.containsKey(3));
			assertTrue(graph.nodeCollection.containsKey(4));
			assertTrue(graph.nodeCollection.containsKey(5));
			assertTrue(graph.nodeCollection.containsKey(6));
			assertTrue(graph.nodeCollection.containsKey(7));
			assertTrue(graph.nodeCollection.containsKey(8));
			assertTrue(graph.nodeCollection.containsKey(9));
			assertTrue(graph.nodeCollection.containsKey(10));
			assertTrue(graph.getChildren(1).contains(2));
			assertTrue(graph.getChildren(1).contains(3));
			assertTrue(graph.getChildren(1).contains(4));
			assertTrue(graph.getChildren(1).contains(5));
			assertTrue(graph.getChildren(1).contains(6));
			assertTrue(graph.getChildren(1).contains(7));
			assertTrue(graph.getChildren(1).contains(8));
			assertTrue(graph.getChildren(1).contains(9));
			assertTrue(graph.getChildren(1).contains(10));
			assertTrue(graph.getChildren(2).contains(1));
			assertTrue(graph.getChildren(3).contains(2));
			assertTrue(graph.getChildren(4).contains(7));
			assertTrue(graph.getChildren(7).contains(3));
			assertTrue(graph.getChildren(3).contains(4));
			assertTrue(graph.getChildren(4).contains(5));
			assertTrue(graph.getChildren(8).contains(4));
			assertTrue(graph.getChildren(8).contains(1));
			assertTrue(graph.getChildren(7).contains(2));
			assertTrue(graph.getChildren(7).contains(1));
		} catch (NodeNotInGraphException e){
			e.printStackTrace();
		}
	}

	@Test
	public void path_finder_good_test() throws AlreadyHasNodeException, NodeNotInGraphException, AlreadyHasEdgeException {
		Graph<WeightedNode> graph = new Graph<>();
		WeightedNode node_1 = new WeightedNode("1",1);
		WeightedNode node_2 = new WeightedNode("2",2);
		WeightedNode node_3 = new WeightedNode("3",3);
		WeightedNode node_4 = new WeightedNode("4",4);
		graph.addNode(node_1);
		graph.addNode(node_2);
		graph.addNode(node_3);
		graph.addNode(node_4);
		graph.addEdge(node_1, node_2);
		graph.addEdge(node_1, node_3);
		graph.addEdge(node_1, node_4);
		graph.addEdge(node_2, node_1);
		graph.addEdge(node_2, node_3);
		graph.addEdge(node_2, node_4);
		graph.addEdge(node_3, node_1);
		graph.addEdge(node_3, node_2);
		graph.addEdge(node_3, node_4);
		WeightedNodePath path_1 = new WeightedNodePath(node_1);
		WeightedNodePath path_2 = new WeightedNodePath(node_3);
		ArrayList<WeightedNodePath> arr_1 = new ArrayList<>();
		ArrayList<WeightedNodePath> arr_2 = new ArrayList<>();
		arr_1.add(path_1);
		arr_2.add(path_2);
		ArrayList<WeightedNode> my_arr_2 = new ArrayList<>();
		my_arr_2.add(node_3);
		try {
			PathFinder<WeightedNode,WeightedNodePath> pathfinder_1 =
					new PathFinder<>(graph,new ArrayList<WeightedNodePath>(),new ArrayList<>());
			assertEquals("PathFinder: Shortest path is not empty",null,pathfinder_1.FindShortestPath());
			PathFinder<WeightedNode,WeightedNodePath> pathfinder_2 =
					new PathFinder<>(graph,arr_1,new ArrayList<WeightedNodePath>());
			assertEquals("PathFinder: Shortest path is not empty",null,pathfinder_2.FindShortestPath());
			PathFinder<WeightedNode,WeightedNodePath> pathfinder_3 =
					new PathFinder<>(graph,new ArrayList<WeightedNodePath>(),arr_2);
			assertEquals("PathFinder: Shortest path is not empty",null,pathfinder_3.FindShortestPath());
		} catch (Exception e){
			e.printStackTrace();
		}
		PathFinder<WeightedNode,WeightedNodePath> my_pathfinder = new PathFinder<>(graph,arr_1,arr_2);
		assertEquals("PathFinder- graphs aren't equal",graph,my_pathfinder.graph);
		assertEquals("PathFinder- start points aren't equal",arr_1,my_pathfinder.start_points);
		assertEquals("PathFinder- end points aren't equal",my_arr_2,my_pathfinder.end_points);
		WeightedNodePath shortest = my_pathfinder.FindShortestPath();
		WeightedNodePath my_shortest = new WeightedNodePath(node_1);
		assertEquals("PathFinder- path found doesnt match",shortest,my_shortest.extend(node_3));
	}

}
