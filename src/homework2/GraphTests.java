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
		assertEquals(new HashMap<String, Set<String>>(), g.children_list);
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
			g.AddEdge("int_3", "int_4");
		} catch (AlreadyContainsException|NotContainsException e) {
			e.printStackTrace();
		}
		HashMap<String,Set<String>> my_hash = new HashMap<>();
		HashSet<String> my_set = new HashSet<>();
		my_set.add("int_4");
		my_hash.put("int_3", my_set);
		my_hash.put("int_4", new HashSet<String>());
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
		ArrayList<String> my_list = new ArrayList<>();
		my_list.add("int_3");
		my_list.add("int_4");
		my_list.add("int_5");
		my_list.add("int_6");
		my_list.add("int_7");
		assertEquals(my_list, g.ListNodes());
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
			g.AddEdge("int_3", "int_6");
			g.AddEdge("int_3", "int_4");
			g.AddEdge("int_3", "int_7");
			g.AddEdge("int_3", "int_5");
			g.AddEdge("int_3", "int_3");

		} catch (AlreadyContainsException|NotContainsException e) {
			e.printStackTrace();
		}
		ArrayList<String> my_list = new ArrayList<>();
		my_list.add("int_3");
		my_list.add("int_4");
		my_list.add("int_5");
		my_list.add("int_6");
		my_list.add("int_7");
		try {
			assertEquals(my_list, g.ListChildren("int_3"));
		} catch (NotContainsException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void check_double_node_error(){
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
		ArrayList<String> my_list = new ArrayList<>();
		my_list.add("int_3");
		my_list.add("int_4");
		my_list.add("int_5");
		my_list.add("int_6");
		my_list.add("int_7");
		assertEquals(my_list, g.ListNodes());
	}
}
