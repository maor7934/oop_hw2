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
	public void graph_creation(){
		Graph<Integer> g = new Graph<>("int_graph");
		assertEquals("int_graph", g.name);
		assertEquals(new HashMap<String,Integer>(), g.nodes);
		assertEquals(new HashMap<Integer, Set<Integer>>(), g.children_list);
	}
	@Test
	public void node_addition(){
		Graph<Integer> g = new Graph<>("int_graph");
		try {
			g.AddNode(3, "int_3");
		} catch (AlreadyContainsException e) {
			e.printStackTrace();
		}
		HashMap<String,Integer> my_hash = new HashMap<>();
		my_hash.put("int_3", 3);
		assertEquals(my_hash, g.nodes);
	}

	@Test
	public void edge_addition(){
		Graph<Integer> g = new Graph<>("int_graph");
		try {
			g.AddNode(3, "int_3");
			g.AddNode(4, "int_4");
			g.AddEdge(3, 4);
		} catch (AlreadyContainsException|NotContainsException e) {
			e.printStackTrace();
		}
		HashMap<Integer,Set<Integer>> my_hash = new HashMap<>();
		HashSet<Integer> my_set = new HashSet<>();
		my_set.add(4);
		my_hash.put(3, my_set);
		my_hash.put(4, new HashSet<>());
		assertEquals(my_hash, g.children_list);
	}

	@Test
	public void check_list_nodes(){
		Graph<Integer> g = new Graph<>("int_graph");
		try {
			g.AddNode(4, "int_4");
			g.AddNode(5, "int_5");
			g.AddNode(3, "int_3");
			g.AddNode(7, "int_7");
			g.AddNode(6, "int_6");
		} catch (AlreadyContainsException e) {
			e.printStackTrace();
		}
		ArrayList<Integer> my_list = g.ListNodes();
		assertTrue(my_list.contains(3));
		assertTrue(my_list.contains(4));
		assertTrue(my_list.contains(5));
		assertTrue(my_list.contains(6));
		assertTrue(my_list.contains(7));
	}

	@Test
	public void check_list_children(){
		Graph<Integer> g = new Graph<>("int_graph");
		try {
			g.AddNode(4, "int_4");
			g.AddNode(5, "int_5");
			g.AddNode(3, "int_3");
			g.AddNode(7, "int_7");
			g.AddNode(6, "int_6");
			g.AddEdge(3, 6);
			g.AddEdge(3, 4);
			g.AddEdge(3, 7);
			g.AddEdge(3, 5);
			g.AddEdge(3, 3);
		} catch (AlreadyContainsException|NotContainsException e) {
			e.printStackTrace();
		}
		try {
			ArrayList<Integer> my_list = g.ListChildren(3);
			assertTrue(my_list.contains(3));
			assertTrue(my_list.contains(4));
			assertTrue(my_list.contains(5));
			assertTrue(my_list.contains(6));
			assertTrue(my_list.contains(7));
		} catch (NotContainsException e) {
			e.printStackTrace();
		}
	}

	@Test(expected = AlreadyContainsException.class)
	public void check_double_node_error() throws AlreadyContainsException{
		Graph<Integer> g = new Graph<>("int_graph");
		g.AddNode(4, "int_4");
		g.AddNode(8, "int_4");
	}

	@Test(expected = NotContainsException.class)
	public void check_node_not_exists() throws NotContainsException{
		Graph<Integer> g = new Graph<>("int_graph");
		g.ListChildren(5);
	}

	@Test(expected = NotContainsException.class)
	public void check_node_not_exists_edge_src() throws NotContainsException, AlreadyContainsException{
		Graph<Integer> g = new Graph<>("int_graph");
		g.AddNode(4, "int_4");
		g.AddEdge(3, 4);
	}

	@Test(expected = NotContainsException.class)
	public void check_node_not_exists_edge_dst() throws NotContainsException, AlreadyContainsException{
		Graph<Integer> g = new Graph<>("int_graph");
		g.AddNode(4, "int_4");
		g.AddEdge(4, 5);
	}

	@Test(expected = NotContainsException.class)
	public void check_node_not_exists_edge_both() throws NotContainsException, AlreadyContainsException {
		Graph<Integer> g = new Graph<>("int_graph");
		g.AddEdge(4, 5);
	}

	@Test(expected = AlreadyContainsException.class)
	public void check_double_edge() throws NotContainsException, AlreadyContainsException {
		Graph<Integer> g = new Graph<>("int_graph");
		g.AddNode(3, "int_3");
		g.AddNode(4, "int_4");
		g.AddEdge(3, 4);
		g.AddEdge(3, 4);
	}

}
