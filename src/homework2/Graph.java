package homework2;

import java.util.*;

class AlreadyContainsException extends Exception{

}

class NotContainsException extends Exception{

}
public class Graph<T> {

    String name;
    HashMap<String,T> nodes;
    HashMap<String,Set<String>> children_list;

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
     * @requires node != Null && name != Null.
     * @modifes add new node to the graph with the name 'name'.
     * @throws AlreadyContainsException if node name is already inside the graph.
     */
    public void AddNode(T node, String name) throws AlreadyContainsException {
        this.checkRep();
        if(this.nodes.containsKey(name)) {
            throw (new AlreadyContainsException());
        }
        this.nodes.put(name,node);
        this.children_list.put(name, new HashSet<String>());
        this.checkRep();
    }

    /**
     * @requires from != Null && to != Null
     * @modifes add new edge to the graph from node 'from' to the node 'to'.
     * @throws NotContainsException if 'from' or 'to' are not inside the graph.
     *         AlreadyContainsException if 'from' already has Edge to 'to'
     */
    public void AddEdge(String from, String to) throws NotContainsException,AlreadyContainsException {
        this.checkRep();
        if (!this.nodes.containsKey(from) || !this.nodes.containsKey(to)) {
            throw (new NotContainsException());
        }
        Set<String> children = this.children_list.get(from);
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
    public ArrayList<String> ListNodes(){
        this.checkRep();
        ArrayList<String> node_names = new ArrayList<>(this.nodes.keySet());
        Collections.sort(node_names);
        this.checkRep();
        return node_names;
    }

    /**
     *
     * @requires parent_name != Null
     * @effects return the list of names of the nodes that are children of node 'parent_name', sorted alphabetically,
     *          if 'parent_node' has no children return empty list.
     * @throws NotContainsException if 'parent_node' not inside the graph.
     */
    public ArrayList<String> ListChildren(String parent_name) throws NotContainsException {
        this.checkRep();
        if (!this.nodes.containsKey(parent_name)) {
            throw (new NotContainsException());
        }
        ArrayList<String> children_names = new ArrayList<>(this.children_list.get(parent_name));
        Collections.sort(children_names);
        this.checkRep();
        return children_names;
    }

    void checkRep(){
        assert (this.name != null);
        assert (this.nodes != null);
        assert (this.children_list != null);
        assert (this.nodes.size() == this.children_list.size());
    }
}
