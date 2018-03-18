package map;

import java.util.*;
import java.util.Map.Entry;

enum colour {
	WHITE, GRAY, BLACK
}

class Node {
	int V;
	colour c;
	int d;
	int f;
	Node p;
	List<Node> Adj;

	public Node(int v) {
		V = v;
		c = colour.WHITE;
		d = 0;
		f = 0;
		p = null;
		Adj = new LinkedList<>();
	}

	@Override
	public boolean equals(Object arg) {
		if (!(arg instanceof Node)) {
			return false;
		}
		Node t = (Node) arg;
		return V == t.V;
	}

	@Override
	public int hashCode() {
		return 17 * 31 + V + d + f;
	}

	@Override
	public String toString() {
		return "Vertices: " + V + "\n" + "colour: " + c + "\n" + "d: " + d + "\n" + "f: " + f+"\n"+
				"size: "+Adj.size();
	}
}

public class Graph {

	static LinkedList<Node> Sort = new LinkedList<>();

	static boolean addVertices(List<Node> g, Node V) {
		if (g.contains(V)) {
			return false;
		}
		g.add(V);
		return true;
	}

	static boolean addEdge(List<Node> g, Node U, Node V) {
		int i = g.indexOf(U);
		int j = g.indexOf(V);
		if (i != -1 && j != -1) {
			return g.get(i).Adj.add(g.get(j));
		}
		return Boolean.FALSE;
	}

	static void DFS(List<Node> g) {
		int time = 0;
		for (Node N : g) {
			if (N.c == colour.WHITE)
				time = DFS_Util(g, N, time);
		}
	}

	private static int DFS_Util(List<Node> g, Node n, int time) {
		n.c = colour.GRAY;
		time++;
		n.d = time;
		for (Node N : n.Adj) {
			if (N.c == colour.GRAY) {
				System.out.println("Back Edge source: " + n.V + "   " + "dest: " + N.V);
			}
			if (N.c == colour.BLACK) {
				System.out.println("Cross Edge source: " + n.V + "   " + "dest: " + N.V);
			}
			if (N.c == colour.WHITE) {
				N.p = n;
				time = DFS_Util(g, N, time);
			}

		}
		n.c = colour.BLACK;
		time++;
		n.f = time;
		Sort.addFirst(n);
		System.out.println(n);
		return time;
	}

	static List<Node> MapTranspose(List<Node> M) {
		List<Node> g = new ArrayList<>();
		for (Node node : M) {
			g.add(new Node(node.V));
		}
		for (Node node : M) {
			for (Node adj : node.Adj) {
				//int i = g.indexOf(adj);
				addEdge(g, adj, node);
				//g.get(i).Adj.add(node);
			}
		}
		return g;
	}

	public static void main(String[] args) {

		List<Node> g = new ArrayList<>();
		addVertices(g, new Node(1));
		addVertices(g, new Node(2));
		addVertices(g, new Node(3));
		addVertices(g, new Node(4));
		addVertices(g, new Node(5));
		addVertices(g, new Node(6));

		addEdge(g, new Node(1), new Node(2));
		addEdge(g, new Node(1), new Node(4));
		addEdge(g, new Node(2), new Node(5));
		addEdge(g, new Node(3), new Node(6));
		addEdge(g, new Node(3), new Node(5));
		addEdge(g, new Node(4), new Node(2));
		addEdge(g, new Node(5), new Node(4));
		addEdge(g, new Node(6), new Node(6));

		DFS(g);
		List<Node> gT = MapTranspose(g);
		System.out.println("##################################################################");
		DFS(gT);
		System.out.println("##################################################################");
		for (Node node : Sort) {
			System.out.println(node);
		}
	}

}
