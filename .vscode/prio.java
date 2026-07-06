import java.util.*;
public class prio {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of processes: ");
        int n = sc.nextInt();
        int pid[]=new int[n];
        System.out.println("Enter burst time of processes: ");
        int bt[]=new int[n];
        System.out.println("Enter priority of processes: ");
        int pri[]=new int[n];
        for(int i=0;i<n;i++){
            pid[i]=i+1;
            bt[i]=sc.nextInt();
            pri[i]=sc.nextInt();
        }
        for(int i =0;i<n-1;i++){
            if(pri[i]>pri[i+1]){
                int temp = pri[i];
                pri[i] = pri[i+1];
                pri[i+1] = temp;
                temp = bt[i];
                bt[i] = bt[i+1];
                bt[i+1] = temp;
            }
        }
        int ct[]=new int[n];
        int wt[]=new int[n];
        int tat[]=new int[n];
        ct[0]=bt[0];
        for(int i =1;i<n;i++){
            ct[i]=ct[i-1]+bt[i];
        }
        for(int i =0;i<n;i++){
            tat[i]=ct[i];
            wt[i]=tat[i]-bt[i];
        }
        System.out.println("PID\tBT\tPRIO\tCT\tTAT\tWT");
        for(int i=0;i<n;i++){
            System.out.println(pid[i]+"\t"+bt[i]+"\t"+pri[i]+"\t"+ct[i]+"\t"+tat[i]+"\t"+wt[i]);
        }

    }
}