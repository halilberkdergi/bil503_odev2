package Graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Graphh {
    public Set<Integer> nodes = new HashSet<>();
    public Map<Integer, Set<Edge>> edges = new HashMap<>();
    public Map<Integer, Boolean> marked = new HashMap<>();

    private Boolean _reverse = false;
    private String FileName ;


    public Graphh()
    {
        this.nodes = new HashSet<>();
        this.edges = new HashMap<>();
        this.marked = new HashMap<>();

    }


    public Graphh(String fileName) throws FileNotFoundException {
        this.FileName = fileName;

        readFile(fileName);


    }

    Graphh (String fileName, Boolean Reverse)throws FileNotFoundException {
        this._reverse = Reverse;

        readFile(fileName);

    }




    private void readFile(String FileName) throws FileNotFoundException
    {
        int n1=0,n2=0,weight=0;
        Scanner scanner = new Scanner(new File(FileName));

        Boolean notFounded=true;
        while (scanner.hasNext() && notFounded) {
            if (scanner.hasNextInt()) {
                System.out.println("Node size: " + scanner.nextInt());

                while (scanner.hasNext()) {
                    if (scanner.hasNextInt()) {
                        System.out.println("Edge size: " + scanner.nextInt());
                        notFounded=false;
                        break;

                    }
                    else
                        scanner.next();

                }
            }
            else
                scanner.next();
        }
        while (scanner.hasNext()) {
            if (scanner.hasNextInt()) {
                n1 = scanner.nextInt();
                this.nodes.add(n1);

                while (scanner.hasNext()) {
                    if (scanner.hasNextInt()) {
                        n2 = scanner.nextInt();
                        this.nodes.add(n2);
                        while (scanner.hasNext()) {
                            if (scanner.hasNextInt()) {
                                weight = scanner.nextInt();
                                Edge e = new Edge(n1,n2,weight);
                                Edge e2 = new Edge(n2,n1,weight);
                                addEdge(n2, e);
                                addEdge(n2, e2);
                                break;
                            } else
                                scanner.next();
                        }
                        break;
                    }
                    else
                        scanner.next();

                }
            }
            else
                scanner.next();
        }

    }

    public void addEdge(Integer n1, Edge e) {
        if (edges.containsKey(n1)) {
            edges.get(n1).add(e);
        } else {
            Set<Edge> to = new HashSet<>();
            to.add(e);
            edges.put(n1, to);
        }
    }


    interface Visitor {
        void visit(int node);
    }



}
