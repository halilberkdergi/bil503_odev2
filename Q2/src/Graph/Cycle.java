package Graph;

import java.util.*;

public class Cycle {
    private Set<Integer> marked;
    private Map<Integer, Set<Integer>> edgeTo;
    private Stack<Integer> cycle;

    /**
     * Determines whether the undirected graph {@code G} has a cycle and,
     * if so, finds such a cycle.
     *
     * @param G the undirected graph
     */
    public Cycle(Graphh G) {
        // need special case to identify parallel edge as a cycle
        if (hasParallelEdges(G)) return;

        // don't need special case to identify self-loop as a cycle
        // if (hasSelfLoop(G)) return;

        marked = new HashSet<>();
        edgeTo = new HashMap<>();
        for (int node : G.nodes)
            if (!marked.contains(node))
                dfs(G, -1, node);
    }


    // does this graph have a self loop?
    // side effect: initialize cycle to be self loop
    private boolean hasSelfLoop(Graphh G) {
        for (int v : G.nodes) {
            for (Edge e : G.edges.get(v)) {
                if (v == e.other(v)) {
                    cycle = new Stack<Integer>();
                    cycle.push(v);
                    cycle.push(v);
                    return true;
                }
            }
        }
        return false;
    }

    // does this graph have two parallel edges?
    // side effect: initialize cycle to be two parallel edges
    private boolean hasParallelEdges(Graphh G) {
        marked = new HashSet<>();

        for (int v: G.nodes) {

            // check for parallel edges incident to v
            for (Edge e : G.edges.get(v)) {
                int w = e.other(v);
                if (marked.contains(w)) {
                    cycle = new Stack<Integer>();
                    cycle.push(v);
                    cycle.push(w);
                    cycle.push(v);
                    return true;
                }
                marked.add(w);
            }

            marked = new HashSet<>();

        }
        return false;
    }

    /**
     * Returns true if the graph {@code G} has a cycle.
     *
     * @return {@code true} if the graph has a cycle; {@code false} otherwise
     */
    public boolean hasCycle() {
        return cycle != null;
    }

    /**
     * Returns a cycle in the graph {@code G}.
     * @return a cycle if the graph {@code G} has a cycle,
     *         and {@code null} otherwise
     */
    public Iterable<Integer> cycle() {
        return cycle;
    }

    private void dfs(Graphh G, int u, int v) {
        marked.add(v);
        for (Edge e : G.edges.get(v)) {
            int w = e.other(v);

            // short circuit if cycle already found
            if (cycle != null) return;

            if (!marked.contains(w)) {
                if (edgeTo.containsKey(w)) {
                    edgeTo.get(w).add(v);
                } else {
                    Set<Integer> to = new HashSet<>();
                    to.add(v);
                    edgeTo.put(w, to);
                }
                dfs(G, v, w);
            }

            // check for cycle (but disregard reverse of edge leading to v)
            else if (w != u) {
                cycle = new Stack<Integer>();
                for (int x : edgeTo.get(v)) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }
    }

    /*
    public static void main(String[] args) {
        In in = new In(args[0]);
        Graph G = new Graph(in);
        Cycle finder = new Cycle(G);
        if (finder.hasCycle()) {
            for (int v : finder.cycle()) {
                StdOut.print(v + " ");
            }
            StdOut.println();
        }
        else {
            StdOut.println("Graph is acyclic");
        }
    }*/


}
