package main;
import Graph.*;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("hello worrrrld!");

        Graphh G = new Graphh("soc-sign-Slashdot090221.txt");
        Cycle finder = new Cycle(G);
        if (finder.hasCycle()) {
            for (int v : finder.cycle()) {
                System.out.print(v + " ");
            }
            System.out.println();
        } else {
            System.out.println("Graph is acyclic");
        }
    }
}
