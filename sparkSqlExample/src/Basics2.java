import scala.reflect.internal.Trees;

public class Basics2 {

    public static void main(String[] args) {


        // if else break continue . PLAY WITH BREAK CONTINUE IN ORDER TO UNDERSTAND BEHAVIOR BETTER .
        String[] back = {"3", "7", "6", "2"};

        for (String bac : back) {
                 if(bac=="6"){
                    // break; //THis EXITS THE for LOOP for thE REST OF THE FOLLOWING LINES OF THE SAME ELEMENT . rest SUBSEQUENT ELEMENTS ARE THEN DONT GO THROW THE LOOP .  no execution of 2 IN THIS CASE .
                    continue;  //skip THE REMAINDER OF THE Body for this iteration/ELEMENT of loop and continue WITH NEXT ELEMENT . means for 6 out of for loop BUT REST element 2 WILL EXECUTE .
                 }
                 else{
                     System.out.println("inside else"+bac);

                     //break;
                     continue;
                    }
                 //System.out.println("bac is "+bac);
        }
        System.out.println("outside loop");



        /* return type in if else  */

        String qwe=returnFunctionCheck();
                int CountGreater = CountGreaterValuesInArray();
 System.out.println("CountGreater is "+CountGreater);

 int[] returnedRotatedArray=rotateKTimesArray(new int[]{1,2,3,4,5,6,7,8}, 3);

 for(int i=0; i<returnedRotatedArray.length;i++) {
     System.out.println("returnedRotatedArray is " + returnedRotatedArray[i]);
 }





 /*
            Equilateral: It's a triangle with 3 sides of equal length.
            Isosceles: It's a triangle with 2 sides of equal length.
            Scalene: It's a triangle with all sides of differing lengths.
            Not A Triangle: The given values of A, B, and C don't form a triangle.
    */
        int a=20;
        int b=20;
        int c=20;

        boolean b1 = ((a == b) && (b != c) && (a != c)) || ((a == c) && (c != b) && (b != a)) || ((c == b) && (b != a) && (c != a));
        System.out.println("boolean is "+b1);

        boolean b2 = ((a == b) || (b != c) || (a != c)) && ((a == c) || (c != b) || (b != a)) && ((c == b) || (b != a) || (c != a));
        System.out.println("boolean is "+b2);



    }


  static String returnFunctionCheck(){
        int i=10;
        if(i>5){
           return "bigger";
        }else if(i==10){
          return "equal";
       }else{
            return "smaller";
        }
    // return null;
    }

    /* find no of elemnts (count) which has big bro /no */
   static int CountGreaterValuesInArray()
   {
     int count=0;
     int[] arr=new int[]{-3,-2,6,8,4,8,5};
     for(int i=0; i<=arr.length-1;i++){
         for(int j=0; j<=arr.length-1;j++){
if(arr[i]<arr[j]){
    count ++;
    break;

}
         }
     }

     return count;
   }



    public static int[] rotateKTimesArray(int[] arr,int k) {
     int N=arr.length-1;
       for (int i = 0; i<k; i++){
                int temp= arr[i];
                arr[i]=arr[N-i];
                arr[N-i]=temp;
            }
            return arr;
        }




}  // ending class





