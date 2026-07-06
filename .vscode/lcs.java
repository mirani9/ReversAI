import java.util.*;
public class lcs {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the first string: ");
        String str1 = sc.nextLine();
        System.out.println("Enter the second string: ");
        String str2 = sc.nextLine();
        int m = str1.length();
        int n = str2.length();
        int dp[][] = new int[m+1][n+1];
        for(int i = 1; i<=m;i++){
            for(int j =1;j<=n;j++){
                if(str1.charAt(i-1)==str2.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1]+1;
                }
                else{
                    dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        System.out.println("Length of longest common subsequence: "+dp[m][n]);
        String ans = "";
        int i =m;
        int j =n;
        while(i>0 && j>0){
            if(str1.charAt(i-1)==str2.charAt(j-1)){
                ans = str1.charAt(i-1) + ans;
            }
            else if(dp[i-1][j]>dp[i][j-1]){
                i--;
            }
            else{
                j--;
            }
            
        }
        System.out.println("Longest common subsequence: " + ans);
    }
}
