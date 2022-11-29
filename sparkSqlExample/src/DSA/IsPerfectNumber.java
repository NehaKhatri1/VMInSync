package DSA;

import java.lang.*;
import java.util.*;
class IsPerfectNumber {
    public static void main(String[] args) {
        // YOUR CODE GOES HERE
        // Please take input and print output to standard input/output (stdin/stdout)
        // DO NOT USE ARGUMENTS FOR INPUTS
        // E.g. 'Scanner' for input & 'System.out' for output
        System.out.println("Enter the N number in integer for n times  you want to enter a number  ");

        Scanner sc =new Scanner(System.in);

        int n=sc.nextInt();
        for(int i=0;i<n;i++){
            System.out.println("Enter a Number ");

            int x = sc.nextInt();
            String ReturnedValue=IsPerfectNo(x);
            System.out.println(ReturnedValue);
        }
    }
    private static String IsPerfectNo(int N){  // perfect No means  sum of all positive factors excluding itself .. ex: 6 == 1+2+3  . 28== 1+2+4+7+14
        int count=0;
        for (int i =1; i<N;i++){
            //  System.out.println("i is  "+i);
            if (N%i == 0){
                count=count+i;}
            else{
                //System.out.println("else case ");
                continue;
            }
        }
        if(count==N){
            return "YES" ;
        }
        else{
            return "NO";
        }
    }
}