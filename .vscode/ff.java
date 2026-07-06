import java.util.*;
public class ff {
    public static void firstfit(int block[],int process[]){
        int b = block.length;
        int p = process.length;
        int allocation []= new int[p];
        Arrays.fill(allocation,-1);

        for(int i=0;i<p;i++){
            for(int j =0;j<b;j++){
                if(block[j]>=process[i]){
                    allocation[i]=j;
                    block[j]-=process[i];
                    break;
                }

            }
        }
        for(int i =0;i<p;i++){
            System.out.println("Process"+(i+1)+"-> Block"+(allocation[i]));
        }
    }

    public void bf(int proc[],int block[]){
        int p = proc.length;
        int b = block.length;
        int allocate[]= new int[p];
        Arrays.fill(allocate,-1);
        for(int i =0;i<p;i++){
            int best=-1;
            for(int j =0;j<b;j++){
                if(block[j]>=proc[i]){
                    if(best==-1 || block[j]<block[best]){
                        best=j;
                    }
                }
            }
            if(best!=-1){
                allocate[i]=best;
                block[best]-=proc[i];
            }
        }
        for(int i=0;i<p;i++){
            System.out.println("P"+(i+1)+"->Block"+allocate[i]);
        }
    }
    public static void wfit(int block[],int process[]){
        int b = block.length;
        int p = process.length;
        int allocate[] = new int [p];
        Arrays.fill(allocate,-1);
        
        for(int i =0;i<p;i++){
            int worst=-1;
            for(int j =0;j<b;j++){
                if(block[j]>=process[i]){
                    if(worst==-1 || block[j]>block[worst]){
                        worst=j;
                    }
                }
            }

            if(worst!=-1){
                allocate[i]=worst;
                block[worst]-=process[i];
            }
        }

        for(int i = 0; i < p ; i++){
            System.out.println("Process"+(i+1)+"-> Block"+allocate[i]);
        }
    }
}
