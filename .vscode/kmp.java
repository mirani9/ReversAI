import java.util.*;
public class kmp{

    public static void calc(String pat, int lps[]){
        int len=0;
        int i =1;
        lps[0]=0;
        while(i<pat.length()){
            if(pat.charAt(i)==pat.charAt(len)){
                len++;
                lps[i]=len;
                i++;
            }
            else{
                if(len!=0){
                    len=lps[len-1];
                }
                else{
                    len=0;
                    lps[i]=len;
                    i++;
                }
            }
        }
    }

    public static void lps(String str, String pat){
        int i = 0;
        int j =0;
        int m = str.length();
        int n = pat.length();
        int lps[] = new int[n];
        calc(pat,lps);
        while(i<m){
            if(str.charAt(i)==pat.charAt(j)){
                i++;
                j++;
            }
            if(j==n){
                System.out.println("soln found at index : "+(i-j));
                j=lps[j-1];
            }
            else if(i<m && str.charAt(i)!=pat.charAt(j)){
                if(j!=0){
                j=lps[j-1];}
                else i++;
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the string: ");
        String str = sc.nextLine();
        System.out.println("Enter the pattern: ");
        String pat = sc.nextLine();
        int n = str.length();
        int m = pat.length();
        int lps[] = new int[m];
        lps(str,pat);
    }
}