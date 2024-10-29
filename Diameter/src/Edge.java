public class Edge {
	int from;
	int to;
	int length;
	int usage;

	public Edge(int from, int to, int usage) {
		this.from = from;
		this.to = to;
		this.length = to - from;
		this.usage = usage;
	}

	public int getUsage() {
		return usage;
	}

	public String toString() {
		String result = from + " to " + to + " : " + usage + " " + length;
		return result;
	}
}
