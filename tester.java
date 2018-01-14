
public class tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DirectedGraph<String> a = new DirectedGraph<String>();
		a.addVertex("a");
		a.addVertex("b");
		a.addVertex("c");
		a.addVertex("d");
		a.addVertex("e");
		a.addVertex("f");
		a.addVertex("g");
		
		a.addEdge("a", "b");
		a.addEdge("a", "c");
		a.addEdge("a", "d");
		a.addEdge("a", "e");
		a.addEdge("a", "f");
		
		System.out.println(a);
		for(String x: a.getAllVertices()){
			System.out.println(x);
		}
		for(String x: a.getNeighbors("a")){
			System.out.println(x);
		}
		a.removeEdge("a","b");
		System.out.println(a);
		
		
		

	}

}
