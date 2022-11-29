package DSA;

import java.lang.*;
import java.util.*;

public class NaturalNoSum {
    public static void main(String[] args) {
        // YOUR CODE GOES HERE
        // Please take input and print output to standard input/output (stdin/stdout)
        // DO NOT USE ARGUMENTS FOR INPUTS
        // E.g. 'Scanner' for input & 'System.out' for output

        System.out.println("Enter the N number in integer for n times  you want to enter a number  ");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
       // for (int i = 0; i < n; i++) {

         //   int x = sc.nextInt();
            int returnedSum = NaturalNoSum(n);
             System.out.println(" returnedSum is "+returnedSum);
        //}
    }

    public static int NaturalNoSum(int n) {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            // System.out.println("i is "+i);
            sum = sum + i;
        }
        return sum;
    }


}

