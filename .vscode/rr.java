import java.util.*;
public class rr {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of processes: ");
        int n = sc.nextInt();
        int pid[]=new int[n];
        System.out.println("Enter burst time of processes: ");
        int bt[]=new int[n];
        for(int i=0;i<n;i++){
            pid[i]=i+1;
            bt[i]=sc.nextInt();
        }
        System.out.println("Enter time quantum: ");
        int tq = sc.nextInt();
        int ct[]=new int[n];
        int wt[]=new int[n];
        int tat[]=new int[n];
        int rem[]=new int[n];
        for(int i=0;i<n;i++){
            rem[i]=bt[i];
        }
        int t=0;
        for(int i=0;i<n;i++){
            while(rem[t]>0){
                if(rem[i]>tq){
                    t+=tq;
                    rem[i]-=tq;
                }
                else{
                    t+=rem[i];
                    ct[i]=t;
                    rem[i]=0;
                }
            }
        }
    }
}
