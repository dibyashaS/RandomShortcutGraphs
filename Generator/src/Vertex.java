import java.util.HashSet;
import java.util.Set;

public class Vertex {
	private Set<Vertex> neighbors = new HashSet<>();
	private int index;

	public Vertex(int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}

	public Set<Vertex> getNeighbor() {
		return neighbors;
	}

	public boolean addNeighbor(Vertex v) {
		return index != v.index && this.neighbors.add(v) && v.getNeighbor().add(this);
	}

	public void removeNeighbor(Vertex v) {
		neighbors.remove(v);
		v.getNeighbor().remove(this);
	}

//      public void replace(Vertex v, Vertex V) {                                                                                                                           
//              neighbors.remove(v);                                                                                                                                        
//              neighbors.add(V);                                                                                                                                           
//      }                                                                                                                                                                   
	public String toString() {
		String result = "";
		for (Vertex v : neighbors) {
			result += v.getIndex() + " ";
		}
		result += "\n";
		return result;
	}
}