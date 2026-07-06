import java.util.*;
public class merge {

    public static void merge(int arr[],int low,int high){
        if(low==high){
            return;
        }
        if(low<high){
            int mid=(low+high)/2;
            merge(arr,low,mid);
            merge(arr,mid+1,high);
            mergeSort(arr,low,mid,high);
        }
    }

    public static void mergeSort(int arr[],int low,int mid,int high){
        int ans[]=new int[high-low+1];
        int i=low;
        int j=mid+1;
        int k=low;
        while(i<=mid && j<=high){
            if(arr[i]<arr[j]){
                ans[k++]=arr[i++];
            }
            else{
                ans[k++]=arr[j++];
            }
        }
        while(i<=mid){
            ans[k++]=arr[i++];
        }
        while(j<=high){
            ans[k++]=arr[j++];
        }
        for(int m = low;m<=high;m++){
            arr[m]=ans[m];
        }
        for(int q = low;q<=high;q++){
            System.out.print(ans[q]+" ");
        }
    }
    public static void main(String[] args) {
        System.out.println("Enter number of elements in first array: ");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int arr[] = new int[n];
        System.out.println("Enter elements of first array: ");
        for(int i =0;i<n;i++){
            arr[i]=sc.nextInt();
        }
        int high = n-1;
        int low = 0;
        merge(arr,low,high);
    }
}
