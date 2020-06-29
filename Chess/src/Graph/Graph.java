package Graph;

import java.util.LinkedList;

/**
 * Generic Graph
 * @author Michael J. Alvarado
 * @date May 2, 2020
 * @param <E> Elements node type 
 */
public class Graph<E>{

	LinkedList<Node<E>> nodes;

	public Graph(){
		nodes = new LinkedList<Node<E>>();
	}

	public int size() {
		return nodes.size();
	}
	
	public LinkedList<Node<E>> getNodeList(){
		return nodes;
	}
	
	/**
	 * @author Michael J. Alvarado
	 * @date May 2, 2020
	 * @param arg - Element to add as node in the graph
	 */
	public void addNode(E arg) {
		nodes.add(new Node<E>(arg));
	}

	/**
	 * Makes a two way edge from arg0 to arg1 and from arg1 to arg0
	 * @author Michael J. Alvarado
	 * @date May 2, 2020
	 * @param arg0 - element to connect the edge (Must be initialize as node in the graph)
	 * @param arg1 - element to connect the edge (Must be initialize as node in the graph)
	 * @return true if edge was added succesfully. If a argument was not found in the graph then return false (Wont add if there is no node with arguments given)
	 */
	public boolean addEdge(double value, E arg0, E arg1) {
			Node<E> node1 = getNode(arg0);
			Node<E> node2 = getNode(arg1);
			if(node1 == null || node2 == null) {
				return false;
			}
			node1.addEdge(new Edge(value, node2));
			node2.addEdge(new Edge(value, node1));
			return true;
	}
	
	/**
	 * @author Michael J. Alvarado
	 * @date May 2, 2020
	 * @param index
	 * @return return the node in a given index from the graph
	 * @throws IndexOutOfBoundsException - if the index is out of range(index < 0 || index >= size())
	 */
	public Node<E> getNode(int index) {
		return nodes.get(index);
	}
	
	/**
	 * @author Michael J. Alvarado
	 * @date May 2, 2020
	 * @param node
	 * @return The index of the node in the graph, if not found return -1
	 */
	public int indexOf(Node<E> node) {
		for (int i = 0; i < nodes.size(); i++) {
			if(nodes.get(i).equals(node))
				return i;
		}
		return -1;
	}
	
	/**
	 * @author Michael J. Alvarado
	 * @date May 2, 2020
	 * @param arg - The element you want to get the node from
	 * @return the node of a given object, if not found return null
	 */
	public Node<E> getNode(E arg) {
		for (Node<E> node : nodes) {
			if(node.get().equals(arg))
				return node;
		}
		return null;
	}
	
	
}
