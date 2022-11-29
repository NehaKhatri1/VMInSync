package DSA;


public class Arrayshifting {

    public static void main(String[] args) {

        int[] arrmain = {5, 6, 7, 8, 9, 0};
        int[] arrdup = new int[arrmain.length];

        for (int i = 0; i < arrmain.length; i++) {
            arrdup[i] = arrmain[i];
            //   System.out.println("arrdup "+arrdup[i]);
        }



       /* for(int i=0;i<arrdup.length;i++) {
         System.out.println("arrdup "+arrdup[i]);
        }*/
        for (int i = 2; i < arrmain.length - 1; i++) {
            // for(int j=2; j<arrdup.length-1;j++){
            // System.out.println("arrmain & "+arrmain[i+1]);
            //   System.out.println("arrdup & "+arrdup[j]);

            arrmain[i + 1] = arrdup[i];
            System.out.println("arrmain * " + arrmain[i + 1]);
            System.out.println("arrdup *" + arrdup[i]);

        }
      /*  for(int i=0;i<arrmain.length;i++) {
           // System.out.println("arrmain "+arrmain[i]);
        }*/
        for (int i = 2; i < arrmain.length; i++) {
            System.out.println(arrmain[i]);
        }

      /*  int temp2=arrmain[2];
        int temp3=arrmain[3];
        int temp4=arrmain[4];

        arrmain[3] =temp2;
        arrmain[4] =temp3;
        arrmain[5] =temp4;


        for(int i=2;i<arrmain.length-1;i++) {
            System.out.println("maNUAL "+arrmain[i]);
        }*/


    }
}