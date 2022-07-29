package sorting;

import java.util.ArrayList;
import java.util.Stack;

public class TopologicalSort {
	
	
	public static void main(String[] args) {
		
		Tree tree = new Tree(6);
		
		tree.addEdge(0, 1, 5);
		tree.addEdge(0, 2, 3);
		tree.addEdge(1, 3, 6);
		tree.addEdge(1, 2, 2);
		tree.addEdge(2, 4, 4);
		tree.addEdge(2, 5, 2);
		tree.addEdge(2, 3, 7);
		tree.addEdge(3, 5, 1);
		tree.addEdge(3, 4, -1);
		tree.addEdge(4, 5, -2);
	 
	    int x = 1;
	    System.out.print("Following are longest distances from source vertex "+ x + " \n" );
	    tree.longestPath(x);

	}
	
	static class Node{
		int vertex;
		int weight;
		
		Node(int _v, int _w){
			vertex = _v;
			weight = _w;
		}
		public int getVertex() {
			return vertex;
		}

		public int getWeight() {
			return weight;
		}

	}
	static class Tree{
		int numNodes;
		ArrayList<ArrayList<Node>> adj ;
		
		Tree(int v){
			numNodes = v;
			adj = new ArrayList<ArrayList<Node>>(v);
			for(int i = 0; i<v; i++) {
				adj.add(new ArrayList<Node>());
			}
		}

		void addEdge(int u, int v, int weight) {
			adj.get(u).add(new Node(v, weight));
		}
		
		void topoSort(int v, boolean[] visited, Stack<Integer> stack) {
			visited[v] = true;
			
			for(Node node : adj.get(v)) {
				if(visited[node.getVertex()] == false) {
					topoSort(node.getVertex(), visited, stack);
				}
			}
			stack.push(v);
		}
		
		void longestPath(int x) {
			Stack<Integer> stack = new Stack<Integer>();
			int dist[] = new int[numNodes];
			for(int i=0; i<numNodes; i++) {
				dist[i] = Integer.MIN_VALUE;
 			}
			dist[x] = 0;
			
			boolean[] visited = new boolean[numNodes];
			for(int i=0; i<numNodes; i++) {
				visited[i] = false;
			}
			
			for(int i=0; i<numNodes; i++) {
				if(visited[i] == false) {
					topoSort(i, visited, stack);
				}
			}
			
			while(stack.isEmpty() == false) {
				int s = stack.pop();
				
				if(dist[s] != Integer.MIN_VALUE) {
					ArrayList<Node> edges = adj.get(s);
					for(Node node : edges) {
						if(dist[node.getVertex()] < dist[s] + node.getWeight() ) {
							dist[node.getVertex()] = dist[s] + node.getWeight();
						}
					}
				}
				

			}
			for(int i =0; i<numNodes; i++)
				if(dist[i] == Integer.MIN_VALUE)
					System.out.println("INF ");
				else
					System.out.println(dist[i] + " ");
			
		}
	}
	
	
	
	

}
