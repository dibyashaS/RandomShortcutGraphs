import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class Diameter {
	private File file;
	private int[][] distance;
	private int[][] usage;
	private List<LinkedList<Integer>> vertices = new ArrayList<>();

	public Diameter(File file) throws FileNotFoundException {
		this.file = file;
		int n = this.getSize();
		this.distance = new int[n][n];
		this.usage = new int[n][n];
		for (int i = 0; i < n; i++) {
			Scanner sc = new Scanner(getLine(i + 1));
			LinkedList<Integer> neighbors = new LinkedList<>();
			while (sc.hasNextInt()) {
				neighbors.add(sc.nextInt());
			}
			vertices.add(neighbors);
			sc.close();
		}

		// set the 2d-array data to really high numbers so that we will change the data
		// when there is shorter path
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				distance[r][c] = Integer.MAX_VALUE;
			}
		}
		// using Dijkstra's algorithm to get the shortest path between all the nodes
		for (int i = 0; i < n; i++) {
			Queue<Integer> queue = new LinkedList<>();
			Set<Integer> visited = new HashSet<>();
			queue.add(i);
			distance[i][i] = 0;
			while (queue.size() > 0) {
				int v = queue.poll();

				if (visited.contains(v)) {
					continue;
				} else {
					visited.add(v);
					queue.addAll(vertices.get(v));
				}
				for (Integer neighbor : vertices.get(v)) {
					int temp = distance[i][v] + 1;
					if (distance[i][neighbor] > temp) {
						distance[i][neighbor] = temp;
					}
				}
			}

		}

		// getting the usage data table using the helper method below
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				Usage(i, j);
			}
		}
	}

	private void Usage(int i, int j) {
		if (i == j) {
			usage[i][j] = 0;
		} else {
			// if i and j are neighbor node, add 1 to the usage
			if (vertices.get(i).contains(j)) {
				usage[i][j]++;
			} else {
				for (Integer neighbor : vertices.get(i)) {
					if (1 + distance[neighbor][j] == distance[i][j]) {
						// we check if there is a neighbor node that connect those 2 node, if there is
						// we increase the usage by 1
						usage[i][neighbor]++;
						if (distance[neighbor][j] == 1) {
							usage[neighbor][j]++;
						} else {
							// call recursive method
							Usage(neighbor, j);
						}
					}
				}
			}
		}
	}

	public String UsageToString() {
		String result = "";
		int n = usage.length;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				result += usage[i][j] + " ";
			}
			result += "\n";
		}
		return result;
	}

	public int[][] getDistance() {
		return distance;
	}

	public int[][] getUsage() {
		return usage;
	}

	public int getSize() throws FileNotFoundException {
		Scanner sc = new Scanner(file);
		int size = sc.nextInt();
		sc.close();
		return size;
	}

	private String getLine(int i) throws FileNotFoundException {
		Scanner sc = new Scanner(file);
		String line = "";
		for (int j = 0; j <= i; j++) {
			line = sc.nextLine();
		}
		sc.close();
		return line;
	}

	public String toString() {
		String result = "";
		int n = distance.length;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				result += distance[i][j] + " ";
			}
			result += "\r";
		}
		return result;
	}

	public int getDiameter() {
		int n = distance.length;
		int max = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (max < distance[i][j])
					max = distance[i][j];
			}
		}
		return max;
	}

	public double getAverageDistance() {
		int n = distance.length;
		double sum = 0;
		double count = 0;
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				sum += distance[i][j];

				count++;
			}
		}
		return sum / count;
	}

	public List<LinkedList<Integer>> getVertices() {
		return vertices;
	}
}
