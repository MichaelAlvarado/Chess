package Graph;

import java.util.LinkedList;

import Graph.Edge;

/**
 * 
 * @author Michael J. Alvarado
 * @date 5/1/2020
 */
@SuppressWarnings("rawtypes")
public class Node<E> implements Comparable<Node>{

	//Use to Create paths
	Node parent; 
	double value;
	double f;
	
	/**
	 * @author Andrea Miranda & Ramphis Lopez
	 * @date May 13, 2020
	 */

	//Node Info
	E object;
	LinkedList<Edge> edges;


	public Node(E arg) {
		object = arg;
		edges = new LinkedList<Edge>();
		value = 0;
	}

	/**
	 * Use to build a path
	 * @author Michael J. Alvarado
	 * @date May 3, 2020
	 * @param parent
	 */
	public void setParent(Node parent) {
		this.parent = parent;
	}

	/**
	 * Use to build a path
	 * @author Michael J. Alvarado
	 * @date May 3, 2020
	 * @param parent
	 */
	public Node getParent() {
		return parent;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public E get() {
		return object;
	}

	public LinkedList<Edge> getEdges(){
		return edges;
	}

	public void addEdge(Edge edge) {
		edges.add(edge);
	}

	public double getF() {
		return f;
	}

	public void setF(double f) {
		this.f = f;
	}

	public LinkedList<Node> getNodeChildrens() {
		LinkedList<Node> childrens = new LinkedList<Node>();
		for(Edge e: edges) {
			childrens.add(e.getNode());
		}
		return childrens;
	}

	/**
	 * clone this node and its parent hierarchy till stop Node found
	 * @author Michael J. Alvarado
	 * @date May 14, 2020
	 * @param stop - parent node to stop the cloning
	 * @return clone intanceof this with clone parents
	 * @throws StackOverflow if stop is not in the parents hierarchy.
	 */
	public Node pathClone(Node stop) {
		Node clone = new Node(this.object);
		clone.setValue(new Double(this.getValue()));
		clone.edges = this.getEdges();
		if(this.get().equals(stop.get())) {
			return this; 
		}
		clone.setParent(this.getParent().pathClone(stop));
		return clone;
	}

	@Override
	public int compareTo(Node n) {
		if(this.f > n.getF()){
			return 1;
		}else if(this.f < n.getF()){
			return -1;
		}else{
			return 0;
		}
	}

}
