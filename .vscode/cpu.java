import java.util.*;
public class cpu {
    public static void fcfs(int bt[]){
        int n = bt.length;
        int at[] = new int[n];
        for(int i=0;i<n;i++){
            at[i]=0;
        }
        int ct[] = new int[n];
        int tat[] = new int[n];
        int wt[] = new int[n];
        ct[0]=bt[0];
        for(int i =1;i<n;i++){
            if(ct[i-1]<at[i]){
                ct[i]=at[i]+bt[i];
            }
            else{
                ct[i]=ct[i-1]+bt[i];
            }
        }
        tat[0]=ct[0]-at[0];
        wt[0]=tat[0]-bt[0];
        for(int i=1;i<n;i++){
            tat[i]=ct[i]-at[i];
            wt[i]=tat[i]-bt[i];
        }
        System.out.println("Process\tAT\tBT\tCT\tTAT\tWT");
        for(int i=0;i<n;i++){
            System.out.println((i+1)+"\t"+at[i]+"\t"+bt[i]+"\t"+ct[i]+"\t"+tat[i]+"\t"+wt[i]);
        }
    }

    public static void sjf(int bt[]){
        int n = bt.length;
        int at[] = new int[n];
        for(int i=0;i<n;i++){
            at[i]=0;
        }
        int ct[] = new int[n];
        int tat[] = new int[n];
        int wt[] = new int[n];
        boolean visited[] = new boolean[n];
        int time=0;
        int completed=0;
        while(completed<n){
            int idx=-1;
            int min = Integer.MAX_VALUE;
            for(int i =0;i<n;i++){
                if(at[i]<=time && !visited[i] && bt[i]<min){
                    min = bt[i];idx=i;
                }
            }
            if(idx==-1){
                time++;
            }
            else{
                visited[idx]=true;
                ct[idx]=time+bt[idx];
                time+=bt[idx];
                tat[idx]=ct[idx]-at[idx];
                wt[idx]=tat[idx]-bt[idx];
                completed++;
            }
        }

        System.out.println("Process\tAT\tBT\tCT\tTAT\tWT");
        for(int i=0;i<n;i++){
            System.out.println((i+1)+"\t"+at[i]+"\t"+bt[i]+"\t"+ct[i]+"\t"+tat[i]+"\t"+wt[i]);
        }
    }

    public static void prior(int bt[],int pr[]){
        int n = bt.length;
        int at[] = new int[n];
            for(int i=0;i<n;i++){
                at[i]=0;
            }
            int ct[] = new int[n];
            int tat[] = new int[n];
            int wt[] = new int[n];
            boolean visited[] = new boolean[n];
            int time=0;
            int completed=0;
            while(completed<n){
                int idx=-1;
                int min = Integer.MAX_VALUE;
                for(int i =0;i<n;i++){
                    if(visited[i]==false && at[i]<=time && pr[i]<min){
                        min = pr[i];
                        idx=i;
                    }
                    if(idx==-1){
                        time++;
                    }
                    else{
                        visited[i]=true;
                        ct[i]=time + bt[i];
                        time+=bt[i];
                        completed++;
                    }
                }
            }
            for(int i=0;i<n;i++){
                tat[i]=ct[i]-at[i];
                wt[i]=tat[i]-bt[i];
            }
            System.out.println("Process\tAT\tBT\tPR\tCT\tTAT\tWT");
        for(int i=0;i<n;i++){
         System.out.println((i+1)+"\t"+at[i]+"\t"+bt[i]+"\t"+pr[i]+"\t"+ct[i]+"\t"+tat[i]+"\t"+wt[i]);
        }
    }

    public static void rr(int bt[],int tq){
        int n = bt.length;
        int at[] = new int[n];
        for(int i=0;i<n;i++){
            at[i]=0;
        }
        int ct[] = new int[n];
        int tat[] = new int[n];
        int wt[] = new int[n];
        boolean visited[] = new boolean[n];
        int rem[] = new int[n];
        for(int i=0;i<n;i++){
            rem[i]=bt[i];
        }
        int time=0;
        boolean done;
        int completed=0;
        do{
            done = true;
            for(int i =0;i<n;i++){
                if(rem[i]>0){
                    done = false;
                    if(rem[i]>tq){
                        time+=tq;
                        rem[i]-=tq;
                    }
                    else{time+=rem[i];
                        rem[i]=0;
                        ct[i]=time;
                        completed++;
                    }
                }
            }
        }
        while(!done);
        for(int i=0;i<n;i++){
            tat[i]=ct[i]-at[i];
            wt[i]=tat[i]-bt[i];
        }
        System.out.println("Process\tAT\tBT\tCT\tTAT\tWT");
        for(int i=0;i<n;i++){
         System.out.println((i+1)+"\t"+at[i]+"\t"+bt[i]+"\t"+ct[i]+"\t"+tat[i]+"\t"+wt[i]);
        }
    }
}
