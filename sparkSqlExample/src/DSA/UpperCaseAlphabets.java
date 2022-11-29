package DSA;

import java.util.List;

public class UpperCaseAlphabets {
    public static void main(String[] args) {
        displayAlphabets();
    }


    public static void displayAlphabets() {

        for (char i = 'A'; i <= 'Z'; i++) {    // prints   A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
           // System.out.print(" "+i);
            switch (i) {
                case 'I':
                    System.out.print(" I got I for intelligent");
                    break;
                case 'W':
                    System.out.print(" I got W for WITTY ");
                    break;
                default:
                    System.out.print("  "+i);
            }
        }




       for (int i = 'A'; i <= 'Z'; i++) {    // prints  65 66 67 68 69 70 71 72 73 74 75 76 77 78 79 80 81 82 83 84 85 86 87 88 89 90
            System.out.print(" "+i); }



    }






 }