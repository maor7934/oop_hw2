package homework2;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.IOException;
import java.util.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PathFinderTest extends ScriptFileTests {

    // black-box test are inherited from super
    public PathFinderTest(java.nio.file.Path testFile) {
        super(testFile);
    }

    @Test
    public void pathfinder_creation() throws AlreadyContainsException, NotContainsException {
        Graph<Integer> g = new Graph<>("int_graph");
        g.AddNode(3, "int_3");
        g.AddNode(3, "int_3");
        g.AddNode(4, "int_4");
        g.AddEdge(3, 4);
        PathFinder<Integer,Path<Integer,Path>> my_pathfinder = new PathFinder<>()
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
