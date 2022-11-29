package DSA;

public class kTimesRotationArray {

    public static void main(String[] args) {

        int[] array=new int[] {1,2,3,4,5,6,7,8,9};
        int K=2;  // rotate N times
        int N=array.length;


    //Rotate(0,N-K-1,array);
    //Rotate(0,N-K-1,array);
    //Rotate(N-K,N-1,array);
    //Rotate(N-K,N-1,array);

        int[] rotatedArray=Rotate(0,N-1,array);

       int[] rotatedArray1= Rotate(0,K-1,rotatedArray);
        int[] rotatedArray2=  Rotate(K,K+N-1,rotatedArray1);

        for(int i=0; i<=rotatedArray.length-1;i++){
            System.out.print("  "+rotatedArray2[i]);

        }


    }



    public static int[] Rotate(int start, int end,int[] arr){

        //int N=arr.length;

        for(int i=start; i<=end/2;i++) {

            int temp = arr[i];
            //if(arr[i]!=arr[end-i){
                arr[i]=arr[end-i];
          //  }

            arr[end-i]=temp;
 //System.out.println(" arr[i] "+ arr[i]+" arr[end] "+arr[end-i]);
        }


     /*for(int j=start; j<end;j++) {
         // System.out.print("showing work");
          System.out.print(" " + arr[j]);
      }  */


        return arr;

    }



}
