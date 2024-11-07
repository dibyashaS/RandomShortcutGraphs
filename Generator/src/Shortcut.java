import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Shortcut {
    private List<Vertex> vertices = new ArrayList<>();
    private List<List<Vertex>> groups = new ArrayList<>();
    private int n;
    private int groupSize;
    private int intraGroupLinks;
    private int interGroupLinks;

    public Shortcut(int n, int intraGroupLinks, int groupSize, int interGroupLinks, String filename) {
        this.n = n;
        this.groupSize = groupSize;
        this.intraGroupLinks = intraGroupLinks;
        this.interGroupLinks = interGroupLinks;

        // Initialize vertices
        for (int i = 0; i < n; i++) {
            vertices.add(new Vertex(i));
        }

        // Create groups
        for (int i = 0; i < n; i += groupSize) {
            List<Vertex> group = new ArrayList<>();
            for (int j = i; j < Math.min(i + groupSize, n); j++) {
                group.add(vertices.get(j));
            }
            groups.add(group);
        }

        int numGroups = n / groupSize;
        Random random = new Random();

        // Add intra-group links
        for (int i = 0; i < numGroups; i++) {
            List<Vertex> group = groups.get(i);
            for (Vertex v : group) {
                while (v.getNeighbor().size() < intraGroupLinks) {
                    Vertex neighbor = group.get(random.nextInt(groupSize));
                    if (neighbor != v && !v.getNeighbor().contains(neighbor)) {
                        v.addNeighbor(neighbor);
                        neighbor.addNeighbor(v);
                    }
                }
            }
        }

        System.out.println("Intra-group links added.");

        // Add inter-group links
        for (int i = 0; i < numGroups; i++) {
            for (Vertex v : groups.get(i)) {
                int linksToAdd = interGroupLinks - v.getNeighbor().size();
                List<Vertex> availableVertices = new LinkedList<>();
                
                // Collect vertices from other groups with space for more links
                for (int j = 0; j < numGroups; j++) {
                    if (j != i) {
                        for (Vertex other : groups.get(j)) {
                            if (other.getNeighbor().size() < intraGroupLinks + interGroupLinks && !v.getNeighbor().contains(other)) {
                                availableVertices.add(other);
                            }
                        }
                    }
                }

                while (linksToAdd > 0 && !availableVertices.isEmpty()) {
                    Vertex neighbor = availableVertices.remove(random.nextInt(availableVertices.size()));
                    v.addNeighbor(neighbor);
                    neighbor.addNeighbor(v);
                    linksToAdd--;
                }

                // If we run out of options to create links, break out early
                if (linksToAdd > 0) {
                    System.out.println("Could not complete inter-group links for some vertices. Try different parameters.");
                    return;
                }
            }
        }

        System.out.println("Inter-group links added.");

        // Write graph to file
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

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(n + "\n");
        for (Vertex v : vertices) {
            result.append(v.toString());
        }
        return result.toString();
    }
}

class Vertex {
    private int index;
    private List<Vertex> neighbors;

    public Vertex(int index) {
        this.index = index;
        this.neighbors = new ArrayList<>();
    }

    public int getIndex() {
        return index;
    }

    public List<Vertex> getNeighbor() {
        return neighbors;
    }

    public void addNeighbor(Vertex v) {
        neighbors.add(v);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(index).append(": ");
        for (Vertex v : neighbors) {
            sb.append(v.getIndex()).append(" ");
        }
        sb.append("\n");
        return sb.toString();
    }
}


