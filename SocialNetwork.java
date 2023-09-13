import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Collections;

import java.util.Set;




public class SocialNetwork {
    private Map<String, List<String>> connections;

    public SocialNetwork() {
        this.connections = new HashMap<>();
    }

    // Add a connection between two users
    public void addConnection(String user, String connection) {
        connections.computeIfAbsent(user, k -> new ArrayList<>()).add(connection);
    }

    // Get the connections of a user
    public List<String> getConnections(String user) {
        List<String> userConnections = connections.get(user);
        if (userConnections != null) {
            System.out.println("User exists: " + user);
            System.out.println("User connections: " + userConnections);
        } else {
            System.out.println("User not found: " + user);
        }
        return userConnections != null ? userConnections : new ArrayList<>();
    }


    // Check if two users are connected
    public boolean areConnected(String user1, String user2) {
        List<String> user1Connections = getConnections(user1);
        return user1Connections.contains(user2);
    }

    // Add a new Node (user) to the network
    public void makeNode(String name) {
        connections.put(name, new ArrayList<>());
    }

    // Connect two vertices (users) with an edge
    public void edgeConnect(String name1, String name2) {
        if (!connections.containsKey(name1) || !connections.containsKey(name2)) {
            System.out.println("One or both users not found.");
            return; // Exit the function if one or both users are not found
        }

        List<String> connections1 = connections.get(name1);
        List<String> connections2 = connections.get(name2);

        if (connections1 == null || connections2 == null) {
            System.out.println("One or both users have no connections.");
            return; // Exit the function if one or both users have no connections
        }

        connections1.add(name2);
        connections2.add(name1);
    }


    // Delete an edge between two vertices (users)
    public void deleteEdge(String name1, String name2) {
        if (!connections.containsKey(name1) || !connections.containsKey(name2)) {
            System.out.println("One or both users not found.");
            return; // Exit the function if one or both users are not found
        }

        List<String> connections1 = connections.get(name1);
        List<String> connections2 = connections.get(name2);

        if (connections1 == null || connections2 == null) {
            System.out.println("One or both users have no connections.");
            return; // Exit the function if one or both users have no connections
        }

        connections1.remove(name2);
        connections2.remove(name1);
    }


    // Delete a Node (user) from the network
    public void deleteNode(String name) {
        if (!connections.containsKey(name)) {
            System.out.println("User not found: " + name);
            return; // Exit the function if the user is not found
        }

        List<String> connectionsToRemove = connections.get(name);
        if (connectionsToRemove != null) {
            for (String connection : connectionsToRemove) {
                List<String> connectionList = connections.get(connection);
                if (connectionList != null) {
                    connectionList.remove(name);
                }
            }
        }

        connections.remove(name);
    }


    // Convert the network to a Graphviz representation
    public String toGraphviz() {
        StringBuilder graphvizBuilder = new StringBuilder();
        graphvizBuilder.append("Copy and paste the code into : dreampuf.github.io/GraphvizOnline \n\ngraph theConnections {\n");
        

        HashSet<String> addedEdges = new HashSet<>(); // Sets cannot contain duplicates

        // Add all edges
        for (String user : connections.keySet()) {
            List<String> userConnections = connections.get(user);
            for (String connection : userConnections) {
                String edge1 = user + " -- " + connection;
                String edge2 = connection + " -- " + user;
                // Check if the edge or its reverse has already been added
                if (!addedEdges.contains(edge1) && !addedEdges.contains(edge2)) {
                    graphvizBuilder.append("    ").append(edge1).append(";\n");
                    // Add the edge and its reverse to the set to avoid duplicates
                    addedEdges.add(edge1);
                    addedEdges.add(edge2);
                }
            }
        }

        // Add isolated nodes to the Graphviz output
        for (String node : connections.keySet()) {
            if (!addedEdges.contains(node)) {
                graphvizBuilder.append("    ").append(node).append(";\n");
            }
        }

        graphvizBuilder.append("}");
        return graphvizBuilder.toString();
    }
    
    public List<String> findPath(String start, String end) { // bfs
        List<String> path = new ArrayList<>();
        if (!connections.containsKey(start) || !connections.containsKey(end)) {
            System.out.println("One or both users not found.");
            return path;
        }

        Map<String, String> parentMap = new HashMap<>();
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            List<String> neighbors = connections.get(current);

            if (neighbors != null) {
                for (String neighbor : neighbors) {
                    if (!visited.contains(neighbor)) {
                        queue.add(neighbor);
                        visited.add(neighbor);
                        parentMap.put(neighbor, current);
                    }
                }
            }
        }

        if (!visited.contains(end)) {
            System.out.println("No path found between " + start + " and " + end);
            return path;
        }

        String current = end;
        while (current != null) {
            path.add(current);
            current = parentMap.get(current);
        }
        Collections.reverse(path);

        System.out.println("Path from " + start + " to " + end + ": " + path);
        return path;
    }

    public void displayAllNodes() {
        System.out.println("Nodes in the network: ");
        for (String node : connections.keySet()) {
            System.out.println(node);
        }
    }


}
