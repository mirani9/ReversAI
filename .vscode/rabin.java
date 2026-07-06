import java.util.*;
public class rabin {
    static final int d =256; 
    static final int q = 101;

    public static void search(String pat,String txt){
        int m = pat.length();
        int n = txt.length();
        int p=0;
        int t=0;
        int h=1;

        for(int i =0;i<m-1;i++){
            h=(h*d)%q;
        }

        for(int i= 0; i<m;i++){
            p = (p*d + pat.charAt(i))%q;
            t = (t*d + txt.charAt(i))%q;
        }
        for(int i =0;i<=n-m;i++){
            if(p==t){
                for(int j =0;j<m;j++){
                    if(txt.charAt(i+j)!=pat.charAt(j)){
                        break;
                    }

                    if(j==m-1){
                        System.out.println("Pattern found at index "+(i));
                    }
                }
            }
            if(i<n-m){
                t=(d*(t-txt.charAt(i)*h)+txt.charAt(i+m))%q;
                if(t<0){
                    t+=q;
                }
            }
            }
        }

        public static void main(String[] args) {
        System.out.println("Enter the string: ");
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        System.out.println("Enter the pattern: ");
        String pattern = sc.nextLine();
        search(pattern, str);
    }


    }
    
    