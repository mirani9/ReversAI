import java.util.*;

public class dfs {
    static boolean visited[];
    public static void dfss(ArrayList<Integer> arr[],int s){
        visited[s]=true;
        System.out.print(s+" ");
        for(int i =0;i<arr[s].size();i++){
            int neigh = arr[s].get(i);
            if(!visited[neigh]){
                dfss(arr,neigh);
            }
        }
      }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of vertices:");
        int v = sc.nextInt(); 
        System.out.println("Enter number of edges:");
        int e = sc.nextInt();
        ArrayList<Integer>arr[] = new ArrayList [v];
        //int arr[] = new int[v];
        //ArrayList<Integer> neigh;
        for(int i =0;i<v;i++){
            arr[i]=new ArrayList<>();
        }
        System.out.println("Enter edges:");
        for(int i = 0;i<e;i++){
            int p = sc.nextInt();
            int q =sc.nextInt();
            arr[p].add(q);
            arr[q].add(p);
        }
        System.out.println("Enter source vertex:");
        int s = sc.nextInt();   
        visited = new boolean[v];
        dfss(arr,s);
    }
}
