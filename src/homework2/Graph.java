package homework2;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Graph<T> {

    /**
     * @requires name != Null.
     * @effects creates new Graph object with the name as param name.
     * @return new object of type Graph<T>.
     */
    Graph<T> CreateGraph(String name){
        ;
    }

    /**
     *
     * @requires node != Null && name != Null.
     * @modifes add new node to the graph with the name 'name'.
     */
    void AddNode(T node, String name) {

    }

    /**
     *
     * @requires from != Null && to != Null
     * @modifes add new edge to the graph from node 'from' to the node 'to'.
     */
    void AddEdge(String from, String to){

    }

    /**
     * @effects return the list of names of the nodes in the graph sorted alphabetically
     */
    ArrayList<String> ListNodes(){

    }

    /**
     *
     * @requires parent_name != Null
     * @effects return the list of names of the nodes that are children of node 'parent_name', sorted alphabetically,
     *          if 'parent_node' has no children return empty list.
     * @throws InputMismatchException if 'parent_node' not inside the graph.
     */
    ArrayList<String> ListChildren(String parent_name){

    }

    /**
     *
     * @requires from != Null && to != Null
     * @effects return a list of the nodes that compose the shortest path between one of the nodes in 'from'
     *          list to one of the nodes in 'to' list. if there is no path - returns empty list.
     * @throws InputMismatchException if 'from' or 'to' are empty lists.
     */
    ArrayList<String> FindPath(List<String> from, List<String> to){
        ;
    }

}
