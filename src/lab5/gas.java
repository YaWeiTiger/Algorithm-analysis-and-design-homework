package lab5;

public class gas {
    public static void greedy(int x[], int n ,int k)
    {
        for(int i=0; i<k; i++)
            if(x[i] > n){
                System.out.println("No solution");
                return ;
            }

        int count = 1;
        System.out.println("第 0 个加油站加油");
        int distance = 0;
        for(int i=0; i<k; i++){
            distance += x[i];
            if(distance > n){
                count++;
                distance=x[i];
                System.out.printf("第 %d 个加油站加油\n", i);
            }
        }
        System.out.printf("共加油%d次\n", count);
    }

    public static void main(String[] args) {

        int[] x = {30,40,80,12,50};// 相邻站的距离
        int k = 5;  // 站点数，算上出发站，目的站
        int d = 90; // 加一次油最长行驶的距离

        greedy(x, d ,k);// 调用算法，计算最少的加油次数

    }
}