package homework2;

import java.util.*;

class AlreadyContainsException extends Exception{

}

class NotContainsException extends Exception{

}
public class Graph<T extends Comparable<T>> {

    private String name;
    private HashMap<String,T> nodes;
    private HashMap<T,Set<T>> children_list;

    /**
     * @requires name != Null.
     * @effects creates new Graph object with the name as param name.
     * @return new object of type Graph<T>.
     */
    public Graph(String name){
        this.name = name;
        this.nodes = new HashMap<>();
        this.children_list = new HashMap<>();
    }

    /**
     * @requires node != Null && name != Null && node.name == name .
     * @modifes add new node to the graph with the name 'name'.
     * @throws AlreadyContainsException if node name is already inside the graph.
     */
    public void AddNode(T node, String name) throws AlreadyContainsException {
        this.checkRep();
        if(this.nodes.containsKey(name)) {
            throw (new AlreadyContainsException());
        }
        this.nodes.put(name,node);
        this.children_list.put(node, new HashSet<T>());
        this.checkRep();
    }

    /**
     * @requires from != Null && to != Null
     * @modifes add new edge to the graph from node 'from' to the node 'to'.
     * @throws NotContainsException if 'from' or 'to' are not inside the graph.
     *         AlreadyContainsException if 'from' already has Edge to 'to'
     */
    public void AddEdge(T from, T to) throws NotContainsException,AlreadyContainsException {
        this.checkRep();
        if (!this.nodes.containsValue(from) || !this.nodes.containsValue(to)) {
            throw (new NotContainsException());
        }
        Set<T> children = this.children_list.get(from);
        if(children.contains(to)){
            throw (new AlreadyContainsException());
        }
        children.add(to);
        this.children_list.put(from,children);
        this.checkRep();
    }

    /**
     * @effects return the list of names of the nodes in the graph sorted alphabetically
     */
    public ArrayList<T> ListNodes(){
        this.checkRep();
        ArrayList<T> nodes = new ArrayList<>(this.nodes.values());
        Collections.sort(nodes);
        this.checkRep();
        return nodes;
    }

    /**
     *
     * @requires parent != Null
     * @effects return the list of names of the nodes that are children of node 'parent_name', sorted alphabetically,
     *          if 'parent_node' has no children return empty list.
     * @throws NotContainsException if 'parent_node' not inside the graph.
     */
    public ArrayList<T> ListChildren(T parent) throws NotContainsException {
        this.checkRep();
        if (!this.nodes.containsKey(parent)) {
            throw (new NotContainsException());
        }
        ArrayList<T> children = new ArrayList<>(this.children_list.get(parent));
        Collections.sort(children);
        this.checkRep();
        return children;
    }

    void checkRep(){
        assert (this.name != null);
        assert (this.nodes != null);
        assert (this.children_list != null);
        assert (this.nodes.size() == this.children_list.size());
    }
}
