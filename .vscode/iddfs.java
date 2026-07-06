import java.util.*;
public class iddfs{
    public static boolean dfs(ArrayList<Integer> graph[], int source, int dest, int depth){
        if(source==dest){
            return true;
        }
        if(depth<=0){
            return false;
        }
        for(int i=0;i<graph[source].size();i++){
            int neigh=graph[source].get(i);
            if(dfs(graph,neigh,dest,depth-1)){
            return true;
        }
        }
        return false;
    }

    public static void Iddfs(ArrayList<Integer> graph[], int source, int dest, int depth){
        
        for(int i=0;i<=depth;i++){
        
        if(dfs(graph,source,dest,i)){
            System.out.println("Found: "+i);
            return;
            }
         else{
            System.out.println("Not Found: "+i);
            }
        }   
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int v = 8;
        ArrayList<Integer> graph[] = new ArrayList[v];
        for(int i = 0;i<v;i++){
            graph[i]=new ArrayList<>();
        }
        graph[0].add(1);
        graph[0].add(2);
        graph[1].add(3);
        graph[1].add(4);
        graph[2].add(5);
        graph[2].add(6);
        graph[4].add(7);
        System.out.println("Enter source and destination: ");
        int source=sc.nextInt();
        int dest=sc.nextInt();
        System.out.println("Enter depth: ");
        int depth=sc.nextInt();
        Iddfs(graph,source,dest,depth);
    }
}