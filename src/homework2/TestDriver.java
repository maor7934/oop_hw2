package homework2;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


/**
 * This class implements a testing driver which reads test scripts
 * from files for testing homework2.Graph and homework2.PathFinder.
 */
public class TestDriver {

	// String -> homework2.Graph: maps the names of graphs to the actual graph
	// TODO: Parameterize the next line correctly.
	private final Map<String,Graph<WeightedNode>> graphs = new HashMap<>();
	// String -> homework2.WeightedNode: maps the names of nodes to the actual node
	private final Map<String,WeightedNode> nodes = new HashMap<>();
	private final BufferedReader input;
	private final PrintWriter output;


	/**
	 * Creates a new homework2.TestDriver.
	 * @requires r != null && w != null
	 * @effects Creates a new homework2.TestDriver which reads command from
	 * <tt>r</tt> and writes results to <tt>w</tt>.
	 */
	public TestDriver(Reader r, Writer w) {
		input = new BufferedReader(r);
		output = new PrintWriter(w);
	}


	/**
	 * Executes the commands read from the input and writes results to the
	 * output.
	 * @effects Executes the commands read from the input and writes
	 * 		    results to the output.
	 * @throws IOException - if the input or output sources encounter an
	 * 		   IOException.
	 */
	public void runTests() throws IOException {

		String inputLine;
		while ((inputLine = input.readLine()) != null) {
			// echo blank and comment lines
			if (inputLine.trim().length() == 0 ||
					inputLine.charAt(0) == '#') {
				output.println(inputLine);
				continue;
			}

			// separate the input line on white space
			StringTokenizer st = new StringTokenizer(inputLine);
			if (st.hasMoreTokens()) {
				String command = st.nextToken();

				List<String> arguments = new ArrayList<>();
				while (st.hasMoreTokens())
					arguments.add(st.nextToken());

				executeCommand(command, arguments);
			}
		}

		output.flush();
	}


	private void executeCommand(String command, List<String> arguments) {

		try {
			if (command.equals("CreateGraph")) {
				createGraph(arguments);
			} else if (command.equals("CreateNode")) {
				createNode(arguments);
			} else if (command.equals("AddNode")) {
				addNode(arguments);
			} else if (command.equals("AddEdge")) {
				addEdge(arguments);
			} else if (command.equals("ListNodes")) {
				listNodes(arguments);
			} else if (command.equals("ListChildren")) {
				listChildren(arguments);
			} else if (command.equals("FindPath")) {
				findPath(arguments);
			} else {
				output.println("Unrecognized command: " + command);
			}
		} catch (Exception e) {
			output.println("Exception: " + e.toString());
		}
	}


	private void createGraph(List<String> arguments) {

		if (arguments.size() != 1)
			throw new CommandException(
					"Bad arguments to CreateGraph: " + arguments);

		String graphName = arguments.get(0);
		createGraph(graphName);
	}


	private void createGraph(String graphName) {

		//TODO: Insert your code here.
		graphs.put(graphName,new Graph<WeightedNode>());
		// graphs.put(graphName, ___);
		output.println("created graph "+graphName);
	}//


	private void createNode(List<String> arguments) {

		if (arguments.size() != 2)
			throw new CommandException(
					"Bad arguments to createNode: " + arguments);

		String nodeName = arguments.get(0);
		String cost = arguments.get(1);
		createNode(nodeName, cost);
	}


	private void createNode(String nodeName, String cost) {

		// TODO: Insert your code here.
		WeightedNode new_node = new WeightedNode(nodeName,Integer.parseInt(cost));
		//nodes.put(nodeName, new homework2.WeightedNode(nodeName,cost));
		nodes.put(nodeName, new_node);
		output.println("created node "+nodeName+" with cost "+ cost );

	}


	private void addNode(List<String> arguments) {

		if (arguments.size() != 2)
			throw new CommandException(
					"Bad arguments to addNode: " + arguments);

		String graphName = arguments.get(0);
		String nodeName = arguments.get(1);
		addNode(graphName, nodeName);
	}


	private void addNode(String graphName, String nodeName) {

		// TODO: Insert your code here.
		Graph<WeightedNode> current_graph = graphs.get(graphName);
		WeightedNode current_node = nodes.get(nodeName);
		if (current_graph == null || current_node == null) {
			output.println("--E-- homework2.TestDriver::addNode: shoudn't get here! graph or node doesn't exists");
			return;
		}
		try {
			current_graph.addNode(current_node);
			output.println("added node "+nodeName+" to "+graphName);
		} catch (AlreadyHasNodeException e) {
			e.printStackTrace();
		}
		// ___ = graphs.get(graphName);
		// ___ = nodes.get(nodeName);


	}

	private void addEdge(List<String> arguments) {

		if (arguments.size() != 3)
			throw new CommandException(
					"Bad arguments to addEdge: " + arguments);

		String graphName = arguments.get(0);
		String parentName = arguments.get(1);
		String childName = arguments.get(2);
		addEdge(graphName, parentName, childName);
	}


