import java.util.*;
public class crc {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the data");
        String data = sc.next();
        System.out.println("Enter the generator polynomial");
        String gen = sc.next();
        int r = gen.length()-1;
        String zeros = "";
        for(int i = 0; i < r; i++) {
            zeros += "0";
        }

        String temp = data + zeros;
        char arr[] = new char[temp.length()];
        for(int i = 0;i<temp.length();i++){
            if(arr[i]==1){[p;]
                for(int j=0;j<gen.length();j++){
                    if(arr[i+j]==gen.charAt(j)){
                        arr[i+j]='0';
                    }
                    else{
                        arr[i+j]='1';
                    }
                }
            }
        }
        String rem="";
        for(int i = temp.length()-r;i<temp.length();i++){
            rem+=arr[i];
        }
        System.out.println("Remainder: "+rem);
    }
}
