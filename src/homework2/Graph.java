package homework2;

import java.util.*;

class AlreadyContainsException extends Exception{

}

class NotContainsException extends Exception{

}
public class Graph<T> {

    protected String name;
    protected HashMap<String,T> nodes;
    protected HashMap<T,Set<T>> children_list;

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
     * @effects returns a list of all nodes in the graph
     */
    public ArrayList<T> ListNodes(){
        this.checkRep();
        ArrayList<T> nodes = new ArrayList<>(this.nodes.values());
        this.checkRep();
        return nodes;
    }

    /**
     *
     * @requires parent != Null
     * @effects returns a list of all children nodes of node parent.
     *          if parent has no children returns an empty list.
     * @throws NotContainsException if 'parent_node' not inside the graph.
     */
    public ArrayList<T> ListChildren(T parent) throws NotContainsException {
        this.checkRep();
        if (!this.nodes.containsValue(parent)) {
            throw (new NotContainsException());
        }
        ArrayList<T> children = new ArrayList<>(this.children_list.get(parent));
        this.checkRep();
        return children;
    }

    /**
     * @effects checks that the Rep. Invariant of this class is not violated.
     * @throws AssertionError if it's violated.
     */
    void checkRep(){
        assert (this.name != null);
        assert (this.nodes != null);
        assert (this.children_list != null);
        assert (this.nodes.size() == this.children_list.size());
    }
}
