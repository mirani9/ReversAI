import java.util.*;
public class insert {

    public static void insert(int arr[],int n){
        for(int i =0;i<n;i++){
            for(int j = i;j>0;j--){
                if(arr[j]<arr[j-1]){
                    int temp = arr[j];
                    arr[j]=arr[j-1];
                    arr[j-1]=temp;
                }
                else{
                    break;
                }
            }
            System.out.println("Pass: "+(i+1)+" "+Arrays.toString(arr));
        }
    }
    public static void main(String[] args) {
        System.out.println("Enter number of elements: ");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int arr[] = new int[n];
        System.out.println("Enter elements: ");
        for(int i =0;i<n;i++){
            arr[i]=sc.nextInt();
        }
        insert(arr,n);
    }
}
