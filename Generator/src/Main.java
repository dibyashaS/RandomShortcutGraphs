public class Shortcut {
    // Your existing Shortcut code

    public static void main(String[] args) {
        // Example parameters for testing
        int n = 100;            // Total number of vertices
        int intraGroupLinks = 3; // Number of intra-group links per vertex
        int groupSize = 10;      // Size of each group
        int interGroupLinks = 2; // Number of inter-group links per vertex
        String filename = "output.txt"; // Output file name

        // Create an instance of Shortcut
        Shortcut shortcut = new Shortcut(n, intraGroupLinks, groupSize, interGroupLinks, filename);
    }
}
