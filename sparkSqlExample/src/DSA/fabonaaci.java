package DSA;

import java.util.ArrayList;
import java.util.List;

public class fabonaaci {

    public static void main(String[] args) {
        List<Integer> returnedList = Fabonacci(500);
        System.out.println("  printing liost " + returnedList);

    }

    public static List<Integer> Fabonacci(int n) {
        int first = 0;
        int second = 1;
        int nextElement = 0;
        List<Integer> myList = new ArrayList<Integer>();
        myList.add(first);
        myList.add(second);
        //  for(int i=0; i<n; i++){
        while ((second + nextElement) <= n) {
            nextElement = first + second;
            first = second;
            second = nextElement;

            myList.add(nextElement);
        }

        return myList;
    }

}




