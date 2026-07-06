import java.util.*;
public class select {

    public static void select(int arr[],int n){
        for(int i =0;i<n;i++){
            int min = i;
            for(int j = 0;j<n-1;j++){
                if(arr[j]<arr[min]){
                    min=j;
                }
            }
            int temp = arr[min];
            arr[min]=arr[i];
            arr[i]=temp;

            System.out.println("Pass: "+(i+1)+" "+Arrays.toString(arr));
        }
        System.out.println();
    }
    public static void main(String[] args) {
        System.out.println("Enter number of elements: ");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int arr[] = new int[n];
        for(int i =0;i<n;i++){
            System.out.println("Enter element "+(i+1)+": ");
            arr[i]=sc.nextInt();
        }
        select(arr,n);
    }
}
