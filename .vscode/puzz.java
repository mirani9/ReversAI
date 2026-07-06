import java.util.*;

class Node{
    int x;
    int y;
    int cost,level;
    int mat[][];
    Node(int x,int y,int level,int mat[][]){
        int newmat[][]= mat.clone();
         for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                newmat[i][j]=mat[i][j];
            }
        }

        this.x=x;
        this.y=y;
        this.level=level;
        this.mat=newmat;
    }
}
public class puzz {
    public static int costs(int mat[][]){
        int cost=0;
        int k = 1;
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                if(mat[i][j]!=0 && mat[i][j]!=k){
                    cost++;
                }
                k++;
            }
        }
        return cost;
    }

    public void solve(int mat[][],int x,int y){
            Node root = new Node(x,y,0,mat);
            PriorityQueue<Node> pq = new PriorityQueue<>((a,b) -> (a.level + a.cost) - (b.level + b.cost));
            pq.add(root);

            int dr[][]= new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
            while(!pq.isEmpty()){
                Node curr = pq.poll();
                while(curr.cost==0){
                    System.out.println("Solved!!!!");
                    return;
                }

                for(int i = 0;i<4;i++){
                    int newX = curr.x + dr[i][j];
                    int newY = curr.y + dr[i][j];
                    if(newX>=0 && newX<=4 && newY>=0 && newY<=4){
                        int newmat[][] = curr.mat.clone();
                        for(int j = 0; j<4; j++){
                            int temp = newmat[curr.x][curr.y];
                        }
                    }
                }
            }
        }
    public static void main(String[] args) {
        int x,y;
        int mat[][] = new int[4][4];
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the 4*4 matrix: ");
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                mat[i][j]=sc.nextInt();
                if(mat[i][j]==0){
                    x=i;
                    y=j;
                }
            }
        }

        
    }
}
