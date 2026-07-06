import java.util.*;
public class quick {

    public static void quick(int arr[],int low,int high){
        if(low<high){
            int pi = partition(arr,low,high);
            quick(arr,low,pi);
            quick(arr,pi+1,high);
        }
        System.out.println("Pass: "+Arrays.toString(arr));
    }
    public static int partition(int arr[],int low,int high){
        int pivot = arr[(high+low)/2];
        int i =low;
        int j = high;
        while (i <= j) {

            while (arr[i] < pivot) i++;
            while (arr[j] > pivot) j--;
            if (i <= j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;    
            j--;
    }
}
        return j;
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
        quick(arr,0,n-1);
    }
}
