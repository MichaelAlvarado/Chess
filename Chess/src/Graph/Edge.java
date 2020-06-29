package Graph;
/**
 * 
 * @author Michael J. Alvarado
 * @date 5/1/2020
 */

public class Edge<E>{

	double value; //Cost of the path
	Node<E> node;

	public Edge(double value, Node<E> node) {
		this.value = value;
		this.node = node;
	}

	public Node<E> getNode() {
		return node;
	}
	
	public double getValue() {
		return value;
	}

}