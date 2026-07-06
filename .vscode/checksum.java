import java.util.*;
public class checksum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of data bits: ");
        int n = sc.nextInt();
        int data[] = new int[n];
        System.out.println("Enter the data bits: ");
        String bin = sc.next();
        int num = Integer.parseInt(bin,2);
        int sum=0;
        for(int i=0;i<n;i++){
            sum+=data[i];
        }
        
        while(sum>255){
            sum = (sum & 255)+(sum>>8);
        }
        int checksum = (~sum)&255;
        System.out.println("Checksum: "+checksum);
    }
}
