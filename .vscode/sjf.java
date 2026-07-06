import java.util.*;
public class sjf {
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
        for(int i=0;i<n-1;i++){
            for(int j=0;j<n-i-1;j++){
                if(bt[j]>bt[j+1]){
                    int temp=bt[j];
                    bt[j]=bt[j+1];
                    bt[j+1]=temp;
                    temp=pid[j];
                    pid[j]=pid[j+1];
                    pid[j+1]=temp;
                }
            }
        }
        int ct[]=new int[n];
        int wt[]=new int[n];
        int tat[]=new int[n];
        ct[0]=bt[0];
        for(int i=1;i<n;i++){
            ct[i]=ct[i-1]+bt[i];
        }
        for(int i=0;i<n;i++){
            tat[i]=ct[i];
            wt[i]=tat[i]-bt[i];
        }
        System.out.println("PID\tBT\tCT\tTAT\tWT");
        for(int i=0;i<n;i++){
            System.out.println(pid[i]+"\t"+bt[i]+"\t"+ct[i]+"\t"+tat[i]+"\t"+wt[i]);
        }

    }
}
