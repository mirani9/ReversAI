import java.util.*;

class edge{
    int dest;
    int src;
    int weight;

    edge(int u, int v, int w){
        this.src=u;
        this.dest=v;
        this.weight=w;
    }
}
public class krus{
    static int parent[];

    public static int find(int x){
        if(parent[x]==x){
            return x;
        }
        return find(parent[x]); 
    }

    public static void union(int x,int y){
        int px = find(x);
        int py = find(y);
        if(px!=py){
            parent[px]=py;
        }
    }

    public static void sort(ArrayList<edge> edges,int e){
        for(int i=0;i<e;i++){
            int min = i;
            for(int j=i+1;j<e;j++){
                if(edges.get(j).weight<edges.get(min).weight){
                    min=j;
                }
            }
            edge temp = edges.get(min);
            edges.set(min,edges.get(i));
            edges.set(i,temp); 
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter no. of vertices: ");
        int v = sc.nextInt();
        System.out.println("Enter no. of edges: ");
        int e = sc.nextInt();
        parent = new int[v];
        for(int i=0;i<v;i++){
            parent[i]=i;
        }
        ArrayList<edge> edges = new ArrayList<>();
        for(int i=0;i<e;i++){
            System.out.println("Enter source for edge "+(i+1)+": ");
            int src = sc.nextInt();
            System.out.println("Enter destination for edge "+(i+1)+": ");
            int dest = sc.nextInt();
            System.out.println("Enter weight for edge "+(i+1)+": ");
            int weight = sc.nextInt();
            edge ed = new edge(src, dest, weight);
            edges.add(ed);
        }
        sort(edges,e);
        int totalweight=0;
        for(int i =0;i<e;i++){
            int x=edges.get(i).src;
            int y=edges.get(i).dest;
            if(find(x)!=find(y)){  
                union(x,y);
                totalweight+=edges.get(i).weight;
            }
        }
        System.out.println("The total weight of the minimum spanning tree is: "+totalweight);
    }
}