import scala.Char;

import java.util.*;

import static java.lang.Math.max;

public class DsaPractice<array6> {

    public static void main(String[] args) {

        // fINDING THE MAX FROM AN ARRAY in java 8
        int[] myArray = {1, 2, 3, 4, 6};
        int[] myNewArray = myArray;
        OptionalInt maxi = Arrays.stream(myArray).reduce((i, j) -> max(i, j));
        System.out.println("max eLMENT is " + maxi);


        // fINDING THE MAX FROM AN ARRAY in java 7 ..approch 1

        Integer[] myArray1 = {1, 2, 60, 3, 4, 6, 70};
        List<Integer> myList = Arrays.asList(myArray1);
        Collections.sort(myList);
        int size = myList.size();
        int maxElement = myList.get(size - 1);
        System.out.println("maxElement is " + maxElement);


        // fINDING THE MAX FROM AN ARRAY in java 7 ..approch 2   //log (n)
        int[] myArray2 = {1, 2, 90, 3, 4, 6, 70, 80};
        int max = -999;
        for (int i = 0; i < myArray2.length - 1; i++) {
            int a = myArray2[i];
            int b = (i != (myArray2.length - 1)) ? myArray2[i + 1] : myArray2[i]; // ternary operator   res=(num1>num2) ? (num1+num2) : (num1-num2)

            if (a > b) {
                max = a;
            }
        }
        System.out.println("max is approch 2 " + max);


        // rotatION OF arRAY eLements BY 1 ..    ex {1,2,3,4,5}  -->  {5,1,2,3,4}
        int[] myArray3 = {1, 2, 90, 3, 4, 6, 70, 80};
        int temp = myArray3[0]; // putting first element into temp
        for (int i = 0; i < myArray3.length - 1; i++) {
            myArray3[i] = myArray3[i + 1];
        }
        myArray3[myArray3.length - 1] = temp;
        for (int i = 0; i < myArray3.length; i++) {
            System.out.println(myArray3[i]);
        }


        int returnedValue = findTriplet();
        int distance = minDistanceBetweenIntArray(1, 2);

        Boolean returnedPrimeNoFlag = IsPrime(183607);
        System.out.println("returnedPrimeNoFlag " + returnedPrimeNoFlag);


        int returnedPerfectSquareNumber = perfectSquareNumber(84630800);
        System.out.println("returnedPerfectSquareNumber is " + returnedPerfectSquareNumber);


        IsArmstrong(500 );

        printMultiples(5);

        Integer[] abc={1,2,3,4};
List<Integer> listOfNo= Arrays.asList(abc);

rotateArrayByNTimes(abc,2);
    }


    // find triplet with sum zero
    public static int findTriplet() {

        //int[] array5={0,-1,2,-3,1,-2,1};
        int[] array5 = {0, -1, 2, -3, 2};

        for (int i = 0; i <= array5.length - 1; i++) {
            int a = array5[i];
            int b = array5[i + 1];
            // int c = array5[i+2];
            int c;
            if (array5.length - 1 < (i + 2)) {
                break;
            } else {
                c = array5[i + 2];
            }
            System.out.println("a is  " + a);
            System.out.println("b is  " + b);
            System.out.println("c is  " + c);
            System.out.println("   *****************   ");

            if ((a + b + c) == 0)
                return 1;

        }
        return 0;
    }


    public static int minDistanceBetweenIntArray(int x, int y) {
        // minimum distance between 2 numbers
        int[] array6 = {1, 2, 3, 2};
        int distance = 0;
        int[] intArray = new int[array6.length];
        //System.out.println("intArray "+intArray);
        for (int i = 0; i <= array6.length-1; i++) {
            intArray [i]= array6[i];
        }   // Int[] to String[] conversion


         for (int i = 0; i <= array6.length-1; i++) {
            // System.out.println("array6.length "+array6.length);
            // System.out.println("intArray.length "+intArray.length);

            System.out.println("intArray is  " + intArray[i]);
        }

        String x1 = String.valueOf(x);
        String y1 = String.valueOf(y);

        for (int i = 0; i <= intArray.length; i++) {
            int x1Distance = intArray.toString().indexOf(x1);
            int y1Index = intArray.toString().indexOf(y1);
            distance = Math.subtractExact(x1Distance, y1Index);
            System.out.println("distance is  " + distance);

        }
        return distance;
    }





