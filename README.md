# SocialNetworkGraph

## Visualization of the network.
> Copy and Paste code into dreampuf.github.io/GraphvizOnline

![alt text](https://github.com/BryanTheLai/SocialNetworkGraph/blob/main/Images/graphviz-Social-Network-Graph.png?raw=true)

## Breath First Search to find path of Jack and Oliver.
> Breadth-first search is an algorithm for searching a tree data structure for a node that satisfies a given property. It starts at the tree root and explores all nodes at the present depth prior to moving on to the nodes at the next depth level.

![alt text](https://github.com/BryanTheLai/SocialNetworkGraph/blob/main/Images/bfs.png?raw=true)

> Pseudocode
 ```
BFS(graph, startNode):
    // Create an empty queue and mark startNode as visited
    queue = createEmptyQueue()
    mark startNode as visited
    enqueue startNode into the queue
    while queue is not empty:
        current = dequeue from the queue
        process current node
        // Explore neighbors of the current node
        for each neighbor of current:
            if neighbor is not visited:
                mark neighbor as visited
                enqueue neighbor into the queue
```

