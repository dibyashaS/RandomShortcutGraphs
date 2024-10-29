import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Shortcut {
	private List<Vertex> vertices = new ArrayList<>();
	private List<List<Vertex>> groups = new ArrayList<>();
	int n;
	int groupSize;
	int intraGroup;

	public Shortcut(int n, int y, int groupSize, int intraGroup, String filename) {
		this.n = n;

		// generate isolated vertices
		for (int i = 0; i < n; i++) {
			vertices.add(new Vertex(i));
		}

		// adds first groupSize vertices to the first group, then second groupSize to second group and so on until all the vertices are in a group
		for (int i = 0; i < n; i += groupSize) {
			List<Vertex> group = new ArrayList<>();
			for (int j = i; j < i + groupSize; j++) {
				group.add(vertices.get(j));
			}
			groups.add(group);
		}

		int numGroups = n / groupSize;

		// TESTING: print each group and which vertices are in each group
		/*for (int i = 0; i < numGroups; i++) {
			System.out.println("Group " + i + ":");
			for (int j = 0; j < groupSize; j++) {
				System.out.print(groups.get(i).get(j).getIndex() + " ");
			}
			System.out.println();
		}*/

		// add shortcuts within the group ensure that each node does not have more than y neighbors
		Random r1 = new Random();
		for (int i = 0; i < numGroups; i++) {
			for (int j = 0; j < groupSize; j++) {
				Vertex v = groups.get(i).get(j);
				int temp = v.getNeighbor().size();
				for (int k = 0; k < y - temp; k++) {
					int r = r1.nextInt(groupSize-1);
					Vertex v2 = groups.get(i).get(r);
					if (r >= j) {
						r++;
					}
					if (v.getNeighbor().contains(v2)
							|| v2.getNeighbor().size() == y) {
						k--;
					} else {
						v.addNeighbor(v2);
					}
				}
			}
		}

		System.out.println("Shortcuts within the group added");

		// add shortcuts between groups ensure that each group does not have more than intraGroup neighbors 
		// TODO: ensure that within a group there are not connections to the same group
		Random r2 = new Random();
		for (int i = 0; i < numGroups; i++) {
			for (int j = 0; j < groupSize; j++) {
				Vertex v = groups.get(i).get(j);
				int temp = v.getNeighbor().size();
				for (int k = 0; k < intraGroup + y - temp; k++) {
					int r = r2.nextInt(numGroups);
					if (r == i || groups.get(i).get(j).getNeighbor().contains(groups.get(r).get(j))
							|| groups.get(r).get(j).getNeighbor().size() == intraGroup + y) {
						k--;
					} else {
						v.addNeighbor(groups.get(r).get(j));
					}
				}
			}
		}

		System.out.println("Shortcuts between the groups added");


		// create a file for this graph
		try {
			FileWriter myWriter = new FileWriter(filename);
			myWriter.write(toString());
			myWriter.close();
			System.out.println("Successfully wrote to the file " + filename);
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

	}

	public String toString() {
		String result = "" + n + "\n";
		for (Vertex v : vertices) {
			result += v.toString();
		}
		return result;
	}
}
