import java.util.*;

class bfs{

    public static void bfs(ArrayList<ArrayList<Integer>> adj,int s){
        boolean visited[] = new boolean[adj.size()];
        Queue<Integer> q = new LinkedList<>();
        visited[s]=true;
        q.add(s);
        while(!q.isEmpty()){
            int u = q.poll();
            System.out.print(u+" ");
            for(int v: adj.get(u)){
                if(!visited[v]){
                    visited[v]=true;
                    q.add(v);
                }
            }
        }
        return;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of vertices:");
        int v = sc.nextInt();
        System.out.println("Enter number of edges:");
        int e = sc.nextInt();
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i = 0;i<v;i++){
            adj.add(new ArrayList<>());
        }
        System.out.println("Enter edges:");
            for(int i =0;i<e;i++){
                int u = sc.nextInt();
                int w = sc.nextInt();
                adj.get(u).add(w);
            }
        System.out.println("Enter source vertex:");
        int s = sc.nextInt();

        bfs(adj,s);
    }
}