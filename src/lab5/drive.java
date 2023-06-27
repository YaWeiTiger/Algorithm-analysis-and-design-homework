package lab5;

import java.util.Scanner;

public class drive {
    private static int N;  //方形网络规模
    private static int A;  //汽车在行驶过程中遇到油库应加满油并付加油费A
    private static int C;  //在需要时可在网格点处增设油库，并付增设油库费用C（不含加油费A）
    private static int B;  //当汽车行驶经过一条网格边时，如果其x坐标或y坐标减少，应付费用B
    private static int K;  //装满油后，能行驶K条边

    private static int[][][] f = new int[50][50][2];
    private static int[][] s = {{-1,0,B},{0,-1,B},{1,0,0},{0,1,0}};

    private static int[][] a = new int[50][50];  //方形网络

    private static int MAX = 100000;
    private static int leastFee;

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        N = input.nextInt();//方格规模
        K = input.nextInt();//装满油后能行驶的网格边数
        A = input.nextInt();//加油费
        B = input.nextInt();//坐标减少时应付的费用
        C = input.nextInt();//增设油库费用

        for(int i=1; i<=N; i++){//输入方形网络
            for(int j=1; j<=N; j++){
                a[i][j] = input.nextInt();
            }
        }

        leastFee = dynamic();
        System.out.println(leastFee);
    }

    private static int dynamic(){
        int i, j, k;
        for (i=1;i<=N;i++){
            for (j=1;j<=N;j++){
                f[i][j][0]=MAX;
                f[i][j][1]=K;
            }
        }

        f[1][1][0] = 0;
        f[1][1][1] = K;

        boolean finish = false;
        int tx, ty;
        while(!finish){
            finish = true;
            for(i=1; i<=N; i++){
                for(j=1; j<=N; j++){
                    if(i==1 && j==1)
                        continue;
                    int minFee = MAX;
                    int driveEdges = MAX;
                    int fee, canDriveEdges;
                    for(k=0; k<4; k++){ //可走的四个方向
                        tx = i + s[k][0];
                        ty = j + s[k][1];
                        if(tx<1 || ty<1 || tx>N || ty>N)
                            continue;

                        fee = f[tx][ty][0] + s[k][2];
                        canDriveEdges = f[tx][ty][1] - 1;
                        if(a[i][j] == 1){
                            fee += A;
                            canDriveEdges = K;
                        }
                        if(a[i][j]==0 && canDriveEdges == 0 && (i!=N||j!=N)){
                            fee += A + C;
                            canDriveEdges = K;
                        }
                        if(fee < minFee){
                            minFee = fee;
                            driveEdges = canDriveEdges;
                        }
                    }

                    if(f[i][j][0] > minFee){
                        finish = false;
                        f[i][j][0] = minFee;
                        f[i][j][1] = driveEdges;
                    }
                }
            }
        }
        return f[N][N][0];
    }
}
