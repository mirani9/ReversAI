import java.util.*;

class item{
    int value;
    int weight;
}  

class knap{
    static double totalval = 0;
    static int w = 0;
    static double ans[];

    public static void sort(ArrayList<item> items) {
        int n = items.size();

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {

                double r1 = (double) items.get(j).value / items.get(j).weight;
                double r2 = (double) items.get(j + 1).value / items.get(j + 1).weight;

                if (r1 < r2) {
                    // swap
                    item temp = items.get(j);
                    items.set(j, items.get(j + 1));
                    items.set(j + 1, temp);
                }
            }
        }
    }
    public static void func(ArrayList<item> items, int c){
        int n = items.size();
        sort(items);
        for(int i = 0;i<n;i++){
            if(w>=items.get(i).weight){
                totalval+=items.get(i).value;
                w-=items.get(i).weight;
                ans[i]=items.get(i).value;
            }
            else{
                double frac = ((double)(items.get(i).value)/items.get(i).weight)*w;
                 totalval += frac;
                 ans[i] = frac; 
            }
        }
        System.out.println("Total value in knapsack: "+totalval);
    }

    static ArrayList<item> items = new ArrayList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter no. of elements: ");
        int n = sc.nextInt();
        ans=new double[n];
        for(int i=0;i<n;i++){
            System.out.println("Enter value for element "+(i+1)+": ");
            int value = sc.nextInt();
            System.out.println("Enter weight for element "+(i+1)+": ");
            int weight = sc.nextInt();
            item it = new item();
            it.value = value;
            it.weight = weight;
            items.add(it);
        }
        System.out.println("Enter capacity of knapsack: ");
        int capacity = sc.nextInt();
        w=capacity;
        func(items,capacity);
    }
}