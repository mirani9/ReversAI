import java.util.*;
class node implements Comparable<node>{
    int v;
    int h;
    node(int v,int h){
        this.v=v;
        this.h=h;
    }
    public int compareTo(node o){
        return this.h-o.h;
    }
}
public class gbfs {
    public static void greedy(int arr[][],int h[],int src,int dest){
        int n = arr.length;
        boolean visited[]=new boolean[n];
        PriorityQueue<node> pq = new PriorityQueue<>();
        pq.add(new node(src,h[src]));
        while(!pq.isEmpty()){
            node curr=pq.poll();
            if(curr.v==dest){
                System.out.println("Reached destination: "+curr.v);
                return;
            }
            if(visited[curr.v]){
                continue;
            }
            visited[curr.v]=true;
            System.out.println("Visiting node: "+curr.v);
            for(int i =0;i<n;i++){
                if(!visited[i] && arr[curr.v][i]==1){
                    pq.add(new node(i,h[i]));
                }
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of vertices: ");
        int n = sc.nextInt();
        System.out.println("Enter adjacency matrix: ");
        int adj[][]=new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                adj[i][j]=sc.nextInt();
            }
        }
        System.out.println("Enter heuristic values: ");
        int h[]=new int[n];
        for(int i=0;i<n;i++){
            h[i]=sc.nextInt();
        }
        System.out.println("Enter source and destination: ");
        int src = sc.nextInt();
        int dest = sc.nextInt();
        greedy(adj,h,src,dest);
    }
}
