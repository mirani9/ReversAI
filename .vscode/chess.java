import java.util.*;

public class chess{

    public static boolean solve(int arr[][],int row){
        if(row==arr.length){
            return true;
        }
        for(int col = 0; col<arr.length;col++){
            if(safe(arr,row,col)){
                arr[row][col]=1;
                if(solve(arr,row+1)){
                    return true;
                }
                arr[row][col]=0;
            }
        }
        return false;
    }

    public static boolean safe(int arr[][],int row,int col){
        for(int i = 0;i<row;i++){
            if(arr[i][col]==1){
                return false;
            }
        }

        for(int i = row,j=col ;i>=0 && j>=0;i--,j--){
            if(arr[i][j]==1){
                return false;
            }
        }

        for(int i =row,j=col ; i>=0 && j< arr.length;i--,j++){
            if(arr[i][j]==1){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("Enter size of chessboard:");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int arr[][] = new int[n][n];
        for(int i =0;i<n;i++){
            for(int j=0;j<n;j++){
                arr[i][j]=0;
            }
        }
        solve(arr,0);
        System.out.println("Solution for "+n+" queens problem:");
        for(int i =0;i<n;i++){
            for(int j=0;j<n;j++){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
    }
}