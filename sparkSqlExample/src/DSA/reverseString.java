package DSA;

public class reverseString {
    public static void main(String[] args) {

        StringBuilder string8 = new StringBuilder("Neha");  // creating Stringbuilder object
        StringBuilder ReversedString = ReverseInBuild(string8);
        System.out.println("  printing ReverseInBuild " + ReversedString);

        String ReversedString1 = Reverse("Khatri");
        System.out.println("  printing ReverseInBuild " + ReversedString1);
    }


    // using stringBuilder reverse function cause A string is of immutable type ...
    public static StringBuilder ReverseInBuild(StringBuilder str) {
        StringBuilder str9 = str.reverse();  //Using built in reverse() method of the StringBuilder class:
        System.out.println("using reverse function on Stringbuilder yields reversed stringBuilder " + str9);
        return str9;
    }


    public static String Reverse(String str) {
        char abc = 'c';
        char[] charArray = new char[]{'a', 'b', 'c', 'd'};  // just for understanding
        String[] str2 = new String[]{"xyz", "we", "qws"}; // just for understanding
        String str3 = "ghjk";  // just for understanding
        char ch = str3.charAt(3);  // just for understanding
        System.out.println(" ch " + ch);  // just for understanding


        String newStr = " ";
        char[] charArray12 = str.toCharArray();
        int n = str.length() - 1;
        System.out.print(" str.length(); " + str.length());
        for (int i = n; i >= 1; --i) {     // reversing the array and then keep adding charcters in "new sting" one after another by decremanting loop.
            System.out.print(" i is  " + i);
            newStr = charArray12[i] + newStr;
        }

        return newStr;
    }
    // return str9;


}