    public static boolean IsPrime(int n){  // prime NO has only 2 factors  1 and itself that means modulus/reminder(n%i==0) should be zero for only 1 and itself (no%restOfTheNos)
        int noOfFactors=0;

        System.out.println(Math.sqrt(n));
        for (int i=1; i<=n; i++){    //  i<= Math.sqrt(n) is not working on all the inputs as 183607's sqrt is 438 and 1 ,89,2063 is its factors . sqrt is not suffice .
            if (n%i == 0){
                System.out.println("factors are  "+i);
                noOfFactors+= 1;
            }
        }
        if (noOfFactors==2 || noOfFactors==1){
            System.out.println("noOfFactors are "+noOfFactors);
            return true;
        }else //System.out.println("noOfFactors in else are  "+noOfFactors);
        return false;
    }

    public static int perfectSquareNumber(int A) {
        System.out.println("A is "+A);

        for(int i=0;i<=Math.sqrt(A);i++) {
            int square =i*i;
            if(A==square){
                System.out.println("retruning i as "+i);
                return i;
            }

        }
        return -1;
    }

    //List<Integer> listOfInteger= Arrays.asList(121, 122, 125, 25);
//listOfInteger.map(e->e==Math.sqrt())


    /*If sum of cubes of each digit of the number is equal to the number itself, then the number is called an Armstrong number.

    For example, 153 = ( 1 * 1 * 1 ) + ( 5 * 5 * 5 ) + ( 3 * 3 * 3 ). */
    private static void IsArmstrong(int n ) {
      /*  Integer.parseInt(String.valueOf(n));
        float floatNo=34.5f;
       // Integer.parseInt(floatNo);  // wont work because parseInt only works for String so either convert to string and then to int or (float) typecast it
        Integer.parseInt(String.valueOf(floatNo));  // ll work
        int received=(int) floatNo;    // ll work
        byte received1=(byte) floatNo; */
        for (int i = 1; i < n; i++) {
            int sum = 0;
            String noValue = String.valueOf(i);
          //  System.out.println(" noValue is " + noValue);
            for (int j = 0; j < noValue.length(); j++) {
                char eachDigit = noValue.charAt(j);
          //      System.out.println(" eachDigit as " + eachDigit);
                int poweredEachDigit = (int) Math.pow((double)Integer.parseInt(String.valueOf(eachDigit)), 3);

//                System.out.println("poweredEachDigit is "+poweredEachDigit);
                sum = sum + poweredEachDigit;

            }
//            System.out.println("sum is "+sum);
             if (sum== i) {
                System.out.println("its an Armstrong no " + i);
            }
        }

    }




   /*
   Input 2: print the table

output :
 2 * 1 = 2
 2 * 2 = 4
 2 * 3 = 6
 2 * 4 = 8
 2 * 5 = 10
 2 * 6 = 12
 2 * 7 = 14
 2 * 8 = 16
 2 * 9 = 18
 2 * 10 = 20
         */
    public static void printMultiples(int n) {
        for (int i = 1; i <= 10; i++) {

            System.out.println(n + " * " + i + " = " + n * i);
        }
    }


    public static void rotateArrayByNTimes(Integer[] arr,int z) {

        for (int j = arr.length - 2; j < arr.length; j++) {
            Integer temp = arr[j];
            arr[j] = arr[j - 1];
            arr[j + 1] = temp;
        }
        for (int i = 1; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }













} // ending class here







