package homework2;


import org.junit.Test;
import static org.junit.Assert.*;
import java.io.IOException;
import java.util.*;

/**
 * This class contains a set of test cases that can be used to test the graph
 * and shortest path finding algorithm implementations of homework assignment
 * #2.
 */
public class GraphTests extends ScriptFileTests {

	// black-box test are inherited from super
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

	@Test(expected = NodeNotInGraphExpection.class)
	public void check_missing_node() throws NodeNotInGraphExpection {
		Graph<Integer> graph = new Graph<>();
		graph.getChildren(1);
	}

	public void check_add_edge() {
		Graph<Integer> graph = new Graph<>();
		try {
			graph.addNode(1);
			graph.addNode(2);
			graph.addEdge(1, 2);
		} catch (AlreadyHasNodeException | NodeNotInGraphExpection | AlreadyHasEdgeException e) {
			e.printStackTrace();
		}
		HashMap<Integer, HashSet<Integer>> check_hash = new HashMap<>();
		check_hash.put(1, new HashSet<Integer>());
		check_hash.put(2, new HashSet<Integer>());
		check_hash.get(1).add(2);
		assertEquals(check_hash, graph.nodeCollection);
	}

	@Test(expected = AlreadyHasEdgeException.class)
	public void check_add_same_edge() throws AlreadyHasNodeException, NodeNotInGraphExpection, AlreadyHasEdgeException {
		Graph<Integer> graph = new Graph<>();
		graph.addNode(1);
		graph.addNode(2);
		graph.addEdge(1, 2);
		graph.addEdge(1, 2);
	}

	@Test(expected = NodeNotInGraphExpection.class)
	public void check_add_edge_missing_first_node()
			throws AlreadyHasNodeException, NodeNotInGraphExpection, AlreadyHasEdgeException {
		Graph<Integer> graph = new Graph<>();
		graph.addNode(1);
		graph.addEdge(1, 2);
	}

	@Test(expected = NodeNotInGraphExpection.class)
	public void check_add_edge_missing_second_node()
			throws AlreadyHasNodeException, NodeNotInGraphExpection, AlreadyHasEdgeException {
		Graph<Integer> graph = new Graph<>();
		graph.addNode(2);
		graph.addEdge(1, 2);
	}

	@Test(expected = NodeNotInGraphExpection.class)
	public void check_add_edge_missing_both_node()
			throws AlreadyHasNodeException, NodeNotInGraphExpection, AlreadyHasEdgeException {
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

		} catch (AlreadyHasNodeException | NodeNotInGraphExpection | AlreadyHasEdgeException e) {
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
		} catch (NodeNotInGraphExpection e){
			e.printStackTrace();
		}
	}

}
