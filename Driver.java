import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        SocialNetwork socialNetwork = new SocialNetwork();
        defaultNodes(socialNetwork);
        Scanner scanner = new Scanner(System.in);

        int mainMenuInput;
        do {
            displayMainMenu();
            mainMenuInput = getUserChoice(scanner);
            handleMainMenuSelection(mainMenuInput, socialNetwork, scanner);
        } while (mainMenuInput != 0);

        System.out.println("Exiting the program. Goodbye!");
        scanner.close(); // Close the scanner before exiting
    }

    private static void handleMainMenuSelection(int mainMenuInput, SocialNetwork socialNetwork, Scanner scanner) {
        switch (mainMenuInput) {
            case 1:
                handleCreateGraphMenu(socialNetwork, scanner);
                break;
            case 2:
                handleSearchAPerson(socialNetwork, scanner);
                break;
            case 3:
                System.out.println("GraphViz representation of the Social Network:\n");
                System.out.println(socialNetwork.toGraphviz());
                System.out.println("\n");
                break;
            case 4:
                socialNetwork.displayAllNodes();
                break;
            case 5:
                System.out.print("Enter start node: ");
                String startNode = getUserString(scanner);
                System.out.print("Enter end node: ");
                String endNode = getUserString(scanner);
                socialNetwork.findPath(startNode, endNode);
                break;

            
            case 0:
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

    private static void handleCreateGraphMenu(SocialNetwork socialNetwork, Scanner scanner) {
        boolean createGraphMenu = true;
        while (createGraphMenu) {
            displayCreateGraphMenu();
            int createGraphInput = getUserChoice(scanner);
            switch (createGraphInput) {
                case 1:
                    System.out.print("Enter the name of the Node: ");
                    String nodeName = getUserString(scanner);
                    socialNetwork.makeNode(nodeName);
                    break;
                case 2:
                    System.out.print("Enter the name of the Node to remove: ");
                    String nodeNameToRemove = getUserString(scanner);
                    socialNetwork.deleteNode(nodeNameToRemove);
                    break;
                case 3:
                    System.out.print("Enter Node 1 to connect: ");
                    String name1 = getUserString(scanner);
                    System.out.print("Enter Node 2 to connect: ");
                    String name2 = getUserString(scanner);
                    socialNetwork.edgeConnect(name1, name2);
                    break;
                case 4:
                    System.out.print("Enter Node 1 to remove edge: ");
                    String name12 = getUserString(scanner);
                    System.out.print("Enter Node 2 remove edge: ");
                    String name22 = getUserString(scanner);
                    socialNetwork.deleteEdge(name12, name22);
                    break;
                case 5:
                    System.out.println("Returning to Main Menu");
                    createGraphMenu = false; // Exit the create graph menu
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static void handleSearchAPerson(SocialNetwork socialNetwork, Scanner scanner) {
        do {
            System.out.print("Enter name to view its Social Network (or '0' to return to Main Menu): ");
            String nodeName = getUserString(scanner);
            if (nodeName.equals("0")) {
                break; // Return to Main Menu
            } else {
                System.out.println("Social Network of " + nodeName + ":");
                System.out.println(socialNetwork.getConnections(nodeName));
            }
        } while (true);
    }

    private static void displayMainMenu() {
        System.out.println("\n===== Social Network Application =====");
        System.out.println("1. Create Graph");
        System.out.println("2. Search for a Person's Social Network");
        System.out.println("3. Visualize the Social Network");
        System.out.println("4. View all Social Network Nodes");
        System.out.println("5. Find Path from Node to Node");
        System.out.println("0. Exit");
    }

    private static int getUserChoice(Scanner scanner) {
        System.out.print("Enter your choice: ");
        while (!scanner.hasNextInt()) {
            scanner.next(); // Clear invalid input
            System.out.print("Invalid input. Please enter a valid choice: ");
        }
        int choice = scanner.nextInt();
        scanner.nextLine(); // Clear the newline character from the input buffer
        return choice;
    }

    private static String getUserString(Scanner scanner) {
        return scanner.nextLine();
    }

    private static void displayCreateGraphMenu() {
        System.out.println("\n===== Create Graph Menu =====");
        System.out.println("1. Add a Node");
        System.out.println("2. Remove a Node");
        System.out.println("3. Add an Edge");
        System.out.println("4. Remove an Edge");
        System.out.println("5. Return to Main Menu");
    }
    private static void defaultNodes(SocialNetwork socialNetwork) {
        // 1. Less People Version
        // Adding nodes
        socialNetwork.makeNode("Alice");
        socialNetwork.makeNode("Bob");
        socialNetwork.makeNode("Charlie");
 
        socialNetwork.makeNode("Eve");
        socialNetwork.makeNode("Frank");
        socialNetwork.makeNode("Grace");
    

        // Adding connections
        socialNetwork.edgeConnect("Alice", "Bob");
        socialNetwork.edgeConnect("Alice", "Charlie");
        socialNetwork.edgeConnect("Bob", "Charlie");
        socialNetwork.edgeConnect("Bob", "Eve");
        socialNetwork.edgeConnect("Eve", "Frank");
        socialNetwork.edgeConnect("Eve", "Grace");
        socialNetwork.edgeConnect("Frank", "Grace");

        /*
        // 2. More People Version
        // Adding nodes
        socialNetwork.makeNode("Alice");
        socialNetwork.makeNode("Bob");
        socialNetwork.makeNode("Charlie");
        socialNetwork.makeNode("David");
        socialNetwork.makeNode("Eve");
        socialNetwork.makeNode("Frank");
        socialNetwork.makeNode("Grace");
        socialNetwork.makeNode("Hannah");
        socialNetwork.makeNode("Isaac");
        socialNetwork.makeNode("Jack");
        socialNetwork.makeNode("Kelly");
        socialNetwork.makeNode("Liam");
        socialNetwork.makeNode("Mia");
        socialNetwork.makeNode("Nora");
        socialNetwork.makeNode("Oliver");
        socialNetwork.makeNode("Paul");
        socialNetwork.makeNode("Quinn");
        socialNetwork.makeNode("Riley");
        socialNetwork.makeNode("Sarah");
        socialNetwork.makeNode("Tom");

        // Adding connections
        socialNetwork.edgeConnect("Alice", "Bob");
        socialNetwork.edgeConnect("Alice", "Charlie");
        socialNetwork.edgeConnect("Alice", "David");
        socialNetwork.edgeConnect("Bob", "Charlie");
        socialNetwork.edgeConnect("Bob", "Eve");
        socialNetwork.edgeConnect("Charlie", "David");
        socialNetwork.edgeConnect("David", "Eve");
        socialNetwork.edgeConnect("Eve", "Frank");
        socialNetwork.edgeConnect("Eve", "Grace");
        socialNetwork.edgeConnect("Frank", "Grace");
        socialNetwork.edgeConnect("Grace", "Hannah");
        socialNetwork.edgeConnect("Hannah", "Isaac");
        socialNetwork.edgeConnect("Isaac", "Jack");
        socialNetwork.edgeConnect("Isaac", "Kelly");
        socialNetwork.edgeConnect("Jack", "Kelly");
        socialNetwork.edgeConnect("Kelly", "Liam");
        socialNetwork.edgeConnect("Liam", "Mia");
        socialNetwork.edgeConnect("Liam", "Nora");
        socialNetwork.edgeConnect("Mia", "Nora");
        socialNetwork.edgeConnect("Mia", "Alice");
        socialNetwork.edgeConnect("Mia", "Grace");
        socialNetwork.edgeConnect("Mia", "Oliver");
        socialNetwork.edgeConnect("Mia", "Charlie");
        socialNetwork.edgeConnect("Paul", "Charlie");
        socialNetwork.edgeConnect("Mia", "Tom");
        socialNetwork.edgeConnect("Riley", "Sarah");
        socialNetwork.edgeConnect("Mia", "Quinn");
        socialNetwork.edgeConnect("Bob", "David");
        socialNetwork.edgeConnect("Alice", "Oliver");
        socialNetwork.edgeConnect("Frank", "Liam");
        socialNetwork.edgeConnect("Kelly", "Quinn");
        socialNetwork.edgeConnect("Hannah", "Eve");
        socialNetwork.edgeConnect("Charlie", "Grace");
        socialNetwork.edgeConnect("Mia", "Paul");
        socialNetwork.edgeConnect("Isaac", "Kelly");
        */

    }

}