	private void addEdge(String graphName, String parentName, String childName) {

		// TODO: Insert your code here.

		Graph<WeightedNode> current_graph = graphs.get(graphName);
		WeightedNode parent__node  = nodes.get(parentName);
		WeightedNode child_node  = nodes.get(childName);
		if (current_graph == null || parent__node == null || child_node == null) {
			output.println("--E-- homework2.TestDriver::addEdge: shoudn't get here! graph or p c node doesn't exists");
			return;
		}
		try {
			if (!current_graph.addEdge(parent__node, child_node)) {
				output.println("--E-- homework2.TestDriver::addEdge: shoudn't get here! the graph doesn't contain the child");
				return;
			}
			output.println("added edge from "+parentName+" to "+childName+ " in "+graphName);
		} catch (AlreadyHasEdgeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NodeNotInGraphException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// output.println(...);

	}


	private void listNodes(List<String> arguments) {

		if (arguments.size() != 1)
			throw new CommandException(
					"Bad arguments to listNodes: " + arguments);

		String graphName = arguments.get(0);
		listNodes(graphName);
	}


	private void listNodes(String graphName) {

		// TODO: Insert your code here.

		Graph<WeightedNode> current_graph = graphs.get(graphName);
		ArrayList<WeightedNode> current_graph_nodes_list = current_graph.getNodes();
		ArrayList<String> nodes_name_list = new ArrayList<String>();

		for (WeightedNode currNode : current_graph_nodes_list) {
			nodes_name_list.add(currNode.getName());
		}
		Collections.sort(nodes_name_list);
		output.print(graphName+" contains:");
		for (String curr_node_name : nodes_name_list) {
			output.print(" "+curr_node_name);
		}
		output.println();

	}


	private void listChildren(List<String> arguments) {

		if (arguments.size() != 2)
			throw new CommandException(
					"Bad arguments to listChildren: " + arguments);

		String graphName = arguments.get(0);
		String parentName = arguments.get(1);
		listChildren(graphName, parentName);
	}


	private void listChildren(String graphName, String parentName){

		// TODO: Insert your code here.

		Graph<WeightedNode> current_graph = graphs.get(graphName);
		WeightedNode parent_node = nodes.get(parentName);
		try {
		ArrayList<WeightedNode> children_of_current_Parent_List = current_graph.getChildren(parent_node);
		ArrayList<String> children_list_of_names = new ArrayList<String>();
		for (WeightedNode curr_node : children_of_current_Parent_List) {
			children_list_of_names.add(curr_node.getName());
		}
		Collections.sort(children_list_of_names);
		output.print("the children of "+parentName+" in " + graphName+" are:");
		for (String curr_child_name : children_list_of_names) {
			output.print(" "+curr_child_name);
		}
		output.println();
	} catch (NodeNotInGraphException e) {
			e.printStackTrace();
		}


	}


	private void findPath(List<String> arguments) {

		String graphName;
		List<String> sourceArgs = new ArrayList<>();
		List<String> destArgs = new ArrayList<>();

		if (arguments.size() < 1)
			throw new CommandException(
					"Bad arguments to FindPath: " + arguments);

		Iterator<String> iter = arguments.iterator();
		graphName = iter.next();

		// extract source arguments
		while (iter.hasNext()) {
			String s = iter.next();
			if (s.equals("->"))
				break;
			sourceArgs.add(s);
		}

		// extract destination arguments
		while (iter.hasNext())
			destArgs.add(iter.next());

		if (sourceArgs.size() < 1)
			throw new CommandException(
					"Too few source args for FindPath");

		if (destArgs.size() < 1)
			throw new CommandException(
					"Too few dest args for FindPath");

		findPath(graphName, sourceArgs, destArgs);
	}


	private void findPath(String graphName, List<String> sourceArgs,
						  List<String> destArgs) {

		// TODO: Insert your code here.

		Graph<WeightedNode> current_graph = graphs.get(graphName);
		ArrayList<WeightedNodePath> start_points = new ArrayList<WeightedNodePath>();
		ArrayList<WeightedNodePath> end_points = new ArrayList<WeightedNodePath>();
		for (int i = 0; i < sourceArgs.size();i++) {
			start_points.add(new WeightedNodePath(nodes.get(sourceArgs.get(i))));
		}
		for (int i = 0; i < destArgs.size();i++) {
			end_points.add(new WeightedNodePath(nodes.get(destArgs.get(i))));
		}
		try {
			PathFinder<WeightedNode, WeightedNodePath> find_path =
					new PathFinder<WeightedNode, WeightedNodePath>(current_graph, start_points, end_points);
			WeightedNodePath shortest_path = find_path.FindShortestPath();
			if (shortest_path != null) {
				output.print("shortest path in " + graphName + ":");
				for (WeightedNode wn : shortest_path) {
					output.print(" " + wn.getName());
				}
				output.println();
			} else {
				output.println("no path found in " + graphName);
			}

		} catch (NodeNotInGraphException e) {
			e.printStackTrace();

		}

	}


	private static void printUsage() {
		System.err.println("Usage:");
		System.err.println("to read from a file: java homework2.TestDriver <name of input script>");
		System.err.println("to read from standard input: java homework2.TestDriver");
	}


	public static void main(String args[]) {

		try {
			// check for correct number of arguments
			if (args.length > 1) {
				printUsage();
				return;
			}

			TestDriver td;
			if (args.length == 0)
				// no arguments - read from standard input
				td = new TestDriver(new InputStreamReader(System.in),
						new OutputStreamWriter(System.out));
			else {
				// one argument - read from file
				java.nio.file.Path testsFile = Paths.get(args[0]);
				if (Files.exists(testsFile) && Files.isReadable(testsFile)) {
					td = new TestDriver(
							Files.newBufferedReader(testsFile, Charset.forName("US-ASCII")),
							new OutputStreamWriter(System.out));
				} else {
					System.err.println("Cannot read from " + testsFile.toString());
					printUsage();
					return;
				}
			}

			td.runTests();

		} catch (IOException e) {
			System.err.println(e.toString());
			e.printStackTrace(System.err);
		}
	}
}


/**
 * This exception results when the input file cannot be parsed properly.
 */
class CommandException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CommandException() {
		super();
	}

	public CommandException(String s) {
		super(s);
	}
}